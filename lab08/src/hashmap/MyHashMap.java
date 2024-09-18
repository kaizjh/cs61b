package hashmap;

import org.apache.hc.client5.http.cookie.Cookie;

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

    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_CAPACITY = 16;
    private final double loadFactor;
    private int capacity;
    private int size;
    private final int resizeFactor = 2;

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
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
        if (key == null) {
            throw new IllegalArgumentException("Please enter a valid key!");
        }

        // 先判断是否需要resize
        if ((double) size / capacity > loadFactor) {
            resize();
        }

        int index = getIndex(key);
        // 遍历，如果找到相同的 key，替换 value 之后，size 不变，返回
        for (Collection<Node> bucket : buckets) {
            for (Node n : bucket) {
                if (n.key.equals(key)) {
                    n.value = value;
                    return;
                }
            }
        }
        // 如果没有相同的 key，添加新的 node，size 加一，返回
        buckets[index].add(new Node(key, value));
        size += 1;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        Collection<Node> bucket = buckets[index];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Collection<Node> bucket = buckets[index];
        for (Node n : bucket) {
            if (n.key.equals(key)) {
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
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> s = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node n : bucket) {
                s.add(n.key);
            }
        }
        return s;
    }

    @Override
    public V remove(K key) {
        V returnValue = null;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Node n : buckets[i]) {
                    if (n.key.equals(key)) {
                        returnValue = n.value;
                        buckets[i].remove(n);
                    }
                }
            }
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /** A helper function, converting key to hashTable index. */
    private int getIndex(K key) { return Math.floorMod(key.hashCode(), capacity); }

    /** A helper function, for put() to resize
     *  multiply the capacity with the resizeFactor
     *  rehash all the item in the hashTable, modulo-ing them by the new number of buckets.
     * */
    private void resize() {
        capacity *= resizeFactor;
        Collection<Node>[] newBuckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) { newBuckets[i] = createBucket(); }

        // 遍历旧哈希表，rehash 之后加入新哈希表中
        for (Collection<Node> bucket : buckets) {
            for (Node n : bucket) {
                newBuckets[getIndex(n.key)].add(n);
            }
        }
        buckets = newBuckets;
    }
}
