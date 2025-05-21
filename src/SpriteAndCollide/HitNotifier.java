//315099184 Oran Shaki
package SpriteAndCollide;

/**
 * The interface indicate that objects that implement it send
 * notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the given listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the given listener.
     */
    void removeHitListener(HitListener hl);
}
