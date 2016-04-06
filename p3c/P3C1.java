import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Map;

public class P3C1 {
	public static void main(String[] args) {
		//First we load all the data and store it in an array list
		//this way we can put the data into the different types of maps
		//without rereading the data from the file
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
		BSTMap<String, Integer> bst = new BSTMap();
		AVLMap<String, Integer> avl = new AVLMap();

		//Put the words into the maps

		System.out.println("HashMap:");

		for (String word : wordbank) {
			if(!hash.containsKey(word)) {
				hash.put(word, 1);
			} else {
				hash.put(word, (hash.get(word) + 1));
			}
		}

		HashMap<Integer, LinkedList<String>> hashcounts = new HashMap();
		Integer currkey;
		LinkedList<String> currval;
		for (Map.Entry<String, Integer> entry : hash.entrySet()) {
			currkey = entry.getValue();
			if (hashcounts.containsKey(currkey)) {
				currval = hashcounts.get(currkey);
				currval.add(entry.getKey());
			} else {
				currval = new LinkedList();
				currval.add(entry.getKey());
			}
			hashcounts.put(currkey, currval);
		}

		int numwords = 0;
		int uniquewords = hash.values().size();
		for (int wordcount : hash.values()) {
			numwords += wordcount;
		}
		System.out.println("There are " + numwords + " words in the document.");
		System.out.println("There are " + uniquewords + " unique words in the document.");

		int largest = 0;
		for (int num : hashcounts.keySet()) {
			if (largest < num) {
				largest = num;
			}
		}
		System.out.println("The most frequent words are: " + hashcounts.get(largest)
			+ ", with a frequency of: " + largest);

		LinkedList<String> atmostthree = new LinkedList();
		atmostthree.addAll(hashcounts.get(1));
		atmostthree.addAll(hashcounts.get(2));
		atmostthree.addAll(hashcounts.get(3));
		System.out.println("The words that occur at most three times are: " + atmostthree);
	}
}