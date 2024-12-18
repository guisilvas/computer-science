#include <stdio.h>

#define size 10

void smallerBigger (int* array)
{
    int small = 0, big = 0;
    for(int i=1; i<size; i++)
    {
        if(array[small] > array[i])
        {
            small = i;
        }
        if(array[big] < array[i])
        {
            big = i;
        }
    }
    printf("O maior elemento do array é array[%d] = %d\n",big,array[big]);
    printf("O menor elemento do array é array[%d] = %d\n",small,array[small]);
}

int main(void)
{
    int arr[size] = {23, 5, 8, 64, 7, 7, 24, 9, 10, 2};

    smallerBigger(arr);
}