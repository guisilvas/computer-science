#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void sort(char *);
void realoc(char *);

int main()
{
    FILE *file_ptr;
    file_ptr = fopen("in.txt", "r");
    char line[100001];
    char output[100001];

    if(file_ptr == NULL)
    {
        exit(EXIT_FAILURE);
    }
    else
    {
        while(!feof(file_ptr)) // reading file line by line
        {
            fgets(line, 100001, file_ptr);
            strncpy(output, line, sizeof(line));
        }
    }
    fclose(file_ptr);
    return 0;
}

void sort(char *array)
{
    for(int i = 0; i < sizeof(array); i++)
    {

    }
}

void realoc(char *array)
{
    for(int i = sizeof(array) - 1; i > 0; i--)
    {
            
    }
}