#include <stdio.h>
#include <stdlib.h>

// Função para contar o número mínimo de ultrapassagens
int overtakes(int grid1[], int grid2[], int n) {
    int *pos = (int *)malloc(n * sizeof(int)); 

    // Armazenar as posições de cada competidor da grid2
    for (int i = 0; i < n; i++) {
        pos[grid2[i] - 1] = i; 
    }

    int cont = 0;

    // Contar as inversões
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (pos[grid1[i] - 1] > pos[grid1[j] - 1]) {
                cont++; 
            }
        }
    }

    free(pos);
    return cont;
}

void fillArray(int grid1[], int grid2[], int n) {
    for (int i = 0; i < n; i++) {
        scanf("%d", &grid1[i]);
    }

    for (int i = 0; i < n; i++) {
        scanf("%d", &grid2[i]);
    }

    int result = overtakes(grid1, grid2, n);
    printf("%d\n", result);
}

int main(void) {
    while (1) {
        int N;
        if (scanf("%d", &N) != 1) {
            break; 
        }

        int *grid1 = (int *)malloc(N * sizeof(int));
        int *grid2 = (int *)malloc(N * sizeof(int));

        fillArray(grid1, grid2, N);

        free(grid1); 
        free(grid2);
    }
    return 0;
}

/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: C LAB07 - Grid de Largada
 * Data: 15/10/2024
 * 
 * Análise de Complexidade:
 * 
 * 1. A função `overtakes` utiliza um array `pos` para mapear a posição de chegada de cada competidor.
 *    - Esta operação leva O(N) tempo, onde N é o número de competidores.
 *
 * 2. O segundo laço aninhado percorre todos os pares de competidores na ordem de largada (grid1).
 *    - Esse laço é O(N^2) na pior das hipóteses, pois compara cada competidor com todos os outros.
 *
 * Portanto, a complexidade total da função `overtakes` é O(N^2) devido ao laço aninhado.
 * 
 * 3. A função `fillArray` é O(N) para ler os dados.
 * 
 * Complexidade de tempo geral: O(N^2)
 */
