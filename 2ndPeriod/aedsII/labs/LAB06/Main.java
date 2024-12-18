import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};
        int[] testTypes = {0, 1, 2}; // 0: aleatório, 1: ordenado, 2: quase ordenado

        GenerationArrayInt generator = new GenerationArrayInt(); 

        // Criar o arquivo CSV
        try (FileWriter writer = new FileWriter("desempenho_quicksort.csv")) {
            // Cabeçalho do CSV
            writer.append("Tamanho,Tipo,Primeiro Pivô,Último Pivô,Pivô Aleatório,Mediana de Três\n");

            for (int size : sizes) {
                for (int type : testTypes) {
                    int[] array = generator.createArray(size, type); 
                    String typeStr = (type == 0) ? "Aleatório" : (type == 1) ? "Ordenado" : "Quase ordenado";

                    List<Long> timesFirstPivot = new ArrayList<>();
                    List<Long> timesLastPivot = new ArrayList<>();
                    List<Long> timesRandomPivot = new ArrayList<>();
                    List<Long> timesMedianOfThree = new ArrayList<>();

                    for (int i = 0; i < 10; i++) { // 10 execuções
                        // Quicksort com Primeiro Pivô
                        QuicksortFirstPivot quicksortFirst = new QuicksortFirstPivot(size);
                        quicksortFirst.entry(array.clone());
                        long startTime = System.nanoTime();
                        quicksortFirst.sort();
                        long endTime = System.nanoTime();
                        timesFirstPivot.add(endTime - startTime);

                        // Quicksort com Último Pivô
                        QuicksortLastPivot quicksortLast = new QuicksortLastPivot(size);
                        quicksortLast.entry(array.clone());
                        startTime = System.nanoTime();
                        quicksortLast.sort();
                        endTime = System.nanoTime();
                        timesLastPivot.add(endTime - startTime);

                        // Quicksort com Pivô Aleatório
                        QuicksortRandomPivot quicksortRandom = new QuicksortRandomPivot(size);
                        quicksortRandom.entry(array.clone());
                        startTime = System.nanoTime();
                        quicksortRandom.sort();
                        endTime = System.nanoTime();
                        timesRandomPivot.add(endTime - startTime);

                        // Quicksort com Mediana de Três
                        QuicksortMedianOfThree quicksortMedian = new QuicksortMedianOfThree(size);
                        quicksortMedian.entry(array.clone());
                        startTime = System.nanoTime();
                        quicksortMedian.sort();
                        endTime = System.nanoTime();
                        timesMedianOfThree.add(endTime - startTime);
                    }

                    // Escreve os tempos no CSV
                    for (int i = 0; i < 10; i++) {
                        writer.append(size + "," + typeStr + "," +
                                timesFirstPivot.get(i) + "," +
                                timesLastPivot.get(i) + "," +
                                timesRandomPivot.get(i) + "," +
                                timesMedianOfThree.get(i) + "\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
