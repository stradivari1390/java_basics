import java.text.DecimalFormat;

public class Ram {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final RamType ramType;
    private final int memorySize;
    private final double weight;

    public Ram(RamType ramType, int memorySize, double weight) {
        this.ramType = ramType;
        this.memorySize = memorySize;
        this.weight = weight;
    }

    public RamType getRamType() {
        return ramType;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeight() {
        return weight;
    }
    Ram setRamType(RamType ramType) {
        return new Ram(ramType, memorySize, weight);
    }
    Ram setMemorySize(int memorySize) {
        return new Ram(ramType, memorySize, weight);
    }
    Ram setWeight(double weight) {
        return new Ram(ramType, memorySize, weight);
    }

    @Override
    public String toString() {
        return "\nRAM: " +
                "type - " + ramType +
                " | capacity - " + memorySize + " Mb" +
                " | weight - " + decimalFormat.format(weight) + " gr";
    }
}
