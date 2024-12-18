#include <stdio.h>
#include <stdbool.h>

bool BinarySearch(int [], int, int);

int main(void)
{
    int array[5] = {1, 2, 3, 4, 5};
    int x = 7, n = sizeof(array) / sizeof(array[0]);
    bool result = BinarySearch(array, x, n);
    printf("\nResult: %s", result ? "true\n" : "false\n");
}

bool BinarySearch(int* array, int x, int n)
{
    int low = 0, high = n - 1, mid = n / 2;
    while(high >= low)
    {
        mid = low + (high - low) / 2;
        if(array[mid] == x)
        {
            return true;
        }
        else if(array[mid] > x)
        {
            high = mid - 1;
        }
        else
        {
            low = mid + 1;
        }
    }
    return false;
}