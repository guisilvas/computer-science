/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA TP01Q12 - RECURSIVO - Ciframento de César
 * Data: 25/08/2024
 */
import java.util.Scanner;

public class CesarCipherRecursive {
    // Chama a função que realiza a cifra
    public static String cipher(String str) {
        return cipher(0, str.length() - 1, str);
    }

    // Função para cifrar caracteres
    public static String cipher(int i, int f, String str) {
        char[] s = str.toCharArray();
        if(i >= f) {
            String result = new String(s);
            return result;
        }
        s[i] = (char)(s[i] + 3);
        return cipher(i + i, f - 1, str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean loop = true;

        // Loop para a leitura e print das palavras
        while (loop) {
            str = MyIO.readLine();
            if (str.length() == 3 && str.equals("FIM")) {
                loop = false;
            } else {
                MyIO.println(cipher(str));
            }
        }
        sc.close();
    }
}
