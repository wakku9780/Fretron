package flightpath;

import flightpath.FlightPath;
import flightpath.FlightPath.Flight;
import flightpath.FlightPath.Point;

import java.util.List;

public class Main {

         public static void main(String[] args) {
        // Define flight paths
        List<Point> flight1 = List.of(new Point(50, 50), new Point(100, 100), new Point(150, 150));
        List<Point> flight2 = List.of(new Point(50, 50), new Point(100, 150), new Point(150, 100));
        List<Point> flight3 = List.of(new Point(50, 50), new Point(200, 100), new Point(150, 200));

        List<List<Point>> allFlights = new ArrayList<>();
        allFlights.add(flight1);
        allFlights.add(flight2);
        allFlights.add(flight3);

        // Create and set up the window
        JFrame frame = new JFrame("Rotated Flight Paths");
        Flight panel = new Flight(allFlights);
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
