package Lab2;

import java.util.Scanner;

public class Lab02_Q2
{
    public static void main(String[] args)
    {
        double firstPersonKg;
        double secondPersonKg;
        final double MERCURY_MULTIPLIER = 0.38;
        final double URANUS_MULTIPLIER = 0.92;
        final double NEPTUNE_MULTIPLIER = 1.19;
        final double SATURN_MULTIPLIER = 1.06;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter weight of first earthling(kg): ");
        firstPersonKg = scanner.nextInt();

        System.out.print("Enter weight of second earthling(kg): ");
        secondPersonKg = scanner.nextInt();

        double mercuryWeightFirst = firstPersonKg * MERCURY_MULTIPLIER;
        double mercuryWeightSecond = secondPersonKg * MERCURY_MULTIPLIER;

        double uranusWeightFirst = firstPersonKg * URANUS_MULTIPLIER;
        double uranusWeightSecond = secondPersonKg * URANUS_MULTIPLIER;

        double neptuneWeightFirst = firstPersonKg * NEPTUNE_MULTIPLIER;
        double neptuneWeightSecond = secondPersonKg * NEPTUNE_MULTIPLIER;

        double saturnWeightFirst = firstPersonKg * SATURN_MULTIPLIER;
        double saturnWeightSecond = secondPersonKg * SATURN_MULTIPLIER;

        System.out.println("                                 MERCURY         URANUS          NEPTUNE          SATURN");
        System.out.printf(" EARTHLING ONE(%5.1fkg) %15.1f %15.1f %15.1f %15.1f\n", firstPersonKg, mercuryWeightFirst,
                uranusWeightFirst, neptuneWeightFirst, saturnWeightFirst);
        System.out.printf(" EARTHLING TWO(%.1fkg) %15.1f %15.1f %15.1f %15.1f", secondPersonKg, mercuryWeightSecond,
                uranusWeightSecond, neptuneWeightSecond, saturnWeightSecond);

        scanner.close();
    }
}
