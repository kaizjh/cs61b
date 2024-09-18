package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!


    private double loadFactor = 0.75;
    private int capacity = 16;
    private int size = 0;
    private final int resizeFactor = 2;
    private Collection[] hashTable;

    /** Constructors */
    public MyHashMap() {
        hashTable = new Collection[capacity];
    }

    public MyHashMap(int initialCapacity) {
        capacity = initialCapacity;
        hashTable = new Collection[capacity];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = loadFactor;
        hashTable = new Collection[capacity];
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        int hashKey = getIndex(key);
        // 如果已经有 bucket，遍历，如果找到相同的 key，替换 value 之后，size 不变，返回
        if (hashTable[hashKey] != null) {
            Collection<Node> buckets = hashTable[hashKey];
            for (Node bucket : buckets) {
                if (bucket.key.equals(key)) {
                    bucket.value = value;
                    return;
                }
            }
        }

        // 如果不是替换，那么，先判断是否需要 resize
        if ((double) size / capacity > loadFactor) {
            resize(hashTable);
            hashKey = getIndex(key); // resize 之后需要新的 hash key
        }

        // 再判断是否需要创建新的 bucket
        if (hashTable[hashKey] == null) {
            hashTable[hashKey] = createBucket();
        }
        // 再添加新的 node，size 加一，返回
        Node newNode = new Node(key, value);
        hashTable[hashKey].add(newNode);
        size += 1;
        return;
    }

    @Override
    public V get(K key) {
        int hashKey = getIndex(key);
        if (hashTable[hashKey] == null) {
            return null;
        }
        Collection<Node> buckets = hashTable[hashKey];
        for (Node bucket : buckets) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int hashKey = getIndex(key);
        if (hashTable[hashKey] == null) {
            return false;
        }
        Collection<Node> buckets = hashTable[hashKey];
        for (Node bucket : buckets) {
            if (bucket.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    /** A helper function, converting key to hashTable index. */
    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    /** A helper function, for put() to resize
     *  multiply the capacity with the resizeFactor
     *  rehash all the item in the hashTable, modulo-ing them by the new number of buckets.
     * */
    private void resize(Collection[] hashTableCopy) {
        capacity *= resizeFactor;
        hashTable = new Collection[capacity];
        size = 0;
        for (int i = 0; i < hashTableCopy.length; i++) {
            if (hashTableCopy[i] != null) {
                Collection<Node> buckets = hashTableCopy[i];
                for (Node bucket : buckets) {
                    put(bucket.key, bucket.value);
                }
            }
        }
    }
}
