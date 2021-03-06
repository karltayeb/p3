import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Karl Tayeb ktayeb1
 * Ryan Dens rdens1
 * Section: 02
 * Assignment: P3C
 * Due: 4/11/2016
 */

/**
 * Class solving problem question 1 from part c of project 3.
 * Reads in all words from input file, prints out:
 *      total number of words
 *      most frequent word(s)
 *      All words that occur at most 3 times
 *      The top 10% of all words sorted by frequency. 
 */
public final class P3C1 {
    /** Makes it so that words that occur <= 3 times are printed. */
    public static final int LEAST_TIMES = 3;
    /** Makes it so words that are in the top 10% of frequency are printed. */
    public static final double TOP_PERCENTAGE = 0.1;
    
    /**
     * Private constructor to avoid instantiation of utility class.
     */
    private P3C1() {
        
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("You need to provde a file.");
            return;
        }

        while (sc.hasNextLine()) {
            wordbank.add(sc.nextLine());
        }
        sc.close();

        HashMap<String, Integer> hash = new HashMap();

        // Put the words into the maps
        for (String word : wordbank) {
            if (!hash.containsKey(word)) {
                hash.put(word, 1);
            } else {
                hash.put(word, (hash.get(word) + 1));
            }
        }

        // Create an AVL map with
        // key = word frequency, value = list of words with that frequency
        AVLMap<Integer, LinkedList<String>> avlcounts = new AVLMap();
        Integer currkey;
        LinkedList<String> currval;
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            currkey = entry.getValue();
            if (avlcounts.hasKey(currkey)) {
                currval = avlcounts.get(currkey);
                currval.add(entry.getKey());
            } else {
                currval = new LinkedList();
                currval.add(entry.getKey());
            }
            avlcounts.put(currkey, currval);
        }

        // Number of words in the document
        int numwords = 0;
        int uniquewords = 0;
        for (int frequency : avlcounts.keys()) {
            numwords += frequency * avlcounts.get(frequency).size();
            uniquewords += avlcounts.get(frequency).size();
        }
        System.out.println("There are " + numwords + " words in the document.");
        System.out.println("There are " + uniquewords
                        + " unique words in the document.");

        // Most frequent words in the document
        int largest = 0;
        for (int num : avlcounts.keys()) {
            if (largest < num) {
                largest = num;
            }
        }
        System.out.println("\nThe most frequent words are: "
                        + avlcounts.get(largest) 
                        + ", with a frequency of: " + largest);

        // Words that occur at most three times
        LinkedList<String> atmostthree = new LinkedList();
        for (int i = 1; i <= LEAST_TIMES; i++) {
            LinkedList<String> temp = avlcounts.get(i);
            if (temp != null) {
                atmostthree.addAll(temp);
            }
        }

        System.out.println("\nNumber of words that occur at most three times: "
                        + atmostthree.size());
        System.out.println("The words that occur at most three times are: "
                        + atmostthree);

        // Top 10% of most frequent words
        LinkedList<String> tenpercent = new LinkedList();
        tenpercent.addAll(avlcounts.get(largest));
        double proportion = (double) tenpercent.size() / uniquewords;
        int next = largest - 1;
        while (proportion < TOP_PERCENTAGE) {
            LinkedList<String> nextlist = avlcounts.get(next--);
            if (nextlist != null) {
                tenpercent.addAll(nextlist);
                proportion = (double) tenpercent.size() / uniquewords;
            }
        }
        System.out.println("\nNumber of words in top 10% by frequency: "
                        + tenpercent.size());
        System.out.println("The top 10% of words by frequency: " + tenpercent);

    }
}
