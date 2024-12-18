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
    int loop = 0; // Verificar quantas vezes o loop foi executado
    bool res = false;
    int i = LEN/2; // Começa da metade do array
    if(arr[i]==x)
    {
        loop++;
        res = true;
        printf("Comparou %dx\n",loop);
    }
    else if(x > arr[i])
    {
        for(int j=LEN/2+1; j<LEN; j++)
        {
            loop++;
            if(x==arr[j])
            {
                    j=LEN;
                    res = true;
            }
            printf("Comparou %dx\n",loop);
        }
    }
    else
    {
        for(int k=LEN/2-1; k>0; k--)
        {
            loop++;
            if(x==arr[k])
            {
                    k=LEN;
                    res = true;
            }
            printf("Comparou %dx\n",loop);
        }
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
