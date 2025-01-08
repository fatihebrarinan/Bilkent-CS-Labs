public class Lab01_Q2REV 
{
    public static void main(String[] args) 
    {   
        double calculation1;
        double calculation2;
        double calculation3; 
        double calculation4;

        calculation1 = ((4.3 + 22) * (5.1 - 7.7)) / (32.2 / 17 - 22);
        calculation2 = ((Math.pow(2, 3)) * (18 - 3.5 * 4.66)) / (Math.pow(2, 4) - 34);
        calculation3 = 3 * Math.pow((24 - 0.222), -1/6);
        calculation4 = (17.1 + 3.6 / 9) + 23 * Math.pow(2, 0.55);

        System.out.println("Expression 1 evaluates to: " + calculation1);
        System.out.println("Expression 2 evaluates to: " + calculation2);
        System.out.println("Expression 3 evaluates to: " + calculation3);
        System.out.println("Expression 4 evaluates to: " + calculation4);
    }
    
}
