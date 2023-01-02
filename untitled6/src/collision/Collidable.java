//207632795
package collision;
import ballinfo.Ball;
import ballinfo.Velocity;
import geomtry.Point;
import geomtry.Rectangle;

/**
 * collidable interface.
 */
public interface Collidable {
    /**
     * this method will get the rectangle collision.
     *
     * @return rectangle collision
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * this methid will resembled the hit.
     *
     * @param collisionPoint  the point that the collision happened.
     * @param currentVelocity current velocity while hit.
     * @param hitter ball hitter.
     * @return new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
