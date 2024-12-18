using System;

class Main{
    static void Main(string[] args){
        int x;
        Console.WriteLine("Enter a number: ");
        x = Console.ReadLine();

        if(x == 0){
            Console.WriteLine(x + " is void");
        } else if(x<0) {
            Console.WriteLine(x + " is negative");
        } else {
            Console.WriteLine(x + " is positive");
        }
    }
}