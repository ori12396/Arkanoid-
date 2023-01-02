//207632795
package hit;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;
import geomtry.Rectangle;

/**
 * @author ori zohar
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    static final int FONT_SIZE = 20;

    private Counter score;
    private Rectangle rectangle;

    /**
     * this is the constructor.
     * @param rectangle rectangle to putt
     * @param counter counter to putt.
     */
    public ScoreIndicator(Rectangle rectangle, Counter counter) {
        this.rectangle = rectangle;
        this.score = counter;
    }

    /**
     * this method draw the surface.
     *
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawText((int) this.rectangle.getBottomLeft().getX(), (int) this.rectangle.getBottomLeft().getY(),
                "Score:" + this.score.getValue(), FONT_SIZE);
    }

    /**
     * timepassed method.
     */
    public void timePassed() {

    }
}
