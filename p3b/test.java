import java.util.Map;
import java.lang.Math;

public class test {
	public static void main(String[] args){
		testSingleRight();
		testLeftRight();
		testSingleLeft();
		testRightLeft();
		randomInsert();
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
		//map.put(4, "four");
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
}
