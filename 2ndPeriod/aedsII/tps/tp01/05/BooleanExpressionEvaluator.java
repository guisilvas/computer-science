/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Disciplina: Algoritmos e Estruturas de Dados II
 * JAVA TP01Q05 - Algebra Boleana
 * Data: 30/08/2024
 */
import java.util.Stack;

public class BooleanExpressionEvaluator {
    public static void main(String[] args) {
        boolean loop = true;
        String str = new String();
        while (loop) {
            str = MyIO.readLine().trim();
            String[] parts = str.split(" ");
            int numInputs = Integer.parseInt(parts[0]);

            if (parts.length < numInputs + 2) {
                System.out.println("NÃO");
                continue;
            }

            int[] inputs = new int[numInputs];
            for (int i = 0; i < numInputs; i++) {
                inputs[i] = Integer.parseInt(parts[i + 1]);
            }

            String expression = String.join(" ", java.util.Arrays.copyOfRange(parts, numInputs + 1, parts.length)).trim();
            boolean result = evaluateExpression(expression, inputs);

            System.out.println(result ? "1" : "0");
        }
    }
    // Método que realiza as trocas de palavras por símbolos
    private static boolean evaluateExpression(String expression, int[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            expression = expression.replace(Character.toString((char) ('A' + i)), Integer.toString(inputs[i]));
        }

        expression = expression.replace("and", "&&")
                               .replace("or", "||")
                               .replace("not", "!")
                               .replace("(", " ( ")
                               .replace(")", " ) ")
                               .replaceAll("\\s+", " ");

        Stack<Boolean> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (token.equals("0") || token.equals("1")) {
                values.push(token.equals("1"));
            } else if (token.equals("!")) {
                operators.push(token);
            } else if (token.equals("&&") || token.equals("||")) {
                while (!operators.isEmpty() && precedence(token) <= precedence(operators.peek())) {
                    applyOperator(operators.pop(), values);
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    applyOperator(operators.pop(), values);
                }
                operators.pop(); 
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(operators.pop(), values);
        }

        if (values.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        return values.pop();
    }

    private static int precedence(String operator) {
        if (operator.equals("!")) return 3;
        if (operator.equals("&&")) return 2;
        if (operator.equals("||")) return 1;
        return 0;
    }
    // Método que aplica a relaçao booleana
    private static void applyOperator(String operator, Stack<Boolean> values) {
        if (operator.equals("!")) {
            if (values.isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            Boolean value = values.pop();
            values.push(!value);
        } else {
            if (values.size() < 2) {
                throw new IllegalStateException("Not enough values in stack for operator: " + operator);
            }
            Boolean right = values.pop();
            Boolean left = values.pop();
            if (operator.equals("&&")) {
                values.push(left && right);
            } else if (operator.equals("||")) {
                values.push(left || right);
            }
        }
    }
}
