public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public void addFirst(int x) {
        IntList tmp = this;
        rest = tmp;
        first = x;
    }

    public int getFirst() {
        return first;
    }

    public static void main(String[] args) {
        IntList lst = new IntList(2, null);
        System.out.println(lst.getFirst());
        lst.addFirst(23);
        System.out.println(lst.getFirst());
    }
}
