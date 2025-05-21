//315099184 Oran Shaki
package Geometry;
import Game.GameLevel;
import SpriteAndCollide.Collidable;
import SpriteAndCollide.Ball;
import SpriteAndCollide.Sprite;
import SpriteAndCollide.Velocity;

import java.awt.Color;
import SpriteAndCollide.HitNotifier;
import SpriteAndCollide.HitListener;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a block, an object which is a sprite, and other objects can
 * collide with it.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    public static final double THRESHOLD = 0.0001;
    private final Geometry.Rectangle rectangle;
    private final Color color;
    //constructor

    /**
     * Creates a new block object.
     *
     * @param color the color of the block
     * @param r     the collision shape
     */
    public Block(Rectangle r, Color color) {
        this.rectangle = r;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the "collision shape" of the object.
     */
    @Override
    public Geometry.Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @return the color of the object.
     */
    public Color getBlockColor() {
        return this.color;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param surface the surface we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getBlockColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param surface the surface we draw on.
     */
    public void drawInvisible(DrawSurface surface) {
        surface.setColor(this.getBlockColor());
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Notify the object that time passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add the object to the game.
     *
     * @param g the game we add the object to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * will be called whenever a hit occurs, and will notify all the
     * registered SpriteAndCollide.HitListener objects by calling their hitEvent method.
     * @param hitter the given ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collision point with
     * a given velocity.
     *
     * @param collisionPoint  the given point
     * @param currentVelocity the velocity of the ball
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (Math.abs(this.rectangle.getUpperLeft().getX()
                - collisionPoint.getX()) < THRESHOLD
                || Math.abs(this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth()
                        - collisionPoint.getX()) < THRESHOLD) {
            dx = -dx;
        }
        if (Math.abs(this.rectangle.getUpperLeft().getY()
                - collisionPoint.getY()) < THRESHOLD
                || Math.abs(this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight()
                        - collisionPoint.getY()) < THRESHOLD) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * @param gameLevel the main game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.getRemovedBlocks().increase(1);
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
