import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
public class Basket {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static int globalItemsCount = 0;
    private static int globalTotalPrice = 0;
    private static int filledBasketsCount = 0;

    private String items;
    private int basketTotalPrice = 0;
    private int basketItemsCount = 0;
    private int limit;
    private double totalWeight = 0;

    public Basket() {
        items = "Goods list:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(@NotNull String items, int basketTotalPrice) {
        this();
        int commaCount = (int) items.chars().filter(c -> c == (int) ',').count();
        this.items += "\n" + items + " - " + basketTotalPrice + " rub.";
        this.basketTotalPrice = basketTotalPrice;
        basketItemsCount = commaCount + 1;
        globalItemsCount += commaCount + 1;
        globalTotalPrice += basketTotalPrice;
        filledBasketsCount += 1;
    }

    private static void increaseGlobalTotalPrice(int count) {
        globalTotalPrice += count;
    }

    private static void increaseGlobalItemsCount(int count) {
        globalItemsCount += count;
    }

    public static double averageItemPrice() {
        return (double) globalTotalPrice / globalItemsCount;
    }
    public static double averageBasketPrice() {
        if (filledBasketsCount != 0) {return (double) globalTotalPrice / filledBasketsCount;}
        else {return 0;}
    }
    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int count) {
        add(name, price, count, 0);
    }

    public void add(String name, int price, int count, double weight) {

        boolean error = items.contains(name);

        if (basketTotalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            return;
        }
        if (items.equals("Goods list:")) {
            filledBasketsCount += 1;
        }
        items = items + "\n" + name + " - " +
                count + " pc. - " + count + " X " + price + " rub.";
        basketItemsCount += count;
        basketTotalPrice += count * price;
        totalWeight += weight * count;

        Basket.increaseGlobalTotalPrice(count * price);
        Basket.increaseGlobalItemsCount(count);
    }

    public void clear() {
        globalItemsCount -= getBasketItemsCount();
        globalTotalPrice -= getBasketTotalPrice();
        items = "Goods list:";
        basketTotalPrice = 0;
        totalWeight = 0;
        basketItemsCount = 0;
        filledBasketsCount -= 1;
    }

    public int getBasketItemsCount() {
        return basketItemsCount;
    }

    public int getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public static int getGlobalTotalPrice() {
        return globalTotalPrice;
    }

    public static int getGlobalItemsCount() {
        return globalItemsCount;
    }

    public static int getFilledBasketsCount() {
        return filledBasketsCount;
    }

    public void print(String title) {
        System.out.println("\n" + title + ":\n");
        if (items.equals("Goods list:")) {
            System.out.println("The basket is empty");
        } else {
            System.out.println(items + "\nTotal items: " + getBasketItemsCount() +
                    "\nTotal weight: " + getTotalWeight() + " kg" +
                    "\nTotal price: " + getBasketTotalPrice() + " rub.");
        }
    }
    public static @NotNull String globalTotalCountPrint() {
        return "\nTOTAL ITEMS: " + Basket.getGlobalItemsCount() + " pc." +
                "\nTOTAL FILLED BASKETS: " + Basket.getFilledBasketsCount() +
                "\nTOTAL COST: " + Basket.getGlobalTotalPrice() + " rub.\n";
    }
    public static @NotNull String globalAveragePricesPrint() {
        return "Average item price: " +
                decimalFormat.format(Basket.averageItemPrice()) +
                " rub.\nAverage basket price: " +
                decimalFormat.format(Basket.averageBasketPrice()) + " rub.";
    }
}
