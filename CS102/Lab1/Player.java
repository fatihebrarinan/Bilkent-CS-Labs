import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public void round(ArrayList<Player> players)
    {
        if (isSkipTurn)
        {
            System.out.println(name + " is skipping this turn.");
            isSkipTurn = false;
            return;
        }
        if (currentSlot != null && currentSlot.getPlayersOnThisSlot().contains(this))
        {
            currentSlot.getPlayersOnThisSlot().remove(this);
        }

        int diceFace = random.nextInt(1, 7);
        System.out.printf("%s played %d\n", this.name, diceFace);
        boardCounter += diceFace;
        if (boardCounter > 15)
        {
            if (boardCounter == 16)
            {
                coins += 6;
            } else
            {
                coins += 3;
            }

            boardCounter = boardCounter % 16;

        }
        currentSlot = board.getSlot(boardCounter);

        currentSlot.getPlayersOnThisSlot().add(this);

        currentSlot.askToBuy(this);

        currentSlot.determineAndPayRent(this);

        currentSlot.handleSpecials(this, players);

        askForAction();

    }

    private void askForAction()
    {
        if (!properties.isEmpty())
        {
            if (isUser)
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

            } else
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

    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    public int getCoins()
    {
        return coins;
    }

    public boolean getIsUser()
    {
        return isUser;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Slot> getProperties()
    {
        return properties;
    }

    public void eliminate()
    {
        isEliminated = true;
    }

    public boolean getIsEliminated()
    {
        return isEliminated;
    }

    public int getBoardCounter()
    {
        return boardCounter;
    }

    public void setBoardCounter(int boardCounter)
    {
        this.boardCounter = boardCounter;
    }

    public void setIsSkipTurn(boolean isSkipTurn)
    {
        this.isSkipTurn = isSkipTurn;
    }
}
