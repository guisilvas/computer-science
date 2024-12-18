/**
 * Algoritmo de ordenacao QuicksortRandomPivot
 * @author Guilherme Soares Silva
 * @version 1 10/2024
 */

import java.util.Random;

public class QuicksortRandomPivot extends GenerationArrayInt {

	/**
	 * Construtor
	 */
   public QuicksortRandomPivot(){
      super();
   }
	/**
	 * Construtor com tamnho do array definido
	 * @param int tamanho do array de numeros inteiros.
	 */
   public QuicksortRandomPivot(int size){
      super(size);
   }


	/**
	 * Algoritmo de ordenacao Quicksort com pivo aleatorio
	 */
   @Override
   public void sort() {
      quicksortRandomPivot(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao QuicksortRandomPivot
    * @param int left inicio do array a ser ordenado
    * @param int right fim do array a ser ordenado
	 */
    private void quicksortRandomPivot(int left, int right) {
        Random rand = new Random();
        int i = left, j = right;
        int pivot = array[left + Math.abs(rand.nextInt()) % (right - left + 1)];
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (left < j)  quicksortRandomPivot(left, j);
        if (i < right)  quicksortRandomPivot(i, right);
    }
}