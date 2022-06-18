import java.text.DecimalFormat;

public class Storage {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final StorageType storageType;
    private final int memorySize;
    private final double weight;

    public Storage(StorageType storageType, int memorySize, double weight) {
        this.storageType = storageType;
        this.memorySize = memorySize;
        this.weight = weight;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeight() {
        return weight;
    }
    Storage setStorageType(StorageType storageType) {
        return new Storage(storageType, memorySize, weight);
    }
    Storage setMemorySize(int memorySize) {
        return new Storage(storageType, memorySize, weight);
    }
    Storage setWeight(double weight) {
        return new Storage(storageType, memorySize, weight);
    }

    @Override
    public String toString() {
        return "\nStorage: " +
                "type - " + storageType +
                " | capacity - " + memorySize + " Mb" +
                " | weight - " + decimalFormat.format(weight) + " gr";
    }
}
