#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int i=0. b=10;

    do
    {
        printf("Run %dx",(i+1));
        i++;
        b--;
    } while(i<3);

    return 0;
}