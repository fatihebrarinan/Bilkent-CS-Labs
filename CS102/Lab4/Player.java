import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Player
{
    private int coins;
    private Color color;
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

    public Player(String name, Board board, boolean isUser, Color color)
    {
        coins = 10;
        this.color = color;
        this.name = name;
        this.board = board;
        this.isUser = isUser;
        this.currentSlot = board.getSlot(0);
        currentSlot.addPlayersOnThisSlot(this);
        isEliminated = false;

    }

    // Play round is the main method where the game is played.
    public void playRound(ArrayList<Player> players, JTextArea logArea, HashMap<String, JButton> buttons,
            GameTest gameTest)
    {
        if (isSkipTurn)
        {
            logArea.append(name + " is skipping this turn.\n");
            if (!isUser)
            {
                isSkipTurn = false;

            }
            return;
        }
        if (!isUser && currentSlot != null && currentSlot.getPlayersOnThisSlot().contains(this))
            currentSlot.getPlayersOnThisSlot().remove(this);

        if (!isUser) // If the player is not user, roll automatically.
        {
            int diceFace = random.nextInt(1, 7);
            logArea.append(this.name + " rolled " + diceFace + "\n");
            boardCounter += diceFace;
            if (boardCounter > 15)
            {
                if (boardCounter == 16)
                    coins += 6;
                else
                    coins += 3;

                boardCounter = boardCounter % 16;
            }
            // Determine the player-slot relation
            currentSlot = board.getSlot(boardCounter);
            currentSlot.getPlayersOnThisSlot().add(this);

            // Determine buying for computer
            currentSlot.handleBuyingForComputer(this, logArea);

            // Determine and pay rents for computer

            currentSlot.payRent(this, logArea);

            // Handle special cases for computer

            currentSlot.handleSpecials(this, players, logArea);

            // Determine selling for computer

            currentSlot.handleSellingForComputer(this, logArea);

            // Determine building for computer

            currentSlot.handleBuildingForComputer(this, logArea);

        } else // If the player is user, hit the button to roll.
        {
            gameTest.setUserTurn(true);
            buttons.get("Roll").setEnabled(true);
        }

    }

    public void eliminate()
    {
        isEliminated = true;
    }

    // Getters

    public int getCoins()
    {
        return coins;
    }

    public Color getColor()
    {
        return color;
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

    public boolean getIsEliminated()
    {
        return isEliminated;
    }

    public int getBoardCounter()
    {
        return boardCounter;
    }

    public boolean getIsSkipTurn()
    {
        return isSkipTurn;
    }

    // Setters

    public void setBoardCounter(int boardCounter)
    {
        this.boardCounter = boardCounter;
    }

    public void setIsSkipTurn(boolean isSkipTurn)
    {
        this.isSkipTurn = isSkipTurn;
    }

    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

}
