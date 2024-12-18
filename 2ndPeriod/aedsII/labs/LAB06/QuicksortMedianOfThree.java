/**
 * Algoritmo de ordenacao QuicksortMedianOfThree
   * @author Guilherme Soares Silva
   * @version 1 10/2024
   */

public class QuicksortMedianOfThree extends GenerationArrayInt {

   /**
    * Construtor
    */
   public QuicksortMedianOfThree(){
      super();
   }
   /**
    * Construtor com tamnho do array definido
    * @param int tamanho do array de numeros inteiros.
    */
   public QuicksortMedianOfThree(int size){
      super(size);
   }


   /**
    * Algoritmo de ordenacao QuicksortMedianOfThree
    */
   @Override
   public void sort() {
      quicksortMedianOfThree(0, n-1);
   }

   /**
    * Metodo que retorna a mediana de 3 elementos
    * @param x
    * @param y
    * @param z
    * @return int median
    */
   private int median(int x, int y, int z) {
      if((x < y) && (x < z)) {
         if(y < z) {
            return y;
         } else {
            return z;
         }
      } else if ((x < y) && (x > z)) {
         return x;
      } else if (y < z){
         return y;
      } else {
         return z;
      }
   }

   /**
    * Algoritmo de ordenacao QuicksortMedianOfThree
    * @param int left inicio do array a ser ordenado
    * @param int right fim do array a ser ordenado
    */
   private void quicksortMedianOfThree(int left, int right) {
      int i = left, j = right;
      int pivot = median(array[left], array[right], array[(left + right)/2]);
      while (i <= j) {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) {
               swap(i, j);
               i++;
               j--;
            }
      }
      if (left < j)  quicksortMedianOfThree(left, j);
      if (i < right)  quicksortMedianOfThree(i, right);
   }
}