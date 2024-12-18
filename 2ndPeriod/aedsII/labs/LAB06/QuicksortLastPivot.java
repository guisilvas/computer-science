/**
 * Algoritmo de ordenacao QuicksortLastPivot
 * @author Guilherme Soares Silva
 * @version 1 10/2024
 */

 public class QuicksortLastPivot extends GenerationArrayInt {

	/**
	 * Construtor
	 */
   public QuicksortLastPivot(){
      super();
   }
	/**
	 * Construtor com tamnho do array definido
	 * @param int tamanho do array de numeros inteiros.
	 */
   public QuicksortLastPivot(int size){
      super(size);
   }


	/**
	 * Algoritmo de ordenacao QuicksortLastPivot
	 */
   @Override
   public void sort() {
      quicksortLastPivot(0, n-1);
   }

	/**
	 * Algoritmo de ordenacao QuicksortLastPivot
    * @param int left inicio do array a ser ordenado
    * @param int right fim do array a ser ordenado
	 */
    private void quicksortLastPivot(int left, int right) {
        int i = left, j = right;
        int pivot = array[right];
        while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (left < j)  quicksortLastPivot(left, j);
        if (i < right)  quicksortLastPivot(i, right);
    }
}