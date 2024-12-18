import java.util.Stack;

public class BooleanRecursive {

    public static void main(String[] args) {
        boolean loop = true;
        while (loop) {
            String str = MyIO.readLine().trim();
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
            try {
                boolean result = evaluateExpression(expression, inputs);
                System.out.println(result ? "1" : "0");
            } catch (IllegalArgumentException e) {
                System.out.println("NÃO");
            }
        }
    }

    private static boolean evaluateExpression(String expression, int[] inputs) {
        // Substituir variáveis na expressão
        for (int i = 0; i < inputs.length; i++) {
            expression = expression.replace(Character.toString((char) ('A' + i)), Integer.toString(inputs[i]));
        }

        // Substituir operadores lógicos por seus equivalentes Java
        expression = expression.replace("and", "&&")
                               .replace("or", "||")
                               .replace("not", "!")
                               .replaceAll("\\s*\\(\\s*", " ( ")
                               .replaceAll("\\s*\\)\\s*", " ) ")
                               .replaceAll("\\s+", " ");

        // Avaliar a expressão
        return evaluate(expression);
    }

    private static boolean evaluate(String expression) {
        // Remove espaços desnecessários
        expression = expression.trim();
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }

        // Se a expressão é uma única variável (0 ou 1), retorne o valor
        if (expression.equals("0")) return false;
        if (expression.equals("1")) return true;

        // Verificar se a expressão está entre parênteses
        if (expression.startsWith("(") && expression.endsWith(")")) {
            return evaluate(expression.substring(1, expression.length() - 1).trim());
        }

        // Encontrar a posição do operador mais externo
        int[] operatorPosition = findMainOperatorPosition(expression);
        if (operatorPosition[0] == -1) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        String leftExpr = expression.substring(0, operatorPosition[0]).trim();
        String operator = expression.substring(operatorPosition[0], operatorPosition[1] + 1).trim();
        String rightExpr = expression.substring(operatorPosition[1] + 1).trim();

        // Avaliar subexpressões e aplicar operador
        boolean left = evaluate(leftExpr);
        boolean right = evaluate(rightExpr);

        switch (operator) {
            case "&&":
                return left && right;
            case "||":
                return left || right;
            case "!":
                return !left;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private static int[] findMainOperatorPosition(String expression) {
        int parens = 0;
        int mainOpIndex = -1;
        int opIndex = -1;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                parens++;
            } else if (ch == ')') {
                parens--;
            } else if (parens == 0 && (ch == '&' || ch == '|' || ch == '!')) {
                if (ch == '!') {
                    mainOpIndex = i;
                    opIndex = i;
                    break;
                } else if (ch == '&' && i + 1 < expression.length() && expression.charAt(i + 1) == '&') {
                    mainOpIndex = i;
                    opIndex = i + 1;
                    break;
                } else if (ch == '|' && i + 1 < expression.length() && expression.charAt(i + 1) == '|') {
                    mainOpIndex = i;
                    opIndex = i + 1;
                    break;
                }
            }
        }

        return new int[]{mainOpIndex, opIndex};
    }
}
