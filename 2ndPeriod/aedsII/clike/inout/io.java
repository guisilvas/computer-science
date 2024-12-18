import java.util.Scanner;

class io
{
    public static void main(String[] args)
    {
        String name;
        int age;
        float height;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        System.out.print("Enter your age: ");
        age = scanner.nextInt();
        System.out.print("Enter your height: ");
        height = scanner.nextFloat();

        System.out.println("\n" + name + " is " + age + " years old and is " + height + "m tall");
        scanner.close();
    }
}

