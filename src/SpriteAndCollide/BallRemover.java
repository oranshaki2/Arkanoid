//315099184 Oran Shaki
package SpriteAndCollide;

import Game.GameLevel;
import Geometry.Block;

/**
 * in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private Counter availableBalls = new Counter();

    /**
     * Constructor.
     * @param g our game
     * @param removed the balls we removed.
     */
    public BallRemover(GameLevel g, Counter removed) {
        this.gameLevel = g;
        this.availableBalls.decrease(removed.getValue());
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
    }
}
