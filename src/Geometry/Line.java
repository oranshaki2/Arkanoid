//315099184 Oran Shaki
package Geometry;

/**
 * Represents a line-segment, connects two points- a start point and an end
 * point.
 */
public class Line {
    public static final double THRESHOLD = 0.0001;
    private final Point start;
    private final Point end;
    private final double b;
    private final double m;

    // constructors

    /**
     * Creates a new line object.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        double diffX = this.start.getX() - this.end.getX();
        double diffY = this.start.getY() - this.end.getY();
        this.m = diffY / diffX;
        this.b = this.start.getY() - m * this.start.getX();
    }

    /**
     * @param x1 the given value on the x-axis
     * @param y1 the given value on the y-axis
     * @param x2 the given value on the x-axis
     * @param y2 the given value on the y-axis
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
        double diffX = this.start.getX() - this.end.getX();
        double diffY = this.start.getY() - this.end.getY();
        this.m = diffY / diffX;
        this.b = this.start.getY() - m * this.start.getX();
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double sumX = this.start.getX() + this.end.getX();
        double sumY = this.start.getY() + this.end.getY();
        return new Point(sumX / 2, sumY / 2);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (other.start.equals(this.start) && other.end.equals(this.end))
                || (other.start.equals(this.end) && other.end.equals(this.start));
    }

    /**
     * @param l the line object
     * @return true if the line is vertical to the x-axis
     */
    public boolean isVertical(Line l) {
        return l.start.getX() == l.end.getX();
    }

    /**
     * calculates the signed area of a square formed by the four points.
     * The parameter is positive if the two lines intersect to the left
     * of the origin, negative if they intersect to the right, and
     * zero if they are parallel.
     *
     * @param other A Geometry.Line object
     * @return The value of t1 for the intersection point, or -1.0 if
     * the lines are collinear, or -2.0 if the lines are not intersect
     */
    private double calculateArea(Line other) {
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        if (ab.isLinearlyDependent(cd)) {
            return -1.0;
        }
        Vector ac = new Vector(this.start, other.start);
        double t1 = ac.cross(cd) / ab.cross(cd);
        double t2 = -(ab.cross(ac) / ab.cross(cd));
        if (t1 < 0.0 || t1 > 1.0 || t2 < 0.0 || t2 > 1.0) {
            return -2.0;
        }
        return t1;
    }

    /**
     * @param other A Geometry.Line object
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double t1 = this.calculateArea(other);
        //not intersecting
        if (t1 == -2.0) {
            return false;
        }
        //the lines are not linear dependent
        if (t1 != -1.0) {
            return true;
        }
        if (isVertical(this)) {
            //if the lines are parallel
            if (Math.abs(this.start.getX() - other.start.getX()) > THRESHOLD) {
                return false;
            }
            double biggerYfirst, biggerYother, minYfirst, minYother;
            biggerYfirst = Math.max(this.start.getY(), this.end.getY());
            biggerYother = Math.max(other.start.getY(), other.end.getY());
            minYfirst = Math.min(this.start.getY(), this.end.getY());
            minYother = Math.min(other.start.getY(), other.end.getY());
            //The line is not above and not below the other line
            return !(biggerYfirst < minYother) && !(minYfirst > biggerYother);
        }
        //the lines have different equations
        if (!(Math.abs(this.b - other.b) <= THRESHOLD)) {
            return false;
        }
        double biggerXfirst, biggerXother, minXfirst, minXother;
        biggerXfirst = Math.max(this.start.getX(), this.end.getX());
        biggerXother = Math.max(other.start.getX(), other.end.getX());
        minXfirst = Math.min(this.start.getX(), this.end.getX());
        minXother = Math.min(other.start.getX(), other.end.getX());
        //The line is not on the right and not on the left of the other line
        return !(biggerXfirst < minXother) && !(minXfirst > biggerXother);
    }

    /**
     * @param other A Geometry.Line object
     * @return Returns the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double t1 = this.calculateArea(other);
        //there in no intersection
        if (t1 == -2.0) {
            return null;
        }
        //the lines don't intersect at an infinite number of points
        if (t1 != -1.0) {
            double x =
                    this.start.getX() + t1
                            * (this.end.getX() - this.start.getX());
            double y = this.start.getY() + t1
                    * (this.end.getY() - this.start.getY());
            return new Point(x, y);
        }

        Vector v1 = new Vector(this.start, this.end);
        Vector v2 = new Vector(other.start, other.end);
        //Both angles are equal
        if (Math.abs(v1.angle() - v2.angle()) <= THRESHOLD) {
            //One starts where the other ends
            if (this.end.equals(other.start)) {
                return this.end;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
        } else {
            //Both start at the same point
            if (this.start.equals(other.start)) {
                return this.start;
            }
            //Both end at the same point
            if (this.end.equals(other.end)) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * @param list A List of intersection points
     * @return the closest point to the line from all the points in the list.
     */
    public Point closestPoint(java.util.List<Point> list) {
        double shortest = this.start.distance(list.get(0));
        Point finalP = list.get(0);
        double newDistance;
        for (Point p : list) {
            newDistance = this.start.distance(p);
            if (newDistance < shortest) {
                shortest = newDistance;
                finalP = p;
            }
        }
        return finalP;
    }


    /**
     * @param rect A rectangle object
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        return closestPoint(rect.intersectionPoints(this));
    }
}
