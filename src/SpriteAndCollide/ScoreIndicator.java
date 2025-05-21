//315099184 Oran Shaki
package SpriteAndCollide;

import Game.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 *  in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    /**
     * Constructor.
     * @param score the score's numeric value.
     */
    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        String text = "Score: " + scoreCounter.getValue();
        surface.drawText(350, 20, text, 20);
        surface.setColor(Color.RED);
        surface.drawText(310, 20, "\u2665", 20);
        surface.drawText(460, 20, "\u2665", 20);
    }

    @Override
    public void drawInvisible(DrawSurface d) {

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
