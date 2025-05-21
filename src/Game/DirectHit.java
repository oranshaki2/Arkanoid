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
 * The first level, in which the ball will hit directly in the block.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        vel.add(Velocity.fromAngleAndSpeed(0, 5));
        return vel;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 220;
    }

    @Override
    public String levelName() {
        return "Level Name: Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Point p = new Point(0, 0);
        Rectangle t = new Geometry.Rectangle(p, GameLevel.WIDTH, GameLevel.HEIGHT);
        Block top = new Block(t, new Color(0, 181, 226));
        Point p2 = new Point(0, 400);
        Rectangle b = new Rectangle(p2, GameLevel.WIDTH, GameLevel.HEIGHT);
        Block bot = new Block(b, new Color(0, 154, 23));
        Point l = new Point(450, 100);
        Rectangle left = new Rectangle(l, 4, 320);
        Block leftPole = new Block(left, Color.white);
        Point r = new Point(550, 100);
        Rectangle right = new Rectangle(r, 4, 320);
        Block rightPole = new Block(right, Color.white);
        Point c = new Point(l.getX(), 340);
        Rectangle cross = new Rectangle(c, r.getX() - l.getX(), 4);
        Block crossBar = new Block(cross, Color.white);
        Point c2 = new Point(l.getX(), 300);
        Rectangle cross2 = new Rectangle(c2, r.getX() - l.getX(), 4);
        Block crossBar2 = new Block(cross2, Color.white);
        Point c3 = new Point(l.getX(), 260);
        Rectangle cross3 = new Rectangle(c3, r.getX() - l.getX(), 4);
        Block crossBar3 = new Block(cross3, Color.white);
        Point c4 = new Point(l.getX(), 220);
        Rectangle cross4 = new Rectangle(c4, r.getX() - l.getX(), 4);
        Block crossBar4 = new Block(cross4, Color.white);
        Point c5 = new Point(l.getX(), 180);
        Rectangle cross5 = new Rectangle(c5, r.getX() - l.getX(), 4);
        Block crossBar5 = new Block(cross5, Color.white);
        ArrayList<Sprite> sl = new ArrayList<Sprite>();
        sl.add(top);
        sl.add(bot);
        sl.add(leftPole);
        sl.add(rightPole);
        sl.add(crossBar);
        sl.add(crossBar2);
        sl.add(crossBar3);
        sl.add(crossBar4);
        sl.add(crossBar5);
        return new Background(new SpriteCollection(sl));
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> list = new ArrayList<Block>();
        Point temp = new Point(330, 150);
        Block b = new Block(new Rectangle(temp, GameLevel.BLOCK_WIDTH,
                GameLevel.BLOCK_HEIGHT), Color.yellow);
        list.add(b);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
