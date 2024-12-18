/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA TP01Q06 - Is
 * Data: 25/08/2024
 */
public class Is {
    // Função que verifica se a string é composta apenas por vogais
    public static boolean isVowel(String s) {
        char[] vowel = {'A', 'E', 'I', 'O', 'U'};
        int count = 0;
        String str = s.toUpperCase();
        for(int i = 0; i < str.length(); i++) {
            for(int j = 0; j < 5; j++) {
                if(str.charAt(i) == vowel[j]) {
                    count++;
                }
            }
        }
        if(count < str.length()) {
            return false;
        } else {
            return true;
        }
    }

    // Função que verifica se a string é composta apenas por consoante
    public static boolean isConsonant(String s) {
        char[] consonant = {
            'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'
        };
        int count = 0;
        String str = s.toUpperCase();
        for(int i = 0; i < str.length(); i++) {
            for(int j = 0; j < 21; j++) {
                if(str.charAt(i) == consonant[j]) {
                    count++;
                }
            }
        }
        if(count < str.length()) {
            return false;
        } else {
            return true;
        }
    }

    // Função que verifica se a string é um número inteiro
    public static boolean isInt(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) > 47 && s.charAt(i) < 58) {
                count++;
            }
        }
        if(count < s.length()) {
            return false;
        } else {
            return true;
        }
    }

    // Função que verifica se a string é um número decimal
    public static boolean isFloat(String s) {
        int count = 0, marker = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) > 47 && s.charAt(i) < 58) {
                count++;
            } else if(s.charAt(i) == '.' || s.charAt(i) == ',') {
                marker++;
            }
        }
        if(marker <= 1 && (count == s.length() || count == s.length() - 1)) {
            return true;
        } else {
            return false;
        }
    }

    // Função que preenche e printa uma string com o resultado das verificações
    public static void whatIs(String s) {
        String[] result = {"", "", "", ""};
        result[0] = isVowel(s) ? "SIM" : "NAO";
        result[1] = isConsonant(s) ? "SIM" : "NAO";
        result[2] = isInt(s) ? "SIM" : "NAO";
        result[3] = isFloat(s) ? "SIM" : "NAO";
        for (String i : result) {
            MyIO.print(i + " ");
        }
        MyIO.print("\n");
    }
    public static void main(String[] args) {
        String str = new String();
        boolean loop = true;
        while(loop) {
            str = MyIO.readLine();
            if(str.equals("FIM")) {
                loop = false;
            } else {
                whatIs(str);
            }
        }
    }
}
