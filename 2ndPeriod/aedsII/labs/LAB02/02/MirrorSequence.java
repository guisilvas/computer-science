/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA LAB02Q02 - Sequencia Espelho
 * Data: 24/08/2024
 */
import java.util.Scanner;

public class MirrorSequence {
    // Função que cria uma StringBuilder com a sequência de números e seu inverso
    public static StringBuilder Mirror(int a, int b) {
        StringBuilder sb = new StringBuilder();
        for(int i = a; i <= b; i++) {
            sb.append(i);
        }
        StringBuilder result = new StringBuilder(sb);
        sb.reverse();
        return result.append(sb);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        while(sc.hasNext()) {
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.println(Mirror(x, y));
        }
        sc.close();
    }
}
