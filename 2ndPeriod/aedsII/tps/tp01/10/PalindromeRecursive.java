/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: JAVA TP01Q10 - RECURSIVO - Palíndromo
 * Data: 25/08/2024
 */
import java.util.Scanner;

class PalindromeRecursive {
    // Verifica se a palavra é 'FIM'
    public static boolean isEnd(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    // Chama a função verificadora com a posição inicial e final da string
    public static boolean isPalindrome(String s) {
        int i = 0, f = s.length() - 1;
        return isPalindrome(i, f, s);
    }
    // Verifica se a palavra é um palíndromo
    public static boolean isPalindrome(int i, int f, String s) {
        if (i >= f) {
            return true;
        }
        if (s.charAt(i) != s.charAt(f)) {
            return false;
        }
        return isPalindrome(i + 1, f - 1, s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str, result;
        boolean loop = true;

        // Loop para a leitura e print das palavras
        while (loop) {
            str = sc.nextLine();
            if (isEnd(str)) {
                loop = false;
            } else {
                result = isPalindrome(str) ? "SIM" : "NAO";
                System.out.println(result);
            }
        }
        sc.close();
    }
}