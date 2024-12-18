package aedsII.clike.operators;
import java.util.Scanner;

class operators {
    public static void main(String[] args){
        int x=10, y=3;
        int sum, sub, mult, remainder, quoc;
        float div; 
        Scanner scanner = new Scanner(System.in);

        sum = x+y;
        System.out.println("x + y = " + sum);
        sub = x-y;
        System.out.println("x - y = " + sub);
        div = (float)x/y;
        System.out.println("x / y = " + div);
        mult = x*y;
        System.out.println("x * y = " + mult);
        remainder = x%y;
        System.out.println("x % y = " + remainder);
        quoc = x/y;
        System.out.println("x / y = " + quoc);
        int inc = x+1;
        System.out.println(x + "++ = " + inc);
        int dec = y-1;
        System.out.println(y + "-- = " + dec);
    }
}
