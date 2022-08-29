import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(findPlanesLeavingInTheNextTwoHours(Airport.getInstance()));
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        long currentTime = System.currentTimeMillis();
        long searchTime = currentTime + 3600 * 1000 * 2;
        ArrayList<Flight> allFLightsList = new ArrayList<>();
        airport.getTerminals().forEach(t -> allFLightsList.addAll(t.getFlights()));
        ArrayList<Flight> departuresWithinNextTwoHoursList = new ArrayList<>();
        allFLightsList.stream()
                .filter((flight) -> (flight.getDate().getTime() >= currentTime &&
                        flight.getDate().getTime() <= searchTime) &&
                        flight.getType() == Flight.Type.DEPARTURE)
                .forEach(departuresWithinNextTwoHoursList::add);
        return departuresWithinNextTwoHoursList;
    }

}