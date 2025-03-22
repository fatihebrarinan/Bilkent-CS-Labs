import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MapPanel extends JPanel
{
    Board board;

    public MapPanel(Board board)
    {
        this.board = board;
        setPreferredSize(new Dimension(600, 600));
        setLayout(new GridLayout(5, 5));
    }

    public void drawEachSlot(Graphics g)
    {
        for (int i = 0; i < 16; i++)
        {
            Slot slot = board.getSlot(i);
            int[] pos = getPosition(i);
            int row = pos[0];
            int col = pos[1];
            int cellSize = 120; // 600 / 5 = 120
            int x = col * cellSize;
            int y = row * cellSize;
            slot.drawSlot(g, x, y);
        }
        for (int i = 0; i < 5; i++)
        {
            g.setColor(Color.BLACK);
            g.drawLine(120 * i, 0, 120 * i, 600);
            g.drawLine(0, 120 * i, 600, 120 * i);
        }

    }

    private int[] getPosition(int index)
    {
        int[] pos = new int[2]; // row, column
        if (index <= 4)
        {
            pos[0] = 0;
            pos[1] = index;
        } else if (index <= 7)
        {
            pos[0] = index - 4;
            pos[1] = 4;
        } else if (index <= 12)
        {
            pos[0] = 4;
            pos[1] = 12 - index;
        } else
        { // indices 13-15
            pos[0] = 15 - index + 1;
            pos[1] = 0;
        }
        return pos;
    }

    @Override
    protected void paintComponent(Graphics graph)
    {
        super.paintComponent(graph);
        drawEachSlot(graph);
    }

    public void refresh()
    {
        repaint();
    }
}
