package Lab7;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab07_Q3
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int input;
        boolean isOn = true;

        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<ArrayList<String>> reviews = new ArrayList<ArrayList<String>>();
        ArrayList<Double> sumOfRatings = new ArrayList<Double>();

        while (isOn)
        {
            System.out.print(
                    "\nMovie Review System:\n1. Add a new movie \n2. Remove a movie \n3. Submit a review and rating for a movie \n4. View all movies, reviews, and average ratings \n5. Exit \nEnter your choice: ");
            input = scanner.nextInt();

            if (input == 1)
            {
                String movie;
                System.out.print("Enter the movie title: ");
                movie = scanner.next();
                // Add movie.
                movies.add(movie);
                System.out.println("Movie added!");
                // Update ArrayLists.
                sumOfRatings.add(0.0);
                ArrayList<String> movieReviews = new ArrayList<String>();
                reviews.add(movieReviews);
            }
            if (input == 2)
            {
                System.out.println("Select a movie to remove:");
                printArrayList(movies);

                System.out.print("Enter movie number: ");
                int movieNum = scanner.nextInt();
                if (movieNum > movies.size())
                {
                    System.out.println("The entered number is wrong.");
                } else
                {
                    // Remove movie
                    System.out.println(movies.get(movieNum - 1) + " has been removed.");
                    movies.remove(movieNum - 1);
                    // Update ArrayLists.
                    sumOfRatings.remove(movieNum - 1);
                    reviews.remove(movieNum - 1);

                }
            }
            if (input == 3)
            {
                System.out.println("Select a movie to review:");
                printArrayList(movies);
                // Get info.
                System.out.print("Enter movie number: ");
                int movieNum = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter your review: ");
                String review = scanner.nextLine();
                reviews.get(movieNum - 1).add(review);
                System.out.print("Enter your rating (1-5): ");
                double rating = scanner.nextInt();
                System.out.println("Review and rating submitted!");

                // Update sumOfRatings
                if (sumOfRatings.get(movieNum - 1) == 0.0)
                {
                    sumOfRatings.set(movieNum - 1, rating);
                } else
                {
                    sumOfRatings.set(movieNum - 1, (rating + sumOfRatings.get(movieNum - 1)) / 2);
                }

                // Update reviews
            }
            if (input == 4)
            {
                for (int i = 1; i < movies.size() + 1; i++)
                {
                    String reviewString = "";
                    for (int j = 0; j < reviews.get(i - 1).size(); j++)
                    {
                        reviewString += ("\n   - " + reviews.get(i - 1).get(j));
                    }

                    System.out.printf("Movie List:\n%d. %s\n   Average Rating: %.1f\n   Reviews:%s\n", i,
                            movies.get(i - 1), sumOfRatings.get(i - 1), reviewString);
                }

            }
            if (input == 5)
            {
                // Exit.
                isOn = false;
            } else
                System.out.println("Invalid output.");
        }
        scanner.close();

    }

    public static void printArrayList(ArrayList<String> arrList)
    {
        for (int i = 0; i < arrList.size(); i++)
        {
            System.out.print((i + 1) + ". " + arrList.get(i) + "\n");
        }
    }
}