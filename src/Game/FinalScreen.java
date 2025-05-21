package Game;
import SpriteAndCollide.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Final screen.
 */
public class FinalScreen implements Animation {
    private Counter score;
    private boolean winner;
    public static final int WIDTH = 800; //The screen width
    public static final int HEIGHT = 600; //The screen height
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Final screen.
     * @param winner true if the player win, otherwise false.
     * @param score the score
     * @param k keyboard.
     */
    public FinalScreen(Counter score, boolean winner, KeyboardSensor k) {
        this.score = score;
        this.winner = winner;
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.GRAY);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        d.setColor(java.awt.Color.WHITE);
        if (!winner) {
            d.drawText(WIDTH / 2 - 80, d.getHeight() / 2, "Game Over", 32);
            d.drawText(WIDTH / 2 - 125, d.getHeight() / 2 + 40,
                    "Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(WIDTH / 2 - 8, d.getHeight() / 2, "You Win!", 32);
            d.drawText(WIDTH / 2 - 20, d.getHeight() / 2 + 40,
                    "Your score is " + this.score.getValue(), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }


    /**
     * returns true if the screen should close.
     * @return !this.running.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
