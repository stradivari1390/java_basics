public class Main {

    public static void main(String[] args) {
        Basket basket1 = new Basket();

        basket1.add("Milk", 40);
        basket1.add("Cookies", 65, 2, 0.150);

        basket1.print("\nBasket_1.");

        basket1.clear();

        basket1.print("\nBasket_1.");

        basket1.add("Sponge", 15);
        basket1.add("Sponge", 15, 2);
        basket1.add("Plate", 120, 2, 0.3);
        basket1.print("\nBasket_1.");

        basket1.clear();
    }
}
