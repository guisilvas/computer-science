//Declarando a classe principal
class Lab01 {
    static int caractere(String name) {
        int answer = 0;
        for(int i=0; i<name.length(); i++) {
            if(name.isUpperCase()) {
                answer++;
            }
        }
        return answer;
    }
    //Declarando a classe main
    public static void main(String[] args) {
        String name = "GuilheRmE";
        caractere(name);
    }
}