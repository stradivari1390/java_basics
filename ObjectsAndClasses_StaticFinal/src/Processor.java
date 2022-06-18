import java.text.DecimalFormat;

public class Processor {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final int frequency;
    private final int numOfCores;
    private final DevelopersList developer;
    private final double weight;
    public Processor(int frequency, int numOfCores, DevelopersList developer, double weight) {
        this.frequency = frequency;
        this.numOfCores = numOfCores;
        this.developer = developer;
        this.weight = weight;
    }
    Processor setFrequency(int frequency) {
        return new Processor(frequency, numOfCores, developer, weight);
    }
    Processor setNumOfCores(int numOfCores) {
        return new Processor(frequency, numOfCores, developer, weight);
    }
    Processor setDeveloper(DevelopersList developer) {
        return new Processor(frequency, numOfCores, developer, weight);
    }
    Processor setWeight(double weight) {
        return new Processor(frequency, numOfCores, developer, weight);
    }

    public int getFrequency() {
        return frequency;
    }

    public int getNumOfCores() {
        return numOfCores;
    }

    public DevelopersList getDeveloper() {
        return developer;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "\nCPU: " +
                "clock signal - " + frequency + " Mhz" +
                " | number of Cores - " + numOfCores +
                " | developer - " + "<<" + developer + ">>" +
                " | weight - " + decimalFormat.format(weight) + " gr";
    }
}
