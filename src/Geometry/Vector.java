//315099184 Oran Shaki
package Geometry;

/**
 * Represents a 2D vector.
 */
public class Vector {
    private final double x;
    private final double y;

    // constructors
    /**
     * Creates a new Geometry.Vector.
     *
     * @param x represents the x-component
     * @param y represents the y-component
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Geometry.Vector that points from a to b.
     *
     * @param a A Geometry.Point object
     * @param b A Geometry.Point object
     */
    public Vector(Point a, Point b) {
        this(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * @return A double representing the angle in radians between the positive
     * x-axis and this vector
     */
    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Calculates the cross product.
     *
     * @param v A Geometry.Vector object representing a given vector
     * @return A double representing the cross product of this vector and
     * the given vector
     */
    public double cross(Vector v) {
        return this.x * v.y - v.x * this.y;
    }

    /**
     * The purpose of this function is to check if two lines are parallel.
     *
     * @param v A Geometry.Vector object
     * @return true if the vector is linearly dependent on the vector,
     * otherwise false.
     */
    public boolean isLinearlyDependent(Vector v) {
        return this.cross(v) == 0.0;
    }
}
