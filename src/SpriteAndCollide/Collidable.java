//315099184 Oran Shaki
package SpriteAndCollide;

import Geometry.Point;
import Geometry.Rectangle;

/**
 * Represents a collidable object.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @return the updated velocity expected after the hit (based on
     *      the force the object inflicted on us).
     * @param hitter the given ball
     * @param collisionPoint the given point
     * @param currentVelocity the given velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
