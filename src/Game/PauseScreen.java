//315099184 Oran Shaki
package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * a very simple animation, that will display a screen with the message paused,
 * press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {
    public static final int WIDTH = 800; //The screen width
    public static final int HEIGHT = 600; //The screen height
    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k the Keyboard Sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(52, 204, 255));
        //fill the screen
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        d.drawRectangle(0, 0, WIDTH, HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(150, d.getHeight() / 2, "paused. press space to "
                        + "continue",
                32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop; }
}
