package Klasor;

import java.util.ArrayList;

public class BattleManager
{
    // Instance Variables
    private final Knight knight; // A Knight instance representing the player-controlled Knight
    private final ArrayList<BattleColumn> columns; // BattleColumn objects for columns in the game grid

    /*
     * Initializes the columns list with a number of BattleColumn instances based on
     * SkyBattleGame.COLS. The Knight is initialized with y=0 position, initial
     * health (SkyBattleGame.INITIAL_HEALTH), and a knight symbol
     * (SkyBattleGame.KNIGHT_SYMBOL).
     */
    public BattleManager()
    {
        this.knight = new Knight(0, SkyBattleGame.INITIAL_HEALTH, SkyBattleGame.KNIGHT_SYMBOL);
        this.columns = new ArrayList<BattleColumn>();
        for (int i = 0; i < SkyBattleGame.COLS; i++)
        {
            columns.add(new BattleColumn(false));
        }
    }

    /*
     * Takes the player’s input (direction) and adjusts the Knight's y position
     * accordingly. It checks for valid movement directions (up, down, stay still),
     * ensures the knight does not go out of bounds. If the move is valid, the
     * shiftGrid method is called first to shift the grid to the left, followed by
     * the move method, which updates the knight's position and checks for
     * collisions. Shifting the grid before moving ensures accurate collision
     * detection based on the knight's new position. The method returns true if the
     * move is valid and successful, or false if the move is invalid or out of
     * bounds.
     */
    public boolean handleMovement(String direction)
    {
        if (direction.equals(SkyBattleGame.MOVE_UP))
        {
            // Make sure knight dont go out of bound.
            if (knight.getY() == 0)
            {
                System.out.println("The knight can't go beyond the sky!");
                return false;
            }
            shiftGrid();
            move(SkyBattleGame.MOVE_UP_DIST);
            return true;
        } else if (direction.equals(SkyBattleGame.MOVE_DOWN))
        {
            // Make sure knight dont go out of bound.
            if (knight.getY() == 9)
            {
                System.out.println("The knight can't dive underground!");
                return false;
            }
            shiftGrid();
            move(SkyBattleGame.MOVE_DOWN_DIST);
            return true;

        } else if (direction.equals(SkyBattleGame.STAY_STILL))
        {
            shiftGrid();
            move(SkyBattleGame.STAY_STILL_DIST);
            return true;

        } else
        {
            System.out.println("Invalid direction.");
            return false;
        }

    }

    /*
     * Updates the Knight's y position by the provided yDist and checks the position
     * for Dark Knights. If the Knight collides with a Dark Knight, its health is
     * reduced by 1, and an attack message is displayed using
     * displayAttackMessage(). Otherwise, the player's score (SkyBattleGame.score)
     * increases by 1.
     */
    private void move(int yDist)
    {
        knight.setY(yDist + knight.getY());
        if (columns.get(0).getElements()[knight.getY()] == SkyBattleGame.DARK_KNIGHT_SYMBOL)
        {
            knight.setHealth(knight.getHealth() - 1);
            displayAttackMessage();
        } else
        {
            SkyBattleGame.score++;
        }
    }

    /*
     * Shifts the grid by moving each column to the left and generating a new column
     * at the rightmost position.
     */
    private void shiftGrid()
    {
        for (int i = 1; i < columns.size(); i++)
        {
            columns.set(i - 1, columns.get(i));
        }
        columns.remove(9);
        BattleColumn e = new BattleColumn(true);
        columns.add(e);
    }

    /*
     * Displays a message to the player when the Knight is attacked by a Dark
     * Knight.
     */
    private void displayAttackMessage()
    {
        System.out.println("!!!!!!!!!!!!!!!\nYou were attacked by a dark knight!\n!!!!!!!!!!!!!!!");
    }

    // Getters

    /*
     * Returns the current instance of the knight.
     */
    public Knight getKnight()
    {
        return knight;
    }

    /*
     * Returns the ArrayList of columns representing the current game grid.
     */
    public ArrayList<BattleColumn> getColumns()
    {
        return columns;
    }
}
