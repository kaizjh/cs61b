import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class Node {
        public Node prev;
        public T item;
        public Node next;
    }

    public Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        size ++;
        Node tmp = new Node();
        tmp.item = x;

        sentinel.next.prev = tmp;
        tmp.next = sentinel.next;

        sentinel.next = tmp;
        tmp.prev = sentinel;
     }

    @Override
    public void addLast(T x) {
        size ++;
        Node tmp = new Node();
        tmp.item = x;

        sentinel.prev.next = tmp;
        tmp.prev = sentinel.prev;

        sentinel.prev = tmp;
        tmp.next = sentinel;
    }

    @Override
    /** A big usage of this function is turing a non-iterable LinkedListDeque61B into an iterable ArrayList. */
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (Node tmp = sentinel.next; tmp != sentinel; tmp = tmp.next) {
            returnList.add(tmp.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        return last.item;
    }

    @Override
    public T get(int index) {
        Node result = sentinel;
        for (int i = index; i < size && i >= 0; i --) {
            result = result.next;
        }
        return result.item;
    }

    /** A helper function of getRecursive(int index). */
    public T getRecursive(int index, Node tmp) {
        if (index >= size || index < 0) {
            return tmp.item;
        }
        return getRecursive(index - 1, tmp.next);
    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }
}
