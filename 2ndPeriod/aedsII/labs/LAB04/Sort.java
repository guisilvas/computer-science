/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: JAVA LAB04Q01 - Sort sort sort
 * Data: 08/09/2024
 */
import java.util.Arrays;
import java.util.Comparator;

public class Sort {

    // Ordena os valores em função do módulo
    public static int[] sort(int[] array, int size, int mod) {
        Integer[] integerArray = Arrays.stream(array).boxed().toArray(Integer[]::new);

        Arrays.sort(integerArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int modA = a % mod;
                int modB = b % mod;
                if (modA < 0) modA += mod;
                if (modB < 0) modB += mod;
                
                int modCompare = Integer.compare(modA, modB);
                if (modCompare != 0) return modCompare;
                
                int parityCompare = Integer.compare(b % 2, a % 2);
                if (parityCompare != 0) return parityCompare;
                
                if (a % 2 == 0 && b % 2 == 0) {
                    return Integer.compare(a, b);
                } else if (a % 2 != 0 && b % 2 != 0) {
                    return Integer.compare(b, a);
                }
                
                return 0;
            }
        });

        return Arrays.stream(integerArray).mapToInt(Integer::intValue).toArray();
    }

    // Printa o valores em ordem do módulo
    public static void print(int[] array, int size, int mod) {
        MyIO.println(size + " " + mod);
        for (int num : array) {
            MyIO.println(num);
        }
    }

    public static void main(String[] args) {
        int N, M;
        N = MyIO.readInt();
        M = MyIO.readInt();
        while (N != 0 && M != 0) {
            int[] array = new int[N];
            for (int i = 0; i < N; i++) {
                array[i] = MyIO.readInt();
            }
            array = sort(array, N, M);
            print(array, N, M);
            N = MyIO.readInt();
            M = MyIO.readInt();
        }
        if(N == 0 && M == 0) {
            MyIO.println(N + " " + M);
        }
    }
}
