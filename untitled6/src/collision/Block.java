//207632795
package collision;

import ballinfo.Ball;
import ballinfo.Velocity;
import biuoop.DrawSurface;
import game.Game;
import game.Sprite;
import geomtry.Point;
import geomtry.Rectangle;
import hit.HitListener;
import hit.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ori zohar
 * this is the block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> listeners;

    /**
     * this is the constructor.
     *
     * @param rectangle rectangle.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.listeners = new ArrayList<HitListener>();
    }

    /**
     * this method add listener to the list.
     *
     * @param hl listener to add.
     */
    public void addHitListener(HitListener hl) {
        this.listeners.add(hl);
    }

    /**
     * this method remove listener from the list.
     *
     * @param hl item to delete.
     */
    public void removeHitListener(HitListener hl) {
        this.listeners.remove(hl);
    }

    /**
     * this method notify a hit.
     *
     * @param hitter the ball that make the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> theListeners = new ArrayList<HitListener>(this.listeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : theListeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * this is accessors method.
     *
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this method implement hit situation.
     * @param hitter the ball that just hit.
     * @param collisionPoint  the point that the collision happened.
     * @param currentVelocity current velocity while hit.
     * @return current velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if collision happened from the left\right
        if (this.rectangle.getHeightLeft().onSegment(collisionPoint)) {
            currentVelocity.setDx(-(Math.abs(currentVelocity.getDx())));
        } else if (this.rectangle.getHeightRight().onSegment(collisionPoint)) {
            currentVelocity.setDx(Math.abs(currentVelocity.getDx()));
        }
        //if collision happened from up\down.
        if (this.rectangle.getWidthUp().onSegment(collisionPoint)
                || this.rectangle.getWidthBottom().onSegment(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * this is accessor method to block color.
     *
     * @return return the block colors.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this is accessors method.
     *
     * @param c color for the block
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * drawing method.
     *
     * @param surface surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * time passed method.
     */
    public void timePassed() {

    }

    /**
     * this method add the block to the game.
     *
     * @param g game.
     */
    public void addToGame(Game g) {
        //if game already exist.
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**
     * this method remove the block from the game.
     *
     * @param game game to remove from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}
