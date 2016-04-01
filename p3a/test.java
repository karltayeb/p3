import java.util.Collection;
import java.util.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

/** Driver class.
 *  
 */
public final  class test {

    /** Constructor.
     *
     */
    private test() {
    }
    /** Driver method.
     *  @param args command line argument.
     */
    public static void main(String[] args) {
    
        testCase1();
        testCase2();
        testCase4();
        testIterator2();
    }
    
     /** Test case 1.
     *  
     */
    public static void testCase1() {    
        System.out.println("\n*********** Test case 1 begins  *********** ");
        BSTMap<Integer, String> bstmap = new BSTMap<>();
        bstmap.put(1, "one");
        bstmap.put(2, "two");
        bstmap.put(3, "three");
        System.out.println(bstmap);
        System.out.println(bstmap.hasKey(2));
        System.out.println(bstmap.get(2));
        System.out.println(bstmap.get(20));
        System.out.println(bstmap);
        System.out.println(bstmap.remove(2));
        System.out.println(bstmap.remove(1));
        System.out.println(bstmap.remove(3));
        System.out.println(bstmap.isEmpty());
        System.out.println(bstmap.remove(7));
        System.out.println("*********** Test case 1 ends  *********** ");
    }
     
     /** Test case 2.
     *  
     */
    public static void testCase2() { 
        System.out.println("\n*********** Test case 2 begins  *********** ");

        BSTMap<Integer, String> bstmap = new BSTMap<>();
        bstmap.put(17, "seventeen");
        bstmap.put(8, "eight");
        bstmap.put(23, "twenty-three");
        bstmap.put(4, "four");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(25, "twenty-five");
        bstmap.put(0, "zero");
        bstmap.put(6, "six");
        bstmap.put(12, "twelve");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(30, "thirty");
        System.out.println(bstmap);
        System.out.println(bstmap.size());
        System.out.println(bstmap.hasKey(15));
        System.out.println(bstmap.hasKey(0));
        System.out.println(bstmap.hasKey(7));
        System.out.println("HAS VALUE: " + bstmap.hasValue("six"));
        System.out.println("HAS VALUE: " + bstmap.hasValue("twenty"));
        System.out.println("HAS VALUE: " + bstmap.hasValue("seven"));
        System.out.println(bstmap.get(8));
        System.out.println(bstmap.get(4));
        System.out.println(bstmap);
        System.out.println(bstmap.remove(20));
        System.out.println(bstmap.remove(30));
        System.out.println(bstmap);
        System.out.println("FIRST KEY: " + bstmap.firstKey());
        System.out.println("LAST KEY: " + bstmap.lastKey());
        System.out.println(bstmap);
        
        /* Iterator specific tests */
        Iterator<Map.Entry<Integer, String>> iter = bstmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getKey());
        }
        
        /* inOrder() test */
        Iterable<Map.Entry<Integer, String>> entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
        /* values test */
        Collection<String> vals = bstmap.values();
        System.out.println("VALUES: " + vals);
        
        /* keys test */
        Collection<Integer> keys = bstmap.keys();
        System.out.println("KEYS: " + keys);
        
        /* entries test */
        System.out.println(bstmap.entries());
        
        /* submap test */
        System.out.println("Submap testing with valid range:" 
             + bstmap.subMap(0, 23) + bstmap.subMap(0, 23).size());
  
        System.out.println("Submap testing with valid range:" 
             + bstmap.subMap(8, 23) + bstmap.subMap(8, 23).size());
        System.out.println("Submap testing with incorrect range:"
             + bstmap.subMap(9, 23) + bstmap.subMap(9, 23).size());
        System.out.println("Submap testing with incorrect range:"
             + bstmap.subMap(9, 23) + bstmap.subMap(9, 23).size());
        System.out.println("Submap testing with incorrect start range 32:" 
             + bstmap.subMap(32, 45));
        System.out.println("Submap testing with incorrect end range:" 
             + bstmap.subMap(8, 45));

        System.out.println("Submap testing with inverted RANGE" 
             + bstmap.subMap(12, 6));


        /* Iterator exception-handling */
        iter = bstmap.iterator();
        System.out.println(bstmap);
        iter.next();
        System.out.println(bstmap.remove(12));
        System.out.println("After remvoe 12:" + bstmap);

        try {
            iter.next();
        } catch (ConcurrentModificationException ex) {
            System.out.println(ex);
        }
        iter = bstmap.iterator();
        while (iter.hasNext()) {
            iter.next();
        }
        try {
            iter.next();
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
        
        System.out.println(bstmap);
        iter = bstmap.iterator();
        iter.next();
        iter.next();
        //iter.remove();
        System.out.println(bstmap);
        
        /* clear tests */
        bstmap.clear();
        System.out.println(bstmap);
        System.out.println(bstmap.size());
        System.out.println(bstmap.isEmpty());
        System.out.println("*********** Test case 2 ends  *********** ");
    }
      
     
    /** Test case 4.
     *  
     */
    public static void testCase4() {
 
        Integer[] iarr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] sarr = {"zro", "one", "two", "three", "four", "five", 
            "six", "seven", "eight", "nine"};
       
        System.out.println("\n*********** Test case 4 begins  *********** ");
       
        BSTMap<Integer, String> bstmap = new BSTMap<>();
       
       
        checkSize();
        checkgetKeyValuePart1();
        checkgetKeyValuePart2();
        checkgetAndRemove();
        inOrderSorted();
        subMapTest();
        extensiveRemove();
       
       //checkLeaf
       //Test case: Check isLeaf()
       
       
    }
       
    /** check size method.
     *  
     */
    public static void checkSize() {
 
        Integer[] iarr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] sarr = {"zro", "one", "two", "three", "four", "five", 
            "six", "seven", "eight", "nine"};
       
        System.out.println("\n*********** Test case 4 begins  *********** ");
       
        BSTMap<Integer, String> bstmap = new BSTMap<>();

       //Check size = 0 with empty constructor
        System.out.println("Size : Empty constructor: " + bstmap.size());
       
       //remove a value from empty construcor should not make the size negative
        bstmap.remove(50);
        System.out.println("Size : Empty constructor: " + bstmap.size());
        for (int i = 0; i < iarr.length; i++) {
            bstmap.put(iarr[i], sarr[i]);
        }
        System.out.println("Size : " + bstmap.size() + " Full list: " + bstmap);
       
       
        for (int i = 0; i < iarr.length; i++) {
            System.out.println("Removing key " + iarr[i] + " " 
                + bstmap.remove(iarr[i]));
            System.out.println("Size after remove : " + bstmap.size());

        }
        System.out.println("After remove all the list is : " + bstmap);
        bstmap.remove(23);
        System.out.println("Size should not be negative :" + bstmap.size());
       
    }
       
    /** check get key values.
     *  
     */
    public static void checkgetKeyValuePart1() {  
        BSTMap<Integer, String> bstmap = new BSTMap<>();
        System.out.println("Is empty? after constructor:" + bstmap.isEmpty());
        System.out.println("Size after constructor:" + bstmap.size());
        System.out.println("Haskey on empty constructor:" + bstmap.hasKey(20));
        System.out.println("Get on empty constructor:" + bstmap.get(20));
        System.out.println("Clearing after empty constructor:");
        bstmap.clear();
        System.out.println("Size after clearing:" + bstmap.size());
        System.out.println("Is empty? after clearing:" + bstmap.isEmpty());
        System.out.println("Get on after clearing:" + bstmap.get(20));

        
        System.out.println("Empty Constructor set and iterating through it");
        Iterator<Map.Entry<Integer, String>> iter = bstmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getKey());
        }
        iter = bstmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getValue());
        }
        
    }
        
    /** check key value get methods.
     *  
     */
    public static void checkgetKeyValuePart2() {  

        BSTMap<Integer, String> bstmap = new BSTMap<>();

        bstmap.put(17, "seventeen");
        bstmap.put(8, "eight");
        bstmap.put(23, "twenty-three");
        bstmap.put(4, "four");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(25, "twenty-five");
        bstmap.put(0, "zero");
        bstmap.put(6, "six");
        bstmap.put(12, "twelve");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(30, "thirty");
        
                
        System.out.println("Is empty? after inserting:" + bstmap.isEmpty());

        System.out.println("Full list:" + bstmap);

        //haskey and checks
        System.out.println("haskey(6) exits:" + bstmap.hasKey(6));
        System.out.println("haskey(96) does not exist:" + bstmap.hasKey(96));
        System.out.println("Get(0):" + bstmap.get(0));
        System.out.println("Get(30):" + bstmap.get(30));
        System.out.println("Get(6):" + bstmap.get(6));
        System.out.println("Get(106) does not exist:" + bstmap.get(106));


        System.out.println("Full list keys");
        Iterator<Map.Entry<Integer, String>> iter = bstmap.iterator();

        iter = bstmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getKey());
        }
        
        System.out.println("Full list values");
        iter = bstmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().getValue());
        }
    

        System.out.println("First key = " + bstmap.firstKey());
        System.out.println("Last key = " + bstmap.lastKey());
       
        System.out.println("Before removing full list:" + bstmap);
        Integer[] iarr = {17, 8, 23, 43, 15, 20, 25, 0, 6, 12, 15, 20, 4, 30};
        for (int i = 0; i < iarr.length; i++) {
            System.out.println("Before removing, haskey(" 
                + iarr[i] + ")" + bstmap.hasKey(iarr[i]));
            System.out.println("removing" + iarr[i] + " "  
                + bstmap.remove(iarr[i]));
          

            System.out.println(bstmap);
            System.out.println("After removing, haskey is " 
                + bstmap.hasKey(iarr[i]));
            System.out.println("Last key = " + bstmap.lastKey());
            System.out.println("first key = " + bstmap.firstKey());
            System.out.println("Size after clearing:" + bstmap.size());
            System.out.println("isEmpty" + bstmap.isEmpty());
          
        }          
    }
 
  /** Get and Remove test.
   *  
   */
    public static void checkgetAndRemove() {  

        BSTMap<Integer, String> bstmap = new BSTMap<>();

        System.out.println("Testing empty entries, extensive get and Remove");
        Iterable<Map.Entry<Integer, String>> entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
        /* values test */
        Collection<String> vals = bstmap.values();
        System.out.println("VALUES: " + vals);
        
        /* keys test */
        Collection<Integer> keys = bstmap.keys();
        System.out.println("KEYS: " + keys);
        
        /* entries test */
        System.out.println(bstmap.entries());


        bstmap.put(17, "seventeen");
        bstmap.put(8, "eight");
        bstmap.put(23, "twenty-three");
        bstmap.put(4, "four");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(25, "twenty-five");
        bstmap.put(0, "zero");
        bstmap.put(6, "six");
        bstmap.put(12, "twelve");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(30, "thirty");
        
        entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
        /* values test */
        vals = bstmap.values();
        System.out.println("VALUES: " + vals);
        
        /* keys test */
        keys = bstmap.keys();
        System.out.println("KEYS: " + keys);
        
        /* entries test */
        System.out.println(bstmap.entries());

        System.out.println("size:" + bstmap.size());
        System.out.println("Full list:" + bstmap);
        System.out.println("Get(20) exist:" + bstmap.get(20));
        System.out.println("remove(20):" + bstmap.remove(20));
        bstmap.put(20, "newtwenty");
        System.out.println("Get(20) exist with new value:" + bstmap.get(20));
        System.out.println("remove(20):" + bstmap.get(20));   
    }
        
    /** Test case inOrderSorted.
     *  
     */
    public static void inOrderSorted() {  

        BSTMap<Integer, String> bstmap = new BSTMap<>();
       
        System.out.println("empty constructor inOrder testing");
        Iterable<Map.Entry<Integer, String>> entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }


        bstmap.put(17, "seventeen");
        bstmap.put(8, "eight");
        bstmap.put(23, "twenty-three");
        bstmap.put(4, "four");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(25, "twenty-five");
        bstmap.put(0, "zero");
        bstmap.put(6, "six");
        bstmap.put(12, "twelve");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(30, "thirty");
        
        /* inOrder() test */
        entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
        bstmap.remove(15);
        bstmap.remove(20);
        bstmap.remove(17);
        bstmap.remove(0);
        bstmap.remove(30);
        System.out.println("After removing inOrder testing");
        entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        bstmap.clear();
        System.out.println("After clearing inOrder testing");

        entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
    }  
   
    /** submap test.
     *  
     */
    public static void subMapTest() {  

        BSTMap<Integer, String> bstmap = new BSTMap<>();
       
        System.out.println("Submap testing Empty constructor:" 
             + bstmap.subMap(0, 23) + bstmap.subMap(0, 23).size());
        System.out.println("Submap testing Empty constructor 2:" 
             + bstmap.subMap(-1, -7) + bstmap.subMap(-1, -7).size());
        System.out.println("Submap testing Empty constructor 3:" 
             + bstmap.subMap(23, 2) + bstmap.subMap(23, 2).size());
             
  
        bstmap.put(17, "seventeen");
        bstmap.put(8, "eight");
        bstmap.put(23, "twenty-three");
        bstmap.put(4, "four");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(25, "twenty-five");
        bstmap.put(0, "zero");
        bstmap.put(6, "six");
        bstmap.put(12, "twelve");
        bstmap.put(15, "fifteen");
        bstmap.put(20, "twenty");
        bstmap.put(30, "thirty");
        
         
        Iterable<Map.Entry<Integer, String>> entries = bstmap.inOrder();
        for (Map.Entry<Integer, String> e : entries) {
            System.out.println("KEY: " + e.getKey());
        }
        
        System.out.println("Submap testing with all range:" 
             + bstmap.subMap(0, 30));
        System.out.println("Submap testing with all range size:" 
             +     bstmap.subMap(0, 30).size());

        System.out.println("Submap testing 8,17:" 
             + bstmap.subMap(8, 17));
        System.out.println("Submap testing with all range size:"  
             +     bstmap.subMap(8, 17).size());

        System.out.println("Submap testing 23,30:" 
             + bstmap.subMap(23, 30));
        System.out.println("Submap testing with all range size:"  
             +     bstmap.subMap(23, 30).size());

        System.out.println("Submap testing 30,30:" 
             + bstmap.subMap(30, 30));
        System.out.println("Submap testing with all range size:"  
             +     bstmap.subMap(30, 30).size());
        
        System.out.println("Submap testing -2,30:" 
             + bstmap.subMap(-2, 30));
        System.out.println("Submap testing with all range size:"  
             +     bstmap.subMap(-2, 30).size());

        System.out.println("Submap testing -2,-30" 
             + bstmap.subMap(-2, -30));
        System.out.println("Submap testing with all range size:"  
             +     bstmap.subMap(-2, -30).size());
        System.out.println("Submap testing with start > end incl:"
             +     bstmap.subMap(25,0));
        System.out.println("Submap testing with start > end incl:"
             +     bstmap.subMap(25,0).size());      
    }

    /** Remove test cases.
     *  
     */
    public static void extensiveRemove() {
        System.out.println("\n*********** Test case 3 begins  *********** ");
        BSTMap<Integer, String> bstmap = new BSTMap<>();
        
        bstmap.put(63, "first");
        bstmap.put(21, "second");
        bstmap.put(19, "third");
        bstmap.put(20, "fourth");
        System.out.println(bstmap);
        bstmap.put(1, "5th");
        System.out.println(bstmap);

        bstmap.put(67, "6th");
        System.out.println(bstmap);

        bstmap.put(55, "7th");
        System.out.println(bstmap);

        bstmap.put(77, "8th");
        System.out.println(bstmap);

        bstmap.put(83, "9th");
        System.out.println(bstmap);

        bstmap.put(14, "10th");
        System.out.println(bstmap);

        bstmap.put(29, "11th");
        System.out.println(bstmap);
        
        /* Get values.. */
        System.out.println("GET: " + bstmap.get(63));
        System.out.println("GET: " + bstmap.get(21));
        System.out.println("GET: " + bstmap.get(67));
        System.out.println("GET: " + bstmap.get(83));
        System.out.println("GET: " + bstmap.get(29));
        System.out.println("GET: " + bstmap.get(19));
        System.out.println("GET: " + bstmap.get(77));
        

        System.out.println("Remove the node with no childeren 29 and 83");
        /* Remove the node with no children */
        System.out.println(bstmap.remove(29));
        System.out.println(bstmap);
        System.out.println(bstmap.remove(83));
        System.out.println(bstmap);
        
        System.out.println("Remove the node with 2 children 63 and root also");
        /* Remove the node with 2 children */
        System.out.println(bstmap.remove(63));
        System.out.println(bstmap);
   
        System.out.println("Remove the node with one child 1");
        /* Remove the node with one child */
        System.out.println(bstmap.remove(1));
        System.out.println(bstmap);
       
        System.out.println("Remove the internalnode with 2 childeren 19");
        /* remove internal node with 2 children testing */
        System.out.println(bstmap.remove(19));
        System.out.println(bstmap);
         
        /* remove node with 2 children testing and root node removed */
        System.out.println(bstmap.remove(67));
        System.out.println(bstmap);
         
        /* Remove the node with one child */
        System.out.println(bstmap.remove(20));
        System.out.println(bstmap);

             
       /* node with no children */
        System.out.println(bstmap.remove(55));
        System.out.println(bstmap);

        /* node with one children */
        System.out.println(bstmap.remove(21));
        System.out.println(bstmap);

        /* Remove root node that had only 1 child left node */
        System.out.println(bstmap.remove(77));
        System.out.println(bstmap);
        System.out.println(bstmap.size());
        
        /* Only 1 node left and clear out and test. */
        bstmap.clear();
        System.out.println(bstmap);
        System.out.println(bstmap.size());
    }
    
    public static void testIterator2() {
        System.out.println("\n*********** Test case Iterator begins  *********** ");
        BSTMap<Integer, String> bstmap = new BSTMap<>();
        
        bstmap.put(63, "first");
        bstmap.put(21, "second");
        bstmap.put(19, "third");
        bstmap.put(20, "fourth");
        System.out.println(bstmap);
        bstmap.put(1, "5th");
        System.out.println(bstmap);

        bstmap.put(67, "6th");
        System.out.println(bstmap);

        bstmap.put(55, "7th");
        System.out.println(bstmap);

        bstmap.put(77, "8th");
        System.out.println(bstmap);

        bstmap.put(83, "9th");
        System.out.println(bstmap);

        bstmap.put(14, "10th");
        System.out.println(bstmap);

        bstmap.put(29, "11th");
        System.out.println(bstmap);
        
        Iterator<Map.Entry<Integer,String>> iter = bstmap.iterator();
        
        if (iter.hasNext()) {
            System.out.println("TRUE HAS NEXT");
        }
//        try {
//            iter.remove();
//        } catch (IllegalStateException ex) {
//            System.out.println("Illegal State");
//        }
        for(int i=1; i < 5; ++i) {
            System.out.println("NEXT: " + iter.next());
        } 
        System.out.println(bstmap);
        if(iter.hasNext()) {
            System.out.println("HAS NEXT TRUE");
        }
        System.out.println("REMOVE Expected 20");
        //iter.remove();
        System.out.println(bstmap);
//        try {
//            while(iter.hasNext()) {
//                iter.remove();
//            }
//        } catch (IllegalStateException ex) {
//            System.out.println("Caught Illegal State");
//        }
//         try {
//             bstmap.put(21,"second");
//             if(iter.hasNext()) {
//                 System.out.println("YES HAS NEXT FAILED");
//             }
//         } catch (ConcurrentModificationException ex) {
//             System.out.println("Concurrent Modification Error Caught");
//         }
        while(iter.hasNext()) {
            System.out.println(iter.next().getKey());
            System.out.println(bstmap);
            //iter.remove();
        }
        System.out.println(bstmap);    
    }    
}