import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private K key;
    private V value;
    private BSTMap left;
    private BSTMap right;
    private int size;

    public BSTMap() {
        key = null;
        value = null;
        left = null;
        right = null;
        size = 0;
    }

    /** A helper function for put(). */
    public void add(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            size += 1;
        }
        if (this.key == null) {
            this.add(key, value);
        } else {
            int cmp = key.compareTo(this.key);
            if (cmp < 0) {
                if (left == null) {
                    left = new BSTMap<K, V>();
                    left.add(key, value);
                }
                left.put(key, value);
            } else if (cmp > 0) {
                if (right == null) {
                    right = new BSTMap<K, V>();
                    right.add(key, value);
                }
                right.put(key, value);
            } else {
                this.value = value;
            }
        }
    }

    @Override
    public V get(K key) {
        if (this.key != null) {
            int cmp = key.compareTo(this.key);
            if (cmp == 0) {
                return this.value;
            } else if (cmp < 0) {
                if (left != null) {
                    return (V) left.get(key); // Kimi: left.get(key) 返回的是 Object 类型，因为 Java 的泛型在运行时会被擦除，这被称为类型擦除。
                }
            } else {
                if (right != null) {
                    return (V) right.get(key);
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (this.key != null) {
            int cmp = key.compareTo(this.key);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                if (left != null) {
                    return left.containsKey(key);
                }
            } else {
                if (right != null) {
                    return right.containsKey(key);
                }
            }
        }
        return false;
    }

    @Override
    public int size() { return size; }

    @Override
    public void clear() {
        key = null;
        value = null;
        left = null;
        right = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        if (key != null) {
            s.add(key);
        }
        if (left != null) {
            s.addAll(left.keySet());
        }
        if (right != null) {
            s.addAll(right.keySet());
        }
        return s;
    }

    @Override
    public V remove(K key) {
        BSTMap<K, V> current = this;
        BSTMap<K, V> parent = null;
        BSTMap<K, V> successor = null;
        boolean isLeftChild = false;

        // 查找要删除的节点
        while (current.key != null && current.key.compareTo(key) != 0) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
        }

        // 如果没有找到要删除的节点
        if (current.key == null) {
            return null;
        }

        if (current.left == null || current.right == null) { // 节点没有子节点或者只有一个子节点
            if (parent == null) { // 如果是根节点
                if (current.left != null) {
                    this.key = (K) current.left.key;
                    this.value = (V) current.left.value;
                    this.left = current.left.left;
                    this.right = current.left.right;
                } else if (current.right != null) {
                    this.key = (K) current.right.key;
                    this.value = (V) current.right.value;
                    this.left = current.right.left;
                    this.right = current.right.right;
                } else {
                    // 没有子节点，直接移除
                    this.key = null;
                    this.value = null;
                    this.left = null;
                    this.right = null;
                }
            } else { // 如果不是根节点
                if (isLeftChild) { // 如果是左子节点
                    if (current.left != null) {
                        parent.left = current.left;
                    } else {
                        parent.left = current.right;
                    }
                } else { // 如果是右子节点
                    if (current.left != null) {
                        parent.right = current.left;
                    } else {
                        parent.right = current.right;
                    }
                }
            }
        } else { // 节点有两个子节点，找到后继节点（右子树中的最小节点）
            successor = current.right;
            while (successor.left != null) {
                parent = successor;
                successor = successor.left;
            }
            // 将后继节点的值复制到当前节点
            V tmp = current.value;
            current.key = successor.key;
            current.value = successor.value;
            current.right = successor.right;
            return tmp;
//            // 删除后继节点
//            if (parent != null) {
//                parent.left = successor.right;
//            }
        }
        return current.value;
    }

    /** Returns an iterator over the keys, in sorted order. */
    @Override
    public Iterator<K> iterator() { return new BSTMapIter(); }

    private class BSTMapIter implements Iterator<K> {

        List<K> kl;

        public BSTMapIter() {
            kl = new ArrayList<>(keySet());
            Collections.sort(kl);
        }

        @Override
        public boolean hasNext() { return !kl.isEmpty(); }

        @Override
        public K next() {
            K tmp = kl.getFirst();
            kl.remove(tmp);
            return tmp;
        }
//
//        // 这是我问 Kimi 得到的实现，原来使用 keySet()输出 Set<K> 不是没有意义的。
//        private Iterator<K> iterator;
//
//        public BSTMapIter() {
//            Set<K> keySet = keySet();
//            iterator = keySet.iterator();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return iterator.hasNext();
//        }
//
//        @Override
//        public K next() {
//            return iterator.next();
//        }
    }

    /** A helper function for testing, which prints out the BSTMap in order of increasing Key. */
    public void printInOrder() {
        
    }
}
