import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Iterator;

public class old_test {
    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap();
	System.out.println(map.hasValue("four"));
        map.put(4, "four");
        map.put(2, "two");
        map.put(9, "nine");
        map.put(6, "six");
        map.put(3, "three");
        map.put(15, "fifteen");
        map.put(170, "oneseventy");
        map.put(-17, "neg seventeen");
        map.put(67, "sixty seven");
        map.put(5, "five");
	
	System.out.println(map.get(170));
	System.out.println(map.put(170, "fur"));
	System.out.println(map.get(170));

	System.out.println(map.hasValue("four"));
	System.out.println(map.hasValue("two"));
	System.out.println(map.hasValue("fifteen"));
	System.out.println(map.hasValue("sixty seven"));
	System.out.println(map.hasValue("three"));

	System.out.println(map.hasValue("thre"));
	System.out.println(map.hasValue("thee"));
	System.out.println(map.hasValue("tee"));

	HashSet<Map.Entry<Integer, String>> entries = (HashSet<Map.Entry<Integer, String>>) map.entries();
	for (Map.Entry<Integer, String> entry : entries) {
		System.out.print(entry.getKey()+" ");
	}
	System.out.println();
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(3).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(2).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(-17).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(5).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(4).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());
	System.out.println(map.remove(6).toString());
	System.out.println("Root is " ); 
        System.out.println(map.toString()+" "+map.size());

	map.remove(9); 
        System.out.println(map.toString()+" "+map.size());
	map.remove(6); 
        System.out.println(map.toString()+" "+map.size());
	map.remove(5); 
        System.out.println(map.toString()+" "+map.size());
	map.remove(15); 
        System.out.println(map.toString()+" "+map.size());
	map.remove(170); 
        System.out.println(map.toString()+" "+map.size());
	map.remove(67);
	BSTMap<Integer, String> submap = map.subMap(3, 67);
        System.out.println(submap.toString()+" "+submap.size());
        System.out.println(map.toString()+" "+map.firstKey()+ " "+ map.lastKey());
	
	map.clear();
	submap = map.subMap(-15, 400);
        System.out.println(map.toString()+" "+map.size());
        System.out.println(submap.toString()+" "+submap.size());
	extensiveRemove();
	testIterator2();
    }

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
        iter.remove();
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
