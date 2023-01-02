//207632795
package game;
import biuoop.DrawSurface;

/**
 * sprite interface.
 */
public interface Sprite {
    /**
     * this method will draw the sprite on the screen.
     *
     * @param d surfacce to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * this method notify all the spirt that times is passed.
     */
    void timePassed();
}
