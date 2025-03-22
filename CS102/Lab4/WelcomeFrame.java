import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame
{
    private JTextField[] playerFields;

    public WelcomeFrame()
    {
        super("Monopoly - Welcome");
        setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns

        // Labels and text fields for players
        String[] labels =
        { "Red Player (You):", "Blue Player:", "Green Player:", "Yellow Player:" };
        playerFields = new JTextField[4];
        Color[] colors =
        { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
        for (int i = 0; i < 4; i++)
        {
            JLabel label = new JLabel(labels[i]);
            label.setForeground(colors[i]);
            add(label);
            playerFields[i] = new JTextField("Player " + (i + 1));
            add(playerFields[i]);
        }

        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String[] names = new String[4];
                for (int i = 0; i < 4; i++)
                {
                    names[i] = playerFields[i].getText();
                }
                dispose(); // Close welcome frame
                new GameTest(names); // Open main game frame
            }
        });
        add(startButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(250, 250));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
