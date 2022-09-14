import java.util.Objects;

public class Line implements Comparable<Line> {

    private String number;
    private String name;

    public Line(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Line line) {
        return number.compareTo(line.getNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line line)) return false;
        return getNumber().equals(line.getNumber()) && getName().equals(line.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName());
    }

    @Override
    public String toString() {
        return name;
    }
}