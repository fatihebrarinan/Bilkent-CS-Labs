package Lab4;

import java.util.Scanner;

/**
 * Lab04_Q4REV
 */
public class Lab04_Q4
{

    public static void main(String[] args)
    {
        String continueCalculating;
        String input;
        String primeNumbers;

        int value;
        int digit;
        int sumOfPrimeNumbers;
        int countOfPrimeNumbers;
        double division;

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning)
        {
            primeNumbers = "";
            sumOfPrimeNumbers = 0;
            countOfPrimeNumbers = 0;

            // Enter a positive number
            System.out.print("Enter a positive number: ");
            input = scanner.nextLine();
            value = Integer.valueOf(input);

            // Loop through every number smaller than input.
            digit = 2;
            while (digit < value)
            {
                boolean isPrime = true;
                // Find the ones that are only dividible with 1 and itself.
                division = 2.0;
                while (division < digit && isPrime)
                {
                    if (digit % division == 0)
                    {
                        isPrime = false;
                    }
                    division++;

                }
                if (isPrime)
                {
                    primeNumbers += digit + " ";
                    sumOfPrimeNumbers += digit;
                    countOfPrimeNumbers++;
                }

                digit++;
            }
            System.out.printf("Prime numbers: %s\nSum of primes: %s\nCount of primes: %s\n", primeNumbers,
                    sumOfPrimeNumbers, countOfPrimeNumbers);

            // Ask if the user wants to run again.
            System.out.print("Do you want to calculate again? (yes/no): ");
            continueCalculating = scanner.nextLine();

            if (continueCalculating.equalsIgnoreCase("no"))
            {
                isRunning = false;
                System.out.println("Program finished.");
            }
        }
        scanner.close();
    }
}