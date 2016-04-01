import java.util.Map;

public class test {
	public static void main(String[] args){
		AVLMap<Integer, String> map = makeMap();
		System.out.println(map);
	}

	private static AVLMap<Integer, String> makeMap() {
		AVLMap<Integer, String> map = new AVLMap();
		map.put(32, "32");
		map.put(256, "256");
		map.put(512, "512");
		map.put(1024, "1024");
		map.put(128, "128");
		map.put(64, "64");
		map.put(8, "8");
		map.put(16, "16");
		map.put(2, "2");
		map.put(4, "4");
		return map;
	}


}