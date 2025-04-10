import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Player class represents the players at the monopoly game. A player can be a
 * user or a computer.
 */
public class Player
{
    private int coins;
    private String name;
    private int boardCounter = 0;
    private Board board;
    private boolean isUser;
    private boolean isEliminated;
    private boolean isSkipTurn = false;
    Slot currentSlot = null;
    private ArrayList<Slot> properties = new ArrayList<Slot>();
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructer starts every player with 10 coins and the slot 0.
     * 
     * @param name
     * @param board
     * @param isUser
     */
    public Player(String name, Board board, boolean isUser)
    {
        coins = 10;
        this.name = name;
        this.board = board;
        this.isUser = isUser;
        this.currentSlot = board.getSlot(0);
        currentSlot.getPlayersOnThisSlot().add(this);
        isEliminated = false;

    }

    /**
     * Round function controls game flow for every player. It is responsible for
     * rolling the dice and doing the necesarry interactions with the slot it landed
     * on.
     * 
     * @param players
     */
    public void round(ArrayList<Player> players)
    {
        if (isSkipTurn)// Special slot 3 makes the player skips the round.
        {
            System.out.println(name + " is skipping this turn.");
            isSkipTurn = false;
            return;
        }
        if (currentSlot != null && currentSlot.getPlayersOnThisSlot().contains(this))// Clears the current slot.
        {
            currentSlot.getPlayersOnThisSlot().remove(this);
        }

        int diceFace = random.nextInt(1, 7);
        System.out.printf("%s played %d\n", this.name, diceFace);
        boardCounter += diceFace;// Rolls the dice.
        if (boardCounter > 15)
        {
            if (boardCounter == 16)
            {
                coins += 6;// Special case 0.
            } else
            {
                coins += 3;// Special case 0.
            }

            boardCounter = boardCounter % 16;

        }
        currentSlot = board.getSlot(boardCounter);// Determines current slot.

        currentSlot.getPlayersOnThisSlot().add(this);

        currentSlot.askToBuy(this);// If the slot is not owned, ask to buy.

        currentSlot.determineAndPayRent(this);// If the slot is owned, pay rent.

        currentSlot.handleSpecials(this, players);// If slot is special, handle it.

        askForAction();

    }

    /**
     * After every round, the user is asked if they want to build house on any slot
     * or sell any of their slot. If the player is computer, it is decided randomly.
     */
    private void askForAction()
    {
        if (!properties.isEmpty())
        {
            if (isUser)// If the player is user
            {
                String propertiesString = "";
                for (Slot slot : properties)
                {
                    propertiesString = propertiesString + slot.getName() + " ";
                }
                System.out.printf(
                        "Do you want to build on/sell any of your properties? Your properties: %s\n(b for build, s for sell, n for none(skip)): ",
                        propertiesString);
                String response = scanner.nextLine();

                if (response == "b")
                {
                    System.out.print("Which slot do you want to build on? ");
                    String slotResponse = scanner.nextLine().toUpperCase();
                    if (propertiesString.contains(slotResponse))
                    {
                        Slot slotToBuyHouse = null;
                        for (int i = 0; i < properties.size(); i++)
                        {
                            if (properties.get(i).getName() == slotResponse)
                            {
                                slotToBuyHouse = properties.get(i);
                                break;
                            }

                        }
                        if (coins >= slotToBuyHouse.getCostToBuildHouse())
                        {
                            if (slotToBuyHouse.getNumOfHouses() < 4)
                            {
                                coins -= slotToBuyHouse.getCostToBuildHouse();
                                slotToBuyHouse.increaseNumOfHousesByOne();
                            } else
                            {
                                System.out.println("Slot already have max amount of houses.");
                            }
                        } else
                        {
                            System.out.println("You don't have enough coins! Skipping.");
                        }
                    } else
                    {
                        System.out.println("Invalid slot. skipping.");
                    }
                } else if (response == "s")
                {
                    System.out.print("Which slot do you want to sell'? ");
                    String slotResponse = scanner.nextLine().toUpperCase();
                    if (propertiesString.contains(slotResponse))
                    {
                        Slot slotToSellHouse = null;
                        for (int i = 0; i < properties.size(); i++)
                        {
                            if (properties.get(i).getName() == slotResponse)
                            {
                                slotToSellHouse = properties.get(i);
                                break;
                            }
                        }
                        slotToSellHouse.resetSlot();
                        coins += slotToSellHouse.getCostToBuy();

                    } else
                    {
                        System.out.println("Invalid slot. skipping.");
                    }
                } else if (response == "n")
                {
                    System.out.println("Skipping.");
                } else
                {
                    System.out.println("Invalid input. Skipping.");
                }

            } else// If the player is computer.
            {
                int randomInt = random.nextInt(10);
                if (randomInt == 0)
                {
                    int randomIndex = random.nextInt(properties.size());
                    Slot randomSlot = properties.get(randomIndex);

                    randomSlot.resetSlot();
                    coins += randomSlot.getCostToBuy();
                    System.out.printf("%s sold slot%s\n", name, randomSlot.getName());
                }
                if (randomInt == 1 || randomInt == 2 || randomInt == 3 || randomInt == 4 || randomInt == 5)
                {
                    int randomIndex = random.nextInt(properties.size());
                    Slot randomSlot = properties.get(randomIndex);
                    if (randomSlot.getNumOfHouses() < 4 && coins >= randomSlot.getCostToBuildHouse())
                    {
                        coins -= randomSlot.getCostToBuildHouse();
                        randomSlot.increaseNumOfHousesByOne();
                        System.out.printf("%s bought house to slot%s\n", name, randomSlot.getName());
                    }
                }
            }
        }
    }

    /**
     * Makes the isEliminated true. User gets eliminated.
     */
    public void eliminate()
    {
        isEliminated = true;
    }

    /**
     * Setter for coins of player.
     * 
     * @param coins
     */
    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    /**
     * Getter for coins of player.
     * 
     * @return
     */
    public int getCoins()
    {
        return coins;
    }

    /**
     * Getter of whether the player is user nor not.
     * 
     * @return
     */
    public boolean getIsUser()
    {
        return isUser;
    }

    /**
     * Getter of player name.
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter of the properties of the player.
     * 
     * @return
     */
    public ArrayList<Slot> getProperties()
    {
        return properties;
    }

    /**
     * Getter of whether the user is eliminated or not.
     * 
     * @return
     */
    public boolean getIsEliminated()
    {
        return isEliminated;
    }

    /**
     * Getter of the sum of the dice scores of the player.
     * 
     * @return
     */
    public int getBoardCounter()
    {
        return boardCounter;
    }

    /**
     * Getter of the sum of the dice scores of the player.
     * 
     * @param boardCounter
     */
    public void setBoardCounter(int boardCounter)
    {
        this.boardCounter = boardCounter;
    }

    /**
     * Setter of the isSkipTurn. Special case 3 uses this method.
     * 
     * @param isSkipTurn
     */
    public void setIsSkipTurn(boolean isSkipTurn)
    {
        this.isSkipTurn = isSkipTurn;
    }
}
