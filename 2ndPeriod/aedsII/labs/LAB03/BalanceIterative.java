/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * Atividade: JAVA LAB03Q01 - Balanço Parênteses
 * Data: 26/08/2024
 */
class BalanceIterative {
    // Chama a função balance passando a posição a ser analisada
    public static boolean balance(String s) {
        return balance(s.length() - 1, s);
    }
    
    // Função que verifica se a quantidade de parênteses está correta matematicamente
    public static boolean balance(int i, String s) {
        int open = 0, close = 0, over = 0; // ( -> open , ) -> close
        while(i >= 0) {
            if (s.charAt(i) == '(' && close > 0 && close > open) {
                open++;
            } else if (s.charAt(i) == ')') {
                close++;
            } else if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                over++;
            }
            i--;
        }
        return (open == close && over == 0) ? true : false;
    }

    public static void main(String[] args) {
        String str, result = new String();
        boolean loop = true;
        while(loop) {
            str = MyIO.readLine();
            if(str.equals("FIM")) {
                loop = false;
            } else {
                result = (balance(str)) ? "correto" : "incorreto";
                MyIO.println(result);
            }
        }
    }
}

