/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: C TP01Q02 - Palindromo
 * Data: 30/08/2024
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LIMIT 256

bool isPalindromeRecursive(char *, int, int);
void containsSpecialChars(char *);

int main(void) {
    char str[MAX_LIMIT];
    bool loop = true;

    while(loop) {
        fgets(str, sizeof(str), stdin);
        str[strcspn(str, "\n")] = '\0'; 
        if(strcmp(str, "FIM") == 0) {
            loop = false;
        } else {
            containsSpecialChars(str);
            printf("%s\n", isPalindromeRecursive(str, 0, strlen(str) - 1) ? "SIM" : "NAO");
        }
    }
    return 0;
}

// Função recursiva para verificar se algum caractere especial está presente na string
void containsSpecialChars(char *s) {
    if (*s == '\0') {
        return;
    }
    if (!(((*s >= 'A') && (*s <= 'Z')) || ((*s >= 'a') && (*s <= 'z')))) {
        if ((*s >= '0') && (*s <= '9')) {
            *s = *s - 1;
        } else {
            *s = 'z';
        }
    }
    containsSpecialChars(s + 1);
}

// Função recursiva para verificar se a palavra é um palíndromo
bool isPalindromeRecursive(char *s, int left, int right) {
    if (left >= right) {
        return true;
    }
    if (s[left] != s[right]) {
        return false;
    }
    return isPalindromeRecursive(s, left + 1, right - 1);
}
