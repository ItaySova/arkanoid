package gamebuilders.gamelevels;
import gamebuilders.Velocity;
import sprites.collidables.Block;
import java.awt.Image;
import java.util.List;
/**
 *@author Itay Sova
 * This interface represents the information of the level.
 */
public interface LevelInformation {
    /**
     * This method returns the number of balls in the level.
     * @return number of balls in the level.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * This method returns an arraylist of velocities of the balls in the level.
     * @return arraylist of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns the speed of the paddle in the level.
     * @return the speed of the paddle in the level.
     */
    int paddleSpeed();
    /**
     * This method returns the width of the paddle in the level.
     * @return the width of the paddle in the level.
     */
    int paddleWidth();
    /**
     * This method returns the level name.
     * @return the level name.
     */
    String levelName();
    /**
     * This method returns the background sprite of the level.
     * @return background sprite of the level.
     */
    Image getBackground();
    /**
     * This method returns an arraylist of blocks that make up this level.
     * @return arraylist of blocks that make up this level.
     */
    List<Block> blocks();

    /**
     * This method returns the number of blocks that should be removed before the level is considered "cleared".
     * @return number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}