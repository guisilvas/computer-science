/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA TP01Q03 - Ciframento de César
 * Data: 22/08/2024
 */
import java.util.Scanner;

public class CesarCipher {
    // Função para alterar caracteres
    /**
     * @param str
     * @return
     */
    public static String cipher(String str) {
        char[] s = str.toCharArray();
        for(int i = 0; i < str.length(); i++) {
            s[i] = (char)(s[i] + 3);
        }
        String result = new String(s);
        return result;
    }
    /**
     * @param args
     */
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
