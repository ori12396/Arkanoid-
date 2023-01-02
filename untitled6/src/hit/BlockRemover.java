//207632795
package hit;

import ballinfo.Ball;
import collision.Block;
import game.Game;

/**
 * @author ori zohar
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * this is the constructor.
     *
     * @param game          the game object.
     * @param removedBlocks the counter.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * this method implement the hit event.
     * @param beingHit the block that being hit
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
