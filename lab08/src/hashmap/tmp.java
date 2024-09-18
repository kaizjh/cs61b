// Kimi:

//package hashmap;
//
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
///**
// * A hash table-backed Map implementation.
// *
// * Assumes null keys will never be inserted, and does not resize down upon remove().
// * @author YOUR NAME HERE
// */
//public class MyHashMap<K, V> implements Map61B<K, V> {
//
//    /**
//     * Protected helper class to store key/value pairs
//     * The protected qualifier allows subclass access
//     */
//    protected class Node {
//        K key;
//        V value;
//
//        Node(K k, V v) {
//            key = k;
//            value = v;
//        }
//    }
//
//    /* Instance Variables */
//    private Collection<Node>[] buckets;
//    private int capacity;
//    private int size;
//    private double loadFactor;
//    private static final int DEFAULT_CAPACITY = 16;
//
//    /** Constructors */
//    public MyHashMap() {
//        this(DEFAULT_CAPACITY, 0.75);
//    }
//
//    public MyHashMap(int initialCapacity) {
//        this(initialCapacity, 0.75);
//    }
//
//    /**
//     * MyHashMap constructor that creates a backing array of initialCapacity.
//     * The load factor (# items / # buckets) should always be <= loadFactor
//     *
//     * @param initialCapacity initial size of backing array
//     * @param loadFactor maximum load factor
//     */
//    public MyHashMap(int initialCapacity, double loadFactor) {
//        this.capacity = initialCapacity;
//        this.loadFactor = loadFactor;
//        buckets = new Collection[capacity];
//        for (int i = 0; i < capacity; i++) {
//            buckets[i] = createBucket();
//        }
//        size = 0;
//    }
//
//    /**
//     * Returns a data structure to be a hash table bucket
//     *
//     * The only requirements of a hash table bucket are that we can:
//     *  1. Insert items (`add` method)
//     *  2. Remove items (`remove` method)
//     *  3. Iterate through items (`iterator` method)
//     *  Note that that this is referring to the hash table bucket itself,
//     *  not the hash map itself.
//     *
//     * Each of these methods is supported by java.util.Collection,
//     * Most data structures in Java inherit from Collection, so we
//     * can use almost any data structure as our buckets.
//     *
//     * Override this method to use different data structures as
//     * the underlying bucket type
//     *
//     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
//     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
//     */
//    protected Collection<Node> createBucket() {
//        return new LinkedHashSet<>();
//    }
//
//    // Implement the methods of the Map61B Interface below
//    @Override
//    public void put(K key, V value) {
//        int index = getIndex(key);
//        Collection<Node> bucket = buckets[index];
//        for (Node node : bucket) {
//            if (node.key.equals(key)) {
//                node.value = value;
//                return;
//            }
//        }
//        bucket.add(new Node(key, value));
//        size++;
//    }
//
//    @Override
//    public V get(K key) {
//        int index = getIndex(key);
//        Collection<Node> bucket = buckets[index];
//        for (Node node : bucket) {
//            if (node.key.equals(key)) {
//                return node.value;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean containsKey(K key) {
//        int index = getIndex(key);
//        Collection<Node> bucket = buckets[index];
//        for (Node node : bucket) {
//            if (node.key.equals(key)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public void clear() {
//        for (int i = 0; i < capacity; i++) {
//            buckets[i].clear();
//        }
//        size = 0;
//    }
//
//    @Override
//    public Set<K> keySet() {
//        Set<K> keySet = new LinkedHashSet<>();
//        for (Collection<Node> bucket : buckets) {
//            for (Node node : bucket) {
//                keySet.add(node.key);
//            }
//        }
//        return keySet;
//    }
//
//    @Override
//    public V remove(K key) {
//        int index = getIndex(key);
//        Collection<Node> bucket = buckets[index];
//        for (Iterator<Node> it = bucket.iterator(); it.hasNext(); ) {
//            Node node = it.next();
//            if (node.key.equals(key)) {
//                V value = node.value;
//                it.remove();
//                size--;
//                return value;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Iterator<K> iterator() {
//        return keySet().iterator();
//    }
//
//    private int getIndex(K key) {
//        return (key.hashCode() & 0x7fffffff) % capacity;
//    }
//}