package gamebuilders.gamelevels;

import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;

/**
 *@author Itay Sova
 * This class is a block creator.
 */
public interface BlockCreator {
    /**
     * Creates a block at the specified location.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return the block created.
     */
    Block create(int xpos, int ypos);
    /**
     * Returns the width of the block.
     * @return the width of the block.
     */
     int getWidth();

    /**
     * Returns the height of the block.
     * @return the height of the block.
     */
    int getheight();

    /**
     * Returns the fill of the block.
     * @return the fill of the block.
     */
    Color getfillColor();

    /**
     * returns the storke color of the block.
     * @return the storke color of the block.
     */
    Color getstrokeColor();
    /**
     * returns the image icon.
     * @return the image icon.
     */
    Image getImageIcon();
    /**
     * returns the number of points to hit.
     * @return the number of points to hit.
     */
    int gethitPoints();
}