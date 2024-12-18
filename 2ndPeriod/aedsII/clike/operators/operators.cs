using System;

class MainClass{
    public static void Main(string[] args){
        int x=6, y=5;
        int sum, sub, mult, remainder, quoc, inc, dec;
        float div;

        sum = x+y;
        Console.WriteLine(x + " + " + y + " = " + sum);
        sub = x-y;
        Console.WriteLine(x + " - " + y + " = " + sub);
        mult = x*y;
        Console.WriteLine(x + " * " + y + " = " + mult);
        div = (float)x/y;
        Console.WriteLine(x + " / " + y + " = " + div);
        quoc = x/y;
        Console.WriteLine(x + " / " + y + " = " + quoc);
        remainder = x%y;
        Console.WriteLine(x + " % " + y + " = " + remainder);
        inc = ++x;
        Console.WriteLine(x-1 + "++ = " + inc);
        dec = --y;
        Console.WriteLine(y+1 + "-- = " + dec);
    }
}