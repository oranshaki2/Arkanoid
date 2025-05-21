package Game;

import SpriteAndCollide.Counter;
import SpriteAndCollide.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    public static final int WIDTH = 800; //The screen width
    public static final int HEIGHT = 600; //The screen height
    public static final int FRAMES_PER_SECOND = 60;
    private final int countFrom;
    private final double frameNumber;
    private final SpriteCollection game;
    private final Counter frames;
    private boolean stop;
    @Override
    public void doOneFrame(DrawSurface d) {
        frames.increase(1);
        this.game.drawAllOn(d);
        int count = this.countFrom - ((int) (this.frames.getValue() / this.frameNumber));
        if (frames.getValue() >= countFrom * this.frameNumber) {
            this.stop = true;
            return;
        }
        d.setColor(java.awt.Color.DARK_GRAY);
        d.drawText(WIDTH / 4 - 1, HEIGHT / 2 + 5,
                "Game start in: " + count, 50);
        d.setColor(Color.BLACK);
        d.drawText(WIDTH / 4, HEIGHT / 2,
                "Game starts in: " + count, 50);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.frameNumber = FRAMES_PER_SECOND / (countFrom / numOfSeconds);
        this.game = gameScreen;
        this.frames = new Counter();
        this.stop = false;
    }
}
