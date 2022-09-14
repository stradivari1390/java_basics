
//import com.fasterxml.jackson.core.ObjectCodec;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DataCollector collector = new DataCollector("data/MoscowMetro.html");
JsonCraft jsonCraft = new JsonCraft(collector);
        collector.collectLines();
        collector.collectStations();
        collector.collectConnections();
//        System.out.println(collector.getConnectedStationsMap()); /*prints all connections*/
        collector.collectStationsInfoFromCsvOrJsonFiles("data/data_stations");

        jsonCraft.createJsonMetroScheme("out/metro.json");
        jsonCraft.createJsonStationDataScheme("out/station_properties.json");

        JSONParser parser = new JSONParser();
        JSONObject jsonMetroObj = (JSONObject) parser.parse(collector.getParsedTextFile("out/metro.json"));
        JSONObject jsonStationsObj = (JSONObject) jsonMetroObj.get("stations");
        jsonStationsObj.keySet().forEach(k -> {
            JSONArray stationsOfLineArray = (JSONArray) jsonStationsObj.get(k);
            System.out.println("Line " + k + ": stations amount = " + stationsOfLineArray.size() + ".");
        });
    }
}