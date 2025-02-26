import java.util.ArrayList;

/**
 * Word class represents a word. It is responsible for finding possible chains
 * for that word.
 */
public class Word
{
    private String wordString;
    private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private ArrayList<Word> possibleChains = new ArrayList<Word>();
    private ArrayList<String> possibilityList = new ArrayList<String>();

    /**
     * Word class constructer
     * 
     * @param wordString
     */
    public Word(String wordString)
    {
        this.wordString = wordString.toUpperCase();
        this.findPossibleStrings();
    }

    /**
     * Finds all the possible chain strings and adds them to possibilityList
     */
    private void findPossibleStrings()
    {

        // Add all the possibilities to the list where only one char is deleted.
        for (int i = 0; i < wordString.length(); i++)
        {
            StringBuilder sb = new StringBuilder(wordString);
            sb.deleteCharAt(i);
            possibilityList.add(sb.toString());
        }
        // Add all the possibilities to the list where only one char is added.
        for (int i = 0; i < alphabet.length; i++)
        {
            String currentChar = Character.toString(alphabet[i]);
            for (int j = 0; j <= wordString.length(); j++)
            {
                possibilityList
                        .add(wordString.substring(0, j) + currentChar + wordString.substring(j, wordString.length()));
            }
        }

    }

    /**
     * If the possibilityList contains the parameter otherWord, returns true.
     * Otherwise returns false.
     * 
     * @param otherWord
     * @return boolean
     */
    public boolean canChain(Word otherWord)
    {
        String otherWordString = otherWord.getWordString();
        if (getPossibilityList().contains(otherWordString))
            return true;
        else
            return false;
    }

    /**
     * Adds the specified word to possible chains.
     * 
     * @param chainWord
     */
    public void addToPossibleChains(Word chainWord)
    {
        this.possibleChains.add(chainWord);
    }

    /**
     * returns the word's string form.
     * 
     * @return
     */
    public String getWordString()
    {
        return this.wordString;
    }

    /**
     * returns the possible chains this word can form.
     * 
     * @return
     */
    public ArrayList<Word> getPossibleChains()
    {
        return possibleChains;
    }

    /**
     * returns the possible chain strings list, possibilityList.
     * 
     * @return
     */
    public ArrayList<String> getPossibilityList()
    {
        return possibilityList;
    }

    /**
     * String representation of a word is it's string form.
     */
    public String toString()
    {
        return getWordString();
    }
}