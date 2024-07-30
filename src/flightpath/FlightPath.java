package flightpath;

import java.util.List; // Import the List interface from the java.util package

public class FlightPath {

    // Static inner class representing a point in 2D space
    public static class Point {
        int x, y; // Coordinates of the point

        // Constructor to initialize the point with given coordinates
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Static inner class representing a flight with a path
    public static class Flight {
        List<Point> path; // List of points representing the flight path

        // Constructor to initialize the flight with a given path
        public Flight(List<Point> path) {
            this.path = path;
        }
    }

    // Method to draw flights and check for intersections
    public static void drawFlights(List<Flight> flights) {
        for (int i = 0; i < flights.size(); i++) { // Loop through each flight
            for (int j = i + 1; j < flights.size(); j++) { // Compare with every other flight
                if (checkIntersect(flights.get(i), flights.get(j))) { // Check if flights intersect
                    System.out.println("Flights " + (i + 1) + " and " + (j + 1) + " intersect!");
                    // Modify flight paths here to avoid intersection if needed
                } else { // If flights do not intersect
                    System.out.println("Drawing flight paths for Flight " + (i + 1) + " and Flight " + (j + 1));
                    drawPath(flights.get(i).path); // Draw path for the first flight
                    drawPath(flights.get(j).path); // Draw path for the second flight
                }
            }
        }
    }

    // Method to draw a path by printing line segments between consecutive points
    public static void drawPath(List<Point> path) {
        for (int i = 0; i < path.size() - 1; i++) { // Loop through each point in the path
            System.out.printf("Line from (%d, %d) to (%d, %d)%n",
                    path.get(i).x, path.get(i).y, path.get(i + 1).x, path.get(i + 1).y); // Print the line segment
        }
    }

    // Method to check if two flights intersect
    public static boolean checkIntersect(Flight flight1, Flight flight2) {
        for (int i = 0; i < flight1.path.size() - 1; i++) { // Loop through each segment of the first flight
            for (int j = 0; j < flight2.path.size() - 1; j++) { // Loop through each segment of the second flight
                if (intersect(flight1.path.get(i), flight1.path.get(i + 1),
                        flight2.path.get(j), flight2.path.get(j + 1))) { // Check if segments intersect
                    return true; // If any segment intersects, return true
                }
            }
        }
        return false; // If no segments intersect, return false
    }

    // Method to check if two line segments intersect
    public static boolean intersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2); // Orientation of (p1, q1, p2)
        int o2 = orientation(p1, q1, q2); // Orientation of (p1, q1, q2)
        int o3 = orientation(p2, q2, p1); // Orientation of (p2, q2, p1)
        int o4 = orientation(p2, q2, q1); // Orientation of (p2, q2, q1)

        // General case: check if the orientations are different
        if (o1 != o2 && o3 != o4) return true;

        // Does not account for collinear points in this implementation
        return false;
    }

    // Method to find the orientation of the triplet (p, q, r)
    public static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); // Calculate the orientation value
        if (val == 0) return 0; // If val is 0, points are collinear
        return (val > 0) ? 1 : 2; // If val is positive, return 1 (clockwise), otherwise return 2 (counterclockwise)
    }
}
