import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

public class DataCollector {
    private final String sourcePath;
    private List<Line> lines = new ArrayList<>();
    private List<Station> stations = new ArrayList<>();
    private Connection connectedStationsMap = new Connection();
    private Document document;

    public DataCollector(String sourcePath) {
        this.sourcePath = sourcePath;
        document = Jsoup.parse(getParsedTextFile(sourcePath));
    }

    public String getParsedTextFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(builder::append);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return builder.toString();
    }

    public void collectLines() {
        Elements lineElements = document.getElementsByAttributeValueStarting("class", "js-metro-line t-metrostation-list-header t-icon-metroln ln");
        lineElements.forEach(element -> {
            Line line = new Line(element.attr("data-line"),
                    element.ownText().replaceAll("ё", "е").trim());
            lines.add(line);
        });
    }

    public void collectStations() throws NoSuchElementException {
        Elements stationsByLinesElements = document.getElementsByClass("js-metro-stations t-metrostation-list-table");
        stationsByLinesElements.forEach(element -> element.getElementsByClass("name").forEach(e -> {
            Station station = new Station(e.text().replaceAll("ё", "е").trim(),
                    lines.stream()
                            .filter(l -> l.getNumber().equals(element.attr("data-line")))
                            .findFirst().get());
            stations.add(station);
        }));
    }

    public void collectConnections() throws NoSuchElementException {
        Elements stationsByLinesElements = document.getElementsByClass("js-metro-stations t-metrostation-list-table");
        stationsByLinesElements.forEach(lineAndItsStations -> lineAndItsStations.select("p:has(span[title])").forEach(stationWithTransfers -> {
            Station transferStation;
            List<Station> connectedStations = new ArrayList<>();
            String transferStationLineNum = lineAndItsStations.attr("data-line");
            String transferInfo = stationWithTransfers.text();
            int indexSpace = transferInfo.lastIndexOf(".");
            String stationName = transferInfo.substring(indexSpace + 1)
                    .replaceAll("ё", "е").trim();
            transferStation = stations.stream()
                    .filter(s -> s.getName().equalsIgnoreCase(stationName) && s.getLineNum().equalsIgnoreCase(transferStationLineNum))
                    .findFirst().get();
            stationWithTransfers.select("span[title]").forEach(transferDescription -> {
                String lineNum = transferDescription.attr("class");
                lineNum = lineNum.substring(lineNum.lastIndexOf("-") + 1);
                String connectedStationName = transferDescription.attr("title");
                connectedStationName = connectedStationName.substring(connectedStationName.indexOf("«") + 1, connectedStationName.lastIndexOf("»"))
                        .replaceAll("ё", "е").trim();
                String finalLineNum = lineNum;
                String finalConnectedStationName = connectedStationName;
                connectedStations.addAll(stations.stream()
                        .filter(s -> s.getName().equalsIgnoreCase(finalConnectedStationName) && s.getLineNum().equalsIgnoreCase(finalLineNum))
                        .toList());
            });
            connectedStationsMap.addStation(transferStation, connectedStations);
        }));
    }


    public void collectStationsInfoFromCsvOrJsonFiles(String path) {
        fileReader(path);
    }


    private void fileReader(String path) throws NullPointerException {
        try {
            File stationInfoDirection = new File(path);

            if (stationInfoDirection.isFile()) {
                if (stationInfoDirection.getName().endsWith(".json")) {
                    getDataFromJson(stationInfoDirection);
                }

                if (stationInfoDirection.getName().endsWith(".csv")) {
                    getDataFromCsv(stationInfoDirection);
                }
            } else {
                File[] files = stationInfoDirection.listFiles();
                for (File file : files) {
                    fileReader(file.getPath());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getDataFromJson(File file) throws NoSuchElementException {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonData = (JSONArray) parser.parse(getParsedTextFile(file.getPath()));
            for (Object object : jsonData) {
                JSONObject stationJsonObject = (JSONObject) object;
                String stationName = stationJsonObject.get("name") != null ?
                        stationJsonObject.get("name").toString().replaceAll("ё", "е").trim() :
                        stationJsonObject.get("station_name").toString().replaceAll("ё", "е").trim();
                if (stations.stream().map(Station::getName).noneMatch(n -> n.equalsIgnoreCase(stationName))) {
                    stations.add(new Station(stationName, null));
                }
                if (file.getName().startsWith("date")) {
                    String openDate = (String) stationJsonObject.get("date");
                    stations.stream().filter(s -> s.getName().equalsIgnoreCase(stationName))
                            .forEach(station -> station.setOpenDate(openDate));
                } else if (file.getName().startsWith("depth")) {
                    String depth = stationJsonObject.get("depth") != null ?
                            stationJsonObject.get("depth").toString()
                                    .replaceAll(",", ".")
                                    .replaceAll("\u2212", "-") :
                            stationJsonObject.get("depth_meters").toString()
                                    .replaceAll(",", ".")
                                    .replaceAll("\u2212", "-");
                    depth = depth.replaceAll("[^-\\d.]", "");
                    if (depth.matches("^-?[0-9]+.?[0-9]*")) {
                        String finalDepth = depth;
                        stations.stream().filter(s -> s.getName().equalsIgnoreCase(stationName)).forEach(station -> station.setDepth(Double.parseDouble(finalDepth)));
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getDataFromCsv(File file) throws IOException, NoSuchElementException {
        BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
        CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);
        List<CSVRecord> records = csvParser.getRecords().stream().filter(record -> !record.get(0).contains("Название")).toList();
        records.forEach(record -> {
            String csvStationName = record.get(0).replaceAll("ё", "е").trim();
            if (stations.stream().map(Station::getName).noneMatch(name -> name.equalsIgnoreCase(csvStationName))) {
                stations.add(new Station(csvStationName, null));
            }
            if (file.getName().startsWith("date")) {
                stations.stream()
                        .filter(s -> s.getName().equalsIgnoreCase(csvStationName) && s.getOpenDate() == null)
                        .forEach(station -> station.setOpenDate(record.get(1)));
            }
            else if (file.getName().startsWith("depth")) {
                String depth = record.get(1)
                        .replaceAll("\u2212", "-")
                        .replaceAll(",", ".")
                        .replaceAll("[^-\\d.]", "");
                if (depth.matches("^-?[0-9]+.?[0-9]*")) {
                    stations.stream()
                            .filter(s -> s.getName().equalsIgnoreCase(csvStationName) && s.getDepth() == null)
                            .forEach(station -> station.setDepth(Double.parseDouble(depth)));
                }
            }
        });
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Map<Station, List<Station>> getConnectedStationsMap() {
        return connectedStationsMap.getConnectedStationsMap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataCollector collector)) return false;
        return Objects.equals(getLines(), collector.getLines()) &&
                Objects.equals(getStations(), collector.getStations()) &&
                Objects.equals(getConnectedStationsMap(), collector.getConnectedStationsMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLines(), getStations(), getConnectedStationsMap());
    }

    @Override
    public String toString() {
        return "DataCollector:\n" + lines + "\n" + stations + "\n" + connectedStationsMap;
    }
}