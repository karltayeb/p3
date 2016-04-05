import java.util.Map;
import java.lang.Math;
import java.util.HashSet;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;

public class test {
	public static void main(String[] args){
		//testSingleRight();
		//testLeftRight();
		//testSingleLeft();
		//testRightLeft();
		//randomInsert();
		//testEntriesKeysValues();
		testSubmap();
		testRemoveLeaves();
		testRemoveSingleChild();
		testRemoveDoubleChild();
	}

	private static void testSingleRight() {
		AVLMap<Integer, String> map = new AVLMap();
		System.out.println("Testing Single Right Rotation");
		map.put(64,"thirtytwo");
		map.put(32, "sixteen");
		map.put(16, "sixtyfour");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed Right Rotate 1");
		} else {
			System.out.println("Failed Right Rotate 1");
		}

		map.clear();
		map.put(32,"thirtytwo");
		map.put(16, "sixteen");
		map.put(64, "sixtyfour");
		map.put(8, "eight");
		map.put(4, "four");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed Right Rotate 2");
		} else {
			System.out.println("Failed Right Rotate 2");
		}
		System.out.println();

		map.clear();
		map.put(128,"");
		map.put(32, "");
		map.put(512, "");
		map.put(16, "");
		map.put(64, "");
		map.put(256,"");
		map.put(1024, "");
		map.put(8, "");
		map.put(24, "");
		map.put(4, "");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 128 && map.isBalanced()) {
			System.out.println("Passed Right Rotate 3");
		} else {
			System.out.println("Failed Right Rotate 3");
		}
		System.out.println();
	}

	private static void testSingleLeft() {
		System.out.println("Testing Single Left Rotation");
		AVLMap<Integer, String> map = new AVLMap();

		map.put(16, "sixtyfour");
		map.put(32, "sixteen");
		map.put(64,"thirtytwo");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed Left Rotate 1");
		} else {
			System.out.println("Failed Left Rotate 1");
		}

		map.clear();
		map.put(32,"thirtytwo");
		map.put(16, "sixteen");
		map.put(64, "sixtyfour");
		map.put(128, "onetwentyeight");
		map.put(256, "twofiftysix");



		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed Right Rotate 2");
		} else {
			System.out.println("Failed Right Rotate 2");
		}
		System.out.println();

		map.clear();
		map.put(128,"");
		map.put(32, "");
		map.put(512, "");
		map.put(16, "");
		map.put(64, "");
		map.put(256,"");
		map.put(1024, "");
		map.put(1000, "");
		map.put(2000, "");
		map.put(3000, "");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 128 && map.isBalanced()) {
			System.out.println("Passed Left Rotate 3");
		} else {
			System.out.println("Failed Left Rotate 3");
		}
		System.out.println();
	}

	private static void testLeftRight() {
		System.out.println("Testing Left Right Rotation");
		AVLMap<Integer, String> map = new AVLMap();

		map.put(64,"thirtytwo");
		map.put(32, "sixteen");
		map.put(40, "fourty");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced());
		System.out.println(map);
		if (map.root() == 40 && map.isBalanced()) {
			System.out.println("Passed LeftRight Rotate 1");
		} else {
			System.out.println("Failed LeftRight Rotate 1");
		}

		map.clear();
		map.put(32,"thirtytwo");
		map.put(16, "sixteen");
		map.put(64, "sixtyfour");
		map.put(8, "eight");
		map.put(9, "nine");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed LeftRight Rotate 2");
		} else {
			System.out.println("Failed LeftRight Rotate 2");
		}	
		System.out.println();

		map.clear();
		map.put(128,"");
		map.put(32, "");
		map.put(512, "");
		map.put(16, "");
		map.put(64, "");
		map.put(256,"");
		map.put(1024, "");
		map.put(59, "");
		map.put(100, "");
		map.put(50, "");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 128 && map.isBalanced()) {
			System.out.println("Passed LeftRight Rotate 3");
		} else {
			System.out.println("Failed LeftRight Rotate 3");
		}
		System.out.println();
	}

	private static void testRightLeft() {
		System.out.println("Testing Right Left Rotation");
		AVLMap<Integer, String> map = new AVLMap();

		map.put(16,"16");
		map.put(64, "64");
		map.put(32, "32");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed LeftRight Rotate 1");
		} else {
			System.out.println("Failed LeftRight Rotate 1");
		}

		map.clear();
		map.put(32, "thirtytwo");
		map.put(16, "sixteen");
		map.put(64, "sixtyfour");
		map.put(256, "256");
		map.put(128, "128");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 32 && map.isBalanced()) {
			System.out.println("Passed RightLeft Rotate 2");
		} else {
			System.out.println("Failed RightLeft Rotate 2");
		}	
		System.out.println();	

		map.clear();
		map.put(128,"");
		map.put(32, "");
		map.put(512, "");
		map.put(16, "");
		map.put(64, "");
		map.put(256,"");
		map.put(1024, "");
		map.put(1000, "");
		map.put(2000, "");
		map.put(900, "");
		//map.put(4, "four");
		System.out.println("Root is " + map.root());
		System.out.println("Balanced: " + map.isBalanced()+ " BF = " + map.balanceFactor());
		System.out.println(map);
		if (map.root() == 128 && map.isBalanced()) {
			System.out.println("Passed RightLeft Rotate 3");
		} else {
			System.out.println("Failed RightLeft Rotate 3");
		}
		System.out.println();
	}

	private static void randomInsert() {
		System.out.println("Testing Random Insertion");
		AVLMap<Integer, String> map = new AVLMap();
		int random = 0;
		int count = 0;
		while(map.isBalanced() && count < 50000) {
			random = (int)(Math.random() * 100000 + 1);
			map.put(random, "");
			count++;
		}
		if (map.isBalanced()) {
			System.out.println("Passed Random Insert Test: Balnced after 10,000 random entries");
		} else {
			System.out.println("FAILED Random Insert Test");			
		}
	}

	private static void testEntriesKeysValues() {
		System.out.println("Testing entries()");
		AVLMap<Integer, String> map = new AVLMap();

		HashSet<Map.Entry<Integer,String>> otherentries = new HashSet();
		HashSet<Integer> otherkeys = new HashSet();
		LinkedList<String> othervalues = new LinkedList();

		int random = 0;
		int count = 0;

		if(map.entries().isEmpty()) {
			System.out.println("Empty map returns empty set");
		} else {
			System.out.println("FAILURE: Empty map does no return empty set.");
		}
		while(count < 10) {
			random = (int)(Math.random() * 100 + 1);
			map.put(random, ""+random);
		//	Map.Entry<Integer, Stinrg> node = new Map.Entry(random, random+"");
		//	otherentries.add(node);
			otherkeys.add(random);
			if (!othervalues.contains(""+random)){
				othervalues.add(""+random);				
			}
			count++;
		}
		//if(sameCollection(map.entries(), otherentries)) {
		//	System.out.println("Entries seems to be working.");
		//}
		if(sameCollection(otherkeys, map.keys())) {
			System.out.println("Keys() seems to be working");
		}
		if (sameCollection(othervalues, map.values())) {
			System.out.println("Values() seems to be working");
		} else {
			System.out.println(map.values());
			System.out.println(othervalues);
		}
	}

	public static void testSubmap() {
		boolean one = false;
		boolean two = false;
		boolean three = false;
		boolean four = false;

		AVLMap<Integer, String> map = new AVLMap();
		for (int i = 0; i < 100; i++){
			map.put(i, i+"");
		}
		AVLMap<Integer, String> submap = map.subMap(20, 30);
		if (submap.size() == 11 && submap.isBalanced()) {
			one = true;
		}
		submap = map.subMap(20, 200);
		if (submap.size() == 80 && submap.isBalanced()) {
			two = true;
		}
		submap = map.subMap(-10, 5);
		if (submap.size() == 6 && submap.isBalanced()) {
			three = true;
		}
		submap = map.subMap(20,19);
		if (submap.size() == 0 && submap.isBalanced()) {
			four = true;
		}
		if (one && two && three && four) {
			System.out.println("Submap works!");
		} else {
			System.out.println("Submap failed");
		}
	}

    public static <T> boolean sameCollection(Collection<T> c1, Collection<T> orig) {
        ArrayList<T> c2 = new ArrayList<T>(orig); // make copy
        if (c1.size() != c2.size())
            return false;
        for (T val : c1) {  // uses iterator
            if (!c2.remove(val))   // so count will be accurate
                return false;
        }
        if (c2.size() != 0)  // should be empty by now
            return false;
        return true;  // passed all tests
    }

    public static void testRemoveLeaves() {
		AVLMap<Integer, String> map = new AVLMap();
		System.out.println("Testing Remove Leaves");
		map.put(64,"");
		map.put(16, "");
		map.put(256, "");
		map.put(8,"");
		map.put(32, "");
		map.put(128, "");
		map.put(512,"");
		map.put(2, "");
		map.put(4, "");
		System.out.println("Root is " + map.root());

		System.out.println("Balanced: " + map.isBalanced());
		map.remove(2);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(4);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(8);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(32);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(128);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(512);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(16);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(256);
		System.out.println("Try to remove root");
		map.remove(64);
		System.out.println(map);   	
    }
    public static void testRemoveSingleChild() {
		AVLMap<Integer, String> map = new AVLMap();
		System.out.println("Testing Remove SingleChild");
		map.put(64,"");
		map.put(16, "");
		map.put(256, "");
		map.put(8,"");
		map.put(32, "");
		map.put(128, "");
		map.put(512,"");
		map.put(2, "");
		map.put(4, "");
		map.put(1024, "");

		System.out.println("Root is " + map.root());

		System.out.println("Balanced: " + map.isBalanced());
		map.remove(512);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(4);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(8);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(2);
		map.remove(1024);
		map.remove(32);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(128);
		System.out.println("Balanced: " + map.isBalanced());
		System.out.println(map);   	
    }

    public static void testRemoveDoubleChild() {
		AVLMap<Integer, String> map = new AVLMap();
		System.out.println("Testing Remove SingleChild");
		map.put(64,"");
		map.put(16, "");
		map.put(256, "");
		map.put(8,"");
		map.put(32, "");
		map.put(128, "");
		map.put(512,"");
		map.put(2, "");
		map.put(4, "");
		map.put(28, "");
		map.put(34,"");
		map.put(124, "");
		map.put(132, "");
		map.put(1020, "");
		map.put(1024, "");

		System.out.println("Root is " + map.root());

		System.out.println("Balanced: " + map.isBalanced());
		map.remove(512);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(128);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(32);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(8);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(16);
		System.out.println("Balanced: " + map.isBalanced());
		map.remove(256);
		System.out.println("Balanced: " + map.isBalanced());

		System.out.println(map);   	
    }
}
