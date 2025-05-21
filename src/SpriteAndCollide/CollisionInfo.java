//315099184 Oran Shaki
package SpriteAndCollide;

import Geometry.Point;

/**
 * Holds the type of the object and the point at which the collision occurs.
 */
public class CollisionInfo {
    private final Point p;
    private final Collidable object;
    //constructor

    /**
     * Creates a new SpriteAndCollide.CollisionInfo object.
     *
     * @param object the type of the object
     * @param p the collision point
     */
    public CollisionInfo(Collidable object, Point p) {
        this.object = object;
        this.p = p;
    }
    // accessors

    /**
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
