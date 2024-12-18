/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: JAVA TP01Q01 - Palindromo
 * Data: 21/08/2024
 */
import java.util.Scanner;

class Palindrome {
    // Função que verifica se a palavra é "FIM"
    public static boolean IsEnd(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    // Função recursiva que verifica se a palavra é um palíndromo
    public static boolean IsPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str, result;
        boolean loop = true;

        // Loop para a leitura e print das palavras
        while (loop) {
            str = sc.nextLine();
            if (IsEnd(str)) {
                loop = false;
            } else {
                MyIO.println(result = IsPalindrome(str) ? "SIM" : "NAO");
            }
        }
        sc.close();
    }
}