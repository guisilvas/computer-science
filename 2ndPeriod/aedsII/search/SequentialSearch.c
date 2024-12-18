#include <stdio.h>
#include <stdbool.h>

int main(void)
{
    int x;
    int array[5] = {1, 2, 3, 4, 5};
    printf("Insert a integer to search on array: ");
    scanf("%d",&x);

    int search = 0;

    bool answer = false;
    for(int i = 0; i < sizeof(array) / sizeof(int); i++)
    {
        search++;
        if(array[i]  == x)
        {
            answer = true;
            i = sizeof(array);
        }
    }
    printf("Loops: %d\nResult: %d\n",search,answer);
}