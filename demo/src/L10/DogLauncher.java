package L10;

import java.util.Comparator;

public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("foo", 12);
        Dog d2 = new Dog("baba", 217);
        Dog d3 = new Dog("foo", 623);
        Dog[] dogs = new Dog[]{d1, d2, d3};
        Comparator<Dog> nc = Dog.getNameComparator();
    }
}