package Lab5;

import java.util.Scanner;

public class Lab05_Q1
{
    public static void main(String[] args)
    {
        final String ONE_STAR = "*";
        final String FIVE_STAR = " *****";

        int input;

        Scanner scanner = new Scanner(System.in);

        // ask for input until a positive integer is given and it is less 30.
        // if valid input is given, print CS on that height with stars. The width is
        // fixed 5 stars.

        // One way of doing: have fixed strings
        // * -> 1
        // ***** -> 2
        // how many 1 is determined by input both on c and s.
        // 2 is fixed 2 times at c and 3 times at s.
        input = 0;
        System.out.print("Please enter a height: ");

        while (input < 5 || input > 30)
        {

            while (!scanner.hasNextInt())
            {
                scanner.next();
                System.out.println("Input can not be an integer.");
                System.out.print("Please enter a height: ");
            }
            input = scanner.nextInt();
            if (input < 5)
            {
                System.out.println("Input can not be smaller than 5.");
                System.out.print("Please enter a height: ");
            }
            if (input > 30)
            {
                System.out.println("Input can not be bigger than 30.");
                System.out.print("Please enter a height: ");
            }
        }

        System.out.println(FIVE_STAR + "\t" + FIVE_STAR);
        for (int i = 0; i < input / 2 - 1; i++)
        {
            System.out.println(ONE_STAR + "\t" + ONE_STAR);
        }
        System.out.println(ONE_STAR + "\t" + FIVE_STAR);
        if (input % 2 == 0)
        {
            input -= 1;
        }
        for (int i = 0; i < input / 2 - 1; i++)
        {

            System.out.println(ONE_STAR + "\t      " + ONE_STAR);
        }
        System.out.println(FIVE_STAR + "\t" + FIVE_STAR);

        scanner.close();

    }
}
