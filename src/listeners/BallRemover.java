package listeners;

import gamebuilders.Counter;
import gamebuilders.GameLevel;
import geometry.Ball;
import sprites.collidables.Block;

/**
 * @author Itay Sova
 * This class represents a function for an object to be able to remove a ball to the game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBallsInGame;
    /**
     * this is a constructor that creates the ball remover from a game and decreases the remained balls.
     * @param game the game currently played which we want to remove balls from.
     * @param remainingBallsInGame the number of balls left in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBallsInGame) {
        this.game = game;
        this.remainingBallsInGame = remainingBallsInGame;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBallsInGame.decrease(1);
    }
}
