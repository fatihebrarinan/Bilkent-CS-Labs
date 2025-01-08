public class Lab01_Q3REV 
{
    public static void main(String[] args) 
    {
        final double TOTAL_WORLD_AREA;
        double dryWorldPercentage;
        double wetWorldPercentage;
        double dryWorldArea;
        double wetWorldArea;
        final double TOTAL_TURKEY_AREA;
        double dryTurkeyArea;
        double wetTurkeyArea;

        TOTAL_WORLD_AREA = 510072000;
        dryWorldPercentage = 0.292;
        TOTAL_TURKEY_AREA = TOTAL_WORLD_AREA / 6510.42;

        //Calculation
        wetWorldPercentage = 1 - dryWorldPercentage;
        dryTurkeyArea = TOTAL_TURKEY_AREA * (76.0/77);
        wetTurkeyArea = TOTAL_TURKEY_AREA * (1.0/77);
        wetWorldArea = TOTAL_WORLD_AREA * wetWorldPercentage;
        dryWorldArea = TOTAL_WORLD_AREA * dryWorldPercentage;
        

        

        // Display
        System.out.println("Turkey has " + (int)dryTurkeyArea + " km2 dry land and " + (int)wetTurkeyArea + " km2 water.");
        System.out.println("Earth has " + (long)dryWorldArea + " km2 dry land and " + (long)wetWorldArea + " km2 water.");
        System.out.println("Turkey has " + (dryTurkeyArea/dryWorldArea) * 100 + " percent of Earth's dry land.");
        System.out.println("Percentage of Earth water outside Turkey is " + (1 - wetTurkeyArea/wetWorldArea) * 100 + " percent.");
        System.out.println("The percentage of Earth's dry land outside Turkey is " + ( (dryWorldArea - dryTurkeyArea) / dryWorldArea ) * 100 );
    }
}
