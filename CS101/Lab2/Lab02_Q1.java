package Lab2;

import java.util.Scanner;

public class Lab02_Q1
{
    public static void main(String[] args)
    {
        double volume;
        double radius;
        double surfaceArea;
        double PI = Math.PI;

        System.out.print("Enter volume of sphere: ");
        Scanner scanner = new Scanner(System.in);

        volume = scanner.nextDouble();
        scanner.nextLine();

        radius = Math.pow((3 * volume) / (4 * PI), 1.0 / 3);

        surfaceArea = 4 * PI * radius * radius;

        System.out.printf("The radius of the sphere is: %16.1f\n", radius);
        System.out.printf("The surface area of the sphere is: %10.1f\n", surfaceArea);

        scanner.close();

    }
}