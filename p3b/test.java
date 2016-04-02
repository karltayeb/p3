import java.util.Map;

public class test {
	public static void main(String[] args){
		AVLMap<Integer, String> map = makeMap();
		System.out.println(map);
		System.out.println(map.isBalanced());

		AVLMap<Integer, String> submap = map.subMap(400,1028);
		System.out.println(submap);
		System.out.println(submap.balanceFactor());
		System.out.println(submap.isBalanced());
	}

	private static AVLMap<Integer, String> makeMap() {
		AVLMap<Integer, String> map = new AVLMap();
	
		//map.put(512, "512");

		//map.put(256, "256");
		map.put(3, "y");
		map.put(1, "y");
		map.put(2, "y");	
		//map.put(2, "2");
		/*
		map.put(1024, "1024");
		map.put(128, "128");
		map.put(64, "64");
		map.put(8, "8");
		map.put(16, "16");
		map.put(2, "2");


		map.put(400, "400");
		map.put(1028, "1028");
		*/
		map.root();
		return map;
	}


}