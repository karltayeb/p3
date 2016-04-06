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
        this.updateHeight(node);
        this.insert(node, this.root);
        this.updateHeight(this.root);
        int bf = balanceFactor(this.root);
        if (Math.abs(bf) > 1) {
            if (bf > 1) {
                if (this.balanceFactor(this.root.left) == -1) {
                    this.root.left = this.leftrotate(this.root.left);
                    this.updateHeight(this.root.left);
                }
                this.root = this.rightrotate(this.root);
                this.updateHeight(this.root);
            } else if (bf < -1) {
                if (this.balanceFactor(this.root.right) == 1) {
                    this.root.right = this.rightrotate(this.root.right);
                    this.updateHeight(this.root.right);
                }
                this.root = this.leftrotate(this.root);
                this.updateHeight(this.root);
            }
        }
        this.root.height =
        	Math.max(this.root.left.height, this.root.right.height) + 1;
        return null;
    }

    private void insert(BNode node, BNode curr) {
        this.modified = true;
        /* If we try to insert on a sentinal, replace the sentinal.
         * since we inspect left and right children, this only happens when
         * inserting at the root of the tree. */
        if (curr.isLeaf()) {
            this.root = node;
            this.size++;
            this.updateHeight(curr);
            return;
        }
        /* Otherwise move right or left and insert*/
        if (node.key.compareTo(curr.key) < 0) {
            if (curr.left.isLeaf()) {
                curr.left = node;
                this.size++;
                this.updateHeight(curr);
                return;
            }
            this.insert(node, curr.left);
        } else {
            if (curr.right.isLeaf()) {
                curr.right = node;
                this.size++;
                this.updateHeight(curr);
                return;
            }
            this.insert(node, curr.right);
        }  
        if (!curr.left.isLeaf()) {
        	curr.left.height = Math.max(curr.left.right.height, curr.left.left.height) + 1;
        }
        if (!curr.right.isLeaf()) {
        	curr.right.height = Math.max(curr.right.right.height, curr.right.left.height) + 1;
        }
        curr.height = Math.max(curr.right.height, curr.left.height) + 1;
        int bfl = this.balanceFactor(curr.left);
        int bfr = this.balanceFactor(curr.right);
        if (bfl > 1) {
        	if (this.balanceFactor(curr.left.left) == -1) {
        		curr.left.left = this.leftrotate(curr.left.left);
        		//this.updateHeight(curr.left.left);
        	}
        	curr.left = this.rightrotate(curr.left);
            return;
        } else if (bfl < -1) {
            if (this.balanceFactor(curr.left.right) == 1) {
                curr.left.right = this.rightrotate(curr.left.right);
            }
            curr.left = this.leftrotate(curr.left);
            return;            
        }
        if (bfr < -1) {
        	if (this.balanceFactor(curr.right.right) == 1) {
        		curr.right.right = this.rightrotate(curr.right.right);
        	}
        	curr.right = this.leftrotate(curr.right);
        	return;
        } else if (bfr > 1) {
            if (this.balanceFactor(curr.right.left) == -1) {
                curr.right.left = this.leftrotate(curr.right.left);
            }
            curr.right = this.rightrotate(curr.right);
            return;        
        }
        return;
    }

    /** Removes a key from the map.
     * @param key to search and remove from map
     * @return value of entry removed from map
     */
    @Override
    public V remove(K key) {
        this.modified = true;
        return this.remove(key, this.root);
    }

    /** Remove entry with specified key from subtree with given root node.
     *  @param key the key of the entry to remove, if there
     *  @param curr the root of the subtree from which to remove the entry
     *  @return the value associated with the removed key, or null if not found
     */
    @Override
    public V remove(K key, BNode curr) {
        this.modified = true;
        if (key == null) {
            return null;
        }
        V temp = this.get(key);
        if (temp != null) {
            curr = this.removeroutine(key, curr);
            this.size--;
        }
        if (!this.root.isLeaf()) {
	        this.root.height = Math.max(this.root.right.height, this.root.left.height) + 1;      
	        int bf = this.balanceFactor(this.root);        
	        if (bf > 1) {
	            if (this.balanceFactor(this.root.left) < 0) {
	                this.root.left = leftrotate(this.root.left);
	            }
	            this.root = this.rightrotate(this.root);
	        }
	        if (bf < -1) {
	            if (this.balanceFactor(this.root.right) > 0) {
	                this.root.right = this.rightrotate(this.root.right);
	            }
	            this.root = this.leftrotate(curr);
	        }
        }
        return temp;
    }

    private BNode removeroutine(K key, BNode curr) {
        if (curr.isLeaf()) {
            return this.leaf;
        }
        if (key.compareTo(curr.key) < 0) {
            curr.left = this.removeroutine(key, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            curr.right = this.removeroutine(key, curr.right);
        } else {
            // We found the node we
            //Catch-all special case for when root is being deleted
            if (curr == this.root) {
                this.removeroot();
                this.size--;
                return this.root;
            } else if (curr.right.isLeaf()
                    && curr.left.equals(this.leaf)) {
                //Has no children, set this node to leaf
                return this.leaf;
            } else if (curr.right.isLeaf()) {
                return curr.left;
            } else if (curr.left.isLeaf()) {
                return curr.right;
            } else {
                //There are two children
                BNode replace = this.find(this.firstKey(curr.right),
                                                            curr.right);
                curr.key = replace.key;
                curr.value = replace.value;
                curr.right = this.removeroutine(replace.key,
                                                    curr.right);
                return curr;
            }
        }

        curr.height = Math.max(curr.right.height, curr.left.height) + 1;      
        int bf = this.balanceFactor(curr);        
        if (bf > 1) {
            if (this.balanceFactor(curr.left) < 0) {
                curr.left = leftrotate(curr.left);
            }
            curr = this.rightrotate(curr);
        }
        if (bf < -1) {
            if (this.balanceFactor(curr.right) > 0) {
                curr.right = this.rightrotate(curr.right);
            }
            curr = this.leftrotate(curr);
        }
        return curr;
    }

    /** Recursively updates the heights of nodes in the tree
     * @param curr the current node in consideration
     */
    private int updateHeight(BNode curr) {
    	if (curr.isLeaf()) {
            curr.height = 0;
    		return 0; //height of leaves is 0
    	}
    	curr.height = 1 +
            Math.max(updateHeight(curr.right), updateHeight(curr.left));
    	return curr.height;

    }
    /** TESTIN METHOD ONLY */
    public K root(){
    	return this.root.key;
    }
    
    public boolean isBalanced() {
    	return isBalanced(this.root);
    }
    private boolean isBalanced(BNode curr) {
    	if (curr.isLeaf()) {
    		return true;
    	} 
        int bf = this.balanceFactor(curr);
        if(Math.abs(bf) > 1) {
            return false;
        } else {
            return this.isBalanced(curr.right) && this.isBalanced(curr.right);
        }
    }

    public int balanceFactor() {
    	return balanceFactor(this.root);
    }
    private int balanceFactor(BNode curr) {
    	if (curr.isLeaf()) {
    		return 0;
    	}
    	return curr.left.height - curr.right.height;
    }

    private void removebalanceroutine(BNode curr) {
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
