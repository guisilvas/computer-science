#include <stdio.h>

#define TAM 7

void Swap(int i, int j, int *array)
{
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
}

void QuickSort(int left, int right, int *array)
{
    int i = left, j = right, pivot = array[(right+left)/2];
    while(i <= j)
    {
        while(array[i] < pivot) i++; //Lower half
        while(array[j] > pivot) j--; //Higher half
        if(i <= j)
        {
            Swap(i, j, array);
            i++;
            j--;
        }
    }
    if(left < j) QuickSort(left, j, array); //Recursive for lower half 
    if(right > i) QuickSort(i, right, array); //Recursive for higher half
}

int main(void)
{
    int array[TAM] = {0, 1, 5, 3, 15, 16, 9, 10, 4, 3, 30, 5, 20, 48, 71, 82};
 
    for(int i=0; i<TAM; i++)
        printf("%d ",array[i]);
    printf("\n\n");

    QuickSort(0, TAM -1, array);

    for(int i=0; i<TAM; i++)
        printf("%d ",array[i]);
    printf("\n");
    
    return 0;
}