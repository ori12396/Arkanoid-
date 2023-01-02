//207632795
package hit;

import ballinfo.Ball;
import collision.Block;
import game.Game;

/**
 * @author ori zohar
 * ball remover class.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * this is the constructor.
     *
     * @param game         the game object.
     * @param removedBalls the counter.
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * this method implement the hit event situation.
     *
     * @param beingHit the block that hit.
     * @param hitter   the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
