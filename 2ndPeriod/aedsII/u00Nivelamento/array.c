#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define TAM 10

bool compare(int x,int arr[],int tam);

int main(void)
{
    int x, arr[TAM];

    for(int i=0; i<TAM; ++i)
    {
        scanf("%d",&arr[i]);
    }

    scanf("%d",&x);
    bool result = compare(x,&arr,TAM);
    printf("%d\n",result);

    return 0;
}

bool compare(int x, int* arr, int tam)
{
    for(int i=0; i<tam; ++i)
    {
        if(*arr==x)
        {
            return true;
        }
        arr++;
    }
    return false;
}