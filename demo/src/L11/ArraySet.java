package L11;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    /* To do:
    0. Implement ArraySet operations: contains, add, size
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */

    private T[] arr;
    private int size;

    public ArraySet() {
        arr = (T[]) new Object[100];
        size = 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i ++) {
            if (x.equals(arr[i])) { // Integers, Strings
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        arr[size] = x;
        size ++;
    }

    public int size() {
        return size;
    }

    /** returns an iterator (a.k.a. seer) into ME */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = arr[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            sb.append(arr[i].toString());
            sb.append(", ");
        }
        sb.append(arr[size - 1].toString());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
//        Iterator<Integer> iter = aset.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        for (int i : aset) {
            System.out.println(i);
        }

        //toString
        System.out.println(aset);
//
//        //equals
//        ArraySet<Integer> aset2 = new ArraySet<>();
//        aset2.add(5);
//        aset2.add(23);
//        aset2.add(42);
//
//        System.out.println(aset.equals(aset2));
//        System.out.println(aset.equals(null));
//        System.out.println(aset.equals("fish"));
//        System.out.println(aset.equals(aset));

        //EXTRA VIDEO CODE
        //ArraySet<String> asetOfStrings = ArraySet.of("hi", "I'm", "here");
        //System.out.println(asetOfStrings);
    }
}
