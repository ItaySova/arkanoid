package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.backgrounds.BackgroundLVL3;
import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 *@author Itay Sova
 * This class holds the level information of level 3.
 */
public class Level3 implements LevelInformation {
    private BackgroundLVL3 background = new BackgroundLVL3();
    private List<Velocity> velocities;
    private List<Block> blocks;
    /**
     * This method builds the level array lists.
     */
    public Level3() {
        this.velocities = new ArrayList<Velocity>();
        this.blocks = new ArrayList<Block>();
    }
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity vBall = null;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            if (i == 0) {
                vBall = Velocity.fromAngleAndSpeed(45, 5);
            } else if (i == 1) {
                vBall = Velocity.fromAngleAndSpeed(-45, 5);
            }
            velocities.add(vBall);
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Thirsty?";
    }

    @Override
    public Image getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        for (int i = 0; i < 5; i++) {
            Block block = new Block(240 + i * 63, 410,  63, 20, Color.lightGray);
            blocks.add(block);
        }
        for (int i = 0; i < 5; i++) {
            Block block = new Block(240 + i * 63, 390, 63, 20, Color.lightGray);
            blocks.add(block);
        }
        for (int i = 0; i < 5; i++) {
            Block block = new Block(240 + i * 63, 370, 63, 20, Color.lightGray);
            blocks.add(block);
        }
        for (int i = 0; i < 5; i++) {
            Block block = new Block(555, 36 + i * 67, 20, 67, Color.red);
            block.setHitCounter(2);
            blocks.add(block);
        }
        for (int i = 0; i < 5; i++) {
            Block block = new Block(220, 36 + i * 67, 20, 67, Color.red);
            block.setHitCounter(2);
            blocks.add(block);
        }

        for (int i = 0; i < 2; i++) {
            Block block = new Block(40 + i * 30, 400, 20, 160, Color.white);
            blocks.add(block);
        }
        Block cupBlock = new Block(240, 36, 315, 334, "Cola");
        cupBlock.getRec().setAmIBorder(true);
        cupBlock.setHitCounter(0);
        blocks.add(cupBlock);
        return blocks;
        /*Block cupBlockLeft = new Block(240, 30, 30, 340, Color.red);
        cupBlockLeft.getRec().setAmIBorder(true);
        cupBlockLeft.setHitCounter(0);
        Block cupBlockRight = new Block(525, 30, 30, 340, Color.red);
        cupBlockRight.getRec().setAmIBorder(true);
        cupBlockRight.setHitCounter(0);
        Block cupBlockTop = new Block(260, 340, 265, 30, Color.red);
        cupBlockTop.getRec().setAmIBorder(true);
        cupBlockTop.setHitCounter(0);
        blocks.add(cupBlockTop);
        blocks.add(cupBlockLeft);
        blocks.add(cupBlockRight);*/
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 27;
    }
}
