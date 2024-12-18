/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: C TP01Q02 - Palindromo
 * Data: 21/08/2024
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LIMIT 256

bool isPalindrome(char *);
void containsSpecialChars(char *);

int main(void) {
    char str[MAX_LIMIT];
    bool loop = true;

    while(loop) {
        fgets(str, sizeof(str), stdin);
        str[strcspn(str, "\n")] = '\0';
        if(!strcmp(str, "FIM")) {
            loop = false;
        } else {
            printf("%s\n",isPalindrome(str) ? "SIM" : "NAO");
        }
    }
    return 0;
}

// Função para verificar se algum caractere especial está presente na string, se houver o troca por um caractere de 1 byte
void containsSpecialChars(char *s) {
    int i = 0;
	while(s[i] != '\0') {
		if(!(s[i] > 64 && s[i] < 91 || s[i] > 96 && s[i] < 122)) {
		    if(s[i] > 47 && s[i] < 58) {
		        s[i] = s[i]-1;
		    } else {
		        s[i] = changeSpecialChar(s, s[i]);
		    }
		} else {
            if(s[i] == 'a' || s[i] == 'A') {
                s[i] += 1;
            } else {
                s[i] -= 1;
            }
        }
		i++;
	}
}

char changeSpecialChar(char c, char*s) {
    
}

// Função para verificar se a palavra é um palíndromo
bool isPalindrome(char *s) {
    containsSpecialChars(s);
    int i = 0, j = strlen(s) - 1;
    while(i < j) {
        if(s[i++] != s[j--]) {
            return false;
        }
    }
    return true;
}