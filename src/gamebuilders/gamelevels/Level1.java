package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 *@author Itay Sova
 * This class holds the level information of level 1.
 */
public class Level1 implements LevelInformation {
   // private BackgroundLVL1 background = new BackgroundLVL1();
    private List<Velocity> velocities;
    private List<Block> blocks;

    /**
     * This method builds the level array lists.
     */
    public Level1() {
       this.velocities = new ArrayList<Velocity>();
       this.blocks = new ArrayList<Block>();
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity vBall = Velocity.fromAngleAndSpeed(0, 3);
        this.velocities.add(vBall);
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Cherry On Top";
    }

    @Override

    public Image getBackground() {
        return null;
    }
    @Override
    public List<Block> blocks() {
        Point upperLeft = new Point(375, 100);
        Rectangle blockRec = new Rectangle(upperLeft, 50, 50);
        Block theOnlyBlock = new Block(blockRec, Color.red);
        this.blocks.add(theOnlyBlock);
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
