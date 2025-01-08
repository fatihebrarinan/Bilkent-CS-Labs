package Lab4;

import java.util.Scanner;

public class Lab04_Q2
{
    public static void main(String[] args)
    {
        int sum, input, lenght, remainder;
        boolean isOn = true;
        Scanner scanner = new Scanner(System.in);

        while (isOn)
        {
            System.out.print("Enter a positive number: ");
            input = scanner.nextInt();
            sum = 0;
            lenght = (int) Math.log10(input) + 1;
            remainder = input;

            if (input == 0)
            {
                isOn = false;
                System.out.println("Program finished.");

            } else if (input > 0)
            {
                // Sum of digits.
                while (lenght > 0)
                {
                    sum += remainder / Math.pow(10, lenght - 1);
                    remainder = remainder % (int) Math.pow(10, lenght - 1);
                    lenght--;
                }
                System.out.printf("Sum of digits: %d\n", sum);

            } else
            {
                System.out.println("Wrong input.");
            }
        }
        scanner.close();
    }
}
