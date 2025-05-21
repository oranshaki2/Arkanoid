//315099184 Oran Shaki
package SpriteAndCollide;

import Geometry.Block;
import Game.GameLevel;


/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private Counter remainingBlocks = new Counter();

    /**
     * @param gameLevel our game.
     * @param removedBlocks how many blocks we removed
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks.decrease(removedBlocks.getValue());
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        //this.gameLevel.setBlockCounter(this.gameLevel.getRemovedBlocks()
        // .getValue() - 1);
    }
}
