import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class Connection {

    private TreeMap<Station, List<Station>> connectedStations;

    public Connection() {
        connectedStations = new TreeMap<>();
    }

    public void addStation(Station station, List<Station> connectedStations) {
        this.connectedStations.put(station, connectedStations);
        station.hasConnection = true;
    }

    public TreeMap<Station, List<Station>> getConnectedStationsMap() {
        return connectedStations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection that)) return false;
        return Objects.equals(connectedStations, that.connectedStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectedStations);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        connectedStations.keySet()
                .forEach(c -> {
                    builder.append(c)
                            .append(", line ")
                            .append(c.getLineNum())
                            .append(" has connection(s) to: ");
                    connectedStations.get(c).forEach(to -> builder.append(to)
                            .append(", line ")
                            .append(to.getLineNum())
                            .append("; "));
                    builder.append("\n");
                });
        return builder.toString().trim();
    }
}