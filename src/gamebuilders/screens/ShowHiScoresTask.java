package gamebuilders.screens;

import gamebuilders.Animation;
import gamebuilders.AnimationRunner;
/**
 * @author Itay Sova
 * This method builds the task that shows the highscores table.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * This method builds the task.
     * @param runner animation runner.
     * @param highScoresAnimation highscores animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * This method runs the task.
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}