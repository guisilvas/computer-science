#ifndef BubbleSort_h
#define BubbleSort_h

#include "Swap.h"

// BubbleSort -> n^2
void bubbleSort(int array[], int length)
{
    for (int i = 0; i < length - 1; i++)
    {
        for (int j = 0; j < length - i - 1; j++)
        {
            if (array[j] > array[j+1])
            {
                swap(array, j, j + 1);
            }
        }
    }
}

// Função otimizada de Bubble Sort
void bubbleSort(int array[], int length)
{
    int swapped = 0; // Flag para verificar se houve troca
    for (int i = 0; i < length - 1 - swapped; i++)
    {
        swapped = false; // Reseta a flag a cada nova passagem
        for (int j = 0; j < length - i - swapped; j++)
        {
            if (array[j] > array[j + 1])
            {
                swap(array, j, j + 1); // Troca os elementos
                swapped = 1; // Marca que houve uma troca
            }
        }
        // Se não houve troca, o array está ordenado
        if (!swapped)
        {
            break; // Encerra o loop se o array estiver ordenado
        }
    }
}

// BubbleSort with last swap saved
void bubbleSortOpt(int array[], int length)
{
    int last_swap = length - 1;
    for (int i = 0; i < length - 1; i++)
    {
        int new_last_swap = 0;
        for (int j = 0; j < last_swap; j++)
        {
            if (array[j] > array[j + 1])
            {
                swap(array, j, j + 1);
                new_last_swap = j;
            }
        }
        if (!new_last_swap)
        {
            i = length;
        }
        else
        {
            last_swap = new_last_swap;
        }
    }
}


#endif