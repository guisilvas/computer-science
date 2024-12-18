/* 
 * Nome: Guilherme Soares Silva
 * Matrícula: 863485
 * Atividade: JAVA TP01Q04 - Alteração Aleatória
 * Data: 29/08/2024
 */
public class IsRecursive {
    // Chaks if string is a vowel
    public static boolean isVowel(String s) {
        String str = s.toLowerCase();
        return isVowel(str, 0);
    }
    public static boolean isVowel(String s, int pos) {
        boolean answer;
        if(pos == s.length()) {
            answer = true;
        } else if(!(vowel(s.charAt(pos), 0))) {
            answer = false;
        } else {
            answer = isVowel(s, pos + 1);
        }
        return answer;
    }
    // Checks if char at a specific position is a vowel
    public static boolean vowel(char ch, int pos) {
        boolean answer;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        if(pos == 5) {
            answer = false;
        } else if (ch == vowels[pos]) {
            answer = true;
        } else {
            answer = vowel(ch, pos + 1);
        }
        return answer;
    }

    // Checks if string is a consonant array
    public static boolean isConsonant(String s) {
        String str = s.toLowerCase();
        return isConsonant(str, 0);
    }
    public static boolean isConsonant(String s, int pos) {
        boolean answer;
        if(pos == s.length()) {
            answer = true;
        } else if(!(consonant(s.charAt(pos), 0))) {
            answer = false;
        } else {
            answer = isConsonant(s, pos + 1);
        }
        return answer;
    }
    // Checks if char at a specific position is a consonant
    public static boolean consonant(char ch, int pos) {
        boolean answer;
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
        if(pos == 21) {
            answer = false;
        } else if(ch == consonants[pos]) {
            answer = true;
        } else {
            answer = consonant(ch, pos + 1);
        }
        return answer;
    }

    // Checks if string is a integer number
    public static boolean isInt(String s) {
        return isInt(s, 0);
    }
    public static boolean isInt(String s, int pos) {
        boolean answer;
        if(pos == s.length()) {
            answer = true;
        } else if(!(s.charAt(pos) > 47 && s.charAt(pos) < 58)) {
            answer = false;
        } else {
            answer = isInt(s, pos + 1);
        }
        return answer;
    }

    // Checks if string is a float number
    public static boolean isFloat(String s) {
        return isFloat(s, 0, 0);
    }
    public static boolean isFloat(String s, int pos, int marker) {
        boolean answer;
        if (pos == s.length()) {
            answer = true;
        } else if ((marker > 1) || ((s.charAt(pos) < 48 || s.charAt(pos) > 57) && s.charAt(pos) != '.' && s.charAt(pos) != ',')){
            answer = false;
        } else if(s.charAt(pos) == '.' || s.charAt(pos) == ',') {
            answer = isFloat(s, pos + 1, marker + 1);
        } else {
            answer = isFloat(s, pos + 1, marker);
        }
        return answer;
    }

    // Call the anothers functions
    public static void whatIs(String s) {
        String[] result = {isVowel(s) ? "SIM" : "NAO", isConsonant(s) ? "SIM" : "NAO", isInt(s) ? "SIM" : "NAO", isFloat(s) ? "SIM" : "NAO"};
        MyIO.print(result[0] + " ");
        MyIO.print(result[1] + " ");
        MyIO.print(result[2] + " ");
        MyIO.println(result[3]);
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