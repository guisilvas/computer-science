/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * JAVA TP01Q05 - Algebra Boleana
 * Data: 30/08/2024
 */
import java.util.Stack;

public class BooleanAlgebra {
    public static void main(String[] args) {
        String str;
        boolean loop = true;
        while (loop) {
            str = MyIO.readLine();
            if (str.equals("0")) {
                loop = false;
            } else {
                // Divide a linha em partes: número de variáveis e o restante da string
                String[] parts = str.split(" ", 2);
                int n = Integer.parseInt(parts[0]);
                String rest = parts[1]; // O restante contém valores e expressão
                
                // Encontra o índice onde a expressão começa
                int index = rest.indexOf(' ') + 1;
                
                // Divide o restante em valores e expressão
                String[] valuesParts = rest.substring(0, index - 1).split(" ");
                boolean[] values = new boolean[n];
                for (int i = 0; i < n; i++) {
                    values[i] = valuesParts[i].equals("1");
                }
                String expression = rest.substring(index).trim(); // A expressão booleana
                
                boolean result = evaluateBooleanExpression(expression, values);
                System.out.println(result ? "SIM" : "NAO");
            }
        }
    }

    // Função que avalia a expressão booleana substituindo as variáveis pelos valores fornecidos
    private static boolean evaluateBooleanExpression(String expression, boolean[] values) {
        expression = replaceVariables(expression, values);
        return evaluateExpression(expression);
    }

    // Substitui variáveis na expressão booleana por valores booleanos
    private static String replaceVariables(String expression, boolean[] values) {
        char var = 'A';
        for (boolean value : values) {
            expression = expression.replace(Character.toString(var), value ? "true" : "false");
            var++;
        }
        return expression;
    }

    // Função que avalia a expressão booleana
    private static boolean evaluateExpression(String expression) {
        Stack<Boolean> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            switch (token) {
                case "true":
                    stack.push(true);
                    break;
                case "false":
                    stack.push(false);
                    break;
                case "and":
                    boolean b1 = stack.pop();
                    boolean b2 = stack.pop();
                    stack.push(b2 && b1); // Note que a ordem é b2 && b1 para respeitar a ordem dos operandos
                    break;
                case "or":
                    boolean b3 = stack.pop();
                    boolean b4 = stack.pop();
                    stack.push(b4 || b3); // Note que a ordem é b4 || b3 para respeitar a ordem dos operandos
                    break;
                case "not":
                    boolean b5 = stack.pop();
                    stack.push(!b5);
                    break;
                default:
                    // Handle other cases if needed
                    break;
            }
        }

        return stack.pop();
    }
}
