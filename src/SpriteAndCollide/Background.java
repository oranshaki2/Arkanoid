package SpriteAndCollide;

import Game.GameLevel;
import biuoop.DrawSurface;
/**
 * @author Oran Shaki.
 * Background class.
 */
public class Background implements Sprite {
    private final SpriteCollection sc;
    /**
     * Constructor.
     * @param sc sprite collection.
     */
    public Background(SpriteCollection sc) {
        this.sc = sc;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.sc.drawAllOn(d);
    }

    @Override
    public void drawInvisible(DrawSurface d) {

    }

    @Override
    public void timePassed() {
        this.sc.notifyAllTimePassed();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
