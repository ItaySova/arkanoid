package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.backgrounds.BackgroundLVL4;
import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 *@author Itay Sova 313246209
 * This class holds the level information of level 4.
 */
public class Level4 implements LevelInformation {
    private BackgroundLVL4 background = new BackgroundLVL4();
    private List<Velocity> velocities;
    private List<Block> blocks;
    /**
     * This method builds the level array lists.
     */
    public Level4() {
        this.velocities = new ArrayList<Velocity>();
        this.blocks = new ArrayList<Block>();
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity vBall = null;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            if (i == 0) {
                vBall = Velocity.fromAngleAndSpeed(0, 6);
            } else if (i == 1) {
                vBall = Velocity.fromAngleAndSpeed(10, 6);
            } else if (i == 2) {
                vBall = Velocity.fromAngleAndSpeed(-10, 6);
            }
            velocities.add(vBall);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Paycheck";
    }

    @Override
    public Image getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {

        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 300, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 275, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 250, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 225, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 200, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 175, 46, 25, "Paycheck");
            blocks.add(dollarBlock);
        }
        for (int i = 0; i < 16; i++) {
            Block dollarBlock = new Block(35 + i * 46, 150, 46, 25, "Paycheck");
            dollarBlock.setHitCounter(4);
            blocks.add(dollarBlock);
        }

        for (int i = 0; i < 2; i++) {
            Block credit = new Block(40 + i * 30, 400, 20, 160, Color.lightGray);
            blocks.add(credit);
        }
        Block safeBox = new Block(350, 35, 100, 90, "Safebox");
        blocks.add(safeBox);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 115;
    }
}
