public class CargoDeliveryInfo {
    private final Dimensions dimensions;
    private final int weight;
    private final String deliveryAddress;
    private final boolean canBeFlipped;
    private final String regNumber;
    private final boolean fragile;

    public CargoDeliveryInfo(Dimensions dimensions, int weight, String deliveryAddress,
                             boolean canBeFlipped, String regNumber, boolean fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canBeFlipped = canBeFlipped;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public String getDimensions() {
        return "length - " + dimensions.getLength() +
                ", width - " + dimensions.getWidth() +
                ", height - " + dimensions.getHeight();
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean getCanBeFlipped() {
        return canBeFlipped;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public boolean getFragile() {
        return fragile;
    }


    public CargoDeliveryInfo setDeliveryAddress(String deliveryAddress) {
        return new CargoDeliveryInfo(dimensions, weight, deliveryAddress, canBeFlipped,
                regNumber, fragile);
    }

    public CargoDeliveryInfo setDimensions(int length, int width, int height) {
        Dimensions dimensionsCopy = new Dimensions(length, width, height);
        return new CargoDeliveryInfo(dimensionsCopy, weight, deliveryAddress, canBeFlipped,
                regNumber, fragile);
    }

    public CargoDeliveryInfo setWeight(int weight) {
        return new CargoDeliveryInfo(dimensions, weight, deliveryAddress, canBeFlipped,
                regNumber, fragile);
    }

    @Override
    public String toString() {
        return "dimensions: " + getDimensions() +
                "\nweight: " + getWeight() +
                "\ndelivery address: " + getDeliveryAddress() +
                (canBeFlipped ? "\ncan " : "\ncan not ") + "be turned over" +
                "\nregNumber: " + getRegNumber() +
                "\nfragile = " + fragile + "\n";
    }
}
