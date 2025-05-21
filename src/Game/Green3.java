//315099184 Oran Shaki
package Game;

import Geometry.Block;
import Geometry.Point;
import Geometry.Rectangle;
import SpriteAndCollide.Background;
import SpriteAndCollide.Sprite;
import SpriteAndCollide.SpriteCollection;
import SpriteAndCollide.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The third level.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 18;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        int angle = -60;
        int speed = 5;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vel.add(Velocity.fromAngleAndSpeed(angle + (20 * i), speed));
        }
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 3;
    }

    @Override
    public int paddleWidth() {
        return 190;
    }

    @Override
    public String levelName() {
        return "Level Name: Green 3";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Block back = new Block(new Rectangle(p, GameLevel.WIDTH,
                GameLevel.HEIGHT),
                new Color(100, 150, 0));
        ArrayList<Sprite> sl = new ArrayList<Sprite>();
        sl.add(back);
        return new Background(new SpriteCollection(sl));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Color c = new Color(i, 30 * i, 20 * i);
                Geometry.Point upperLeftBlock =
                        new Geometry.Point(730 - GameLevel.BLOCK_WIDTH * j,
                        80 + GameLevel.BLOCK_HEIGHT * i);
                Geometry.Rectangle rectangle =
                        new Geometry.Rectangle(upperLeftBlock,
                                GameLevel.BLOCK_WIDTH,
                        GameLevel.BLOCK_HEIGHT);
                Block b = new Block(rectangle, c);
                list.add(b); //add blocks to the game
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
