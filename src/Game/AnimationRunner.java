//315099184 Oran Shaki
package Game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper = new Sleeper();

    /**
     * Constructor.
     * @param frames the given number of frames per second.
     * @param gui the given GUI.
     */
    public AnimationRunner(int frames, GUI gui) {
        this.framesPerSecond = frames;
        this.gui = gui;
    }

    /**
     * @param animation the animation we would like to activate.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
