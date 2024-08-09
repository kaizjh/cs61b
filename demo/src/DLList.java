// You can change Placeholder to any name you like
public class DLList<Placeholder> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public Placeholder item;
        public IntNode next;

    }

    public static void main(String[] args) {
        // DLList<String> d2 = new DLList<>("hello");
        // d2.addLast("world");
    }

}