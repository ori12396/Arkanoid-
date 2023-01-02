//207632795
package hit;

import ballinfo.Ball;
import collision.Block;

/**
 * @author ori .
 * PrintingHitListener class.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this method handle the hit event.
     * @param beingHit block that just being hit.
     * @param hitter the ball that made the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
