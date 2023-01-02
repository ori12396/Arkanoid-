//207632795
package hit;

import ballinfo.Ball;
import collision.Block;

/**
 * Hitlistener interface.
 */
public interface HitListener {
    /**
     * the function that handle the hit event.
     * @param beingHit block that just being hit.
     * @param hitter the ball that made the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
