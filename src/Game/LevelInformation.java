package Game;

import Geometry.Block;
import SpriteAndCollide.Sprite;
import SpriteAndCollide.Velocity;

import java.util.List;

/**
 * specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls ledt in the game.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * @return The initial speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return The initial width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     */
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
