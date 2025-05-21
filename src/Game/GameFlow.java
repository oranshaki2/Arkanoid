package Game;

import SpriteAndCollide.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * in charge of creating the different levels, and moving from one level to
 * the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter scoreCounter;
    private GUI g;
    private Boolean winner;

    /**
     * Constructor.
     */
    public GameFlow() {
        this.scoreCounter = new Counter();
        this.g = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        this.keyboardSensor = this.g.getKeyboardSensor();
        this.winner = true;
    }

    /**
     * @param levels a list of all three levels in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.g,
                    this.keyboardSensor, this.scoreCounter);
            level.initialize();
            this.animationRunner = level.getRunner();
            while (level.getRemovedBlocks().getValue() < levelInfo.numberOfBlocksToRemove()
                    && level.getRemovedBalls().getValue() < levelInfo.numberOfBalls()) {
                level.playOneTurn();
            }
            if (level.getRemovedBalls().getValue() == levelInfo.numberOfBalls()) {
                this.winner = false;
                break;
            }
        }
        FinalScreen screen = new FinalScreen(this.scoreCounter, winner,
                this.keyboardSensor);
        KeyPressStoppableAnimation kpsa =
                new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, screen);
        this.animationRunner.run(kpsa);
        this.g.close();
    }
}
