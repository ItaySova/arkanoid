package gamebuilders.gamelevels;

import sprites.collidables.Block;
import java.awt.Color;
import java.awt.Image;

/**
 *@author Itay Sova
 * This class makes a block.
 */
public class BlockMaker implements BlockCreator {
    private Color fillColor;
    private Color strokeColor;
    private int height, width, hitPoints;
    private Image imageIcon = null;
    private String otherColor = "";
    /**
     * This constructor builds all the info this class needs to make a block.
     * @param height of the block.
     * @param width of the block.
     * @param hitPoints of the block.
     * @param fillColor of the block.
     * @param strokeColor of the block.
     * @param imageIcon on the block.
     * @param otherColor of the block.
     */
    public BlockMaker(int height, int width, int hitPoints, Color fillColor, Color strokeColor, Image imageIcon,
                       String otherColor) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.imageIcon = imageIcon;
        this.otherColor = otherColor;
    }
    @Override
    public Block create(int xpos, int ypos) {
        Block newBlock = new Block(xpos, ypos, this.width, this.height, this.fillColor, this.strokeColor,
                otherColor);
        newBlock.setIconImage(imageIcon);
        newBlock.setHitCounter(this.hitPoints);
        return newBlock;
    }
    @Override
    public int getWidth() {
        return this.width;
    }
    @Override
    public int getheight() {
        return this.height;
    }
    @Override
    public Color getfillColor() {
        return this.fillColor;
    }
    @Override
    public Color getstrokeColor() {
        return this.strokeColor;
    }
    @Override
    public Image getImageIcon() {
        return this.imageIcon;
    }
    @Override
    public int gethitPoints() {
        return this.hitPoints;
    }
}
