package flightpath;

 package FlightPath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Flight extends JPanel {
    private List<List<Point>> flightPaths;

    public Flight(List<List<Point>> flightPaths) {
        this.flightPaths = flightPaths;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Move the origin to the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.translate(centerX, centerY);

        // Rotate by 270 degrees clockwise (or -90 degrees)
        g2d.rotate(Math.toRadians(270));

        // Move the origin back to the top-left corner
        g2d.translate(-centerX, -centerY);

        // Draw each flight path and add circles at the end of each line segment
        for (List<Point> path : flightPaths) {
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < path.size() - 1; i++) {
                Point p1 = path.get(i);
                Point p2 = path.get(i + 1);

                // Draw the line
                g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));

                // Draw a circle at the end point of each line segment
                g2d.fillOval(p2.x - 5, p2.y - 5, 10, 10); // Circle of radius 5
            }
        }

        // Restore the original transform
        g2d.setTransform(originalTransform);
    }
