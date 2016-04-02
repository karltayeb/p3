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
        return temp;
    }

    /** Recursively updates the heights of nodes in the tree
     * @param curr the current node in consideration
     */
    private int updateHeight(BNode curr) {
    	if (curr.isLeaf() || curr.left.isLeaf() && curr.right.isLeaf()) {
    		curr.height = 0;
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
    	} else if (Math.abs(balanceFactor(curr)) > 1) {
    		return false;
    	} else {
    		return (isBalanced(curr.right) && isBalanced(curr.left));
    	}
    }
    private int balanceFactor(BNode curr) {
    	if (curr == null || curr.isLeaf()) {
    		return 0;
    	}
    	return curr.right.height - curr.left.height;
    }

}
