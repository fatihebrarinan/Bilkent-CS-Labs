package Lab3;

import java.util.Scanner;

public class Lab03_Q2
{
    public static void main(String[] args)
    {
        double GPA;
        int creditHours;
        int extraHours;
        int honorsRecieved;
        double familyIncome;

        int scholarshipScore = 200;

        String reason = "";

        // Getting numbers and scholarshipScore

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student's GPA: ");
        GPA = scanner.nextDouble();

        System.out.print("Enter the total number of completed credit hours: ");
        creditHours = scanner.nextInt();
        if (creditHours <= 40)
        {
            scholarshipScore += creditHours * 15;
        } else
        {
            scholarshipScore += 600;
        }

        System.out.print("Enter the extracurricular activity hours: ");
        extraHours = scanner.nextInt();

        if (extraHours <= 30)
        {
            scholarshipScore += extraHours * 10;
        } else
        {
            scholarshipScore += 300;
        }

        System.out.print("Enter the number of academic honors received: ");
        honorsRecieved = scanner.nextInt();

        if (honorsRecieved <= 5)
        {
            scholarshipScore += honorsRecieved * 20;
        } else
        {
            scholarshipScore += 100;
        }

        System.out.print("Enter the total annual family income: ");
        familyIncome = scanner.nextDouble();

        scholarshipScore -= 5 * (familyIncome / 5000);

        scanner.close();

        System.out.printf("Total scholarship score: %d\n", scholarshipScore);

        // Seeing if eligible for scholarship
        if (GPA < 3.5)
        {
            reason += "\nGPA is below 3.5.";
        }
        if (creditHours < 30)
        {
            reason += "\nFewer than 30 completed credit hours.";
        }
        if (extraHours < 20)
        {
            reason += "\nFewer than 20 extracurricular activity hours.";
        }
        if (familyIncome > 75000)
        {
            reason += "\nFamily income exceeds $75000.";
        }
        if (scholarshipScore < 900)
        {
            reason += "\nTotal scholarship score is below 900 points.";
        }

        if (reason == "")
        {
            System.out.println("The student is eligible for the scholarship.");
        } else
        {
            System.out.printf("The student is not eligible for the scholarship. Reason:\n%s", reason);
        }
    }
}
