package listeners;

import geometry.Ball;
import sprites.collidables.Block;

/**
 * @author Itay Sova
 * This class represents an object that prints information about blocks that were hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this method prints information about blocks that were hit.
     * @param beingHit the block that was hitted.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitCounter() + " points was hit.");
    }
}