/**
 * Algoritmo de ordenacao QuicksortFirstPivot
 * @author Guilherme Soares Silva
 * @version 1 10/2024
 */

public class QuicksortFirstPivot extends GenerationArrayInt {

	/**
	 * Construtor
	 */
   public QuicksortFirstPivot(){
      super();
   }
	/**
	 * Construtor com tamnho do array definido
	 * @param int tamanho do array de numeros inteiros.
	 */
   public QuicksortFirstPivot(int size){
      super(size);
   }


	/**
	 * Algoritmo de ordenacao QuicksortFirstPivot
	 */
   @Override
   public void sort() {
      quicksortFirstPivot(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao QuicksortFirstPivot
    * @param int left inicio do array a ser ordenado
    * @param int right fim do array a ser ordenado
	 */
    private void quicksortFirstPivot(int left, int right) {
        int i = left, j = right;
        int pivot = array[left];
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (left < j)  quicksortFirstPivot(left, j);
        if (i < right)  quicksortFirstPivot(i, right);
    }
}