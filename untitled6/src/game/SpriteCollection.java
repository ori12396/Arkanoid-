//207632795
package game;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author ori zohar
 * Spritecollection class implempents.
 */
public class SpriteCollection {
    //fields.
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * accessors method.
     *
     * @param s sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * method that notified all the Sprites collection that time is passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<>(this.sprites);
        for (Sprite sprite : temp) {
            sprite.timePassed();
        }
    }

    /**
     * method that draw on for all sprites.
     *
     * @param d surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * this method remove sprite item.
     * @param s item to remove.
     */
    public void removeItem(Sprite s) {
        this.sprites.remove(s);
    }
}