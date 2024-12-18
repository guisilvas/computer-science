/* C
Primitives types:   Observacao:
char                caractere
int                 inteiro
float               ponto flutuante
double              ponto flutuante
char[]              cadeia de caracteres

C++
bool                booleano

C#
String                          cadeia de caracteres
sbyte,short, int e long         inteiro com sinal
byte, ushort, uint e ulong      inteiro sem sinal
float, double e decimal         ponto flutuante

Java
byte, short, int e long         inteiro
classe String                   cadeia de caracteres

Python
str                             cadeia de caracteres
*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    char name[20];
    int age;
    float height;

    scanf("%s",name);
    scanf("%d",&age);
    scanf("%f",&height);

    printf("%s is %d years old and is %.2fm tall",name,age,height);

    return 0;
}