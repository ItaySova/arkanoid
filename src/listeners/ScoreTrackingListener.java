package listeners;

import geometry.Ball;
import sprites.collidables.Block;
import gamebuilders.Counter;
/**
 * @author Itay Sova
 * This class represents a score tracking listener object.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * A constructor that builds a score tracking listener from a score counter object.
     * @param scoreCounter the given score counter object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * this method updates the game score when a block is being hit.
     * if the block that is being hit is removed,
     * score is increased by 10 points, 5 otherwise.
     * @param beingHit the block that is being hit.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitCounter() - 1 > 0) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHitCounter() - 1 == 0) {
            this.currentScore.increase(10);
        }
    }

    /**
     * Accessor method which returns the current score.
     * @return the current score value.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}