import java.util.Map;

public class test {
	public static void main(String[] args){
		testSingleRight();
		testLeftRight();
	}

	private static void testSingleRight() {
		AVLMap<Integer, String> map = new AVLMap();

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
	}

	private static void testLeftRight() {
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
	}


}