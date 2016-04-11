import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Class solving problem question 1 from part c of project 3.
 * Reads in the file and does the following:
 *      makes it into hashmap, BSTMap, and AVLMap
 *      finds the time for creation of each map and prints it.
 *      done on a list of 100, 1000, and 100,000 words
 */
public final class P3C2 {
    
    /** Number of words analyzed at max. */
    public static final int MAX_WORDS = 100000;

    /** Number of words analyzed at min. */
    public static final int MIN_WORDS = 100;
    
    /** Number of words analyzed at mid. */
    public static final int MID_WORDS = 1000;
    
    /**
     * Private constructor to avoid instantiation of utility class.
     */
    private P3C2() {
        
    }
      
    /**
     * Main method driving the class.
     * Loads in all data, preforms analysis
     * @param args is the string array of command line inputs
     */
    public static void main(String[] args) {
        // First we load all the data and store it in an array list
        // this way we can put the data into the different types of maps
        // without rereading the data from the file
        LinkedList<String> wordbank = new LinkedList();
        Scanner sc;
        try {
            sc = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.err.println("Sorry we couldn't find the input file.");
            return;
        }

        while (sc.hasNextLine()) {
            wordbank.add(sc.nextLine());
        }
        sc.close();

        trim(wordbank);

        // Put the words into the maps
        System.out.println("Wordbank size: " + wordbank.size());
        
        // Time build of hashmap        
        System.out.println("Timing HashMap:");
        long lStartTime = System.currentTimeMillis();
        
        HashMap<String, Integer> hashmap = new HashMap();
        for (String word : wordbank) {
            if (!hashmap.containsKey(word)) {
                hashmap.put(word, 1);
            } else {
                hashmap.put(word, (hashmap.get(word) + 1));
            }
        }

        long lEndTime = System.currentTimeMillis();
        long difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);

        // Time build of BSTMap
        System.out.println("Timing BSTMap:");
        lStartTime = System.currentTimeMillis();
        
        BSTMap<String, Integer> bst = new BSTMap();
        for (String word : wordbank) {
            if (!bst.hasKey(word)) {
                bst.put(word, 1);
            } else {
                bst.put(word, (bst.get(word) + 1));
            }
        }

        lEndTime = System.currentTimeMillis();
        difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);

        // Time build of AVLMap                
        System.out.println("Timing AVLMap:");
        lStartTime = System.currentTimeMillis();

        AVLMap<String, Integer> avl = new AVLMap();
        for (String word : wordbank) {
            if (!avl.hasKey(word)) {
                avl.put(word, 1);
            } else {
                avl.put(word, (avl.get(word) + 1));
            }
        }

        lEndTime = System.currentTimeMillis();
        difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);
    }

    /** trims the wordbank to 100, 1000, or 100000 words in length.
     * @param wordbank the original list of words
     */
    private static void trim(LinkedList<String> wordbank) {
        // Trim the wordbank to be 100, 1000, or 100000 words.
        if (wordbank.size() >= MAX_WORDS) {
            while (wordbank.size() > MAX_WORDS) {
                wordbank.removeLast();
            }
        } else if (wordbank.size() >= MID_WORDS) {
            while (wordbank.size() > MID_WORDS) {
                wordbank.removeLast();
            }
        } else {
            while (wordbank.size() > MIN_WORDS) {
                wordbank.removeLast();
            }
        }
    }
}