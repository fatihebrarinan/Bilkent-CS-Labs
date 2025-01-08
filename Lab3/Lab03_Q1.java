package Lab3;

import java.util.Scanner;

class Lab03_Q1
{
    public static void main(String[] args)
    {
        double weight;
        double height;
        double BMI;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your weight in kilograms: ");
        weight = scanner.nextDouble();
        System.out.print("Enter your height in meters: ");
        height = scanner.nextDouble();
        scanner.close();

        BMI = weight / (height * height);

        System.out.printf("Your BMI is: %.2f\n", BMI);

        if (BMI < 18.5)
        {
            System.out.println("You are underweight.");
        } else if (BMI <= 24.9)
        {
            System.out.println("You are of normal weight.");
        } else if (BMI <= 29.9)
        {
            System.out.println("You are overweight.");
        } else
        {
            System.out.println("Your BMI indicates that you are above the recommended range.");
        }

    }
}