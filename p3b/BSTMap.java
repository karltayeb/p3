import java.util.Collection;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;


/** Binary Search Tree Map implementation with inner Node class.
 *  @param <K> the base type of the keys in the entries
 *  @param <V> the base type of the values
 */
public class BSTMap<K extends Comparable<? super K>, V>
    implements MapJHU<K, V>, Iterable<Map.Entry<K, V>> {

    /** Inner node class.  Do not make this static because you want
        the K to be the same K as in the BSTMap header.
    */
    public class BNode extends AbstractMap.SimpleEntry<K, V> {
        /** The key of the entry (null if sentinel node). */
        protected K key;
        /** The value of the entry (null if sentinel node). */
        protected V value;
        /** The left child of this node. */
        protected BNode left;
        /** The right child of this node. */
        protected BNode right;
        /** The height of the subtree below this node */
        protected int height;

        /** Create a new node with a particular key and value.
         *  @param k the key for the new node
         *  @param v the value for the new node
         */
        BNode(K k, V v) {
            super(k, v);
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
            this.height = 0;
            if(this.key == null) {
                this.height = -1;
            }
        }

        /** Check whether this node is a leaf sentinel, based on key.
         *  @return true if leaf, false otherwise
         */
        public boolean isLeaf() {
            return this.key == null;  // this is a sentinel-based implementation
        }
        /** Simple Entry Methods.
         * @param o object to test equality on
         * @return boolean on equality
         */
        @Override
        public boolean equals(Object o) {
            //TODO check if this is correct
            BNode other = null;
            try {
                other = (BNode) o;
            } catch (ClassCastException e) {
                return false;
            }
            return super.equals(o);
        }
        /** Returns key.
         * @return key
         */
        @Override
        public K getKey() {
            return this.key;
        }
        /** Returns hashcode.
         * @return hashcode
         */
        @Override
        public int hashCode() {
            return 0;
        }
        /** Set value of Entry.
         * @param val value to be put in entry
         * @return value replaced by set
         */
        @Override
        public V setValue(V val) {
            V temp = this.value;
            this.value = val;
            return temp;
        }
        /** Gets the value of an entry.
         * @return the value in the entry
         */
        @Override
        public V getValue() {
            return this.value;
        }
        /** Custom toString method.
         * @return string representation of entry
         */
        @Override
        public String toString() {
            return "(" + this.key.toString() + ", "
                + this.value.toString() + ", " + this.height + ")";
        }
    }

    /** The root of this tree. */
    protected BNode root;
    /** The number of entries in this map (== non-sentinel nodes). */
    protected int size;
    /** Single leaf sentinal. */
    protected final BNode leaf;
    /** Track if iterator should be invalidated. */
    protected boolean modified;

    /** Create an empty tree with a sentinel root node.
     */
    public BSTMap() {
        // empty tree is a sentinel for the root
        this.root = new BNode(null, null);
        this.leaf = new BNode(null, null);
        this.modified = true;
        this.size = 0;
    }
    /** Returns size of map.
     * @return size of the map
     */
    @Override()
    public int size() {
        return this.size;
    }
    /** Clears the map. */
    @Override()
    public void clear() {
        this.modified = true;
        this.root = new BNode(null, null);
        this.size = 0;
    }
    /** Tells if the map is empty.
     * @return boolean if the map is empty
     */
    @Override()
    public boolean isEmpty() {
        return this.size() == 0;
    }
    /** Checks if the map has a given key.
     * @param key to check map for
     * @return boolean if its there
     */
    @Override()
    public boolean hasKey(K key) {
        return this.hasKey(key, this.root);
    }

    /** See if a key is in an entry in a subtree.
     *  @param key the key to search for
     *  @param curr the root of the subtree being searched
     *  @return true if found, false otherwise
     */
    public boolean hasKey(K key, BNode curr) {
        if (key == null) {
            return false;
        }
        if (curr.isLeaf()) {
            return false;
        }
        if (key.compareTo(curr.key) < 0) {
            return this.hasKey(key, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            return this.hasKey(key, curr.right);
        } else {
            return true;
        }
    }

    /** See if a value is in the tree.
     *  @param value the value being searched for
     *  @return true if found, false otherwise
     */
    @Override()
    public boolean hasValue(V value) {
        return this.hasValue(value, this.root); 
    }

    /** See if a value is in the tree.
     *  @param value the value being searched for
     *  @param curr the current node of the tree
     *  @return true if found, false otherwise
     */
    private boolean hasValue(V value, BNode curr) {
        if (value == null) {
            return false;
        }
        if (curr.isLeaf()) {
            return false;
        }
        if (curr.value.equals(value)) {
            return true;
        }
        return this.hasValue(value, curr.left)
            || this.hasValue(value, curr.right);
    }
    
    /** Get the value stored with a key in the map.
     * @param key we are checking the map for
     * @return the value stored with that key
     */
    @Override()
    public V get(K key) {
        return this.get(key, this.root);
    }

    /** Get the value associated with key from subtree with given root node.
     *  @param key the key of the entry
     *  @param curr the root of the subtree from which to get the entry
     *  @return the value associated with the key, or null if not found
     */
    public V get(K key, BNode curr) {
        if (key == null) {
            return null;
        }
        if (curr.isLeaf()) {
            return null;
        }
        if (key.compareTo(curr.key) < 0) {
            return this.get(key, curr.left);
        } else if (key.compareTo(curr.key) == 0) {
            return curr.value;
        } else {
            return this.get(key, curr.right);   
        }
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
    private V put(K key, V val, BNode curr) throws IllegalArgumentException {
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
        return null;
    }

    /** Insert a node into a subtree.
     * @param node the node to insert at
     * @param curr the node of the current subtree
     */
    private void insert(BNode node, BNode curr) {
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
    
    /** Removes a key from the map.
     * @param key to search and remove from map
     * @return value of entry removed from map
     */
    @Override()
    public V remove(K key) {
        this.modified = true;
        return this.remove(key, this.root);
    }

    /** Remove entry with specified key from subtree with given root node.
     *  @param key the key of the entry to remove, if there
     *  @param curr the root of the subtree from which to remove the entry
     *  @return the value associated with the removed key, or null if not found
     */
    public V remove(K key, BNode curr) {
        this.modified = true;
        if (key == null) {
            return null;
        }
        V temp = this.get(key);
        if (temp != null) {
            this.removeroutine(key, curr);
        }
        return temp;
    }
    /** Helper method for remove that carries out deletion.
     * @param key the key of the entry to remove, if there
     * @param curr the root of the subtree from which to remove the entry
     * @return the node associated with the removed key, or null if not found
     */
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
        return curr;
    }

    /** Helper function to remove root.*/
    private void removeroot() {
        BNode newroot = this.find(this.firstKey(this.root.right),
                                                    this.root.right);
        if (newroot.isLeaf()) {
            newroot = this.find(this.lastKey(this.root.left), this.root.left);
            this.root.key = newroot.key;
            this.root.value = newroot.value;
            this.root.left = this.removeroutine(newroot.key, this.root.left);
        } else {
            this.root.key = newroot.key;
            this.root.value = newroot.value;
            this.root.right = this.removeroutine(newroot.key, this.root.right);
        }
    }
    
    /** Remove maxvalued node in a subtree.
     * @param curr root of subtree we're searching
     * @return the node that is being removed
     */
    protected BNode removemax(BNode curr) {
        if (curr.right.isLeaf()) {
            return curr.left;
        }
        curr.right = this.removemax(curr.right);
        return curr;
    }

    /** Remove minvalued node in a subtree.
     * @param curr root of subtree we're searching
     * @return the node that is being removed
     */
    protected BNode removemin(BNode curr) {
        if (curr.left.isLeaf()) {
            return curr.right;
        }
        curr.right = this.removemin(curr.left);
        return curr;
    }
    
    /** Set of entries in the map.
     * @return a set of entries in the map.
     */
    @Override()
    public Set<Map.Entry<K, V>> entries() {
        HashSet<Map.Entry<K, V>> entrySet =
                new HashSet<Map.Entry<K, V>>(this.size());
        this.inOrderAddToCollection(this.root, entrySet);
        return entrySet;        
    }

    /** Set of keys in the map.
     * @return the set of keys inthe map.
     */
    @Override()
    public Set<K> keys() {
        HashSet<Map.Entry<K, V>> entrySet =
            (HashSet<Map.Entry<K, V>>) this.entries();
        HashSet<K> keySet = new HashSet(entrySet.size());
        for (Map.Entry<K, V> entry : entrySet) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    /** Collection of values in the map.
     * @return the collection of values in the map.
     */
    @Override()
    public Collection<V> values() {
        LinkedList<V> valueList = new LinkedList<V>();
        HashSet<Entry<K, V>> entrySet = (HashSet<Entry<K, V>>) this.entries();
        for (Map.Entry<K, V> entry : entrySet) {
            valueList.add(entry.getValue());
        }
        return valueList;
    }

    /* -----   BSTMap-specific functions   ----- */
   
     /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the min key
     */
    public K firstKey(BNode curr) {
        /* Special case, curr is a sentinal */
        if (curr.isLeaf()) {
            return null;
        }
        /* If the nodes left child is a sentinal, its the firstKey */
        if (curr.left.isLeaf()) {
            return curr.key;
        }
        return this.firstKey(curr.left);
    }

     /** Get the smallest key in a subtree.
     *  @return the min key
     */
    public K firstKey() {
        return this.firstKey(this.root);
    }

    /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the max key
     */
    public K lastKey(BNode curr) {
        /* Special case, curr is a sentinal */
        if (curr.isLeaf()) {
            return null;
        }
        /* If the nodes right child is a sentinal, its the lastKey */
        if (curr.right.isLeaf()) {
            return curr.key;
        }
        return this.lastKey(curr.right);
    }
    
    /** Get the smallest key in a subtree.
     *  @return the max key
     */
    public K lastKey() {
        return this.lastKey(this.root);
    }

    /** Inorder traversal that produces an iterator over key-value pairs.
     *  @return an iterable list of entries ordered by keys
     */
    public Iterable<Map.Entry<K, V>> inOrder() {
        return this.inOrder(this.root);
    }
    
    /** Inorder traversal produces an iterator over entries in a subtree.
     *  @param curr the root of the subtree to iterate over
     *  @return an iterable list of entries ordered by keys
     */
    private Collection<Map.Entry<K, V>> inOrder(BNode curr) {
        LinkedList<Map.Entry<K, V>> ordered = new LinkedList<Map.Entry<K, V>>();
        this.inOrderAddToCollection(curr, ordered);
        return ordered;
    }

    /** Inorder traversal that also adds the nodes to a collection.
     *  Do this instead so that you only need to recursively copy
     *  a reference to the collection rather than the whole thing
     *  @param curr the current root of the subtree
     *  @param collection the collection you are adding nodes to
     */
    private void inOrderAddToCollection(BNode curr,
                Collection<Map.Entry<K, V>> collection) {
        if (curr.isLeaf()) {
            return;
        }
        this.inOrderAddToCollection(curr.left, collection);
        collection.add(curr);
        this.inOrderAddToCollection(curr.right, collection);
    }
    
    /** Returns a copy of the portion of this map whose keys are in a range.
     *  @param fromKey the starting key of the range, inclusive if found
     *  @param toKey the ending key of the range, inclusive if found
     *  @return the resulting submap
     */
    public BSTMap<K, V> subMap(K fromKey, K toKey) {
        BSTMap<K, V> submap = new BSTMap();
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
    private void addToSubmap(BSTMap<K, V> map, BNode curr, K low, K high) {
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
    /** To string function for BSTMap.
     * @return String representation of BSTMap
     */
    @Override
    public String toString() {
        String out = "[";
        LinkedList<Map.Entry<K, V>> ordered =
                (LinkedList<Map.Entry<K, V>>) this.inOrder(this.root);
        for (Map.Entry<K, V> entry : ordered) {
            BNode node = (BNode) entry;
            out += node.toString();
            out += ", ";
        }
        out += "]";
        return out;
    }
    /* ---------- from Iterable ---------- */

    @Override
    /** Constructor for iterator*/
    public Iterator<Map.Entry<K, V>> iterator() {
        return new BSTMapIterator();
    }

    @Override
    public void forEach(Consumer<? super Map.Entry<K, V>> action) {
        // you do not have to implement this
    }

    @Override
    public Spliterator<Map.Entry<K, V>> spliterator() {
        // you do not have to implement this
        return null;
    }

    /* -----  insert the BSTMapIterator inner class here ----- */
    /** Iterator for the BSTMap.
     */
    protected class BSTMapIterator implements Iterator<Map.Entry<K, V>> {
        /** A list that holds the BSTMap.
         * Based off preorder traversal
         */
        private List<BNode> inOrderList;
        /** Iterator instantiation. */
        private Iterator<BNode> iter;
        /**Last element returned by the iterator.*/
        private BNode prev;
        /** Number of time the "next" function is called.
         * Minus the number of times "remove" is called.
         */
        private boolean canRemove;
        
        /**
         * Constructs the Iterator.
         */
        BSTMapIterator() {
            this.inOrderList = (LinkedList) BSTMap.this.inOrder();
            this.iter = this.inOrderList.iterator();
            BSTMap.this.modified = false;
            this.prev = null;
            this.canRemove = false;
        }
        
        /**
         * Tells if there is a node after the current node.
         * @return true if there is, else false.
         * @throws ConcurrentModificationException if map is changed
         */
        public boolean hasNext() throws ConcurrentModificationException {
            /** Check to see if the iterator has been invalidated */
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            return this.iter.hasNext();
        }
        
        /**
         * Gives the node after the current node based off inorder transversal.
         * @return the node after the current node
         * @throws ConcurrentModificationException if map is changed
         */
        public Entry<K, V> next() throws ConcurrentModificationException {
            /** Check to see if the iterator has been invalidated */
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            this.canRemove = true;
            this.prev = this.iter.next();
            return this.prev;
        }
        
        /**
         * Removes the last node returned by the iterator.
         * @throws ConcurrentModificationException if map is changed
         */
        @Override
        public void remove() throws ConcurrentModificationException {
            /** Check to see if the iterator has been invalidated */
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            if (this.canRemove) {
                this.canRemove = false;
                BSTMap.this.remove(this.prev.key);
                BSTMap.this.modified = false;
                this.prev = null;
                this.iter.remove();
            }
        }
    }
}
