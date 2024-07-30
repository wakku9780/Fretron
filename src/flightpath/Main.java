package flightpath;

import flightpath.FlightPath;
import flightpath.FlightPath.Flight;
import flightpath.FlightPath.Point;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Define flight paths
        List<Point> flight1Path = List.of(new Point(1, 1), new Point(2, 2), new Point(3, 3));
        List<Point> flight2Path = List.of(new Point(1, 1), new Point(2, 4), new Point(3, 2));
        List<Point> flight3Path = List.of(new Point(1, 1), new Point(4, 2), new Point(3, 4));

        Flight flight1 = new Flight(flight1Path);
        Flight flight2 = new Flight(flight2Path);
        Flight flight3 = new Flight(flight3Path);

        List<Flight> flights = List.of(flight1, flight2, flight3);

        // Draw the flights
        FlightPath.drawFlights(flights);
    }
}
