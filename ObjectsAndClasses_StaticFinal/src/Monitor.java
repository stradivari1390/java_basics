import java.text.DecimalFormat;

public class Monitor {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final double diagonal;
    private final double weight;
    private final MonitorType monitorType;

    public Monitor(double diagonal, double weight, MonitorType monitorType) {
        this.diagonal = diagonal;
        this.weight = weight;
        this.monitorType = monitorType;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public double getWeight() {
        return weight;
    }

    public MonitorType getMonitorType() {
        return monitorType;
    }
    Monitor setDiagonal(double diagonal) {
        return new Monitor(diagonal, weight, monitorType);
    }
    Monitor setWeight(double weight) {
        return new Monitor(diagonal, weight, monitorType);
    }
    Monitor setMonitorType(MonitorType monitorType) {
        return new Monitor(diagonal, weight, monitorType);
    }

    @Override
    public String toString() {
        return "\nMonitor: " +
                "diagonal - " + decimalFormat.format(diagonal) + '"' +
                " | type - " + monitorType +
                " | weight - " + decimalFormat.format(weight) + " gr";
    }
}
