import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameTest extends JFrame
{
    private boolean endOfGame = false;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Board board = new Board();
    JTextArea logArea;
    JButton rollButton, buyButton, endTurnButton, sellButton, buildButton;
    HashMap<String, JButton> buttons;
    private Player user;
    private boolean isUserTurn = false;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    private int currentPlayerIndex = 0;
    MapPanel mapPanel;
    int turn = 1;

    // Constructer of GameTest
    public GameTest(String[] names)
    {
        // Set the players.
        user = new Player("", board, true, Color.RED);
        players.add(user);
        players.add(new Player("", board, false, Color.YELLOW));
        players.add(new Player("", board, false, Color.BLUE));
        players.add(new Player("", board, false, Color.GREEN));

        // Set the names. ANd shuffle ArrayList
        for (int i = 0; i < players.size(); i++)
        {
            players.get(i).setName(names[i]);
        }
        Collections.shuffle(players);

        // Set the layout to BorderLayout.
        setLayout(new BorderLayout());

        // Determine the panels.

        // Map Panel
        mapPanel = new MapPanel(board);
        add(mapPanel, BorderLayout.WEST);

        // Log Panel
        logArea = new JTextArea(10, 300);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Button Panel
        buttons = new HashMap<String, JButton>();

        JPanel controlPanel = new JPanel(new GridLayout(5, 1));

        rollButton = new JButton("Roll");
        buttons.put("Roll", rollButton);
        buyButton = new JButton("Buy");
        buttons.put("Buy", buyButton);
        endTurnButton = new JButton("End Turn");
        buttons.put("End Turn", endTurnButton);
        sellButton = new JButton("Sell");
        buttons.put("Sell", sellButton);
        buildButton = new JButton("Build");
        buttons.put("Build", buildButton);

        rollButton.setEnabled(false);
        buyButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        sellButton.setEnabled(false);
        buildButton.setEnabled(false);

        rollButton.addActionListener(e -> handleRollForUser());
        buyButton.addActionListener(e -> handleBuyForUser());
        endTurnButton.addActionListener(e -> endUserTurn());
        sellButton.addActionListener(e -> handleSellForUser());
        buildButton.addActionListener(e -> handleBuildForUser());

        controlPanel.add(rollButton);
        controlPanel.add(buyButton);
        controlPanel.add(endTurnButton);
        controlPanel.add(sellButton);
        controlPanel.add(buildButton);

        add(controlPanel, BorderLayout.EAST);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        loopGame();
    }

    public void handleRollForUser()
    {
        if (user.currentSlot != null)
            user.currentSlot.getPlayersOnThisSlot().remove(user);

        // Roll dice for player.
        int diceFace = random.nextInt(1, 7);
        logArea.append(this.user.getName() + " rolled " + diceFace + "\n");
        user.setBoardCounter(diceFace + user.getBoardCounter());
        if (user.getBoardCounter() > 15)
        {
            if (user.getBoardCounter() == 16)
                user.setCoins(user.getCoins() + 6);
            else
                user.setCoins(user.getCoins() + 3);

            user.setBoardCounter(user.getBoardCounter() % 16);
        }
        // Determine the user-slot relation
        user.currentSlot = board.getSlot(user.getBoardCounter());
        user.currentSlot.getPlayersOnThisSlot().add(user);

        // Handle specials and rents
        mapPanel.refresh();
        user.currentSlot.handleSpecials(user, players, logArea);
        user.currentSlot.payRent(user, logArea);

        // Disable rollButton
        rollButton.setEnabled(false);

        // Enable relevant buttons.
        endTurnButton.setEnabled(true);

        if (!user.currentSlot.getOwned() && !user.currentSlot.getIsSpecial()
                && user.getCoins() >= user.currentSlot.getCostToBuy())
            buyButton.setEnabled(true);

        if (!user.getProperties().isEmpty())
        {
            sellButton.setEnabled(true);
            buildButton.setEnabled(true);
        }

    }

    public void handleBuyForUser()
    {
        user.setCoins(user.getCoins() - user.currentSlot.getCostToBuy());
        user.currentSlot.setOwned(true);
        user.currentSlot.setOwner(user.getName());
        user.currentSlot.setOwnerPlayer(user);
        user.getProperties().add(user.currentSlot);
        logArea.append("Slot" + user.currentSlot.getName() + " is bought by " + user.getName() + "\n");
        logArea.append(user.getName() + " have " + user.getCoins() + " coins left.\n");
        buyButton.setEnabled(false);
        mapPanel.refresh();
    }

    public void handleSellForUser()
    {
        JDialog sellDialog = new JDialog(this, "Sell Property", true); // Use modal JDialog
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel text = new JLabel("Which slot do you want to sell?");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(text);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Use a final array to bypass lambda's "effectively final" restriction
        final Slot[] selectedSlot =
        { null };

        // Add buttons for each property
        for (Slot slot : user.getProperties())
        {
            JButton button = new JButton("Sell " + slot.getName() + " for " + slot.getCostToBuy());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                selectedSlot[0] = slot; // Set the selected slot
                sellDialog.dispose(); // Close the dialog
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Add a cancel button
        JButton returnButton = new JButton("Return");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> sellDialog.dispose());
        panel.add(returnButton);

        // Configure dialog
        sellDialog.add(panel);
        sellDialog.setPreferredSize(new Dimension(250, 250));
        sellDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sellDialog.pack();
        sellDialog.setLocationRelativeTo(this);
        sellDialog.setVisible(true); // Blocks until dialog is closed

        // Process the selection AFTER the dialog is closed
        if (selectedSlot[0] != null)
        {
            selectedSlot[0].resetSlot();
            user.setCoins(user.getCoins() + selectedSlot[0].getCostToBuy());
            user.getProperties().remove(selectedSlot[0]);
            logArea.append(user.getName() + " sold slot " + selectedSlot[0].getName() + "\n");
            JOptionPane.showMessageDialog(null,
                    "You sold " + selectedSlot[0].getName() + " for " + selectedSlot[0].getCostToBuy() + " coins.");
        }
        sellButton.setEnabled(false);
        mapPanel.refresh();
    }

    public void handleBuildForUser()
    {
        JDialog sellDialog = new JDialog(this, "Build Property", true); // Use modal JDialog
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel text = new JLabel("Which slot do you want to build on?");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(text);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Use a final array to bypass lambda's "effectively final" restriction
        final Slot[] selectedSlot =
        { null };

        // Add buttons for each property
        for (Slot slot : user.getProperties())
        {
            JButton button = new JButton("Build on " + slot.getName());
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> {
                selectedSlot[0] = slot; // Set the selected slot
                sellDialog.dispose(); // Close the dialog
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Add a cancel button
        JButton returnButton = new JButton("Return");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> sellDialog.dispose());
        panel.add(returnButton);

        // Configure dialog
        sellDialog.add(panel);
        sellDialog.setPreferredSize(new Dimension(250, 250));
        sellDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sellDialog.pack();
        sellDialog.setLocationRelativeTo(this);
        sellDialog.setVisible(true); // Blocks until dialog is closed

        // Process the selection AFTER the dialog is closed
        if (selectedSlot[0] != null)
        {
            // Builds on the selected slot if there are less than 4 houses.
            if (selectedSlot[0].getNumOfHouses() < 4 && user.getCoins() >= selectedSlot[0].getCostToBuildHouse())
            {
                user.setCoins(user.getCoins() - selectedSlot[0].getCostToBuildHouse());
                selectedSlot[0].increaseNumOfHousesByOne();
                logArea.append(user.getName() + " built house on slot " + selectedSlot[0].getName()
                        + ". The rent is now " + selectedSlot[0].getRent() + "\n");
                JOptionPane.showMessageDialog(null, "You built house on " + selectedSlot[0].getName()
                        + ". The rent is now " + selectedSlot[0].getRent() + "\n");
            } else
                JOptionPane.showMessageDialog(null, "Couldn't build house on this slot!");
        }
        buildButton.setEnabled(false);
        mapPanel.refresh();
    }

    public void endUserTurn()
    {
        isUserTurn = false;
        endTurnButton.setEnabled(false);
        buyButton.setEnabled(false);
        sellButton.setEnabled(false);
        buildButton.setEnabled(false);

        currentPlayerIndex++;
        SwingUtilities.invokeLater(this::loopGame);
    }

    // Start the rounds

    public void loopGame()
    {

        if (endOfGame)
            return;

        // Reset index if out of bounds
        if (currentPlayerIndex >= players.size())
        {
            currentPlayerIndex = 0;
            turn++;
        }

        Player currentPlayer = players.get(currentPlayerIndex);

        // Skip eliminated players
        if (currentPlayer.getIsEliminated())
        {
            currentPlayerIndex++;
            SwingUtilities.invokeLater(this::loopGame);
            return;
        }

        if (currentPlayer.getIsUser())
        {
            // User's turn
            setUserTurn(true);
            currentPlayer.playRound(players, logArea, buttons, this);
            if (currentPlayer.getIsSkipTurn())
            {
                currentPlayer.setIsSkipTurn(false); // Reset flag
                currentPlayerIndex++;
                SwingUtilities.invokeLater(this::loopGame);
            }
        } else
        {
            // Computer's turn: proceed automatically
            currentPlayer.playRound(players, logArea, buttons, this);
            checkForElimination();
            currentPlayerIndex++;

            if (!currentPlayer.getIsEliminated())
            {
                Timer timer = new Timer(1000, e -> SwingUtilities.invokeLater(this::loopGame));
                timer.setRepeats(false);
                timer.start();
            }
        }
        mapPanel.refresh();

    }

    private void checkForElimination()
    {
        for (int j = 0; j < players.size(); j++)
        {
            if (players.get(j).getIsEliminated())
            {
                players.remove(j);
                j--; // Adjust index after removal
            }
        }
        if (players.size() == 1)
        {
            logArea.append("The winner is " + players.get(0).getName() + "\n");
            JOptionPane.showMessageDialog(null, players.get(0).getName() + " won the game on turn " + turn);
            endOfGame = true;
        }
    }

    public void setUserTurn(boolean userTurn)
    {
        this.isUserTurn = userTurn;
    }

}