package Lab6;

import java.util.Scanner;

public class Lab06_Q2
{
    public static void main(String[] args)
    {
        String email = "";
        String password = "";
        Scanner scanner = new Scanner(System.in);

        while (!validateEmail(email))
        {
            if (!email.equals(""))
                System.out.println("The email is not valid, please enter a valid email.");

            System.out.print("Enter the email: ");
            email = scanner.nextLine();
        }

        while (!validatePassword(password))
        {
            System.out.print("Enter the password: ");
            password = scanner.nextLine();

            if (!validatePassword(password))
                System.out.println("The password is not valid, please enter another password.");
            else
                System.out.println("The password is valid.");

        }
        System.out.println("Registration is successful.");

        scanner.close();
    }

    public static boolean validateEmail(String email)

    {
        boolean validEmail = true;
        String studentName;
        String studentSurname;
        String studentUniversity;

        int dotIndex1 = email.indexOf(".");
        int dotIndex2 = email.indexOf(".", dotIndex1 + 1);
        int dotIndex3 = email.indexOf(".", dotIndex2 + 1);
        int atIndex = email.indexOf("@");

        // Validate email.
        if (dotIndex1 == -1 || dotIndex2 == -1 || dotIndex3 == -1 || atIndex == -1 || !email.contains(".edu.tr")
                || !(dotIndex1 < atIndex && atIndex < dotIndex2 && dotIndex2 < dotIndex3))
            validEmail = false;

        if (validEmail)
        {
            // Get name,surname, university.
            studentName = email.substring(0, dotIndex1);
            studentName = studentName.substring(0, 1).toUpperCase() + studentName.substring(1);
            studentSurname = email.substring(dotIndex1 + 1, atIndex);
            studentSurname = studentSurname.substring(0, 1).toUpperCase() + studentSurname.substring(1);
            studentUniversity = email.substring(atIndex + 1, dotIndex2);
            studentUniversity = studentUniversity.substring(0, 1).toUpperCase() + studentUniversity.substring(1);
            // Print name,surname and university.
            System.out.printf("Student name: %s\nStudent surname: %s\nUniversity name: %s\n", studentName,
                    studentSurname, studentUniversity);
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean validatePassword(String password)
    {
        boolean validPassword = false;
        // Check if string contains digit.
        boolean containsDigit = false;
        for (int i = 0; i < password.length(); i++)
        {
            char character = password.charAt(i);
            if (Character.isDigit(character))
                containsDigit = true;
        }
        // Check if password is valid.
        if (password.length() >= 8 && containsDigit && !password.equals(password.toUpperCase())
                && !password.equals(password.toLowerCase()))
            validPassword = true;

        if (validPassword)
            return true;
        else
            return false;
    }
}
