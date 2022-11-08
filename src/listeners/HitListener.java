package listeners;

import geometry.Ball;
import sprites.collidables.Block;

/**
 * @author Itay Sova
 * This interface represents the hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block being hit.
     * @param hitter the geometry.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}