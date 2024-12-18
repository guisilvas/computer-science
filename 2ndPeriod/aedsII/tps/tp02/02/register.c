#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct {
    int id;
    int generation;
    char name[100];
    char description[100];
    char types[2][100]; 
    char abilities[10][100]; 
    int numAbilities;
    double weight;
    double height;
    int captureRate;
    int isLegendary;
    time_t captureDate;
} Pokemon;

void printPokemon(Pokemon* pokemon) {
    printf("[#%d -> %s: %s - ", pokemon->id, pokemon->name, pokemon->description);
    printf("[%s", pokemon->types[0]);
    if (strlen(pokemon->types[1]) > 0) {
        printf(", %s", pokemon->types[1]);
    }
    printf("] - [");
    for (int i = 0; i < pokemon->numAbilities; i++) {
        printf("'%s'", pokemon->abilities[i]);
        if (i < pokemon->numAbilities - 1) {
            printf(", ");
        }
    }
    printf("] - %.1fkg - %.1fm - %d%% - %s - %d gen] - ", 
           pokemon->weight, pokemon->height, pokemon->captureRate, 
           pokemon->isLegendary ? "true" : "false", pokemon->generation);
    struct tm *tm = localtime(&pokemon->captureDate);
    printf("%02d/%02d/%04d\n", tm->tm_mday, tm->tm_mon + 1, tm->tm_year + 1900);
}

Pokemon* readPokemon(char* line) {
    Pokemon* pokemon = (Pokemon*) malloc(sizeof(Pokemon));
    char name[100], description[100], type1[100], type2[100], abilities[100];
    int id, generation, captureRate, isLegendary;
    double weight, height;
    struct tm tm = {0}; // Inicializa a struct tm

    // O formato de entrada deve ser ajustado conforme a sua necessidade
    sscanf(line, "%d,%d,%99[^,],%99[^,],%99[^,],%99[^,],%99[^,],%lf,%lf,%d,%d,%d/%d/%d",
           &id, &generation, name, description, type1, type2, abilities, &weight, &height, &captureRate, &isLegendary, &tm.tm_mday, &tm.tm_mon, &tm.tm_year);

    pokemon->id = id;
    pokemon->generation = generation;
    strcpy(pokemon->name, name);
    strcpy(pokemon->description, description);
    strcpy(pokemon->types[0], type1);
    strcpy(pokemon->types[1], type2);

    // Processa as habilidades
    char* token = strtok(abilities, "[]\"");
    pokemon->numAbilities = 0;
    while (token != NULL) {
        // Remove espaços em branco antes e depois do token
        while (*token == ' ') token++;
        if (*token != '\0') {
            strcpy(pokemon->abilities[pokemon->numAbilities], token);
            pokemon->numAbilities++;
        }
        token = strtok(NULL, "[]\",");
    }

    pokemon->weight = weight;
    pokemon->height = height;
    pokemon->captureRate = captureRate;
    pokemon->isLegendary = isLegendary;

    // Ajusta a data
    tm.tm_year -= 1900; // Ajusta o ano
    tm.tm_mon -= 1;     // Ajusta o mês
    pokemon->captureDate = mktime(&tm);

    return pokemon;
}

int main() {
    FILE* file = fopen("/tmp/pokemon.csv", "r");
    if (file == NULL) {
        printf("Error opening file\n");
        return 1;
    }
    
    char line[1024];
    char entrada[1024];
    
    while (1) {
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") == 0) {
            break;
        }
        
        int linha = atoi(entrada);
        fseek(file, 0, SEEK_SET); // Reseta a leitura do arquivo para o início
        
        for (int i = 0; i < linha - 1; i++) {
            fgets(line, sizeof(line), file);
        }
        
        fgets(line, sizeof(line), file);
        Pokemon* pokemon = readPokemon(line);
        printPokemon(pokemon);
        free(pokemon);
    }
    
    fclose(file);
    return 0;
}
