package sprites;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;

/**
 * @author Itay Sova
 * This interface is for a sprite object.
 */
public interface Sprite {
    /**
     * This method draws a sprite object on the screen.
     * @param d a draw surface to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * This method adds a sprite to the game.
     * @param g the game.
     */
    void addToGame(GameLevel g);
    /**
     * This method notify the sprite object that time has passed.
     */
    void timePassed();

}