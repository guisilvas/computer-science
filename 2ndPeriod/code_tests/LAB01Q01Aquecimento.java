import java.util.Scanner;

public class LAB01Q01Aquecimento {
    // Função que verifica se a letra é maiúscula
    public static boolean verifyCaps(char letter) {
        return (letter >= 'A' && letter <= 'Z');
    }

    // Função que verifica se a palavra é 'FIM'
    public static boolean end(String text) {
        return text.equals("FIM");
    }

    // Função que conta o número de letras maiúsculas
    public static int countCaps(String w, int pos) {
        int ans = 0;
        if (pos < w.length()) {
            if (verifyCaps(w.charAt(pos))) {
                ans = 1 + countCaps(w, pos + 1);
            } else {
                ans = countCaps(w, pos + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] word = new String[256];  // Array para armazenar as linhas
        int entry = 0;

        // Lendo todas as linhas
        while (true) {
            String line = scanner.nextLine();
            if (end(line)) { //Encontrou a palavra 'FIM' pois a função end retornou true
                break;
            }
            word[entry++] = line; // Armazena a linha no array
        }

        // Para cada linha de entrada, gerando uma saída contendo o número de letras maiúsculas da entrada
        for (int i = 0; i < entry; i++) {
            System.out.println(countCaps(word[i], 0));
        }

        scanner.close();
    }
}
