public class isConsoante1 {

    boolean isConsoante(String s, int n){
        boolean resp= true;
        if (n != s.length()){
        if (s.charAt(n)<'0' || s.charAt(n)>'9'){
        if (isVogal(s.charAt(n)) == true){
        resp= false;
        } else {
        resp=isConsoante(s, n + 1);
        }
        } else {
        resp=false;
        }
        }
        return resp;
    }
    
    public static void  main(String[] args) {
        
    }
}
