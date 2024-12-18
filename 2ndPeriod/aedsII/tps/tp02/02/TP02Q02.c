#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define STR_SIZE 80

// Estrutura para armazenar a data
typedef struct {
    int day;
    int month;
    int year;
} Date;

// Estrutura para os tipos de Pokémon
typedef struct {
    int count;
    char type1[STR_SIZE];
    char type2[STR_SIZE];
} Types;

// Estrutura para as habilidades do Pokémon
typedef struct {
    int count;
    char ability1[STR_SIZE];
    char ability2[STR_SIZE];
    char ability3[STR_SIZE];
    char ability4[STR_SIZE];
    char ability5[STR_SIZE];
    char ability6[STR_SIZE];
} Abilities;

// Estrutura para o Pokémon
typedef struct {
    int id;
    int generation;
    char name[STR_SIZE];
    char description[STR_SIZE];
    Types types;
    Abilities abilities;
    double weight;
    double height;
    int captureRate;
    bool legendary;
    Date captureDate;
} Pokemon;

void displayFlag();

int findCommaIndex(char* str);

double convertStringToDouble(char* str);

int convertStringToYear(char* str);

int convertStringToInt(char* str);

Date convertStringToDate(char* str);

char* convertDateToString(Date date);

Pokemon* createPokemon(char* line);

void displayPokemon(Pokemon* pokemon);

Pokemon** loadPokemonsFromFile(const char* filePath);

void displayFlag() {
    printf("FLAG\n");
}

// Função que encontra o índice da primeira '.' na string
int findCommaIndex(char* str) {
    bool found = false;
    int index = 0;
    for (; index < strlen(str) && !found; index++) {
        if (str[index] == '.') found = true;
    }
    return index - 1;
}

// Converte uma string para um double
double convertStringToDouble(char* str) {
    int index = 0;
    double result = 0.0;
    int length = strlen(str);
    int commaIndex = findCommaIndex(str),
        digitsAfterComma = length - commaIndex - 1,
        power = commaIndex - 1;

    // Processa a parte inteira
    for (index = 0; index < commaIndex; index++) {
        result += (double)((str[index] - '0') * pow(10, power));
        power--;
    }

    // Processa a parte decimal
    power = 1;
    for (index = commaIndex + 1; index < length; index++) {
        result += (double)((str[index] - '0') / pow(10, power));
        power++;
    }
    return result;
}

// Converte uma string para um ano (int)
int convertStringToYear(char* str) {
    int result = 0;
    int exponent = 3;
    int index = 0;
    while (index < 4) {
        result += (int)(str[index] - '0') * (int)pow(10.0, (double)exponent);
        index++;
        exponent--;
    }
    return result;
}

// Converte uma string para um int
int convertStringToInt(char* str) {
    int result = 0;
    int index = strlen(str) - 1,
        exponent = 0;
    while (index >= 0) {
        result += (int)(str[index] - '0') * (int)pow(10.0, (double)exponent);
        index--;
        exponent++;
    }
    return result;
}

// Converte uma string no formato "dd/mm/yyyy" para uma estrutura Date
Date convertStringToDate(char* str) {
    char* dayStr = strtok(str, "/");
    char* monthStr = strtok(NULL, "/");
    char* yearStr = strtok(NULL, "/");
    Date result;
    result.day = convertStringToInt(dayStr);
    result.month = convertStringToInt(monthStr);
    result.year = convertStringToYear(yearStr);
    return result;
}

// Converte uma estrutura Date para uma string
char* convertDateToString(Date date) {
    char* result = malloc(40 * sizeof(char));
    sprintf(result, "%02d/%02d/%04d", date.day, date.month, date.year);
    return result;
}

// Cria uma estrutura Pokemon a partir de uma linha CSV
Pokemon* createPokemon(char* line) {
    Pokemon* newPokemon = calloc(1, sizeof(Pokemon));
    
    int lineLength = strlen(line);
    char* formattedLine = malloc(lineLength * sizeof(char));
    bool insideQuotes = true;
    int j = 0;
    
    // Formata a linha
    for (int i = 0; i < lineLength; i++) {
        if (line[i] == '"') insideQuotes = !insideQuotes;
        else if (line[i] == ',' && insideQuotes) formattedLine[j++] = ';';
        else if (line[i] != '[' && line[i] != ']') formattedLine[j++] = line[i];
    }

    // Divide a linha formatada em componentes
    char** components = calloc(12, sizeof(char*));
    int componentCount = 0;
    for (components[componentCount++] = strtok(formattedLine, ";"); components[componentCount] = strtok(NULL, ";"); componentCount++);

    // Preenche os campos da estrutura Pokemon
    newPokemon->id = convertStringToInt(components[0]);
    newPokemon->generation = convertStringToInt(components[1]);
    strcpy(newPokemon->name, components[2]);
    strcpy(newPokemon->description, components[3]);

    newPokemon->types.count = componentCount == 11 ? 1 : 2;
    strcpy(newPokemon->types.type1, components[4]);
    if (newPokemon->types.count == 2) strcpy(newPokemon->types.type2, components[5]);

    // Processa as habilidades
    bool hasCommaInAbilities = false;
    int abilitiesPosition = componentCount == 10 ? 6 : componentCount == 11 ? 5 : 6;
    for (int j = 0; j < strlen(components[abilitiesPosition]); j++)
        if (components[abilitiesPosition][j] == ',') hasCommaInAbilities = true;

    if (!hasCommaInAbilities) {
        newPokemon->abilities.count = 1;
        strcpy(newPokemon->abilities.ability1, components[abilitiesPosition]);
    } else {
        char* formattedAbilities = malloc(STR_SIZE * sizeof(char));
        strcpy(formattedAbilities, components[abilitiesPosition]);
        for (int x = 0; x < strlen(formattedAbilities); x++)
            if (formattedAbilities[x] == ' ' && formattedAbilities[x - 1] == ',') formattedAbilities[x] = ',';

        char* abilityBuffer;
        abilityBuffer = strtok(formattedAbilities, ",");
        newPokemon->abilities.count = 1;
        strcpy(newPokemon->abilities.ability1, abilityBuffer);

        for (int i = 2; i <= 6; i++) {
            if ((abilityBuffer = strtok(NULL, ",")) != NULL) {
                newPokemon->abilities.count++;
                switch (newPokemon->abilities.count) {
                    case 2: strcpy(newPokemon->abilities.ability2, abilityBuffer); break;
                    case 3: strcpy(newPokemon->abilities.ability3, abilityBuffer); break;
                    case 4: strcpy(newPokemon->abilities.ability4, abilityBuffer); break;
                    case 5: strcpy(newPokemon->abilities.ability5, abilityBuffer); break;
                    case 6: strcpy(newPokemon->abilities.ability6, abilityBuffer); break;
                }
            }
        }
        free(formattedAbilities);
    }

    // Processa o peso e a altura
    if (componentCount > 10) {
        newPokemon->weight = convertStringToDouble(components[7 - (12 - componentCount)]);
        newPokemon->height = convertStringToDouble(components[8 - (12 - componentCount)]);
    } else {
        newPokemon->weight = 0.0;
        newPokemon->height = 0.0;
    }

    newPokemon->captureRate = convertStringToInt(components[9 - (12 - componentCount)]);
    newPokemon->legendary = strcmp(components[10 - (12 - componentCount)], "1") == 0;
    newPokemon->captureDate = convertStringToDate(components[11 - (12 - componentCount)]);

    free(formattedLine);
    free(components);
    return newPokemon;
}

// Exibe os detalhes de um Pokémon
void displayPokemon(Pokemon* pokemon) {
    printf("[#%d -> %s: %s - [", pokemon->id, pokemon->name, pokemon->description);

    if (pokemon->types.count == 1) {
        printf("'%s'] - [", pokemon->types.type1);
    } else {
        printf("'%s', '%s'] - [", pokemon->types.type1, pokemon->types.type2);
    }

    for (int i = 1; i <= pokemon->abilities.count; i++) {
        switch (i) {
            case 1: printf("%s", pokemon->abilities.ability1); break;
            case 2: printf(", %s", pokemon->abilities.ability2); break;
            case 3: printf(", %s", pokemon->abilities.ability3); break;
            case 4: printf(", %s", pokemon->abilities.ability4); break;
            case 5: printf(", %s", pokemon->abilities.ability5); break;
            case 6: printf(", %s", pokemon->abilities.ability6); break;
        }
    }
    printf("] - %.1lfkg - %.1lfm - %d%c - ", pokemon->weight, pokemon->height, pokemon->captureRate, '%');
    printf("%s - %d gen] - ", pokemon->legendary ? "true" : "false", pokemon->generation);
    char* captureDateString = convertDateToString(pokemon->captureDate);
    printf("%s\n", captureDateString);
    free(captureDateString);
}

// Carrega os Pokémons de um arquivo CSV
Pokemon** loadPokemonsFromFile(const char* filePath) {
    Pokemon** pokemonsArray = calloc(801, sizeof(Pokemon*));
    FILE* csvFile = fopen(filePath, "rt");
    char* headerLine = malloc(1000 * sizeof(char));
    fgets(headerLine, 999, csvFile);
    free(headerLine);

    for (int i = 0; i < 801; i++) {
        char* buffer = malloc(1000 * sizeof(char));
        fgets(buffer, 999, csvFile);
        pokemonsArray[i] = createPokemon(buffer);
        free(buffer);
    }
    fclose(csvFile);
    return pokemonsArray;
}

// Função principal
int main() {
    Pokemon** pokemons = loadPokemonsFromFile("/tmp/pokemon.csv");
    bool continueLoop = true;

    while (continueLoop) {
        char* userInput = malloc(1001 * sizeof(char));
        scanf(" %10[^\n]", userInput);
        if (strcmp(userInput, "FIM") == 0) {
            continueLoop = false;
        } else {
            displayPokemon(pokemons[convertStringToInt(userInput) - 1]);
        }
        free(userInput);
    }

    // Liberação de memória para os Pokémons
    for (int i = 0; i < 801; i++) {
        free(pokemons[i]); 
    }
    free(pokemons);

    return 0;
}
