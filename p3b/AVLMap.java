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
    @Override()
    public V put(K key, V val) {
        this.modified = true;
        return this.put(key, val, this.root);
    }

    /** Put <key,value, curr> entry into subtree with given root node.
     *  @throws IllegalArgumentException if you give it a null key/val
     *  @param key the key of the entry
     *  @param val the value of the entry
     *  @param curr the root of the subtree into which to put the entry
     *  @return the original value associated with the key, or null if not found
     */
    protected V put(K key, V val, BNode curr) throws IllegalArgumentException {
        this.modified = true;
        if (key == null || val == null) {
            throw new IllegalArgumentException();
        }
        /* If the map has the key, replace the value */
        if (this.hasKey(key, curr)) {
            return this.find(key, curr).setValue(val);
        }
        
        /* Otherwise make a new node and put it in the tree */
        BNode node = new BNode(key, val);
        node.right = this.leaf;
        node.left = this.leaf;
        this.insert(node, this.root);
        this.updateHeight(this.root);
        return null;
    }

    @Override
    protected void insert(BNode node, BNode curr) {
        this.modified = true;
        /* If we try to insert on a sentinal, replace the sentinal.
         * since we inspect left and right children, this only happens when
         * inserting at the root of the tree. */
        if (curr.isLeaf()) {
            this.root = node;
            this.size++;
            return;
        }
        /* Otherwise move right or left and insert*/
        if (node.key.compareTo(curr.key) < 0) {
            if (curr.left.isLeaf()) {
                curr.left = node;
                this.size++;
                return;
            }
            this.insert(node, curr.left);
        } else {
            if (curr.right.isLeaf()) {
                curr.right = node;
                this.size++;
                return;
            }
            this.insert(node, curr.right);
        }
        
        this.updateHeight(curr);
        int bf = this.balanceFactor(curr);
        if (bf > 1) {
        	if (this.balanceFactor(curr.left) == -1) {
        		curr.left = leftrotate(curr.left);
        	}
        	curr = this.rightrotate(curr);
        	return;
        }
        if (bf < -1) {
        	if (this.balanceFactor(curr.right) == 1) {
        		curr.right = rightrotate(curr.right);
        	}
        	curr = this.leftrotate(curr);
        	return;
        }

        return;
    }

    /** Find and return the node with a key in a subtree, null if not found.
     * @param key the key to look for
     * @param curr the root of the subtree we're searching
     * @return the node we find
     */
    protected BNode find(K key, BNode curr) {
        if (curr.isLeaf()) {
            return this.leaf;
        }
        if (key.compareTo(curr.key) < 0) {
            return this.find(key, curr.left);
        } else if (key.compareTo(curr.key) == 0) {
            return curr;
        } else {
            return this.find(key, curr.right);
        }
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
    	return curr.left.height - curr.right.height;
    }

    private void removebalanceroutine(BNode curr) {
    	/**
    	// Descend to the lowest unbalanced subtree
    	while(!this.isBalanced(curr)) {
    		this.balanceroutine(curr.left);
    		this.balanceroutine(curr.right);
    	}
    	if (this.isBalanced(curr)) {
    		return;
    	}
    	if (this.balanceFactor(curr) < -1) {
    		curr = this.leftrotate(curr);
    		return;
    	} else {
    		curr = this.rightrotat(curr);
    		return;
    	}
    	*/
    	return;
    }

    private BNode rightrotate(BNode pivot) {
    	BNode up = pivot.left;
    	BNode temp = up.right;
    	if (this.root == pivot) {
    		this.root = up;
    	}
    	up.right = pivot;
    	pivot.left = temp;
    	this.updateHeight(up);
    	return up;
    }
    private BNode leftrotate(BNode pivot) {
    	BNode up = pivot.right;
    	BNode temp = up.left;
    	if (this.root == pivot) {
    		this.root = up;
    	}
    	up.left = pivot;
    	pivot.right = temp;
    	this.updateHeight(up);
    	return up;
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
