import java.util.Objects;

public class Station implements Comparable<Station> {
    private Line line;
    private String name;
    private String openDate;
    private Double depth;
    public boolean hasConnection = false;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public String getOpenDate() {
        return openDate;
    }

    public Double getDepth() {
        return depth;
    }

    public Line getLine() {
        return line;
    }

    public String getLineNum() throws NullPointerException {
        return line.getNumber();
    }

    public String getName() {
        return name;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate; //try DateTimeFormatter.ofPattern("dd.MM.yyyy").parse(openDate).toString()
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(Station station) {
        if(line.getNumber().compareTo(station.getLineNum()) != 0) {
            return line.getNumber().compareTo(station.getLineNum());
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station station)) return false;
        return hasConnection == station.hasConnection && line.equals(station.line) && getName().equals(station.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, getName(), hasConnection);
    }

    @Override
    public String toString() {
        return name;
    }
}
