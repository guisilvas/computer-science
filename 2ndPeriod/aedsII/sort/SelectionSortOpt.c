#include <stdio.h>

void SelectionOpt(int* array, int n)
{
    int right = n - 1, left = 0;
    for(int i = 0; i < n - 1; i++)
    {
        int high = i, low = i;
        for(int j = 0; j < n - 1; j++)
        {
            if(array[j] > array[high])
            {
                high = j;
            }
            else if(array[j] < array[low])
            {
                low = j;
            }
        }
        swap(array, low, left);
        if(left == high)
        {
            high = low;
        }
        left++;
        right--;
    }
}

int main(void)
{
    int array[5] = {3, 6, 3, 7, 8, 9};
    int size = sizeof(array) / sizeof(array[0]);
    for(int i = 0; i < size - 1; i++)
    {
        printf("array[%d] = %d",i,array[i]);
    }
    SelectionOpt(array, size);
    printf("\nUsing selection sort: ");
    for(int i = 0; i < size - 1; i++)
    {
        printf("array[%d] = %d",i,array[i]);
    }
    return 0;
}