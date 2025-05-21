//315099184 Oran Shaki
package Geometry;
import java.util.ArrayList;
/**
 * Represents a rectangle object.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the point on the rectangle located on the upper left
     *                  side.
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a list of intersection points.
     *
     * @param line the line we are checking intersection with
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        Point upperRight = new Point(upperLeft.getX() + width,
                upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        Line top = new Line(upperLeft, upperRight);
        Line right = new Line(upperRight, lowerRight);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);

        java.util.List<Line> sides = new ArrayList<Line>();
        //Add the sides of the rectangle to the list
        sides.add(top);
        sides.add(right);
        sides.add(bottom);
        sides.add(left);

        for (Line side : sides) {
            if (line.isIntersecting(side)) {
                Point p = line.intersectionWith(side);
                if (!intersectionPoints.contains(p)) {
                    intersectionPoints.add(p);
                }
            }
        }
        return intersectionPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
