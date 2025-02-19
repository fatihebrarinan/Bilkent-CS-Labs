import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Slot
{
    private boolean owned = false;
    private String owner = ".";
    private Player ownerPlayer;
    private int numOfHouses = 0;
    private int tag;
    private int costToBuy;
    private int costToBuildHouse;
    private String name;
    private int rent;
    private boolean isSpecial = false;
    private ArrayList<Player> playersOnThisSlot = new ArrayList<Player>();
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Slot(int tag, String name)
    {
        // tag indicates what type of slot this is. 0,1,2,3 are special slots. 4 = ABC,
        // 5 = DEF, 6 = GHI, 7 = JKL
        this.tag = tag;
        this.name = name;
        this.determineCost();
        if (this.tag == 0 || this.tag == 1 || this.tag == 2 || this.tag == 3)
        {
            isSpecial = true;
        }

    }

    public void handleSpecials(Player player, ArrayList<Player> players)
    {
        if (tag == 1)
        {
            int randomNumber = random.nextInt(6);
            switch (randomNumber)
            {
            case 0:
                player.setCoins(player.getCoins() - 2);
                break;
            case 1:
                player.setCoins(player.getCoins() - 1);
                break;
            case 2:
                player.setBoardCounter(player.getBoardCounter() + 1);
                break;
            case 3:
                player.setBoardCounter(player.getBoardCounter() + 2);
                break;
            case 4:
                player.setCoins(player.getCoins() + 1);
                player.setBoardCounter(player.getBoardCounter() + 1);
                break;
            case 5:
                player.setCoins(player.getCoins() + 2);
                player.setBoardCounter(player.getBoardCounter() + 2);
                break;
            }
        }
        if (tag == 2)
        {
            for (Player p : players)
            {
                if (p != player)
                {
                    p.setCoins(p.getCoins() - 1);
                }
            }
            player.setCoins(player.getCoins() + players.size() - 1);
        }
        if (tag == 3)
        {
            System.out.printf("%s landed on special slot %s and will skip their next turn.\n", player.getName(), name);
            player.setIsSkipTurn(true);
        }
    }

    private void determineCost()
    {
        switch (this.tag)
        {
        case 4:
            costToBuy = 2;
            costToBuildHouse = 1;
            break;
        case 5:
            costToBuy = 4;
            costToBuildHouse = 1;
            break;
        case 6:
            costToBuy = 6;
            costToBuildHouse = 2;
            break;
        case 7:
            costToBuy = 8;
            costToBuildHouse = 3;
            break;
        default:
            break;

        }
    }

    public void determineAndPayRent(Player player)
    {
        if (owned && owner != player.getName())
        {
            switch (this.tag)
            {
            case 4:
                switch (numOfHouses)
                {
                case 0:
                    rent = 1;
                    break;
                case 1:
                    rent = 2;
                    break;
                case 2:
                    rent = 3;
                    break;
                case 3:
                    rent = 4;
                    break;
                case 4:
                    rent = 6;
                    break;
                }
                break;
            case 5:
                switch (numOfHouses)
                {
                case 0:
                    rent = 2;
                    break;
                case 1:
                    rent = 2;
                    break;
                case 2:
                    rent = 3;
                    break;
                case 3:
                    rent = 3;
                    break;
                case 4:
                    rent = 7;
                    break;
                }
                break;
            case 6:
                switch (numOfHouses)
                {
                case 0:
                    rent = 1;
                    break;
                case 1:
                    rent = 3;
                    break;
                case 2:
                    rent = 4;
                    break;
                case 3:
                    rent = 6;
                    break;
                case 4:
                    rent = 7;
                    break;
                }
                break;
            case 7:
                switch (numOfHouses)
                {
                case 0:
                    rent = 3;
                    break;
                case 1:
                    rent = 3;
                    break;
                case 2:
                    rent = 6;
                    break;
                case 3:
                    rent = 6;
                    break;
                case 4:
                    rent = 9;
                    break;
                }
            }
            player.setCoins(player.getCoins() - rent);
            if (player.getCoins() < 0)
            {
                player.eliminate();
            }
            ownerPlayer.setCoins(ownerPlayer.getCoins() + rent);
        }
    }

    public void askToBuy(Player player)
    {
        if (!owned && !isSpecial)
        {
            if (player.getIsUser())
            {
                System.out.print("Do you want to buy this slot? (y/n) ");
                String answer = scanner.nextLine();
                if (answer.toLowerCase().equals("y"))
                {
                    player.setCoins(player.getCoins() - this.costToBuy);
                    owned = true;
                    owner = player.getName();
                    ownerPlayer = player;
                    player.getProperties().add(this);
                    System.out.printf("Player %s have %d coins left.\n", owner, player.getCoins());

                }
            } else
            {
                if (random.nextInt(2) == 0)
                {
                    player.setCoins(player.getCoins() - this.costToBuy);
                    owned = true;
                    owner = player.getName();
                    ownerPlayer = player;
                    player.getProperties().add(this);
                    System.out.printf("Slot%s is bought by %s\n", name, owner);
                    System.out.printf("Player %s have %d coins left.\n", owner, player.getCoins());
                }
            }

        }
    }

    public int getCostToBuildHouse()
    {
        return costToBuildHouse;
    }

    public String getTopString()
    {
        return String.format("%s.%s%d", name, owner.substring(0, 1).toLowerCase(), numOfHouses);
    }

    public String getBottomString()
    {
        StringBuilder playersString = new StringBuilder("....");

        for (int i = 0; i < playersOnThisSlot.size() && i < playersString.length(); i++)
        {
            playersString.setCharAt(i, playersOnThisSlot.get(i).getName().substring(0, 1).toLowerCase().charAt(0));
        }

        return playersString.toString();
    }

    public ArrayList<Player> getPlayersOnThisSlot()
    {
        return playersOnThisSlot;
    }

    public String getName()
    {
        return name;
    }

    public void increaseNumOfHousesByOne()
    {
        numOfHouses += 1;
    }

    public void resetSlot()
    {
        owned = false;
        owner = ".";
        numOfHouses = 0;
    }

    public int getCostToBuy()
    {
        return costToBuy;
    }

    public int getNumOfHouses()
    {
        return numOfHouses;

    }
}
