#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int x=7, y=5;

    printf("The sum between: %d + %d = %d\n",x,y,(x+y));
    printf("The sub between: %d - %d = %d\n",x,y,(x-y));
    printf("The mult between: %d x %d = %d\n",x,y,(x*y));
    double div = (double)x/y;
    printf("The division between: %d / %d = %.2lf\n",x,y,div);
    printf("The remainder between: %d %% %d = %d\n",x,y,(x%y));
    printf("The quotient between: %d / %d = %d\n",x,y,(x/y));
    int inc = x++;
    printf("Increment: %d++ = %d\n",x-1,x);
    int dec = y--;
    printf("Decrement: %d-- = %d\n",y+1,y);

    return 0;
}