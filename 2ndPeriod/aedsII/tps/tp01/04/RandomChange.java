/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA TP01Q04 - Alteração Aleatória
 * Data: 21/08/2024
 */
import java.util.Random;

public class RandomChange {
    // Função que altera a letra gerada em x por y na string
    public static String Randomize(String s) {
        Random generator = new Random();
        generator.setSeed(4);
        int x = ((char)'a' + (Math.abs(generator.nextInt()) % 26));
        int y = ((char)'a' + (Math.abs(generator.nextInt()) % 26));
        char[] charS = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == (char)x) {
                charS[i] = (char)y;
            }
        }
        String result = new String(charS);
        return result;
    }
    public static void main(String[] args) {
        String str;
        boolean loop = true;
        
        while (loop) {
            str = MyIO.readLine();
            if (str.equals("FIM")) {
                loop = false;
            } else {
                MyIO.println(Randomize(str));
            }
        }
    }
}