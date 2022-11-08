package listeners;

import gamebuilders.Counter;
import gamebuilders.GameLevel;
import geometry.Ball;
import sprites.collidables.Block;

/**
 * @author Itay Sova
 * This class is responsible to remove blocks that were hitted from a game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocksInGame;
    /**
     * A constructor that creates the block remover from a game and the counter of the remaining blocks in the game.
     * @param game a game currently played to remove blocks from.
     * @param remainingBlocksInGame the number of blocks left in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocksInGame) {
        this.game = game;
        this.remainingBlocksInGame = remainingBlocksInGame;
    }

    /**
     * This method removes from the game blocks that reach 0 hit points and also decreasing the counter of
     * remaining blocks by 1.
     * @param beingHit the block that was hit.
     * @param hitter the ball that hitted the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitCounter() == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            this.remainingBlocksInGame.decrease(1);
        }
    }

}