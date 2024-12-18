/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int verifyCaps(char letter);

int end(char *text);

int countCaps(const char *word);

int main(void) {
    char entry[1000][1000]; // Array de strings para armazenar as palavras
    int entryNum = 0; // Posições

    // Leitura da entrada padrão
    do {
        if (fgets(entry[entryNum], 1000, stdin) != NULL) {
            size_t len = strlen(entry[entryNum]);
            if (len > 0 && entry[entryNum][len-1] == '\n') {
                entry[entryNum][len-1] = '\0';
            }
        }

        // Verifica se a palavra é "FIM", se for true encerra
        if (end(entry[entryNum])) {
            break;
        }
        
        entryNum++;
    } while (entryNum < 1000); 

    // Para cada linha de entrada, gerando uma saída contendo o número de letras maiúsculas da entrada
    for (int i = 0; i < entryNum; i++) {
        printf("%d\n", countCaps(entry[i]));
    }

    return 0;
}

// Função que verifica se a letra é maiúscula
int verifyCaps(char letter) {
    return (letter >= 'A' && letter <= 'Z');
}

// Função que verifica se a palavra é 'FIM'
int end(char *text) {
    return strcmp(text, "FIM") == 0;
}

// Função que conta o número de letras maiúsculas
int countCaps(const char *word) {
    int count = 0;
    for (int i = 0; word[i] != '\0'; i++) {
        if (verifyCaps(word[i])) {
            count++;
        }
    }
    return count;
}
