import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is responsible for reading the file and finding the possible
 * chains for each word.
 */
public class TextReader
{
    private ArrayList<Word> allWords = new ArrayList<Word>();

    /**
     * Appends every new line item in words.txt to the allWords ArrayList.
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    public void readFile(String fileName) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(fileName));

        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            allWords.add(new Word(line));

        }

        sc.close();
    }

    /**
     * Finds all of the possible chains for each word in allWords. If a word don't
     * have any possible chains, it is deleted from allWords.
     */
    public void findPossibleChains()
    {
        Map<String, Word> wordMap = new HashMap<>();

        for (Word word : allWords)
        {
            wordMap.put(word.getWordString(), word);
        }

        ArrayList<Word> filteredWords = new ArrayList<Word>();
        // Loop through allWords
        for (Word word : allWords)
        {
            boolean hasChain = false;

            // Loop through all the possible chain strings of that word.
            for (String possibleWord : word.getPossibilityList())
            {
                if (wordMap.containsKey(possibleWord))
                {
                    Word otherWord = wordMap.get(possibleWord);
                    // If the string is both in allWords and possibilityList, it is added to that
                    // word's possibleChains ArrayList.
                    word.addToPossibleChains(otherWord);
                    otherWord.addToPossibleChains(word);
                    hasChain = true;
                }
            }

            if (hasChain)
            {
                filteredWords.add(word); // Only store words that can chain
            }
        }

        allWords = filteredWords; // Replace with optimized list
    }

    /**
     * returns the ArrayList of all the word objects.
     * 
     * @return
     */
    public ArrayList<Word> getAllWords()
    {
        return allWords;
    }

}
