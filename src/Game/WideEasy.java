//315099184 Oran Shaki
package Game;

import Geometry.Block;
import Geometry.Point;
import Geometry.Rectangle;
import SpriteAndCollide.Background;
import SpriteAndCollide.Moon;
import SpriteAndCollide.Sprite;
import SpriteAndCollide.SpriteCollection;
import SpriteAndCollide.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The second level.
 */
public class WideEasy implements LevelInformation {
    private static final int ANOTHER_BLOCK = 50;
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        int angle = -10;
        int speed = 5;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(angle + (180 * i), speed));
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Level Name: Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        ArrayList<Sprite> sl = new ArrayList<Sprite>();
        sl.add(new Moon(175, 430));
        return new Background(new SpriteCollection(sl));
    }
    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Point temp = new Point(25 + ANOTHER_BLOCK * i, 150);
            Block b = new Block(new Rectangle(temp, GameLevel.BLOCK_WIDTH,
                    GameLevel.BLOCK_HEIGHT), new Color(65, 65, 95));
            list.add(b);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
