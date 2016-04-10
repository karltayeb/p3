import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class P3C2 {
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
		}

		while (sc.hasNextLine()) {
			wordbank.add(sc.nextLine());
		}
		sc.close();

		// Trim the wordbank to be 100, 1000, or 100000 words.
		if (wordbank.size() >= 100000){
			while (wordbank.size() > 100000) {
				wordbank.removeLast();
			}
		} else if (wordbank.size() >= 1000) {
			while (wordbank.size() > 1000) {
				wordbank.removeLast();
			}
		} else {
			while (wordbank.size() > 100) {
				wordbank.removeLast();
			}			
		}

		HashMap<String, Integer> hashmap = new HashMap();
		BSTMap<String, Integer> bst = new BSTMap();
		AVLMap<String, Integer> avl = new AVLMap();

		//Put the words into the maps

		System.out.println("Timing HashMap:");

		long lStartTime = System.currentTimeMillis();

		for (String word : wordbank) {
			if(!hashmap.containsKey(word)) {
				hashmap.put(word, 1);
			} else {
				hashmap.put(word, (hashmap.get(word) + 1));
			}
		}

		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		System.out.println("Elapsed milliseconds: " + difference);

		System.out.println("Timing BSTMap:");
		lStartTime = System.currentTimeMillis();

		for (String word : wordbank) {
			if(!bst.hasKey(word)) {
				bst.put(word, 1);
			} else {
				bst.put(word, (bst.get(word) + 1));
			}
		}

		lEndTime = System.currentTimeMillis();
		difference = lEndTime - lStartTime;
		System.out.println("Elapsed milliseconds: " + difference);

		System.out.println("Timing AVLMap:");
		lStartTime = System.currentTimeMillis();

		for (String word : wordbank) {
			if(!avl.hasKey(word)) {
				avl.put(word, 1);
			} else {
				avl.put(word, (avl.get(word) + 1));
			}
		}

		lEndTime = System.currentTimeMillis();
		difference = lEndTime - lStartTime;
		System.out.println("Elapsed milliseconds: " + difference);

	}
}