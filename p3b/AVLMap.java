import java.lang.Math;

/** AVL Tree Map implmeentation extended from BSTMap
 * @param <K> the base type of the keys in the entries
 * @param <V> the base type of the values
 */
public class AVLMap<K extends Comparable<? super K>, V> extends BSTMap<K, V>{
	public AVLMap() {
		super();
	}

    /** Put <key,value> entry into subtree with given root node.
     *  @param key the key of the entry
     *  @param val the value of the entry
     *  @return the original value associated with the key, or null if not found
     */
    @Override
    public V put(K key, V val) {
        this.modified = true;
        V temp = this.put(key, val, this.root);
        this.updateHeight(this.root);
        while (!this.isBalanced()){ //runs at most twice
        	//this.balanceroutine(this.root);
        	break;
        }
        return temp;
    }

    /** Recursively updates the heights of nodes in the tree
     * @param curr the current node in consideration
     */
    private int updateHeight(BNode curr) {
    	if (curr.isLeaf()) {
    		return -1; //height of leaves is -1
    	} else {
    		curr.height =
    			Math.max(updateHeight(curr.right), updateHeight(curr.left));
    		curr.height++;
    	}
    	return curr.height;

    }
    /** TESTIN METHOD ONLY */
    public void root(){
    	System.out.println("Root = " + this.root);
    }

    public boolean isBalanced() {
    	return isBalanced(this.root);
    }
    private boolean isBalanced(BNode curr) {
    	if (curr.isLeaf()) {
    		return true;
    	} 
    	if (Math.abs(balanceFactor(curr)) <= 1
    		&& isBalanced(curr.right) && isBalanced(curr.left)) {
    		return true;
    	}
    	return false;
    }
    public int balanceFactor() {
    	return balanceFactor(this.root);
    }
    private int balanceFactor(BNode curr) {
    	if (curr == null || curr.isLeaf()) {
    		return 0;
    	}
    	return curr.right.height - curr.left.height;
    }

    private void balanceroutine() {

    }


    /** Returns a copy of the portion of this map whose keys are in a range.
     *  @param fromKey the starting key of the range, inclusive if found
     *  @param toKey the ending key of the range, inclusive if found
     *  @return the resulting submap
     */
    public AVLMap<K, V> subMap(K fromKey, K toKey) {
        AVLMap<K, V> submap = new AVLMap();
        K firstKey = this.firstKey(this.root);
        K lastKey = this.lastKey(this.root);
        // return an empty map if underlying map is empty
        if (this.root.isLeaf()) {
            return submap;
        }
        // adjust bounds of submap
        if (firstKey != null && fromKey.compareTo(firstKey) < 0) {
            fromKey = firstKey;
        }
        if (lastKey != null && toKey.compareTo(lastKey) > 0) {
            toKey = lastKey;
        }
        this.addToSubmap(submap, this.root, fromKey, toKey);
        return submap;
    }
   
    /** Helper function for subMap.
     * @param map the map we are traversing
     * @param curr the current node
     * @param low the min value key we'll allow
     * @param high the max value key we'll allow 
     */
    protected void addToSubmap(BSTMap<K, V> map, BNode curr, K low, K high) {
        if (curr.key == null) {
            return;
        }
        if (curr.key.compareTo(low) >= 0 && curr.key.compareTo(high) <= 0) {
            map.put(curr.key, curr.value);
            this.addToSubmap(map, curr.left, low, high);
            this.addToSubmap(map, curr.right, low, high);
        }
        if (curr.key.compareTo(low) < 0) {
            this.addToSubmap(map, curr.right, low, high);
        }
        if (curr.key.compareTo(high) > 0) {
            this.addToSubmap(map, curr.left, low, high);
        }
    }

}
