package gamebuilders;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * @author Itay Sova
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * This method constructs the animation runner from gui and frames per second.
     * @param gui the gui which the animation runs on.
     * @param framesPerSecond the frames per second the animation should run by.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * This method runs the animation.
     * @param animation the animation we want to run.
     */
    public void run(Animation animation) {   //},Image background) {

        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * This is an accessor method which returns the gui of the animation.
     * @return the gui of the animation.
     */
    public GUI getGui() {
        return gui;
    }

}