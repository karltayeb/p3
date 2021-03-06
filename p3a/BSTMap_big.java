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
    private class BNode<K, V> extends AbstractMap.SimpleEntry<K, V> {
        /** The key of the entry (null if sentinel node). */
        private K key;
        /** The value of the entry (null if sentinel node). */
        private V value;
        /** The left child of this node. */
        private BNode<K, V> left;
        /** The right child of this node. */
        private BNode<K, V> right;

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
            BNode<K, V> other = null;
            try {
                other = (BNode<K, V>) o;
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
        public String toString() {
            return "(" + this.key.toString() + ", " + this.value.toString() + ")";
        }
    }

    /** The root of this tree. */
    private BNode<K, V> root;
    /** The number of entries in this map (== non-sentinel nodes). */
    private int size;
    /** Single leaf sentinal. */
    private BNode<K, V> leaf;
    /** Track if iterator should be invalidated. */
    private boolean modified;
    /** The last value removed. */
    private V lastRemoved;

    /** Create an empty tree with a sentinel root node.
     */
    public BSTMap() {
        // empty tree is a sentinel for the root
        this.root = new BNode<K, V>(null, null);
        this.leaf = new BNode<K, V>(null, null);
        this.lastRemoved = null;
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
        this.root = new BNode<K, V>(null, null);
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
    public boolean hasKey(K key, BNode<K, V> curr) {
        if (key == null) {
            //Throw Error?
        }
        if (curr.isLeaf()) {
            return false;
        }
        if (key.compareTo(curr.key) < 0) {
            return this.hasKey(key, curr.left);
        } else if (key.compareTo(curr.key) == 0) {
            return true;
        } else {
            return this.hasKey(key, curr.right);
        }
    }

    @Override()
    public boolean hasValue(V value) {
        return false; 
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
    public V get(K key, BNode<K, V> curr) {
        if (key == null) {
            //Throw error
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
    private V put(K key, V val, BNode<K, V> curr) {
        this.modified = true;
        // TODO: check that key, val are not null and throw error otherwise?

        /* If the map has the key, replace the value */
        if (this.hasKey(key, curr)) {
            return this.find(key, curr).setValue(val);
        }
        /* Otherwise make a new node and put it in the tree */
        BNode<K, V> node = new BNode<K, V>(key, val);
        node.right = leaf;
        node.left = leaf;
        this.insert(node, this.root);
        return null;
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
    public V remove(K key, BNode<K, V> curr) {
        if (key == null) {
            //throw error
        }
        this.removehelp(key, curr);
        return this.lastRemoved;
    }
    
    /** Helper method for remove that carries out deletion.
     * @param key the key of the entry to remove, if there
     * @param curr the root of the subtree from which to remove the entry
     * @return the node associated with the removed key, or null if not found
     */
    private BNode<K, V> removehelp(K key, BNode<K, V> curr) {
        if (key == null) {
            //Return error?
        }
        if (curr.isLeaf()) {
            return null;
        }
        if (key.compareTo(curr.key) < 0) {
            curr.left = this.removehelp(key, curr.left); 
        } else if (key.compareTo(curr.key) > 0) {
            curr.right = this.removehelp(key, curr.left);
        } else {
            /*Now we have found the node we want to delete (or its not here)*/
            this.lastRemoved = curr.value;
            if (curr.left.isLeaf()) {
                return curr.right;
            } else if (curr.right.isLeaf()) {
                return curr.left;
            } else {
                /* Case where there are two children, we need to find
                 * the greatest key smaller than what we are deleting */
                BNode<K, V> node = this.find(this.lastKey(curr.left), curr.left);
                /* Replace deleted value with biggest value smaller than it*/
                curr.key = node.key;
                curr.value = node.value;
                /* Delete the biggest value smaller from it from
                 * the left subtree. By being the biggest value of
                 * this subtree it necessarily doesn't have two children
                 * thus we are guarunteed not to have infinite recursion */
                this.remove(curr.key, curr.left);
            }
        }
        return null;
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
    /** Insert a node into a subtree.
     * @param node the node to insert at
     * @param curr the node of the current subtree
     */
    private void insert(BNode<K, V> node, BNode<K, V> curr) {
        this.modified = true;
        /* If we try to insert on a sentinal, replace the sentinal. */
        if (curr.isLeaf()) {
            curr = node;
            this.size++;
            System.out.println("Got here.");
            if (curr.isLeaf()) System.out.println("Still leaf.");
            return;
        }
        /* Otherwise move right or left and insert*/
        if (node.key.compareTo(curr.key) <= 0) {
            this.insert(node, curr.left);
        } else if (node.key.compareTo(curr.key) > 0) {
            this.insert(node, curr.right);
        }
    }
    /** Find and return the node with a key in a subtree, null if not found.
     * @param key the key to look for
     * @param curr the root of the subtree we're searching
     */
    private BNode<K, V> find(K key, BNode<K, V> curr) {
        if (curr.isLeaf()) {
            return null;
        }
        if (key.compareTo(curr.key) < 0) {
            return this.find(key, curr.left);
        } else if (key.compareTo(curr.key) == 0) {
            return curr;
        } else {
            return this.find(key, curr.right);
        }
    }

    /* -----   BSTMap-specific functions   ----- */

    /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the min key
     */
    public K firstKey(BNode<K, V> curr) {
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

    /** Get the smallest key in a subtree.
     *  @param curr the root of the subtree to search
     *  @return the max key
     */
    public K lastKey(BNode<K, V> curr) {
        /* Special case, curr is a sentinal */
        //TODO: should this throw an error?
        if (curr.isLeaf()) {
            return null;
        }
        /* If the nodes right child is a sentinal, its the lastKey */
        if (curr.right.isLeaf()) {
            return curr.key;
        }
        return this.firstKey(curr.right);
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
    private Collection<Map.Entry<K, V>> inOrder(BNode<K, V> curr) {
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
    private void inOrderAddToCollection (BNode<K, V> curr, Collection<Map.Entry<K, V>> collection) {
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
        return null;
    }
    
    @Override
    public String toString() {
        String out = "[";
        LinkedList<Map.Entry<K, V>> ordered = (LinkedList<Map.Entry<K, V>>) this.inOrder(this.root);
        for (Map.Entry<K, V> entry : ordered) {
            BNode <K, V> node = (BNode <K, V>) entry;
            out += node.toString();
            out += ", ";
        }
        out += "]";
        return out;
    }

    /* ---------- from Iterable ---------- */

    @Override
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
    private class BSTMapIterator implements Iterator<Map.Entry<K, V>> {
        private LinkedList<BNode<K, V>> list;
        private Iterator<BNode<K, V>> iter;
        private boolean canRemove;
        public BSTMapIterator() {
            this.list = (LinkedList) BSTMap.this.inOrder();
            iter = list.iterator();
            BSTMap.this.modified = false;
        }
        @Override
        public boolean hasNext() {
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            return iter.hasNext();
        }
        @Override
        public Map.Entry<K, V> next() {
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            this.canRemove = true;
            return iter.next();
        }
        @Override
        public void remove() {
            if (BSTMap.this.modified) {
                throw new ConcurrentModificationException();
            }
            if (!this.canRemove) {
                throw new IllegalStateException();
            }
            /* TODO: Actually remove */
            this.canRemove=false;
            return; 
        }
    }
}
