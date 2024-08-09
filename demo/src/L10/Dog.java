package L10;

import java.util.Comparator;

public class Dog {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    /** Class NameComparator is private, this is a helper function to create a new NameComparator. */

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
