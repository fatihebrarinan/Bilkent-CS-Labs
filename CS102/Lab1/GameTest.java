import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameTest
{
    public static void main(String[] args)
    {
        // Determine the player count and initialize them

        Player player1, player2, player3, user;
        ArrayList<Player> players = new ArrayList<Player>();
        Board board = new Board();
        String coinsString = "";

        Scanner scanner = new Scanner(System.in);

        System.out.print("What's your username? ");
        String username = scanner.nextLine();
        user = new Player(username, board, true);
        players.add(user);

        System.out.print("How many computer players do you want in your game? (1 to 3) ");
        int numOfComputer = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What should be their names? ");
        for (int i = 1; i < numOfComputer + 1; i++)
        {
            System.out.printf("Enter one name: ", i);
            String name = scanner.nextLine();

            switch (i)
            {
            case 1:
                player1 = new Player(name, board, false);
                players.add(player1);
                break;
            case 2:
                player2 = new Player(name, board, false);
                players.add(player2);
                break;
            case 3:
                player3 = new Player(name, board, false);
                players.add(player3);
                break;
            default:
                break;
            }
        }
        Collections.shuffle(players);

        // Start the rounds
        System.out.printf("%s", board.toString());

        boolean isOn = true;
        int counter = 0;
        while (isOn && counter < 100)
        {

            for (int i = 0; i < players.size(); i++)
            {
                players.get(i).round(players);
            }

            for (int j = 0; j < players.size(); j++)
            {
                coinsString += String.format("%s has %d coins left.\n", players.get(j).getName(),
                        players.get(j).getCoins());
            }
            System.out.printf("%s\n%s", board.toString(), coinsString);

            for (Player player : players)
            {
                if (player.getIsEliminated())
                {
                    players.remove(player);
                }
            }
            if (players.size() == 1)
            {
                isOn = false;
            }
            counter++;
        }
        System.out.printf("The winner is %s\n", players.get(0).getName());
        scanner.close();

    }
}