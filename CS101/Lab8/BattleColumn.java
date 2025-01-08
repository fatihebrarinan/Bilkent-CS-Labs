package Klasor;

import java.util.Random;

public class BattleColumn
{

    /*
     * An array of char representing the contents of the column. Each element in the
     * array corresponds to a column in the grid and can either be an empty space or
     * a Dark Knight.
     */
    char[] contentOfColumn;

    /*
     * Initializes a new column with a size corresponding to the number of rows in
     * the grid (SkyBattleGame.ROWS). It calls the generate() method to populate the
     * column with either empty spaces or Dark Knights depending on the
     * hasDarkKnights parameter.
     */

    public BattleColumn(boolean hasDarkKnights)
    {
        contentOfColumn = new char[SkyBattleGame.ROWS];
        generate(hasDarkKnights);
    }

    /*
     * Populates the column by first initializing all positions as empty
     * (initEmptyColumn()), and then randomly placing Dark Knights if hasDarkKnights
     * is true. It calls addDarkKnights() to handle the placement of Dark Knights.
     */
    public void generate(boolean hasDarkKnights)
    {
        this.initEmptyColumn();
        Random random = new Random();
        if (hasDarkKnights)
        {
            this.addDarkKnights(random);
        }
    }

    /*
     * Fills the column’s elements array with the empty column symbol
     * (SkyBattleGame.EMPTY_COL_SYMBOL), ensuring that all positions start as empty.
     */
    private void initEmptyColumn()
    {
        for (int i = 0; i < contentOfColumn.length; i++)
        {
            contentOfColumn[i] = SkyBattleGame.EMPTY_COL_SYMBOL;
        }
    }

    /*
     * Adds a random number of Dark Knights (between 1 and the configured maximum,
     * SkyBattleGame.MAX_DARK_KNIGHT_SIZE) to random positions in the column using
     * the Random object. Each selected position is replaced by the Dark Knight
     * symbol (SkyBattleGame.DARK_KNIGHT_SYMBOL). Notice that if the same location
     * is chosen multiple times, fewer dark nights will be created.
     */
    private void addDarkKnights(Random random)
    {
        int knightNum = random.nextInt(1, SkyBattleGame.MAX_DARK_KNIGHT_SIZE + 1);

        for (int i = 0; i < knightNum; i++)
        {
            int randomIndex = random.nextInt(SkyBattleGame.ROWS);
            this.contentOfColumn[randomIndex] = SkyBattleGame.DARK_KNIGHT_SYMBOL;
        }
    }

    /*
     * Returns the array of char representing the contents of the column. Each
     * element in the array corresponds to a row and can either be empty or contain
     * a Dark Knight.
     */
    public char[] getElements()
    {
        return contentOfColumn;
    }
}
