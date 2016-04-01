import java.util.Collection;
import java.util.Map;
import java.util.Iterator;

public final class testIterator {
    
    /**
     * Constructor for test class.
     */
    private testIterator() {
        
    }
    
    /**
     * Main method driving test class.
     * @param args
     */
    public static void main(String[] args) {
        iteratorTest();
        extraTest();
    }
    
    /**
     * Testing the BSTIterator class.
     */
    public static void iteratorTest() {
        System.out.println("Iterator Test:");
        
        BSTMap<Integer, String> bst = new BSTMap<Integer, String>();
        
        for (int i = 1; i <= 10; i ++) {
            String temp = "test" + i;
            bst.put(i, temp);
        }
        
        bst.put(11, "test11");
        System.out.println(bst);
        
        Iterator<Map.Entry<Integer,String>> iter = bst.iterator();
        
        /**Test hasNext */
        if(iter.hasNext()) {
            System.out.println("hasNext appears to work.");
        }
        System.out.println("Testing next, remove, and has next. Doing this iteratively across the iterator confirms removal and boundary conditions."); 
        while(iter.hasNext()) {
	    iter.next();
            iter.remove();
            System.out.println(bst);
	} 
    }
    
    public static void extraTest() {
        System.out.println("Other tests");
        
        BSTMap<Integer, String> bst = new BSTMap<Integer, String>();
        
        for (int i = 1; i <= 10; i ++) {
            String temp = "test" + i;
            bst.put(i, temp);
        }
        
        System.out.println("Print out bst:");

        System.out.println(bst);
        

        System.out.println("Print out entries");
        System.out.println(bst.entries());
        
        System.out.println("Print out Keys:");
        Collection<Integer> keys = bst.keys();
        System.out.println(keys);
        
        System.out.println("Print out Values:");
        Collection<String> values = bst.values();
        System.out.println(values);
        
    }
}
