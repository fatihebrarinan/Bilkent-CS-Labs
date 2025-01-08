package Lab5;

import java.util.Random;

public class Lab05_Q2
{
    public static int bound = 125;
    public static String deck = "";
    public static String red = "R";
    public static String green = "G";
    public static String blue = "B";
    public static String magenta = "M";
    public static String yellow = "Y";

    public static void main(String[] args)
    {
        // Variables for players and routes.
        String player1 = "";
        String player2 = "";
        String player3 = "";
        String cardsDrawn = "";

        String colorString = "";
        int colorStringLenght;

        String player1Route2 = "", player1Route3 = "", player1Route4 = "", player1Route5 = "", player1Route6 = "";
        int player1RoutePoints = 0;
        int player1RemainingCardCount = 0;

        String player2Route2 = "", player2Route3 = "", player2Route4 = "", player2Route5 = "", player2Route6 = "";
        int player2RoutePoints = 0;
        int player2RemainingCardCount = 0;

        String player3Route2 = "", player3Route3 = "", player3Route4 = "", player3Route5 = "", player3Route6 = "";
        int player3RoutePoints = 0;
        int player3RemainingCardCount = 0;

        boolean routeAvaiable = true;

        // Variables for game control
        boolean isOn = true;
        int round;

        // Creating the deck.
        for (int i = 0; i < 25; i++)
            deck += red + green + blue + magenta + yellow;

        // Shuffling the deck.
        deck = shuffle(deck);

        // Print deck.
        System.out.println("Welcome!\nLet's shuffle the deck:\n" + deck);

        // Assigning cards to players.
        for (int i = 0; i < 5; i++)
        {
            player1 = assign(player1);
            player2 = assign(player2);
            player3 = assign(player3);
        }

        // Print player's hand before start.
        System.out.printf("Game begins!\nPlayer1: %s\nPlayer2: %s\nPlayer3: %s\n", player1, player2, player3);

        // Game rounds
        round = 1;
        while (isOn)
        {
            System.out.println("##### Game round #" + round);

            // Player
            // 1-----------------------------------------------------------------------------------------------
            System.out.println("## Player1 ##");
            System.out.println("Previous hand: " + player1);

            // colorString is the max amount of color like "RRR".
            colorString = maxColor(player1);

            // Check if there is available route.
            if (colorString.equals(""))
                routeAvaiable = false;
            else if (colorString.length() == 2 && !player1Route2.equals("")
                    || colorString.length() == 3 && !player1Route3.equals("")
                    || colorString.length() == 4 && !player1Route4.equals("")
                    || colorString.length() == 5 && !player1Route5.equals("")
                    || colorString.length() == 6 && !player1Route6.equals(""))
                routeAvaiable = false;
            else
                routeAvaiable = true;

            // if there is no available route:
            if (!routeAvaiable)
            {
                // Draw cards.
                for (int i = 0; i < 3; i++)
                {
                    cardsDrawn = assign(cardsDrawn);
                }
                player1 += cardsDrawn;
                System.out.println("Card drawn: " + cardsDrawn);
                System.out.println("Current hand: " + player1);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player1Route2,
                        player1Route3, player1Route4, player1Route5, player1Route6);
                cardsDrawn = "";
            }
            // else there is an available route:
            else
            {
                // Add the color to the correct route
                colorStringLenght = colorString.length();
                if (colorStringLenght == 2 && player1Route2.equals(""))
                {
                    player1Route2 = colorString;
                } else if (colorStringLenght == 3 && player1Route3.equals(""))
                {
                    player1Route3 = colorString;
                } else if (colorStringLenght == 4 && player1Route4.equals(""))
                {
                    player1Route4 = colorString;
                } else if (colorStringLenght == 5 && player1Route5.equals(""))
                {
                    player1Route5 = colorString;
                } else if (colorStringLenght == 6 && player1Route6.equals(""))
                {
                    player1Route6 = colorString;
                }

                // Remove colorString's characters from player.
                player1 = player1.replaceAll(colorString.substring(colorStringLenght - 1), "");

                // Print route completed.
                System.out.println("Route completed!");
                System.out.println("Current hand: " + player1);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player1Route2,
                        player1Route3, player1Route4, player1Route5, player1Route6);
            }

            // Player2---------------------------------------------------------------------------------------------------

            System.out.println("## Player2 ##");
            System.out.println("Previous hand: " + player2);

            // colorString is the max amount of color like "RRR".
            colorString = maxColor(player2);

            // Check if there is available route.
            if (colorString.equals(""))
                routeAvaiable = false;
            else if (colorString.length() == 2 && !player2Route2.equals("")
                    || colorString.length() == 3 && !player2Route3.equals("")
                    || colorString.length() == 4 && !player2Route4.equals("")
                    || colorString.length() == 5 && !player2Route5.equals("")
                    || colorString.length() == 6 && !player2Route6.equals(""))
                routeAvaiable = false;
            else
                routeAvaiable = true;

            // if there is no available route:
            if (!routeAvaiable)
            {
                // Draw cards.
                for (int i = 0; i < 3; i++)
                {
                    cardsDrawn = assign(cardsDrawn);
                }
                player2 += cardsDrawn;

                System.out.println("Card drawn: " + cardsDrawn);
                System.out.println("Current hand: " + player2);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player2Route2,
                        player2Route3, player2Route4, player2Route5, player2Route6);
                cardsDrawn = "";
            }
            // else there is an available route:
            else
            {
                // Add the color to the correct route
                colorStringLenght = colorString.length();
                if (colorStringLenght == 2 && player2Route2.equals(""))
                {
                    player2Route2 = colorString;
                } else if (colorStringLenght == 3 && player2Route3.equals(""))
                {
                    player2Route3 = colorString;
                } else if (colorStringLenght == 4 && player2Route4.equals(""))
                {
                    player2Route4 = colorString;
                } else if (colorStringLenght == 5 && player2Route5.equals(""))
                {
                    player2Route5 = colorString;
                } else if (colorStringLenght == 6 && player2Route6.equals(""))
                {
                    player2Route6 = colorString;
                }

                // Remove colorString's characters from player.
                player2 = player2.replaceAll(colorString.substring(colorStringLenght - 1), "");

                // Print route completed.
                System.out.println("Route completed!");
                System.out.println("Current hand: " + player2);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player2Route2,
                        player2Route3, player2Route4, player2Route5, player2Route6);
            }

            // Player3-------------------------------------------------------------------------------------------------------

            System.out.println("## Player3 ##");
            System.out.println("Previous hand: " + player3);

            // colorString is the max amount of color like "RRR".
            colorString = maxColor(player3);

            // Check if there is available route.
            if (colorString.equals(""))
                routeAvaiable = false;
            else if (colorString.length() == 2 && !player3Route2.equals("")
                    || colorString.length() == 3 && !player3Route3.equals("")
                    || colorString.length() == 4 && !player3Route4.equals("")
                    || colorString.length() == 5 && !player3Route5.equals("")
                    || colorString.length() == 6 && !player3Route6.equals(""))
                routeAvaiable = false;
            else
                routeAvaiable = true;

            // if there is no available route:
            if (!routeAvaiable)
            {
                // Draw cards.
                for (int i = 0; i < 3; i++)
                {
                    cardsDrawn = assign(cardsDrawn);
                }
                player3 += cardsDrawn;
                System.out.println("Card drawn: " + cardsDrawn);
                System.out.println("Current hand: " + player3);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player3Route2,
                        player3Route3, player3Route4, player3Route5, player3Route6);
                cardsDrawn = "";
            }
            // else there is an available route:
            else
            {
                // Add the color to the correct route
                colorStringLenght = colorString.length();
                if (colorStringLenght == 2 && player3Route2.equals(""))
                {
                    player3Route2 = colorString;
                } else if (colorStringLenght == 3 && player3Route3.equals(""))
                {
                    player3Route3 = colorString;
                } else if (colorStringLenght == 4 && player3Route4.equals(""))
                {
                    player3Route4 = colorString;
                } else if (colorStringLenght == 5 && player3Route5.equals(""))
                {
                    player3Route5 = colorString;
                } else if (colorStringLenght == 6 && player3Route6.equals(""))
                {
                    player3Route6 = colorString;
                }

                // Remove colorString's characters from player.
                player3 = player3.replaceAll(colorString.substring(colorStringLenght - 1), "");

                // Print route completed.
                System.out.println("Route completed!");
                System.out.println("Current hand: " + player3);
                System.out.printf("Route#2: %s\nRoute#3: %s\nRoute#4: %s\nRoute#5: %s\nRoute#6: %s\n", player3Route2,
                        player3Route3, player3Route4, player3Route5, player3Route6);
            }
            // if any of the player's routes are complete, end the game.
            if (!player1Route2.equals("") && !player1Route3.equals("") && !player1Route4.equals("")
                    && !player1Route5.equals("") && !player1Route6.equals("")
                    || !player2Route2.equals("") && !player2Route3.equals("") && !player2Route4.equals("")
                            && !player2Route5.equals("") && !player2Route6.equals("")
                    || !player3Route2.equals("") && !player3Route3.equals("") && !player3Route4.equals("")
                            && !player3Route5.equals("") && !player3Route6.equals(""))
            {
                isOn = false;
            }
            round++;

        }
        // Calculate route points and remaining card counts
        player1RoutePoints = player1Route2.length() + player1Route3.length() + player1Route4.length()
                + player1Route5.length() + player1Route6.length();
        player2RoutePoints = player2Route2.length() + player2Route3.length() + player2Route4.length()
                + player2Route5.length() + player2Route6.length();
        player3RoutePoints = player3Route2.length() + player3Route3.length() + player3Route4.length()
                + player3Route5.length() + player3Route6.length();

        player1RemainingCardCount = player1.length();
        player2RemainingCardCount = player2.length();
        player3RemainingCardCount = player3.length();

        // Show the results.
        System.out.println("*********************\nGame finished!");

        // Player1 results.
        System.out.printf("Player1's total route points: %s,\nPlayer1's remaining card count in the hand: %s\n",
                player1RoutePoints, player1RemainingCardCount);
        // Player2 results.
        System.out.printf("Player2's total route points: %s,\nPlayer2's remaining card count in the hand: %s\n",
                player2RoutePoints, player2RemainingCardCount);
        // Player3 results.
        System.out.printf("Player3's total route points: %s,\nPlayer3's remaining card count in the hand: %s\n",
                player3RoutePoints, player3RemainingCardCount);

        System.out.println("Number of remaining cards on deck: " + deck.length());
        System.out.printf("TOTAL = %s", player1RemainingCardCount + player1RoutePoints + player2RemainingCardCount
                + player2RoutePoints + player3RemainingCardCount + player3RoutePoints + deck.length());

    }

    public static String assign(String player)
    {
        int randInt;
        String randChar;
        String deckPart1;
        String deckPart2;
        Random random = new Random();

        // Get random character from deck and add it to player.
        randInt = random.nextInt(bound);
        randChar = deck.substring(randInt, randInt + 1);
        player += randChar;

        // Remove that character from deck.
        deckPart1 = deck.substring(0, randInt);
        deckPart2 = deck.substring(randInt + 1);
        deck = deckPart1 + deckPart2;

        bound--;
        return player;
    }

    public static String shuffle(String deck)
    {
        int randInt1 = 0;
        int randInt2 = 0;
        String randChar1;
        String randChar2;
        String deckPart1;
        String deckPart2;
        // shuffleAmount determines how many switches are going to happen.
        int shuffleAmount = 100;
        Random random = new Random();

        for (int i = 0; i < shuffleAmount; i++)
        {

            // Get 2 random characters from deck.
            randInt1 = random.nextInt(bound);
            randChar1 = deck.substring(randInt1, randInt1 + 1);

            randInt2 = random.nextInt(bound);
            randChar2 = deck.substring(randInt2, randInt2 + 1);

            // Switch those 2 characters.
            deckPart1 = deck.substring(0, randInt1);
            deckPart2 = deck.substring(randInt1 + 1);
            deck = deckPart1 + randChar2 + deckPart2;

            deckPart1 = deck.substring(0, randInt2);
            deckPart2 = deck.substring(randInt2 + 1);
            deck = deckPart1 + randChar1 + deckPart2;
        }
        return deck;
    }

    public static String maxColor(String player)
    {
        int redCount, greenCount, blueCount, magentaCount, yellowCount, highestCount;
        String result = "";

        redCount = player.length() - player.replaceAll(red, "").length();
        greenCount = player.length() - player.replaceAll(green, "").length();
        blueCount = player.length() - player.replaceAll(blue, "").length();
        magentaCount = player.length() - player.replaceAll(magenta, "").length();
        yellowCount = player.length() - player.replaceAll(yellow, "").length();

        if (redCount < 2 && greenCount < 2 && blueCount < 2 && magentaCount < 2 && yellowCount < 2)
        {
            return "";
        }
        highestCount = Math.max(Math.max(Math.max(Math.max(redCount, greenCount), blueCount), magentaCount),
                yellowCount);

        if (redCount == highestCount)
        {
            for (int i = 0; i < highestCount; i++)
            {
                result += "R";
            }
        } else if (greenCount == highestCount)
        {
            for (int i = 0; i < highestCount; i++)
            {
                result += "G";
            }
        } else if (blueCount == highestCount)
        {
            for (int i = 0; i < highestCount; i++)
            {
                result += "B";
            }
        } else if (magentaCount == highestCount)
        {
            for (int i = 0; i < highestCount; i++)
            {
                result += "M";
            }
        } else if (yellowCount == highestCount)
        {
            for (int i = 0; i < highestCount; i++)
            {
                result += "Y";
            }
        }

        return result;
    }

}
