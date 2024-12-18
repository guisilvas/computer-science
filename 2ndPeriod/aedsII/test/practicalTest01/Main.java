// Importando a biblioteca de entradas
import java.util.Scanner;

class Main
{
    // Método verificador
    public static boolean Verify(String alpha, String msg, int k, int n)
    {
        int ver = 0; // Verifica qnts caracteres pertencem ao alfabeto
        for(int i=0; i<n; i++) // Percorre a mensagem
        {
            for(int j=0; j<k; j++) // Percorre o alfabeto
            {
                if(msg.charAt(i) == alpha.charAt(j)) // Verifica se o carcater da msg está em determinada posição do alfabeto
                {
                    j = k; // Para o loop
                    ver++; // Icrementa o verificador
                }
            }
        }
        if(ver == n) // Se o verificador for igual ao tamanho da mensagem retorna true, caso contrário false
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void main(String[] args)
    {
        // Definindo leitor de entradas e variáveis
        Scanner sc = new Scanner(System.in);
        int k, n;
        String alpha = new String();
        String msg = new String();

        // Lendo entradas
        k = sc.nextInt();
        n = sc.nextInt();
        alpha = sc.next();
        msg = sc.next();

        // Printando o resultado
        if(Verify(alpha, msg, k, n))
        {
            System.out.println("S");
        }
        else
        {
            System.out.println("N");
        }
        // Fechando o leitor de entradas
        sc.close();
    }
}