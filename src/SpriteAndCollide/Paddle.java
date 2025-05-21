//315099184 Oran Shaki
package SpriteAndCollide;
import Game.GameLevel;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * Represents a paddle, an object which is a sprite, and other objects can
 * collide with it.
 */
public class Paddle implements Sprite, Collidable {
    public static final double PADDLE_HEIGHT = 10 - 0.1; //in order to make
    // sure the paddle's height is less than the ball's radius.
    public static final int BALL_SPEED = 5;
    public static final double ANGLE_PART1 = 300.0;
    public static final double ANGLE_PART2 = 330.0;
    public static final double ANGLE_PART4 = 30.0;
    public static final double ANGLE_PART5 = 60.0;
    public static final double RIGHT_EDGE = 780;
    public static final double LEFT_EDGE = 24.9;
    private biuoop.KeyboardSensor keyboard;
    private Geometry.Rectangle rectangle;
    private int speed;
    //constructor

    /**
     * Creates a new paddle.
     * @param k the connection to the keyboard.
     * @param rect the shape of the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(KeyboardSensor k, Rectangle rect, int speed) {
        this.keyboard = k;
        this.rectangle = rect;
        this.speed = speed;
    }
    //new Rectangle(new Geometry.Point(360.0, 570.0),PADDLE_WIDTH, PADDLE_HEIGHT);

    /**
     * Creates the movement of the paddle to the left, only if it is still
     * in the range of the screen.
     */

    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() > LEFT_EDGE) {
            Geometry.Point p = new Geometry.Point(this.rectangle.getUpperLeft()
                    .getX() - 5, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Rectangle(p, this.rectangle.getWidth(), PADDLE_HEIGHT);
        }
    }

    /**
     * Creates the movement of the paddle to the right, only if it is still
     * in the range of the screen.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()
                < RIGHT_EDGE) {
            Geometry.Point p = new Geometry.Point(this.rectangle.getUpperLeft()
                    .getX() + 5, this.rectangle.getUpperLeft().getY());
            this.rectangle = new Geometry.Rectangle(p, this.rectangle.getWidth(),
                    PADDLE_HEIGHT);
        }
    }


    /**
     * Makes the paddle move.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle after coloring it.
     *
     * @param d the surface we draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(255, 213, 128));
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    @Override
    public void drawInvisible(DrawSurface d) {
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game we add to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    /**
     * @return the "collision shape" of the object.
     */
    @Override
    public Geometry.Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collision point with
     * a given velocity.
     *
     * @param collisionPoint  the given point
     * @param currentVelocity the velocity of the ball
     * @param hitter the ball
     * @return the updated velocity expected after the hit (based on
     * the angle the object hit).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double startingX = this.rectangle.getUpperLeft().getX();
        if (startingX <= collisionPoint.getX()
                && collisionPoint.getX() <= startingX + 30.0) {
            return Velocity.fromAngleAndSpeed(ANGLE_PART1, BALL_SPEED);
        } else if (startingX + 30.0 < collisionPoint.getX()
                && collisionPoint.getX() <= startingX + 60.0) {
            return Velocity.fromAngleAndSpeed(ANGLE_PART2, BALL_SPEED);
        } else if (startingX + 60.0 < collisionPoint.getX()
                && collisionPoint.getX() <= startingX + 90.0) {
            return new Velocity(dx, -dy);
        } else if (startingX + 90.0 < collisionPoint.getX()
                && collisionPoint.getX() <= startingX + 120.0) {
            return Velocity.fromAngleAndSpeed(ANGLE_PART4, BALL_SPEED);
        } else if (startingX + 120.0 < collisionPoint.getX()
                && collisionPoint.getX() <= startingX + 150.0) {
            return Velocity.fromAngleAndSpeed(ANGLE_PART5, BALL_SPEED);
        }
        return currentVelocity;
    }
}
