package Klasor;

import java.util.ArrayList;
import java.util.Scanner;

public class SkyBattleGame
{
    // Game Settings
    public final static int COLS = 10;
    public final static int ROWS = 10;
    public final static int INITIAL_HEALTH = 10;
    public final static int MAX_DARK_KNIGHT_SIZE = 5;

    // Directions
    public static final String MOVE_UP = "w";
    public static final String MOVE_DOWN = "s";
    public static final String STAY_STILL = "d";

    // Direction Values
    public static final int MOVE_UP_DIST = -1;
    public static final int MOVE_DOWN_DIST = 1;
    public static final int STAY_STILL_DIST = 0;

    // Symbols
    public final static char EMPTY_COL_SYMBOL = ' ';
    public final static char DARK_KNIGHT_SYMBOL = '♘';
    public final static char KNIGHT_SYMBOL = '♞';

    // Game Members
    public static int score = 0;
    private static BattleManager battleManager;
    private static Scanner scanner;

    public static void main(String[] args)
    {
        initializeGame();
        playGame();
        endGame();
    }

    /*
     * Initializes the battleManager to set up the game’s mechanics and starts the
     * scanner to read user input.
     */
    private static void initializeGame()
    {
        battleManager = new BattleManager();
        scanner = new Scanner(System.in);
    }

    /*
     * Runs the main game loop, which continues until the Knight’s health is
     * depleted. It repeatedly renders the game and handles user input.
     */
    private static void playGame()
    {
        while (battleManager.getKnight().isAlive())
        {
            renderGame();
            handleUserInput();
        }
    }

    /*
     * Prints the final score and closes the scanner when the game is over.
     */
    private static void endGame()
    {
        System.out.println("Game over! You have died. Your total score is: " + score);
        scanner.close();
    }

    /*
     * Displays the current state of the game by rendering the battleground and game
     * information (Knight's stats and points).
     */
    private static void renderGame()
    {
        Knight knight = battleManager.getKnight();
        ArrayList<BattleColumn> columns = battleManager.getColumns();

        System.out.println("=====================================");
        displayBattleground(knight, columns);
        displayGameInformation(knight);
        System.out.println("=====================================");
    }

    /*
     * Renders the grid of battle columns and displays the Knight's position within
     * the grid.
     */
    private static void displayBattleground(Knight knight, ArrayList<BattleColumn> columns)
    {
        for (int i = 0; i < ROWS; i++)
        {
            System.out.print("|");
            for (int j = 0; j < COLS; j++)
            {
                if (isKnightPositionedAt(knight, j, i))
                {
                    System.out.print(" " + knight.getSymbol() + " ");
                    continue;
                }
                BattleColumn column = columns.get(j);
                System.out.print(" " + column.getElements()[i] + " ");
            }
            System.out.println("|");
        }
    }

    /*
     * Prints the current game points and the Knight’s health.
     */
    private static void displayGameInformation(Knight knight)
    {
        System.out.println("Game point: " + score);
        System.out.println(knight);
    }

    /*
     * Reads the player's input for knight movements (up, down, or stay still) and
     * directs it to battleManager to execute.
     */
    private static void handleUserInput()
    {
        String direction;
        do
        {
            System.out.println("Enter a direction to move the knight (w/up, d/stay still, s/down):");
            direction = scanner.next();
        } while (!battleManager.handleMovement(direction));
    }

    /*
     * Checks whether or not the Knight is located at the given coordinates (x, y)
     * in the grid.
     */
    private static boolean isKnightPositionedAt(Knight knight, int x, int y)
    {
        return x == 0 && knight.getY() == y;
    }
}
