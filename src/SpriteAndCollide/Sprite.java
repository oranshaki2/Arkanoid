//315099184 Oran Shaki
package SpriteAndCollide;
import Game.GameLevel;
import biuoop.DrawSurface;
/**
 * Represents a sprite object.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the surface we draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * draw the frame to the screen.
     * @param d the surface we draw on.
     */
    void drawInvisible(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite object to the game.
     * @param g the game.
     */
    void addToGame(GameLevel g);
}
