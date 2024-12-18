#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define LEN 10

void fillArray(int* arr)
{
        printf("Lets fill the array.\n");
        for(int i=0; i<LEN; i++)
        {
            printf("index %d: ",i);
                scanf("%d",&arr[i]);
        }
}

bool search(int x, int* arr)
{
        int loop = 0; //Variavel de controle para ver a quantidade de execuções
        bool res = false;
        for(int i=0; i<LEN; i++)
        {
                loop++;
                if(x==arr[i])
                {
                        i=LEN;
                        res = true;
                }
                printf("Comparou %dx\n",loop);
        }
        return res;
}

int main(void)
{
    int x;
    int* arr;
    arr = (int*)malloc(LEN*sizeof(int));

    fillArray(arr);

    printf("Enter a int number to search on a array: ");
    scanf("%d",&x);

    bool result = search(x, arr);
    if(result)
    {
            printf("The number %d is on the array.\n",x);
    }
    else
    {
            printf("The number %d isnt on the array.\n",x);
    }

    free(arr);
}
