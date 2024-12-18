/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: C TP01Q09 - Arquivo
 * Data: 30/08/2024
 */
#include <stdio.h>

void writeValuesToFile(const char *filename, int count);
void readValuesInReverse(const char *filename, int count);

int main() {
    int n;

    scanf("%d", &n);

    writeValuesToFile("values.txt", n);
    readValuesInReverse("values.txt", n);

    return 0;
}

void writeValuesToFile(const char *filename, int count) {
    FILE *file = fopen(filename, "wb");
    if (file == NULL) {
        return;
    }

    for (int i = 0; i < count; i++) {
        float value;
        scanf("%f", &value);
        fwrite(&value, sizeof(float), 1, file);
    }

    fclose(file);
}

void readValuesInReverse(const char *filename, int count) {
    FILE *file = fopen(filename, "rb");
    if (file == NULL) {
        return;
    }

    // Move o ponteiro do arquivo para o final
    fseek(file, 0, SEEK_END);

    // Lê os valores do arquivo em ordem inversa
    for (int i = 0; i < count; i++) {
        // Move o ponteiro para a posição do número que queremos ler
        fseek(file, -((long)sizeof(float) * (i + 1)), SEEK_END);
        float value;
        fread(&value, sizeof(float), 1, file);
        printf("%.3f\n", value);
    }

    fclose(file);
}
