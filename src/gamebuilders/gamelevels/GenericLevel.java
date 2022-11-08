package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.collidables.Block;

import java.awt.Image;
import java.util.List;
/**
 *@author Itay Sova
 * This class creates a level.
 */
public class GenericLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Image background;
    private int numberOfBlocksToRemove;
    private List<Block> blocks;

    /**
     * This method builds the level according to the parameters needs for a level.
     * @param levelName name of the level.
     * @param initialBallVelocities the velocities array of each ball.
     * @param background the image background.
     * @param paddleSpeed the speed of the paddle.
     * @param paddleWidth the width of the paddle.
     * @param numberOfBlocksToRemove the number of blocks to remove from the level.
     * @param blocks the blocks array.
     */
    public GenericLevel(String levelName, List<Velocity> initialBallVelocities, Image background,
                        int paddleSpeed, int paddleWidth, int numberOfBlocksToRemove,
                        List<Block> blocks) {
        this.numberOfBalls = initialBallVelocities.size();
        this.initialBallVelocities = initialBallVelocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
        this.blocks = blocks;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Image getBackground() {
        return this.background;
    }
    @Override
    public List<Block> blocks() {
        return blocks;  //null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
