package Lab2;

import java.util.Scanner;

public class Lab02_Q1REV
{
    public static void main(String[] args)
    {
        double volume;
        double radius;
        double surfaceArea;
        double PI = Math.PI;

        System.out.print("Enter radius of the sphere: ");
        Scanner scanner = new Scanner(System.in);

        radius = scanner.nextDouble();
        scanner.nextLine();

        volume = 4 / 3 * PI * Math.pow(radius, 3);

        surfaceArea = 4 * PI * radius * radius;

        System.out.printf("The volume of the sphere is: %11.1f\n", volume);
        System.out.printf("The surface area of the sphere is: %5.1f\n", surfaceArea);

        scanner.close();

    }
}