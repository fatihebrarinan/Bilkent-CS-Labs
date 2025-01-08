/**
 * This program aims to print information about the lab schedule and grading policy.
 * It has certain syntax errors that are to be fixed by the student.
 * 
 * @author Ugur Dogrusoz
 */
public class Lab01_Q1
{      
   public static void main( String args[] ) 
   {
      // Constants
      final int LABS_COUNT = 9;
      final double LABS_GRADE_PERCENT = 15;  // grading policy
      final double GRADE_PERCENT_PER_LAB = LABS_GRADE_PERCENT / LABS_COUNT;
      
      // Variables
      String courseAndSemester;
      
      // First initialize the course & semester information
      courseAndSemester = "CS101 in Fall 2024";
      
      // Then print out welcome message
      System.out.println( "Hi there! "  + "Welcome to Lab 01 of " + courseAndSemester + "!" );
      System.out.println( "Here are some details about lab grades:" );
      System.out.println();
      
      // Now print out the grading policy
      System.out.println( "There are "+ LABS_COUNT + " lab sessions this semester." );
      System.out.println( "Labs make up " + LABS_GRADE_PERCENT + "% of your total grade." );
      System.out.println( "This lab:\t" + GRADE_PERCENT_PER_LAB + "\tpoints" );
      System.out.println( "All labs:\t" + LABS_GRADE_PERCENT + "\tpoints" );
      System.out.println( "Don't let the low weight fool you. Labs are extremely important!" );
      
      // Finally wish them luck and finish
      System.out.println();
      System.out.println( "Best of luck!" );
   }
}