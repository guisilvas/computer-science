using System;

class MainClass
{
    public static void Main(string[] args)
    {
        //c# have native string
        string name;
        int age;
        float height;

        Console.Write("Enter your name: ");
        name = Console.ReadLine(); //default string
        Console.Write("Enter your age: ");
        age = int.Parse(Console.ReadLine()); //a conversion is necessary
        Console.Write("Enter your height: ");
        height = float.Parse(Console.ReadLine());

        Console.WriteLine("\n"+name+" is "+age+" years old and is "+height+"m tall");

    }
}