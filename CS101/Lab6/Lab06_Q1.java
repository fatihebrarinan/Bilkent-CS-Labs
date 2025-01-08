package Lab6;

import java.util.Scanner;

public class Lab06_Q1
{

    public static void main(String[] args)
    {
        // Greatest common divisor and least common multiple part.
        int number1;
        int number2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        number1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        number2 = scanner.nextInt();
        if (isRelativelyPrime(number2, number1))
            System.out.printf("%s and %s are relatively prime.\n", number1, number2);
        else
            System.out.printf("%s and %s are not relatively prime.\n", number1, number2);

        System.out.printf("Greatest common divisor of %s and %s is %s\n", number1, number2,
                greatestCommonDivisor(number2, number1));
        System.out.printf("Least common multiple of %s and %s is %s\n", number1, number2,
                leastCommonMultiple(number2, number1));

        // Binary part.
        int binaryNumber;
        System.out.print("\nEnter a decimal number: ");
        binaryNumber = scanner.nextInt();

        System.out.printf("Binary representation of %s is %s\n", binaryNumber, intToBinary(binaryNumber));
        System.out.printf("Binary reversal of %s is %s", binaryNumber, binaryReversal(binaryNumber));

        scanner.close();
    }

    public static int greatestCommonDivisor(int num1, int num2)
    {
        int greatestCommonDivider = 0;
        // Find the largest number that divides two numbers.
        for (int i = 1; i < Math.max(num1, num2) + 1; i++)
        {
            if (num1 % i == 0 && num2 % i == 0)
                greatestCommonDivider = i;
        }
        return greatestCommonDivider;
    }

    public static int leastCommonMultiple(int num1, int num2)
    {
        int leastCommonMultiple;
        // Find the smallest number that is multiple of two numbers.
        // GCD * LCM = num1 * num2
        leastCommonMultiple = num1 * num2 / greatestCommonDivisor(num1, num2);
        return leastCommonMultiple;
    }

    public static boolean isRelativelyPrime(int num1, int num2)
    {
        if (greatestCommonDivisor(num1, num2) == 1)
            return true;
        else
            return false;
    }

    public static long intToBinary(int binaryNum)
    {
        long binaryRepresentation = 0;
        int tenMultiplier = 1;
        // Find the binary representation of binaryNum.
        while (binaryNum > 0)
        {
            binaryRepresentation += (binaryNum % 2) * tenMultiplier;
            binaryNum = binaryNum / 2;
            tenMultiplier *= 10;
        }
        return binaryRepresentation;
    }

    public static long binaryReversal(int binaryNum)
    {
        long binaryReverse = 0;
        long intToBinary = intToBinary(binaryNum);

        // Reverse the binary representation.
        while (intToBinary > 0)
        {
            binaryReverse *= 10;
            binaryReverse += intToBinary % 10;
            intToBinary = intToBinary / 10;
        }

        return binaryReverse;
    }

}