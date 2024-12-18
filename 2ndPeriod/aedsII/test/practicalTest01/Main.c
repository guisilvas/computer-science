// Importando bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Criando uma struct de crianças
typedef struct
{
    char name[21];

} Children;

// Função de ondenação
void Sort(Children chils[], int n)
{
    for(int i = 0; i < n - 1 ; i++)
    {
        for(int j = 0; j < n - 1 - i; j++)
        {
            // Compara por ordem alfabética
            int cmp = strcmp(chils[j].name, chils[j+1].name);
            if(cmp > 0) // Se o nome a direita for menor, realiza o swapp
            {
                // Realizando o swapp 
                Children aux = chils[j];
                chils[j] = chils[j+1];
                chils[j+1] = aux;
            }
        }
    }
}

int main()
{
    // Declarando variáveis
    int n, pos = 0, neg = 0;
    scanf("%d", &n);

    Children chils[n];

    // Lendo as entradas
    for(int i=0; i<n; i++)
    {
        char signal;
        scanf(" %c %s ", &signal, chils[i].name);

        if(signal == '+')
        {
            pos++;
        }
        else if(signal == '-')
        {
            neg++;
        }
    }

    // Ordenando o array de struct Children chils
    Sort(chils, n);

    // Mostrando os nomes em ordem alfabética 
    for(int p = 0; p < n; p++)
    {
        printf("%s\n", chils[p].name);
    }
    // Mostrando a quantidade de crianças que se comportaram e que não se comportaram
    printf("Se comportaram: %d | Nao se comportaram: %d", pos, neg);
    return 0;
}