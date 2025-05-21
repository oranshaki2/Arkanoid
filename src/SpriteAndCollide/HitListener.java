//315099184 Oran Shaki
package SpriteAndCollide;

import Geometry.Block;

/**
 * Objects that want to be notified of hit events, should implement the
 * interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the object being hit
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
