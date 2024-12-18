/*
No ano de 2020 o Papai Noel não poderá sair de casa para entregar presentes por conta da pandemia do Coronavirus.
Então ele ordenou que seus duendes fossem entregar no lugar dele no dia do natal.
Como eles são bastante inexperientes, irão se dividir em vários times compostos de três membros:
Um líder, um entregador e um piloto de trenó.
O plano do Papai Noel é que os líderes das equipes seja sempre os duendes mais velhos,
por esse motivo ele pediu para todos escreverem seus nomes e idades em uma lista.
Como você é um duende programador, resolveu ajudar o Papai Noel a organizar a lista e montar os times a partir dela.

Segue abaixo algumas regras e fatos:

A lista deve ser organizada em ordem descendente de idade;
Caso dois duendes possuírem a mesma idade, deve se organizar por ordem ascendente de nome;
Não existe dois duendes de mesmo nome;
Nenhum duende tem mais de 20 caracteres em seu nome;
Os duendes da lista tem idade entre 10 e 100 anos;
Os primeiros 1/3 dos duendes (os mais velhos), serão os líderes dos times;
A ordem dos duendes entregadores e pilotos seguem a mesma lógica dos líderes. Ex)
Se há 6 duendes na lista, haverá dois times, onde o duende mais velho é líder do time 1,
e o segundo mais velho é líder do time 2.
O terceiro mais velho é entregador do time 1 e o quarto mais velho é entregador do time 2.
O quinto é piloto de trenó do time 1 e o último é piloto do time 2;

Entrada
A entrada é composta de um número inteiro N (3 <= N <= 30), onde N é múltiplo de 3, que representa a quantidade de duedes na lista.
Em seguida as próximas N linhas contém o nome e a idade de cada duende.

Saída
A saída é composta de 4 linhas por time. A primeira linha deve seguir o formato "Time X", onde X é o número do time.
A segunda, terceira e quarta linha contém, respectivamente, o nome e idade do duende líder, entregador e piloto de trenó.
Depois de cada time, deverá haver uma linha em branco, inclusive após o último time.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct 
{
    char name[21];
    int age;
} Duende;

void sort(Duende duendes[], int N) {
    for (int i = 0; i < N - 1; i++) {
        int bigger = i;
        for (int j = i + 1; j < N; j++) {
            if (duendes[j].age > duendes[bigger].age) {
                bigger = j;
            } else if (duendes[j].age == duendes[bigger].age) {
                // Comparar nomes se as idades são iguais
                if (strcmp(duendes[j].name, duendes[bigger].name) < 0) {
                    bigger = j;
                }
            }
        }
        // Troca de posições
        if (bigger != i) {
            Duende aux = duendes[bigger];
            duendes[bigger] = duendes[i];
            duendes[i] = aux;
        }
    }
}

int main()
{
    int N;
    scanf("%d",&N);

    Duende duendes[N];
    for(int i = 0; i < N; i++)
    {
        scanf(" %21s",duendes[i].name);
        scanf(" %d",&duendes[i].age);
    }
    sort(duendes, N);

    int teams = N / 3;
    for(int i = 0; i < teams; i++)
    {
        printf("Time %d\n", i + 1);
        printf("%s %d\n",duendes[i].name, duendes[i].age);
        printf("%s %d\n",duendes[i + teams].name, duendes[i + teams].age);
        printf("%s %d\n\n",duendes[i + (teams * 2)].name, duendes[i + (teams * 2)].age);
    }

    return 0;
}
