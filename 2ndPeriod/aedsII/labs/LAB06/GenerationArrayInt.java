/**
 * Geracao de elementos de um array de inteiros
 * @author Guilherme Soares Silva
 * @version 1 10/2024
 */

import java.util.*;

public class GenerationArrayInt {
/*
    * Leitor de entrada
    */
    Scanner scan = new Scanner(System.in);

    /**
     * Atributos da classe
    */
    protected int[] array;
    protected int n;

    /**
     * Cria um array com base no tipo especificado.
     * @param size Tamanho do array.
     * @param type Tipo do array: 0 (aleatório), 1 (ordenado), 2 (quase ordenado).
     * @return O array gerado.
     */
    public int[] createArray(int size, int type) {
        int[] array = new int[size];
        Random rand = new Random();
        
        switch (type) {
            case 0: // Aleatório
                for (int i = 0; i < size; i++) {
                    array[i] = rand.nextInt(size);
                }
                break;
            case 1: // Ordenado
                for (int i = 0; i < size; i++) {
                    array[i] = i;
                }
                break;
            case 2: // Quase ordenado
                for (int i = 0; i < size; i++) {
                    array[i] = i;
                }
                // Trocando elementos
                for (int i = 0; i < size / 10; i++) {
                    int index1 = rand.nextInt(size);
                    int index2 = rand.nextInt(size);
                    swapArray(array, index1, index2);
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + type);
        }
        
        return array;
    }

    /**
     * Troca o conteudo de duas posicoes do array
     * @param array O array onde a troca ocorrerá.
     * @param i int primeira posicao
     * @param j int segunda posicao
     */
    private void swapArray(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Metodo Construtor com tamanho padrao
    */
    public GenerationArrayInt(){
        array = new int[100];
        n = array.length;
    }
    /**
     * Metodo Construtor com tamanho definido
    * @param int tamanho do array de numeros inteiros
    */
    public GenerationArrayInt(int size){
        array = new int[size];
        n = array.length;
    }


    /**
     * Produz um array ordenado de modo crescente
    */
    public void crescentArrayInt() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }
    /**
     * Produz um array ordenado de modo decrescente
    */
    public void decrescentArrayInt() {
        for (int i = 0; i < n; i++) {
            array[i] = n - 1 - i;
        }
    }
    /**
     * Produz um array com numeros aleatorios
    */
    public void randomArrayInt() {
        Random rand = new Random();
        crescentArrayInt();	
        for (int i = 0; i < n; i++) {
            swap(i, Math.abs(rand.nextInt()) % n);
        }
    }


    /**
     * Efetua a leitura dos elementos via entrada padrao
    */
    public void defaultEntry() {
        n = scan.nextInt();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scan.nextInt();
        }
    }

    /**
     * Recebe um array e efetua a leitura dos elementos via entrada padrao
    */
    public void entry(int[] vet){
        n = vet.length;
        array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = vet[i];
        }
    }


    /**
     * Mostra os k primeiros elementos do array
    * @param int k indica a quantidade de elementos do array a serem mostrados.
    */
    public void printKArrayInt(int k) {
        System.out.print("[");

        for (int i = 0; i < k; i++) {
            System.out.print(" ("+i+")" + array[i]);
        }

        System.out.println("]");
    }


    /**
     * Mostra os elementos do array.
    */
    public void printArrayInt() {
        System.out.print("[");

        for (int i = 0; i < n; i++) {
            System.out.print(" ("+i+")" + array[i]);
        }

        System.out.println("]");
    }


    /**
     * Troca o conteudo de duas posicoes do array
    * @param i int primeira posicao
    * @param j int segunda posicao
    */
    public void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    /**
     * Retorna o timestamp atual
    * @return timestamp atual
    */
    public long now(){
        return new Date().getTime();
    }


    /**
     * Retorna verdadeiro/falso indicando se o array esta ordenado
    * @return boolean indicando se o array esta ordenado
    */
    public boolean isOrdenedArrayInt(){
        boolean resp = true;
        for(int i = 1; i < n; i++){
            if(array[i] < array[i-1]){
                i = n;
                resp = false;
            }
        }
        return resp;
    }

    /*
    * Metodo a ser implementado nas subclasses
    */
    public void sort(){
        System.out.println("Método a ser implementado nas subclasses.");
    }
}