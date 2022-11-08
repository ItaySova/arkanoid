package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.backgrounds.BackgroundLVL2;
import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 *@author Itay Sova
 * This class holds the level information of level 2.
 */
public class Level2 implements LevelInformation {
    private BackgroundLVL2 background = new BackgroundLVL2();
    private List<Velocity> velocities;
    private List<Block> blocks;
    /**
     * This method builds the level array lists.
     */
    public Level2() {
        this.velocities = new ArrayList<Velocity>();
        this.blocks = new ArrayList<Block>();
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity vBall;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            vBall = Velocity.fromAngleAndSpeed(0, 4);
            velocities.add(vBall);
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 450;
    }

    @Override
    public String levelName() {
        return "Burger after dessert";
    }

    @Override
    public Image getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        for (int i = 0; i < this.numberOfBlocksToRemove() - 1; i++) {
            Block block = new Block(75 + i * 50, 300, 50, 20, "Burger");
            blocks.add(block);
        }
        Block adderBlock = new Block(385, 150, 27, 20, Color.white);
        blocks.add(adderBlock);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}
