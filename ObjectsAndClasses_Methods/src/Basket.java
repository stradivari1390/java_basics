public class Basket {

    private static int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int totalCount = 0;
    private int limit;
    private double totalWeight = 0; //in kilograms

    public Basket() {
        increaseCount(1);
        items = "Goods list:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int count) {
        add(name, price, count, 0);
    }

    public void add(String name, int price, int count, double weight) {

        boolean error = contains(name);

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            return;
        }

        items = items + "\n" + name + " - " +
                count + " pc. - " + count + " X " + price + "rub.";
        totalCount += count;
        totalPrice += count * price;
        totalWeight += weight * count;
    }

    public void clear() {
        items = "Goods list:";
        totalPrice = 0;
        totalWeight = 0;
        totalCount = 0;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty() || items == "Goods list:") {
            System.out.println("The basket is empty");
        } else {
            System.out.println(items + "\nTotal items: " + getTotalCount() +
                    "\nTotal weight: " + getTotalWeight() + " kg" +
                    "\nTotal price: " + getTotalPrice() + " rub.");
        }
    }
}
