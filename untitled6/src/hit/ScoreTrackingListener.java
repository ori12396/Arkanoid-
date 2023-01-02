//207632795
package hit;

import ballinfo.Ball;
import collision.Block;

/**
 * @author ori zohar
 * score class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this is the constructor.
     *
     * @param scoreCounter score to enter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * accessors method.
     *
     * @return the score.
     */
    public Counter getScore() {
        return this.currentScore;
    }

    /**
     * this method handle hit event situtation in context of score.
     *
     * @param beingHit the block that being hit.
     * @param hitter the ball that made the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
