//315099184 Oran Shaki
package Game;
import Geometry.Line;
import Geometry.Point;
import SpriteAndCollide.Collidable;
import SpriteAndCollide.CollisionInfo;

import java.util.ArrayList;
/**
 * Represents the environment of the game.
 */
public class GameEnvironment {
    private final java.util.ArrayList<Collidable> list;

    //constructor
    /**
     * initialize an empty list.
     */
    public GameEnvironment() {
        this.list = new ArrayList<Collidable>();
    }


    /**
     * add the given collidable to the environment.
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * @param c the object we remove.
     */
    public void deleteCollidable(Collidable c) {
        this.list.remove(c);
    }

    /**
     * The object moving from line.start() to line.end().
     *
     * @param trajectory the expected route of the ball.
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double shortest = Double.POSITIVE_INFINITY;
        CollisionInfo finalObject = null;
        ArrayList<Collidable> tempList = new ArrayList<>(this.list);
        for (Collidable object : tempList) {
            Point intersectionP = trajectory.closestIntersectionToStartOfLine(
                    object.getCollisionRectangle());
            if (intersectionP != null) {
                double newDistance = intersectionP.distance(trajectory.start());
                if (newDistance < shortest) {
                    shortest = newDistance;
                    finalObject = new CollisionInfo(object, intersectionP);
                }
            }
        }
        return finalObject;
    }
}
