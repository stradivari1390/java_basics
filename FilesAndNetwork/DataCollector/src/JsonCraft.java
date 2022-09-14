import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonCraft {
    DataCollector collector;

    public JsonCraft(DataCollector collector) {
        this.collector = collector;
    }

    public void createJsonMetroScheme(String outPath) throws IOException {
        JSONObject jsonMetroMap = new JSONObject();
        BufferedWriter writerMetroMap = Files.newBufferedWriter(Paths.get(outPath));
        JSONObject jsonLinesAndStations = new JSONObject();
        JSONArray jsonConnectedStations = new JSONArray();
        JSONArray jsonLines = new JSONArray();

        collector.getLines().forEach(l -> {
            JSONArray stationsArray = new JSONArray();
            List<Station> stationsList = collector.getStations().stream()
                    .filter(station -> station.getLine() != null &&
                            station.getLineNum().equalsIgnoreCase(l.getNumber()))
                    .toList();
            stationsList.forEach(s -> stationsArray.add(s.getName()));
            jsonLinesAndStations.put(l.getNumber(), stationsArray);
        });
        JSONArray stationsArray = new JSONArray();
        collector.getStations().stream()
                .filter(station -> station.getLine() == null)
                .forEach(s -> stationsArray.add(s.getName()));
        jsonLinesAndStations.put("Unidentified", stationsArray);
        collector.getConnectedStationsMap().entrySet().forEach(entry -> {
            if (!jsonConnectedStations.toString().contains(entry.getKey().getName())) {
                JSONArray connectionsObjectsArray = new JSONArray();
                JSONObject connectionObject = new JSONObject();
                connectionObject.put("line", entry.getKey().getLineNum());
                connectionObject.put("station", entry.getKey().getName());
                connectionsObjectsArray.add(connectionObject);
                entry.getValue().forEach(values -> {
                    JSONObject connectionObjects = new JSONObject();
                    connectionObjects.put("line", values.getLineNum());
                    connectionObjects.put("station", values.getName());
                    connectionsObjectsArray.add(connectionObjects);
                });
                jsonConnectedStations.add(connectionsObjectsArray);
            }
        });
        collector.getLines().forEach(l -> {
            JSONObject lineData = new JSONObject();
            lineData.put("number", l.getNumber());
            lineData.put("name", l.getName());
            jsonLines.add(lineData);
        });
        jsonMetroMap.put("stations", jsonLinesAndStations);
        jsonMetroMap.put("connections", jsonConnectedStations);
        jsonMetroMap.put("lines", jsonLines);
        try {
            writerMetroMap.write(jsonMetroMap.toJSONString());
            writerMetroMap.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJsonStationDataScheme(String outPath) throws IOException {
        JSONObject jsonStations = new JSONObject();
        BufferedWriter writerStationProperties = Files.newBufferedWriter(Paths.get(outPath));
        JSONArray jsonStationData = new JSONArray();

        collector.getStations().forEach(station -> {
            JSONObject jsonStation = new JSONObject();
            if (station.getName() != null) {
                jsonStation.put("name", station.getName());
            }
            if (station.getLine() != null) {
                jsonStation.put("line", station.getLine().getName());
            }
            if (station.getOpenDate() != null) {
                jsonStation.put("date", station.getOpenDate());
            }
            if (station.getDepth() != null) {
                jsonStation.put("depth", station.getDepth());
            }
            jsonStation.put("hasConnection", station.hasConnection);
            jsonStationData.add(jsonStation);
        });
        jsonStations.put("stations", jsonStationData);
        try {
            jsonStations.writeJSONString(writerStationProperties);
            writerStationProperties.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
