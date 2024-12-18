#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x;
    scanf("%d",&x);

    while(x!=0)
    {
        if(x%2==0)
        {
            printf("P\n");
        }
        else
        {
            printf("I\n");
        }
        scanf("%d",&x);
    }

    return 0;
}