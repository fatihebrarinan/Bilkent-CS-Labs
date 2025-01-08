package Lab4;

import java.util.Scanner;

class Lab04_Q3REV
{

    public static void main(String[] args)
    {
        int input, lenght, sumOfDigits, numOfDigits, remainder, valueOfDigit, reversedOutput, productOfDigits;
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
            productOfDigits = 1;

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

                    if (valueOfDigit != 0)
                    {
                        productOfDigits *= valueOfDigit;
                    }

                    remainder = remainder % (int) Math.pow(10, lenght - 1);
                    sumOfDigits += valueOfDigit;

                    // Reversing the number.
                    reversedOutput += Math.pow(10, numOfDigits - lenght) * valueOfDigit;

                    lenght--;

                }
                System.out.printf("Reversed number : %d\nSum of digits: %d\nProduct of non-zero digits: %d\n",
                        reversedOutput, sumOfDigits, productOfDigits);

            }
        }
        scanner.close();

    }

}