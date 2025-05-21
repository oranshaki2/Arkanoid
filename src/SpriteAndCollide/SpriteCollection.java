//315099184 Oran Shaki
package SpriteAndCollide;
import biuoop.DrawSurface;

import java.util.ArrayList;
/**
 * Represents the collection of all sprites.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * Creates new sprite's collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * Constructor. Makes an object with Array list.
     * @param l a list of sprites.
     */
    public SpriteCollection(ArrayList<Sprite> l) {
        this.sprites = l;
    }
    /**
     * Add the object to the sprites list.
     *
     * @param  s the sprite we add to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * @param s the given object we delete
     */
    public void deleteSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * @return the sprites list
     */
    public java.util.ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesList = new ArrayList<>(this.sprites);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
