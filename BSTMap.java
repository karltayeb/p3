import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.lang.IllegalStateException;


/** Binary Search Tree Map implementation with inner Node class.
 *  @param <K> the base type of the keys in the entries
 *  @param <V> the base type of the values
 */
public class BSTMap<K extends Comparable<? super K>, V>
    implements MapJHU<K, V>, Iterable<Map.Entry<K, V>> {

    /** Inner node class.  Do not make this static because you want
        the K to be the same K as in the BSTMap header.
    */
    private class BNode extends AbstractMap.SimpleEntry<K, V> {
        /** The key of the entry (null if sentinel node). */
        private K key;
        /** The value of the entry (null if sentinel node). */
        private V value;
        /** The left child of this node. */
        private BNode left;
        /** The right child of this node. */
        private BNode right;

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
        }

        /** Check whether this node is a leaf sentinel, based on key.
         *  @return true if leaf, false otherwise
         */
        public boolean isLeaf() {
            return this.key == null;  // this is a sentinel-based implementation
        }
        /** Simple Entry Methods */
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
        @Override
        public K getKey() {
            return this.key;
        }
        @Override
        public int hashCode() {
            return 0;
        }
        @Override
        public V setValue(V val) {
            V temp = this.value;
            this.value = val;
            return temp;
        }
        @Override
        public V getValue() {
            return this.value;
        }
        @Override
        public String toString() {
            return "(" + this.key.toString() + ", " + this.value.toString() + ")";
        }
    }

    /** The root of this tree. */
    private BNode root;
    /** The number of entries in this map (== non-sentinel nodes). */
    private int size;
    /** Single leaf sentinal. */
    private final BNode leaf;
    /** Track if iterator should be invalidated. */
    private boolean modified;

    /** Create an empty tree with a sentinel root node.
     */
    public BSTMap() {
        // empty tree is a sentinel for the root
        this.root = new BNode(null, null);
        this.leaf = new BNode(null, null);
        this.modified = true;
        this.size = 0;
    }

    @Override()
    public int size() {
        return this.size;
    }

    @Override()
    public void clear() {
        this.modified = true;
        this.root = new BNode(null, null);
        this.size = 0;
    }

    @Override()
    public boolean isEmpty() {
        return this.size() == 0;
    }

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

    @Override()
    public boolean hasValue(V value) {
        return this.hasValue(value, this.root); 
    }
    
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
        return this.hasValue(value, curr.left) || this.hasValue(value, curr.right);
    }
    
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

    @Override()
    public V put(K key, V val) {
        this.modified = true;
        return this.put(key, val, this.root);
    }

    /** Put <key,value> entry into subtree with given root node.
     *  @param key the key of the entry
     *  @param val the value of the entry
     *  @param curr the root of the subtree into which to put the entry
     *  @return the original value associated with the key, or null if not found
     */
    private V put(K key, V val, BNode curr) throws IllegalArgumentException {
        this.modified = true;
        // TODO: check that key, val are not null and throw error otherwise?
        if (key == null || val == null) {
            throw new IllegalArgumentException();
        }
        /* If the map has the key, replace the value */
        /*
        if (this.hasKey(key, curr)) {
            return this.find(key, curr).setValue(val);
        }
        */
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
        if (node.key.compareTo(curr.key) <= 0) {
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
     */
    private BNode find(K key, BNode curr) {
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
        if (key == null) {
            //throw error
        }
        V temp = this.get(key);
        if (temp != null) {
            this.removeroutine(key, curr);
            this.size--;
        }
        return temp;
    }
    
    /** Helper method for remove that carries out deletion.
     * @param key the key of the entry to remove, if there
     * @param curr the root of the subtree from which to remove the entry
     * @return the node associated with the removed key, or null if not found
     */
    private BNode removeroutine(K key, BNode curr) {
        if (key == null) {
            //error?
        }
        if (curr.isLeaf()) {
            return this.leaf;
        }
        if (key.compareTo(curr.key) < 0) {
            curr.left = this.removeroutine(key, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            curr.right = this.removeroutine(key, curr.right);
        } else {
            // We found the node we want to delete
            if (curr == this.root) { //Catch-all special case for when root is being deleted
                    BNode newroot = this.find(this.firstKey(this.root.right), this.root.right);
                    if (newroot.isLeaf()) {
                        newroot = this.find(this.lastKey(this.root.left), this.root.left);
                        this.root.key = newroot.key;
                        this.root.value = newroot.value;
                        this.root.left = this.removeroutine(newroot.key, this.root.left);
                        return this.root;
                    }
                    this.root.key = newroot.key;
                    this.root.value = newroot.value;
                    this.root.right = this.removeroutine(newroot.key, this.root.right);
                    return this.root;
            } else if (curr.right.isLeaf() && curr.left.equals(this.leaf)) {
                //Has no children, set this node to leaf
                return this.leaf;
            } else if (curr.right.isLeaf()) {
                return curr.left;
            } else if (curr.left.isLeaf()) {
                return curr.right;
            } else {
                //There are two children
                BNode replace = this.find(this.firstKey(curr.right), curr.right);
                curr.key = replace.key;
                curr.value = replace.value;
                curr.right = this.removeroutine(replace.key, curr.right);
                return curr;
            }
        }
        return curr;
    }
    
    //FOR TESTING ONLY, REMOVE WHEN DONE
    public void removemax() {
        this.removemax(this.root);
    }
    //FOR TESTING ONLY, REMOVE WHEN DONE
    public V getRoot() {
        return this.root.value;
    }

    private BNode removemax(BNode curr) {
        if (curr.right.isLeaf()) {
            return curr.left;
        }
        curr.right = this.removemax(curr.right);
        return curr;
    }

    private BNode removemin(BNode curr) {
        if (curr.left.isLeaf()) {
            return curr.right;
        }
        curr.right = this.removemin(curr.left);
        return curr;
    }
    
    @Override()
    public Set<Map.Entry<K, V>> entries() {
    // Fill in
        return null;
    }

    @Override()
    public Set<K> keys() {
    //Fill in
        return null;
    }

    @Override()
    public Collection<V> values() {
    // Fill in
        return null;
    }


    /* -----   BSTMap-specific functions   ----- */
   
     /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the min key
     */
    public K firstKey(BNode curr) {
        /* Special case, curr is a sentinal */
        //TODO: should this throw an error?
        if (curr.isLeaf()) {
            return null;
        }
        /* If the nodes left child is a sentinal, its the firstKey */
        if (curr.left.isLeaf()) {
            return curr.key;
        }
        return this.firstKey(curr.left);
    }

    public K firstKey() {
        return this.firstKey(this.root);
    }

    /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the max key
     */
    public K lastKey(BNode curr) {
        /* Special case, curr is a sentinal */
        //TODO: should this throw an error?
        if (curr.isLeaf()) {
            return null;
        }
        /* If the nodes right child is a sentinal, its the lastKey */
        if (curr.right.isLeaf()) {
            return curr.key;
        }
        return this.lastKey(curr.right);
    }
    
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
    private void inOrderAddToCollection (BNode curr, Collection<Map.Entry<K, V>> collection) {
        if (curr.isLeaf()) {
            return;
        }
        inOrderAddToCollection(curr.left, collection);
        collection.add(curr);
        inOrderAddToCollection(curr.right,collection);
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

    @Override
    public String toString() {
        String out = "[";
        LinkedList<Map.Entry<K, V>> ordered = (LinkedList<Map.Entry<K, V>>) this.inOrder(this.root);
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
    public Iterator<Map.Entry<K, V>> iterator() {
        return null;
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
}
