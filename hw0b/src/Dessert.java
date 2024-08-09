public class Dessert {

    int flavor;
    int price;

    static int numDesserts;

    public Dessert(int f, int p) {
        flavor = f;
        price = p;
        numDesserts ++;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
