package Lab4;

import java.util.Scanner;

public class Lab04_Q1
{

    public static void main(String[] args)
    {

        int input, count = 1, sum = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        input = scanner.nextInt();

        while (input > 0)
        {
            while (count <= input)
            {
                sum *= count;
                count++;
            }
            System.out.printf("Factorial of %d is %d.\n", input, sum);

            count = 1;
            sum = 1;
            System.out.print("Enter a number: ");
            input = scanner.nextInt();
        }
        System.out.println("Program finished.");

        scanner.close();
    }
}
