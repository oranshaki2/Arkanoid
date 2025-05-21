//315099184 Oran Shaki
package SpriteAndCollide;

import Geometry.Point;

/**
 * Represents the speed of the ball.
 */
public class Velocity {
    private final double dx;
    private final double dy;
    // constructor

    /**
     * Creates a new velocity.
     *
     * @param dx represents the velocity on the x-axis
     * @param dy represents the velocity on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // accessors
    /**
     * @return the velocity on y-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the velocity on y-axis
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     *
     * @param p the point we are willing to change
     * @return The new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Converts the angle to radians, and calculate dx, dy using trigonometry.
     *
     * @param angle the given angle
     * @param speed the given speed
     * @return The new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.sin(radians) * speed;
        double dy = Math.cos(radians) * (-speed);
        return new Velocity(dx, dy);
    }
}