import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Slot class represents a slot at the monopoly game. Every slot that is not
 * special can be owned, can have houses on it, has cost to build a house and
 * buy and it has a name and rent price.
 */
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

    /**
     * Constructor determines if the slot is special. It also determines the costs
     * for this slot depending on it's tag.
     * 
     * @param tag
     * @param name
     */
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

    /**
     * Determines what to do on each special slots except 0th.
     * 
     * @param player
     * @param players
     */
    public void handleSpecials(Player player, ArrayList<Player> players)
    {
        if (tag == 1)// If it's special slot 1, then a random dice it thrown.
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
        if (tag == 2)// If it's special slot 2, then everyone gives that player one coin.
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
        if (tag == 3)// If it's special slot 3, then the player skips one round.
        {
            System.out.printf("%s landed on special slot %s and will skip their next turn.\n", player.getName(), name);
            player.setIsSkipTurn(true);
        }
    }

    /**
     * Determines the cost to buy this slot or build house on it.
     */
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

    /**
     * Calculates the rent and pays it for the player.
     * 
     * @param player
     */
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

            // Pay the rent.
            player.setCoins(player.getCoins() - rent);
            if (player.getCoins() < 0)
            {
                player.eliminate();
            }
            ownerPlayer.setCoins(ownerPlayer.getCoins() + rent);
        }
    }

    /**
     * If the slot is not owned, this method asks the player if they want to buy
     * this slot. If the player is computer, then it randomly decides.
     * 
     * @param player
     */
    public void askToBuy(Player player)
    {
        if (!owned && !isSpecial)
        {
            if (player.getIsUser())// If it's the user.
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
            } else// If it's computer.
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

    /**
     * returns the cost to build a house on this slot.
     * 
     * @return
     */
    public int getCostToBuildHouse()
    {
        return costToBuildHouse;
    }

    /**
     * returns the top half of this slot's string representation.
     * 
     * @return
     */
    public String getTopString()
    {
        return String.format("%s.%s%d", name, owner.substring(0, 1).toLowerCase(), numOfHouses);
    }

    /**
     * returns the bottom half of this slot's string representation.
     * 
     * @return
     */
    public String getBottomString()
    {
        StringBuilder playersString = new StringBuilder("....");

        for (int i = 0; i < playersOnThisSlot.size() && i < playersString.length(); i++)
        {
            playersString.setCharAt(i, playersOnThisSlot.get(i).getName().substring(0, 1).toLowerCase().charAt(0));
        }

        return playersString.toString();
    }

    /**
     * returns players on this slot.
     * 
     * @return
     */
    public ArrayList<Player> getPlayersOnThisSlot()
    {
        return playersOnThisSlot;
    }

    /**
     * returns the name of the slot.
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * increases the number of houses on this slot.
     */
    public void increaseNumOfHousesByOne()
    {
        numOfHouses += 1;
    }

    /**
     * Resets this slot. Is called when a players sells this slot.
     */
    public void resetSlot()
    {
        owned = false;
        owner = ".";
        numOfHouses = 0;
    }

    /**
     * returns the cost the buy this slot.
     * 
     * @return
     */
    public int getCostToBuy()
    {
        return costToBuy;
    }

    /**
     * returns the number of houses on this slot.
     * 
     * @return
     */
    public int getNumOfHouses()
    {
        return numOfHouses;

    }
}
