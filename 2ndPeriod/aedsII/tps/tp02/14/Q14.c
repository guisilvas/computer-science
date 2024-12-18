#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

#define strSize 80

void flag() {
    printf("FLAG\n");
}

// Função para encontrar o índice da última vírgula em uma string
int commaIndex(char* s) {
    bool found = false;
    int i = 0;
    for (; i < strlen(s) && !found; i++) {
        if (s[i] == '.') found = true;
    }
    return i - 1;
}

// Função para converter uma string em um número double
double parseDouble(char* s) {
    int i = 0;
    double result = 0.0;
    int length = strlen(s);
    int comma = commaIndex(s),
        amtLeft = length - comma + 2,
        j = comma - 1;
    for (i = 0; i < comma; i++) {
        result += (double)((s[i] - 48) * pow(10, j));
        j--;
    }
    j = 1;
    for (i = i + 1; i < length; i++) {
        result += (double)((s[i] - 48) / pow(10, j));
        j++;
    }
    return result;
}

// Função para extrair o ano de uma string
int parseYear(char* s) {
    int result = 0;
    int exp = 3;
    int i = 0;
    while (i < 4) {
        result += (int)(s[i] - 48) * (int)pow(10.0, (double)exp);
        i++;
        exp--;
    }
    return result;
}

// Função para converter uma string em um número inteiro
int parseInt(char* s) {
    int result = 0;
    int i = strlen(s) - 1,
        exp = 0;
    while (i >= 0) {
        result += (int)(s[i] - 48) * (int)pow(10.0, (double)exp);
        i--;
        exp++;
    }
    return result;
}

// Estrutura para armazenar a data (dia, mês, ano)
typedef struct {
    int day;
    int month;
    int year;
} Date;

// Função para converter uma string em uma estrutura Date
Date strToDate(char* s) {
    char* d = strtok(s, "/");
    char* m = strtok(NULL, "/");
    char* y = strtok(NULL, "/");
    Date result;
    result.day = parseInt(d);
    result.month = parseInt(m);
    result.year = parseYear(y);
    return result;
}

// Função para converter uma estrutura Date em uma string
char* dateToStr(Date date) {
    char* result = malloc(40 * sizeof(char));
    sprintf(result, "%02d/%02d/%04d", date.day, date.month, date.year);
    return result;
}

// Estrutura para armazenar os tipos de Pokémon
typedef struct {
    int count; // Número de tipos
    char type1[strSize];
    char type2[strSize];
} Types;

// Estrutura para armazenar as habilidades de Pokémon
typedef struct {
    int count; // Número de habilidades
    char ab1[strSize];
    char ab2[strSize];
    char ab3[strSize];
    char ab4[strSize];
    char ab5[strSize];
    char ab6[strSize];
} Abilities;

// Estrutura para armazenar todos os detalhes de um Pokémon
typedef struct {
    int id;
    int generation;
    char name[strSize];
    char description[strSize];
    Types types;
    Abilities abilities;
    double weight;
    double height;
    int captureRate;
    bool legendary;
    Date captureDate;
} Pokemon;

// Função para criar um Pokémon a partir de uma linha de CSV
Pokemon* makeMon(char* line) {
    Pokemon* result = calloc(1, sizeof(Pokemon));
    
    int length = strlen(line);
    char* format = malloc(length * sizeof(char));
    bool control = true;
    int j = 0;
    for (int i = 0; i < length; i++) {
        if (line[i] == '"') control = !control;
        else if (line[i] == ',' && control) format[j++] = ';';
        else if (line[i] != '[' && line[i] != ']') format[j++] = line[i];
    }

    char** aux = calloc(12, sizeof(char*));
    int nSplits = 0;
    for (aux[nSplits++] = strtok(format, ";"); aux[nSplits] = strtok(NULL, ";"); nSplits++);

    result->id = parseInt(aux[0]);
    result->generation = parseInt(aux[1]);
    strcpy(result->name, aux[2]);
    strcpy(result->description, aux[3]);

    result->types.count = nSplits == 11 ? 1 : 2;
    strcpy(result->types.type1, aux[4]);
    if (result->types.count == 2) strcpy(result->types.type2, aux[5]);

    // Verifica se há vírgula nas habilidades e as analisa
    bool hasCommaInAbilities = false;
    int abilitiesPos = nSplits == 10 ? 6 : nSplits == 11 ? 5 : 6;
    for (int j = 0; j < strlen(aux[abilitiesPos]); j++)
        if (aux[abilitiesPos][j] == ',') hasCommaInAbilities = true;
    if (!hasCommaInAbilities) {
        result->abilities.count = 1;
        strcpy(result->abilities.ab1, aux[abilitiesPos]);
    } else {
        char* formattedAbilities = malloc(strSize * sizeof(char));
        strcpy(formattedAbilities, aux[abilitiesPos]);
        for (int x = 0; x < strlen(formattedAbilities); x++)
            if (formattedAbilities[x] == ' ' && formattedAbilities[x - 1] == ',') formattedAbilities[x] = ',';

        char* buffer;
        buffer = strtok(formattedAbilities, ",");
        result->abilities.count = 1;
        strcpy(result->abilities.ab1, buffer);

        if ((buffer = strtok(NULL, ","))) {
            result->abilities.count = 2;
            strcpy(result->abilities.ab2, buffer);

            if ((buffer = strtok(NULL, ","))) {
                result->abilities.count = 3;
                strcpy(result->abilities.ab3, buffer);

                if ((buffer = strtok(NULL, ","))) {
                    result->abilities.count = 4;
                    strcpy(result->abilities.ab4, buffer);

                    if ((buffer = strtok(NULL, ","))) {
                        result->abilities.count = 5;
                        strcpy(result->abilities.ab5, buffer);

                        if ((buffer = strtok(NULL, ","))) {
                            result->abilities.count = 6;
                            strcpy(result->abilities.ab6, buffer);
                        }
                    }
                }
            }
        }
    }

    // Analisa peso e altura se disponíveis
    if (nSplits > 10) {
        result->weight = parseDouble(aux[7 - (12 - nSplits)]);
        result->height = parseDouble(aux[8 - (12 - nSplits)]);
    } else {
        result->weight = 0.0;
        result->height = 0.0;
    }

    result->captureRate = parseInt(aux[9 - (12 - nSplits)]);

    result->legendary = strcmp(aux[10 - (12 - nSplits)], "1") == 0;

    result->captureDate = strToDate(aux[11 - (12 - nSplits)]);

    return result;
}

// Função para imprimir detalhes de um Pokémon
void printMon(Pokemon* poke) {
    printf("[#%d -> %s: %s - [", poke->id, poke->name, poke->description);

    if (poke->types.count == 1) printf("'%s'] - [", poke->types.type1);
    else printf("'%s', '%s'] - [", poke->types.type1, poke->types.type2);

    if (poke->abilities.count == 1)
        printf("%s] - ", poke->abilities.ab1);
    else if (poke->abilities.count == 2)
        printf("%s, %s] - ", poke->abilities.ab1, poke->abilities.ab2);
    else if (poke->abilities.count == 3)
        printf("%s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3);
    else if (poke->abilities.count == 4)
        printf("%s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4);
    else if (poke->abilities.count == 5)
        printf("%s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5);
    else if (poke->abilities.count == 6)
        printf("%s, %s, %s, %s, %s, %s] - ", poke->abilities.ab1, poke->abilities.ab2, poke->abilities.ab3, poke->abilities.ab4, poke->abilities.ab5, poke->abilities.ab6);

    printf("%.1f kg - %.1f m - %d%% ", poke->weight, poke->height, poke->captureRate);

    if (poke->legendary) printf("true ");
    else printf("false ");

    printf("- %d gen] - ", poke->generation);

    char* captureDateStr = dateToStr(poke->captureDate);
    printf("%s\n", captureDateStr);
}

// Função para ler dados de Pokémon de um arquivo CSV
Pokemon** readFile(const char* path) {
    Pokemon** result = calloc(801, sizeof(Pokemon*));
    FILE* csv = fopen(path, "rt");
    char* trash = malloc(1000 * sizeof(char));
    fgets(trash, 999, csv);
    free(trash);
    for (int i = 0; i < 801; i++) {
        char* buffer = malloc(1000 * sizeof(char));
        fgets(buffer, 999, csv);
        result[i] = makeMon(buffer);
        free(buffer);
    }
    fclose(csv);
    return result;
}

// Função para calcular a diferença de tempo
double diff(clock_t start, clock_t end) {
    return ((double)(end - start)) / CLOCKS_PER_SEC;
}

// Função para registrar o desempenho de tempo para ordenação
void logTP(const char* fileName, double time, int comps, int moves) {
    FILE* file = fopen(fileName, "w");
    if (file) {
        fprintf(file, "Matrícula: 863485\t");
        fprintf(file, "Tempo de Execução: %lf\t", time);
        fprintf(file, "Comparações: %d\t", comps);
        fprintf(file, "Movimentos: %d", moves);
        fclose(file);
    } else {
        printf("Erro ao abrir arquivo");
    }
}

// Função para registrar o desempenho de tempo para busca
void logSearch(const char* fileName, double time, int comps) {
    FILE* file = fopen(fileName, "w");
    if (file) {
        fprintf(file, "Matrícula: 863485\t");
        fprintf(file, "Tempo de Execução: %lf\t", time);
        fprintf(file, "Comparações: %d", comps);
        fclose(file);
    } else {
        printf("Erro ao abrir arquivo");
    }
}

int comps = 0; // Contador de comparações
int moves = 0; // Contador de movimentos

// Função para verificar se duas strings são iguais
bool equals(const char* s1, const char* s2) {
    return 0 == strcmp(s1, s2);
}

// Função para trocar dois inteiros em um array
void swapInt(int* arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
}

// Função para trocar dois Pokémon em um array
void swapPoke(Pokemon** arr, int a, int b) {
    Pokemon* tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
    moves += 3; // Contar movimentos para a troca
}

// Função para obter o caractere em um índice específico
int charAt(const char* s, int x) {
    if (x < strlen(s))
        return s[x];
    else return 0;
}

// Função para encontrar o comprimento máximo da habilidade
int maxAbi(Pokemon** pokes, int n) {
    int max = 0;
    for (int i = 0; i < n; i++) {
        int len = strlen(pokes[i]->abilities.ab1);
        if (len > max) max = len;
    }
    return max;
}

// Função de ordenação radix baseada nas habilidades
void radix(Pokemon** pokes, int n, int maxAb) {
    for (int pos = maxAb - 1; pos >= 0; pos--) {
        int count[256] = {0};
        Pokemon** tmp = calloc(n, sizeof(Pokemon*));

        for (int i = 0; i < n; i++) {
            int cha = charAt(pokes[i]->abilities.ab1, pos);
            count[cha]++;
        }

        for (int i = 1; i < 256; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int cha = charAt(pokes[i]->abilities.ab1, pos);
            tmp[--count[cha]] = pokes[i];
            moves++;
        }

        for (int i = 0; i < n; i++) {
            pokes[i] = tmp[i];
            moves++;
        }

        free(tmp);
    }
}

// Função para determinar se duas strings devem ser trocadas
bool shouldSwap(char* a, char* b) { 
    bool result = false;
    int i = -1;
    do {
        i++;
        if (a[i] > b[i]) result = true;
    } while (a[i] == b[i]);
    return result;
}

// Função de ordenação por inserção para Pokémon
void insertion(Pokemon** arr, int n) {
    for (int i = 1; i < n; i++) {
        Pokemon* tmp = arr[i];
        int j = i - 1;
        while (j >= 0 && 0 == strcmp(arr[j]->abilities.ab1, tmp->abilities.ab1) && shouldSwap(arr[j]->name, tmp->name)) {
            comps++;
            moves++;
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = tmp;
        moves++;
    }
}

// Função para ordenar Pokémon
void sort(Pokemon** pokes, int n) {
    radix(pokes, n, maxAbi(pokes, n));
    insertion(pokes, n);
}

// Função principal para executar o programa
int main() {
    Pokemon** pokes = readFile("/tmp/pokemon.csv");

    int i = 0;
    int* usingIds = malloc(100 * sizeof(int));
    
    char* input = malloc(20 * sizeof(char));
    scanf(" %s", input);
    while (!equals(input, "FIM")) {
        usingIds[i] = parseInt(input);
        i++;
        free(input);
        input = malloc(20 * sizeof(char));
        scanf(" %s", input);
    }
    free(input);

    // Cria um array de Pokémon a serem usados
    Pokemon** using = calloc(i, sizeof(Pokemon*));
    for (int j = 0; j < i; j++) {
        using[j] = pokes[usingIds[j] - 1];
    }
    free(usingIds);

    clock_t start = clock(); // Inicia o cronômetro
    sort(using, i); // Ordena os Pokémon selecionados
    for (int j = 0; j < i; printMon(using[j++]));
    clock_t end = clock(); // Para o cronômetro

    free(using);
    free(pokes);
    logTP("863485_radixsort.txt", diff(start, end), comps, moves); // Registra o desempenho

    return 0;
}
