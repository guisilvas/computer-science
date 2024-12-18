/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: C LAB02Q01 - Combinador
 * Data: 24/08/2024
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LIMIT 256

// Header da função que realiza a concatenação
char* Concatenate(int, int, char*, char*);

int main(void) {
    char str1[MAX_LIMIT], str2[MAX_LIMIT];
    char* result;
    while(scanf("%s",str1) == 1) {
        scanf("%s",str2);
        int size1 = strlen(str1), size2 = strlen(str2);
        result = Concatenate(size1, size2, str1, str2);
        printf("%s\n",result);
    }
    return 0;
}

// Função que concatena as duas strings da entrada
char* Concatenate(int size1, int size2, char* s1, char* s2) {
    int pos1 = 0, pos2 = 0;
    char* result = (char*)malloc((size1 + size2)*sizeof(char));
    for(int i = 0; i < (size1 + size2); i++) {
        if(pos1 > size1 - 1) {
            result[i] = s2[pos2];
            pos2++;
        } else if(pos2 > size2 - 1) {
            result[i] = s1[pos1];
            pos1++;
        } else {
            if(i%2==0) {
                result[i] = s1[pos1];
                pos1++;
            } else {
                result[i] = s2[pos2];
                pos2++;
            }
        }
    }
    return result;
}