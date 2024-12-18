/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 */

public class LAB01Q01AquecimentoRecursivo {
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
        String[] entry = new String[1000]; // Array de string para armazenar as palavras
        int entryNum = 0; // Posições

        //Leitura da entrada padrao
        do {
            entry[entryNum] = MyIO.readLine();
        } while (end(entry[entryNum++]) == false);
        entryNum--;   //Desconsiderar ultima linha contendo a palavra FIM

        // Para cada linha de entrada, gerando uma saída contendo o número de letras maiúsculas da entrada
        for (int i = 0; i < entryNum; i++) {
            System.out.println(countCaps(entry[i], 0));
        }

    }
}
