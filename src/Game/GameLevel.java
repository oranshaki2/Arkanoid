//315099184 Oran Shaki
package Game;
import Geometry.Block;

import Geometry.Point;
import Geometry.Rectangle;

import SpriteAndCollide.Ball;
import SpriteAndCollide.BallRemover;
import SpriteAndCollide.BlockRemover;
import SpriteAndCollide.Collidable;
import SpriteAndCollide.Counter;
import SpriteAndCollide.HitListener;
import SpriteAndCollide.Paddle;
import SpriteAndCollide.ScoreIndicator;
import SpriteAndCollide.ScoreTrackingListener;
import SpriteAndCollide.Sprite;
import SpriteAndCollide.SpriteCollection;
import SpriteAndCollide.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.List;

/**
 * Represents the game with all the different objects and the animation.
 */
public class GameLevel implements Animation {
    private final biuoop.KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private boolean running;
    public static final int WIDTH = 800; //The screen width
    public static final int HEIGHT = 600; //The screen height
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BALL_RADIUS = 10;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int POINTS_FOR_WINNING = 100;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter removedBlocks = new Counter();
    private final Counter removedBalls = new Counter();
    private Block invisibleBlock;
    private final LevelInformation level;
    private final Counter scoreCounter;
    private final ScoreTrackingListener stl;

    /**
     * Creates a new game object.
     * @param info the given level information object.
     * @param gui the given GUI.
     * @param ks Keyboard Sensor.
     * @param score the number of scores.
     */
    public GameLevel(LevelInformation info, GUI gui,
                     KeyboardSensor ks, Counter score) {
        this.scoreCounter = score;
        this.stl = new ScoreTrackingListener(this.scoreCounter);
        this.level = info;
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.keyboard = ks;
        this.runner = new AnimationRunner(FRAMES_PER_SECOND, gui);
    }

    /**
     * Add a new object to the game environment.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a new object to the sprites' collection.
     *
     * @param s a sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates 4 frames in order to prevent the ball from disappear.
     */
    public void createFrames() {
        Geometry.Point p1 = new Geometry.Point(0.0, 20.0);
        Geometry.Point p2 = new Point(20.0, 20.0);
        Geometry.Point p3 = new Geometry.Point(780.0, 20.0);
        new Block(new Rectangle(p1, 20.0, 600.0),
                Color.GRAY).addToGame(this); //left frame
        new Block(new Geometry.Rectangle(p2, 760.0, 20.0),
                Color.GRAY).addToGame(this); //upper block
        new Block(new Geometry.Rectangle(p3, 20.0, 600.0),
                Color.GRAY).addToGame(this); //right block
    }

    /**
     * Creates the last frame.
     * @param hl the hit listener object
     * @return the invisible block
     */
    public Block invisibleFrame(HitListener hl) {
        Geometry.Point point = new Geometry.Point(20.0, 600.0);
        Block b = new Block(new Geometry.Rectangle(point, 760.0, 20.0),
                new Color(52, 204, 255));
        b.addToGame(this);
        b.addHitListener(hl);
        return b;
    }

    /**
     * Creates balls according to how many balls requested, then add them to
     * the game.
     */
    public void createBalls() {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(360, 560, BALL_RADIUS, Color.WHITE);
            Velocity v = this.level.initialBallVelocities().get(i);
            ball.setVelocity(v);
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * Initialize a new game: create the Blocks and SpriteAndCollide.Ball (and SpriteAndCollide.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        if (this.level.getBackground() != null) {
            this.level.getBackground().addToGame(this);
        }
        BlockRemover blockRemover = new BlockRemover(this, this.removedBlocks);
        List<Block> blocks = this.level.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.stl);
        }
        BallRemover bhl = new BallRemover(this, this.removedBalls);
        createFrames();
        this.invisibleBlock = invisibleFrame(bhl);
        createBalls();
        ScoreIndicator indicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(indicator);
    }


    /**
     * @param c the given object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.deleteCollidable(c);
    }

    /**
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.deleteSprite(s);
    }

    /**
     * @return the counter object of blocks.
     */
    public Counter getRemovedBlocks() {
        return this.removedBlocks;
    }

    /**
     * @return the counter object of balls.
     */
    public Counter getRemovedBalls() {
        return this.removedBalls;
    }
    /**
     * set a new counter as the blocks counter.
     * @param num the new counter.
     */
    public void setBlockCounter(int num) {
        this.removedBlocks.setCounter(num);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(500, 15, this.level.levelName(), 15);
        this.sprites.drawAllOn(d); //operate the function for every object
        this.invisibleBlock.drawInvisible(d);
        this.sprites.notifyAllTimePassed();
        //No blocks left in the game
        if (removedBlocks.getValue() == this.level.numberOfBlocksToRemove()) {
            this.scoreCounter.increase(POINTS_FOR_WINNING);
            this.running = false;
        }
        //No balls left in the game
        if (removedBalls.getValue() == this.level.numberOfBalls()) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")
        || this.keyboard.isPressed("פ")) {
            KeyPressStoppableAnimation kpsa =
                    new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard));
            this.runner.run(kpsa);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Keep playing as long as there are balls and blocks left.
     */
    public void playOneTurn() {
        Rectangle paddleShape = new Rectangle(new Geometry.Point(300.0, 570.0),
                this.level.paddleWidth(), Paddle.PADDLE_HEIGHT);
        Paddle paddle = new Paddle(this.keyboard, paddleShape,
                this.level.paddleSpeed());
        paddle.addToGame(this);
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // use our runner to run the current animation, which is one turn of
        // the game.
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * getter function.
     * @return Animation Runner
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }
}
