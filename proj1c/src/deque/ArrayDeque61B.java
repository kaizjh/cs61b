package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] arr;
    private int first;
    private int last;
    private int size;
    private double leastUsage;

    public ArrayDeque61B() {
        arr = (T[]) new Object[8];
        first = 0;
        last = 1;
        size = 0;
        leastUsage = 0.25;
    }

    /** A helper function for addFirst and addLast, copy old array into a new bigger array, without arraycopy!
     *  After resizing, the first comes to the head of the line, and the last comes to the end of the line,
     *  and nulls after the last.
     */
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
//        System.arraycopy(arr, 0, tmp, size - last, first + 1);
//        System.arraycopy(arr, last, tmp, 0, size - last);
//        arr = tmp;
        for (int i = 0; i < size; i ++) {
            tmp[i] = get(i);
        }
        arr = tmp;
        first = -1;
        last = size;
    }

    @Override
    /** Add an element at the first of the circle deque, with constant time. */
    public void addFirst(T x) {
        if (arr.length == size) {
            resize(size * 2);
        }
        first = Math.floorMod(first, arr.length);
        arr[first] = x;
        first -= 1;
        size += 1;
    }

    @Override
    /** Add an element at the last of the circle deque, with constant time. */
    public void addLast(T x) {
        if (arr.length == size) {
            resize(size * 2);
        }
        last = Math.floorMod(last, arr.length);
        arr[last] = x;
        last += 1;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    /** Check if the arrayDeque61B is empty, with constant time. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    /** Return size of the arrayDeque61B. */
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = get(0);
        first += 1;
        arr[Math.floorMod(first, arr.length)] = null;
        size -= 1;
        if (size < arr.length * leastUsage) {
            resize(arr.length / 2);
        }
        return returnValue;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T returnValue = get(size - 1);
        last -= 1;
        arr[Math.floorMod(last, arr.length)] = null;
        size -= 1;
        if (size < arr.length * leastUsage) {
            resize(arr.length / 2);
        }
        return returnValue;
    }

    @Override
    /** Get arrayDeque61B's element by index, with constant time. */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return arr[Math.floorMod(first + index + 1, arr.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
