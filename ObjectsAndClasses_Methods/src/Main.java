

public class Main {

    public static void main(String[] args) {


        Basket basket1 = new Basket("Gluten, Powder", 365);
        Basket basket2 = new Basket();
        Basket basket3 = new Basket(50);

        basket1.add("Milk", 45);
        basket1.add("Cookies", 65, 2, 0.155);

        basket2.add("Sponge", 15);
        basket2.add("Towel", 205, 5);
        basket2.add("Plate", 125, 2, 0.35);

        basket1.print("Basket_1");
        basket2.print("Basket_2");
        basket3.print("Basket_3");
        System.out.println(Basket.globalTotalCountPrint());
        System.out.println(Basket.globalAveragePricesPrint());

        basket2.clear();

        basket1.print("Basket_1");
        basket2.print("Basket_2");
        basket3.print("Basket_3");
        System.out.println(Basket.globalTotalCountPrint());
        System.out.println(Basket.globalAveragePricesPrint());
    }
}
