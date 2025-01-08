package Lab2;

import java.util.Scanner;

public class Lab02_Q3
{
    public static void main(String[] args)
    {
        String statement;
        String date;
        String day;
        String month;
        String year;
        String minute;
        String hour;

        Scanner scanner = new Scanner(System.in);

        // Get the statement from user.
        System.out.print("Enter date and time: ");
        statement = scanner.nextLine();
        statement.trim();

        // We will use substring and indexOf methods here.

        int gap1Index = statement.indexOf("/");
        int gap2Index = statement.indexOf("/", gap1Index + 1);
        int gap3Index = statement.indexOf(",");
        int gap4Index = statement.indexOf("-");
        int gap5Index = statement.indexOf(":");

        year = statement.substring(0, gap1Index);
        month = statement.substring(gap1Index + 1, gap2Index);
        date = statement.substring(gap2Index + 1, gap3Index);
        day = statement.substring(gap3Index + 1, gap4Index);
        hour = statement.substring(gap4Index + 1, gap5Index);
        minute = statement.substring(gap5Index + 1);

        // Display
        String dateText = "Date:";
        String dayText = "Day:";
        String monthText = "Month:";
        String yearText = "Year:";
        String timeText = "Time:";

        System.out.printf("%-9s%s\n", dateText, date);
        System.out.printf("%-9s%s\n", dayText, day);
        System.out.printf("%-9s %s\n", monthText, month);
        System.out.printf("%-9s %s\n", yearText, year);
        System.out.printf("%-9s %s minutes after%s\n", timeText, minute, hour);

        scanner.close();
    }
}