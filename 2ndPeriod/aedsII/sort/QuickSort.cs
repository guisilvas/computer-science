using System;

class MainClass {

    // Define o tamanho para o vetor
    const int TAM = 7;

    /*
    * Realiza a troca de posição de alguns elementos
    */
    public static void swap(int i, int j, int [] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*
    * Método QuickSort de ordenação
    */
    private static void QuickSort(int esq, int dir, int [] array) {
        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  QuickSort(esq, j, array);
        if (i < dir)  QuickSort(i, dir, array);
    }

    /*
     *
     */
    public static void Main (string[] args) {
        int[] vetor = new int[TAM]{20, 10, 30, 50, 45, 35, 5};

        
        for(int i=0; i<TAM; i++)
            Console.Write(vetor[i] + " ");
        Console.Write("\n\n");

        QuickSort(0, TAM -1, vetor);

        for(int i=0; i<TAM; i++)
            Console.Write(vetor[i] + " ");
        Console.Write("\n");
    }
}