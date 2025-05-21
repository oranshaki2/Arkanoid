//315099184 Oran Shaki
package SpriteAndCollide;

import Game.GameLevel;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Vector;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents a ball, the main object of the program.
 */
public class Ball implements Sprite {
    private Geometry.Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;
    // constructors

    /**
     * Creates a new ball object.
     *
     * @param center the middle point of the circle
     * @param r      the radius
     * @param color  the color of the ball
     */
    public Ball(Geometry.Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * @param x the value on the x-axis
     * @param y the value on the y-axis
     * @param r the radius
     * @param color the given color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    // accessors

    /**
     * @return The value of the center point on X axis
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return The value of the center point on Y axis
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return The value of the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return The velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball object on the screen.
     *
     * @param surface the screen we are willing to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), r);
    }

    @Override
    public void drawInvisible(DrawSurface d) {
    }

    /**
     * Creates a new velocity object.
     *
     * @param v the given speed
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Initializes or changes the current velocity.
     *
     * @param dx the given speed on the x-axis
     * @param dy the given speed on the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Creates a new game environment object.
     *
     * @param environment the given environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Creates the movement of the ball.
     */
    public void moveOneStep() {
        double x = this.center.getX();
        double y = this.center.getY();

        Vector v = new Vector(this.getVelocity().getDx(),
                this.getVelocity().getDy());
        double angle = v.angle();

        //calculates the value of the end point of the movement on the X-axis
        double trajectoryEdgeX = this.center.getX() + Math.cos(angle) * this.r;
        //calculates the value of the end point of the movement on the Y-axis
        double trajectoryEdgeY = this.center.getY() + Math.sin(angle) * this.r;
        //calculate the route the ball is about to make
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                trajectoryEdgeX, trajectoryEdgeY);

        //check possibility for collide
        if (this.environment.getClosestCollision(trajectory) != null) {
            CollisionInfo info =
                    this.environment.getClosestCollision(trajectory);
            //find the collision point
            Geometry.Point collisionP = info.collisionPoint();

            Velocity newV = info.collisionObject().hit(this, collisionP,
                    this.getVelocity());
            //updates the ball's velocity
            this.setVelocity(newV);
        }
        //move the ball another step
        this.center = new Geometry.Point(x + this.getVelocity().getDx(),
                y + this.getVelocity().getDy());
    }

    /**
     * Add the object to the game.
     *
     * @param g the game we are willing to add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param g our game.
     */
    public void removeFromGame(GameLevel g) {
        g.getRemovedBalls().increase(1);
        g.removeSprite(this);
    }
}
