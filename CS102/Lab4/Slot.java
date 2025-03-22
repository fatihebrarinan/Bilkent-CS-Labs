import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
    private ArrayList<Player> playersOnThisSlot;
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
        playersOnThisSlot = new ArrayList<Player>();
    }

    public void handleSpecials(Player player, ArrayList<Player> players, JTextArea logArea)
    {
        if (tag == 1)
        {
            int randomNumber = random.nextInt(6);
            switch (randomNumber)
            {
            case 0:
                player.setCoins(player.getCoins() - 2);
                logArea.append(player.getName() + " landed on special slot 1 and lost 2 coins.\n");
                break;
            case 1:
                player.setCoins(player.getCoins() - 1);
                logArea.append(player.getName() + " landed on special slot 1 and lost 1 coins.\n");
                break;
            case 2:
                player.setBoardCounter(player.getBoardCounter() + 1);
                logArea.append(player.getName() + " landed on special slot 1 and moved 1 slot.\n");
                break;
            case 3:
                player.setBoardCounter(player.getBoardCounter() + 2);
                logArea.append(player.getName() + " landed on special slot 1 and moved 2 slots.\n");
                break;
            case 4:
                player.setCoins(player.getCoins() + 1);
                player.setBoardCounter(player.getBoardCounter() + 1);
                logArea.append(
                        player.getName() + " landed on special slot 1. They gained 1 coin and and moved 1 slot.\n");
                break;
            case 5:
                player.setCoins(player.getCoins() + 2);
                player.setBoardCounter(player.getBoardCounter() + 2);
                logArea.append(
                        player.getName() + " landed on special slot 1. They gained 2 coin and and moved 2 slot.\n");
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
            logArea.append(player.getName() + " landed on special slot 2. Everyone is giving a coin to them.\n");
        }
        if (tag == 3)
        {
            logArea.append(player.getName() + " landed on special slot 3 and will skip their next turn.\n");
            player.setIsSkipTurn(true);
        }
    }

    private void determineRent()
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
    }

    public void payRent(Player player, JTextArea logArea)
    {
        if (owned && owner != player.getName())
        {
            determineRent();

            player.setCoins(player.getCoins() - rent);
            if (player.getCoins() < 0)
            {
                logArea.append(player.getName() + " don't have enough coins to pay the rent! They are eliminated!\n");
                JOptionPane.showMessageDialog(null, player.getName() + " is eliminated.");
                player.eliminate();
                this.playersOnThisSlot.remove(player);
                for (int i = 0; i < player.getProperties().size(); i++)
                    player.getProperties().get(i).resetSlot();
            } else
            {
                logArea.append(
                        player.getName() + " paid " + rent + " coins as rent to " + ownerPlayer.getName() + "\n");
            }
            ownerPlayer.setCoins(ownerPlayer.getCoins() + rent);
        }
    }

    public void handleBuyingForComputer(Player player, JTextArea logArea)
    {
        if (!owned && !isSpecial && player.getCoins() >= costToBuy)
        {
            if (random.nextInt(2) == 0)
            {
                player.setCoins(player.getCoins() - this.costToBuy);
                owned = true;
                owner = player.getName();
                ownerPlayer = player;
                player.getProperties().add(this);
                logArea.append("Slot" + name + " is bought by " + owner + "\n");
                logArea.append(owner + " have " + player.getCoins() + " coins left.\n");
            }
        }
    }

    public void handleSellingForComputer(Player player, JTextArea logArea)
    {
        if (!player.getProperties().isEmpty())
        {
            int randomInt = random.nextInt(10);
            if (randomInt == 0) // Sells the slot 1 in 10 chance.
            {
                int randomIndex = random.nextInt(player.getProperties().size());
                Slot randomSlot = player.getProperties().get(randomIndex);

                randomSlot.resetSlot();
                player.setCoins(player.getCoins() + randomSlot.getCostToBuy());
                player.getProperties().remove(randomSlot);
                logArea.append(player.getName() + " sold slot" + randomSlot.getName() + "\n");
            }
        }
    }

    public void handleBuildingForComputer(Player player, JTextArea logArea)
    {
        if (!player.getProperties().isEmpty() && player.getCoins() >= costToBuildHouse)
        {
            int randomInt = random.nextInt(10);
            if (randomInt < 5) // Computer builds half of the time.
            {
                int randomIndex = random.nextInt(player.getProperties().size());
                Slot randomSlot = player.getProperties().get(randomIndex); // Chooses a random slot.
                // Builds on that random slot if there are less than 4 houses.
                if (randomSlot.getNumOfHouses() < 4 && player.getCoins() >= randomSlot.getCostToBuildHouse())
                {
                    player.setCoins(player.getCoins() - randomSlot.getCostToBuildHouse());
                    randomSlot.increaseNumOfHousesByOne();
                    logArea.append(player.getName() + " bought house to slot" + randomSlot.getName() + "\n");
                }
            }
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
            }

        }
    }

    // drawSlot is responsible to show the slot in the UI.

    public void drawSlot(Graphics g, int x, int y)
    {
        int width = 120;

        // Draw header
        g.setColor(owned ? ownerPlayer.getColor() : Color.WHITE);
        g.fillRect(x, y, width, 20);
        g.setColor(Color.BLACK);
        g.drawString(name, x + 5, y + 15);

        // Draw houses
        for (int i = 0; i < numOfHouses; i++)
        {
            g.fillRect(x + width - 25 - (i * 15), y + 5, 10, 10);
        }

        // Draw separator
        g.setColor(Color.GRAY);
        g.drawLine(x, y + 19, x + width, y + 19);

        // Draw player area
        g.setColor(Color.WHITE);
        g.fillRect(x, y + 20, width, 100);

        // Draw players
        int[][] positions =
        {
                { x + 10, y + 30 },
                { x + 60, y + 30 },
                { x + 10, y + 80 },
                { x + 60, y + 80 } };
        for (int i = 0; i < playersOnThisSlot.size() && i < 4; i++)
        {
            g.setColor(playersOnThisSlot.get(i).getColor());
            g.fillOval(positions[i][0], positions[i][1], 40, 40);
        }
    }

    // Additional methods.

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

    public void increaseNumOfHousesByOne()
    {
        numOfHouses += 1;
        determineRent();
    }

    public void addPlayersOnThisSlot(Player player)
    {
        playersOnThisSlot.add(player);
    }

    public void resetSlot()
    {
        owned = false;
        owner = ".";
        numOfHouses = 0;
    }

    // Getters

    public int getRent()
    {
        return rent;
    }

    public int getCostToBuildHouse()
    {
        return costToBuildHouse;
    }

    public ArrayList<Player> getPlayersOnThisSlot()
    {
        return playersOnThisSlot;
    }

    public String getName()
    {
        return name;
    }

    public int getCostToBuy()
    {
        return costToBuy;
    }

    public int getNumOfHouses()
    {
        return numOfHouses;

    }

    public boolean getOwned()
    {
        return owned;
    }

    public boolean getIsSpecial()
    {
        return isSpecial;
    }

    // Setters

    public void setOwned(boolean owned)
    {
        this.owned = owned;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void setOwnerPlayer(Player ownerPlayer)
    {
        this.ownerPlayer = ownerPlayer;
    }
}
