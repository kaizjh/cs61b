public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /** An instance variable to record the first element of a SLList instance
     *  If we lost the first, we lost the whole SLList instance.
     */
    private IntNode first;

    // The constructor
    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        IntNode tmp = first;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new IntNode(x, null);
    }

    /** This is a helper function
     *  Returns the number of items in the list though IntNode using recursion. */
    public int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }
        return 1 + size(p.next);
    }

    /** This is the real size function, and it is allowed in java to name two methods the same name,
     *  since they have different parameters. */
    public int size() {
        return size(first);
    }
}
