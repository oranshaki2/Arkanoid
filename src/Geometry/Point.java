//315099184 Oran Shaki
package Geometry;

/**
 * Represents a point with an x and a y value. can measure the distance to
 * other points, and if it is equal to another point.
 */
public class Point {
    public static final double THRESHOLD = 0.0001;
    private final double x;
    private final double y;

    /**
     * @param x the given x value
     * @param y the given y value
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * The function calculates distance between points.
     *
     * @param other the second point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double diffX = this.x - other.x;
        double diffY = this.y - other.y;
        double sum = diffX * diffX + diffY * diffY;
        double d = Math.sqrt(sum);
        return d;
    }

    /**
     * The function compares between two points to check equality.
     *
     * @param other the second point
     * @return True if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        //in order to check equality accurately
        return (Math.abs(this.getX() - other.getX()) <= THRESHOLD
                && Math.abs(this.getY() - other.getY()) <= THRESHOLD);
    }

    /**
     * @return the x value
     */
    //accessors
    public double getX() {
        return x;
    }

    /**
     * @return the y value
     */
    public double getY() {
        return y;
    }
}