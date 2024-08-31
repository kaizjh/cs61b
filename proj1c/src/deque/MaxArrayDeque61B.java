package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    Comparator<T> co;

    public MaxArrayDeque61B(Comparator<T> c) {
        co = c;
    }

    public MaxArrayDeque61B() { }

    public T max() {
        if (isEmpty()) {
            return null;
        } else {
            T maxitem = get(0);
            for (int i = 1; i < size(); i ++) {
                if (co.compare(maxitem, get(i)) < 0) {
                    maxitem = get(i);
                }
            }
            return maxitem;
        }
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        } else {
            T maxitem = get(0);
            for (int i = 1; i < size(); i ++) {
                if (c.compare(maxitem, get(i)) < 0) {
                    maxitem = get(i);
                }
            }
            return maxitem;
        }
    }
}
