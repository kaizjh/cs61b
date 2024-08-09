import org.apache.commons.codec.cli.Digest;

// You can change Placeholder to any name you like
public class DLList<Placeholder> {

    public class IntNode {
        public IntNode prev;
        public Placeholder item;
        public IntNode next;

    }

    private IntNode sentinel;
    private int size;

    public static void main(String[] args) {
        // DLList<String> d2 = new DLList<>("hello");
        // d2.addLast("world");
    }
}
