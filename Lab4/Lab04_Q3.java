package Lab4;

import java.util.Scanner;

public class Lab04_Q3
{
    public static void main(String[] args)
    {
        int input, lenght, sumOfDigits, numOfDigits, remainder, valueOfDigit, reversedOutput;
        boolean isOn = true;
        Scanner scanner = new Scanner(System.in);

        while (isOn)
        {
            System.out.print("Enter a positive number: ");
            input = scanner.nextInt();
            lenght = (int) Math.log10(input) + 1;
            sumOfDigits = 0;
            numOfDigits = lenght;
            remainder = input;
            reversedOutput = 0;

            if (input <= 0)
            {
                isOn = false;
                System.out.println("Input cannot be zero or negative.\nProgram finished.");

            } else if (lenght > 8)
            {
                isOn = false;
                System.out.println("Number exceeds 8 digits.\nProgram finished.");
            } else
            {
                while (lenght > 0)
                {
                    // Getting every digit's value and adding it to the sum.
                    valueOfDigit = remainder / (int) Math.pow(10, lenght - 1);
                    remainder = remainder % (int) Math.pow(10, lenght - 1);
                    sumOfDigits += valueOfDigit;

                    reversedOutput += Math.pow(10, numOfDigits - lenght) * valueOfDigit;

                    lenght--;

                }
                System.out.printf("Reversed number : %d\nSum of digits: %d\n", reversedOutput, sumOfDigits);

            }
        }
        scanner.close();

    }
}
