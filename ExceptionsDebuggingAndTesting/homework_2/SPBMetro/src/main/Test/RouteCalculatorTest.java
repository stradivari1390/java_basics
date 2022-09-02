import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    List<Station> routeOnTheLine;
    List<Station> routeOneConnection;
    List<Station> routeTwoConnections;
    List<Station> routeShortest;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    Station station1;
    Station station2;
    Station station3;
    Station station4;
    Station station5;
    Station station6;
    Station station7;
    Station station8;

    @Override
    public void setUp() throws Exception {
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "First");
        Line line2 = new Line(2, "Second");
        Line line3 = new Line(3, "Third");

        station1 = new Station("Alabama", line1);
        station2 = new Station("Broadway", line1);
        station3 = new Station("CarnegieHall", line2);
        station4 = new Station("Denver", line2);
        station5 = new Station("Ebony", line3);
        station6 = new Station("Fall", line3);
        station7 = new Station("Garlef", line3);
        station8 = new Station("Harlem", line3);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);
        Stream.of(station1, station2, station3, station4, station5, station6, station7, station8)
                .peek(station -> station.getLine().addStation(station))
                .forEach(stationIndex::addStation);

        stationIndex.addConnection(Stream.of(station2, station4).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(station3, station7).collect(Collectors.toList()));

//        Stream.of(station7, station2, station4).forEach(stationIndex::getConnectedStations);

        routeCalculator = new RouteCalculator(stationIndex);

        routeOnTheLine = Stream.of(station5, station6, station7, station8)
                .collect(Collectors.toList());
        routeOneConnection = Stream.of(station1, station2, station4, station3)
                .collect(Collectors.toList());
        routeTwoConnections = Stream.of(station1, station2, station4, station3, station7, station6)
                .collect(Collectors.toList());
        routeShortest = Stream.of(station1, station2, station4, station3, station7, station8)
                .collect(Collectors.toList());
    }

    @Test
    public void testGetShortestRoute() {
        List<Station> actual = routeCalculator.getShortestRoute(station1, station8);
        List<Station> expected = routeShortest;
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(routeShortest);
        double expected = 14.5;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteOnTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(station5, station8);
        List<Station> expected = routeOnTheLine;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(station1, station3);
        List<Station> expected = routeOneConnection;
        assertEquals(expected, actual);
    }

    //    @Test
//    public void testIsConnected() {}
//    @Test
//    public void testGetRouteViaConnectedLine() {}
    @Test
    public void testGetRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(station1, station6);
        List<Station> expected = routeTwoConnections;
        assertEquals(expected, actual);
    }

    @Override
    public void tearDown() throws Exception {
    }
}

