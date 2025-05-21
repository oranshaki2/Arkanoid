//315099184 Oran Shaki
package SpriteAndCollide;
import Geometry.Block;

/**
 * update counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * @param scoreCounter the number of points.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit the block object.
     * @param hitter the given ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
