package gamebuilders;

import biuoop.DrawSurface;
/**
 * @author Itay Sova
 */
public interface Animation {
    /**
     * This method draws on the surface for each frame in the animation.
     * @param d the surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method stops the animation.
     * @return a boolean value which stops the animation.
     */
    boolean shouldStop();

    /**
     * This method returns the key pressed.
     * @return the key.
     */
    String getKey();
}