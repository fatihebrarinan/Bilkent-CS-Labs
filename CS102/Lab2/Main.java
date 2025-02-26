import java.io.FileNotFoundException;
import java.util.*;

/**
 * Test class.
 */
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        Map<String, Word> wordMap = new HashMap<>();
        Random random = new Random();
        boolean isOn = true;

        // allWords is created with every word's possible chains.
        TextReader textReader = new TextReader();
        textReader.readFile("words.txt");
        textReader.findPossibleChains();

        for (Word word : textReader.getAllWords())
        {
            wordMap.put(word.getWordString(), word); // Putting them in a hashmap significantly reduces the time to
                                                     // search for a word.
        }

        // Main loop, menu.
        while (isOn)
        {
            System.out.println(
                    "Welcome to the chain program! What do you want to do?\n0.Exit program\n1. Play word chain guessing game\n2.Generate word chains");
            int menuInput = scanner.nextInt();
            scanner.nextLine();
            switch (menuInput)
            {
            case 0:
                System.out.println("Exiting program. Goodbye!");
                isOn = false;
                break;
            case 1:
                playChainGame(scanner, wordMap, random, textReader);
                break;
            case 2:
                generateChainApp(scanner, wordMap, random);
                break;

            default:
                System.out.println("Invalid input.");
                break;
            }

        }
        scanner.close();

    }

    /**
     * This method generates a chain from a user input, max lenght of the chain is
     * 10.
     * 
     * @param scanner
     * @param wordMap
     * @param random
     */
    public static void generateChainApp(Scanner scanner, Map<String, Word> wordMap, Random random)
    {
        while (true)
        {
            System.out.print("Enter a word to start the chain with (or -1 to return menu): ");
            String inputString = scanner.nextLine().toUpperCase();

            if (inputString.equals("-1"))
            {
                System.out.println("Returning to menu.");
                break;
            }

            Word currentWord = wordMap.get(inputString);

            if (currentWord == null)
            {
                System.out.println("Word not found in list. Try again.");
                continue;
            }

            System.out.println("Generated Chain: " + generateChain(currentWord, random, 10));// Uses generateChain
                                                                                             // method.
        }
    }

    /**
     * This method generates a 3 item long chain list and asks the user to find the
     * middle one.
     * 
     * @param scanner
     * @param wordMap
     * @param random
     * @param textReader
     */
    public static void playChainGame(Scanner scanner, Map<String, Word> wordMap, Random random, TextReader textReader)
    {
        while (true)
        {
            ArrayList<Word> currentChain = null;
            do
            {
                int randomIndex = random.nextInt(textReader.getAllWords().size());
                Word randomWord = textReader.getAllWords().get(randomIndex);
                currentChain = generateChain(randomWord, random, 3);
            } while (currentChain.size() < 3); // Generates a chain until its length is 3.

            System.out.printf("%s - ? - %s\nGuess the word. You have 3 guesses. (or -1 to return menu): ",
                    currentChain.get(0), currentChain.get(2));
            String inputString = scanner.nextLine().toUpperCase();
            boolean isCorrect = false;
            if (inputString.equals("-1"))
            {
                System.out.println("Returning to menu.");
                break;
            } else if (inputString.equals(currentChain.get(1).getWordString()))
            {
                System.out.printf("Thats correct!\n");
                isCorrect = true;
            } else
            {
                for (int i = 0; i < 2; i++)// If the user's guess is wrong, ask them 2 more times.
                {
                    System.out.printf("Thats wrong! Try again: ");
                    inputString = scanner.nextLine().toUpperCase();
                    if (inputString.equals(currentChain.get(1).getWordString()))
                    {
                        System.out.printf("Thats correct!\n");
                        isCorrect = true;
                        break;
                    }
                }
                if (!isCorrect)// If they are not able to find still, give them the answer.
                {
                    System.out.printf("The answer was %s!\n", currentChain.get(1).getWordString());
                }

            }
        }
    }

    /**
     * 
     * @param currentWord
     * @param random
     * @param num
     * @return
     */
    public static ArrayList<Word> generateChain(Word currentWord, Random random, int num)
    {
        ArrayList<Word> currentChain = new ArrayList<>();
        int counter = 0;

        while (!currentChain.containsAll(currentWord.getPossibleChains()) && counter < num)// Ensure that there will not
                                                                                           // be duplicate.
        {
            ArrayList<Word> possibleChains = currentWord.getPossibleChains();
            if (possibleChains.isEmpty())
                break;

            Word nextWord = possibleChains.get(random.nextInt(possibleChains.size()));// Get a random word from the
                                                                                      // possibleChains

            if (!currentChain.contains(nextWord))
            {
                currentWord = nextWord;
                currentChain.add(currentWord);
            } else
            {
                counter--;// If the random word is in the current chain, don't count this round (<BUG
                          // HERE>)
            }

            counter++;
        }
        return currentChain;
    }

}
