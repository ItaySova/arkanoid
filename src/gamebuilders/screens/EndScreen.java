package gamebuilders.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamebuilders.Animation;

import java.awt.Color;

/**
 * @author Itay Sova
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private boolean win;

    /**
     * This method builds the endscreen by keyboard of the game, the score to flash, and whether its a win or a lost.
     * And initializing the stop to true.
     * @param k the keyboard of the game.
     * @param score the score the player achieved.
     * @param win the win state.(won or didn't).
     */
    public EndScreen(KeyboardSensor k, int score, boolean win) {
        this.keyboard = k;
        this.score = score;
        this.stop = true;
        this.win = win;
    }

    /**
     * This method draws the end screen on the surface.
     * @param d the surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.win) {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.RED);
            d.fillCircle(d.getWidth() / 2 - 200, d.getHeight() / 2 - 50, 20);
            d.fillCircle(d.getWidth() / 2 - 300, d.getHeight() / 2 - 50, 20);
            d.drawCircle(d.getWidth() / 2 - 250, d.getHeight() / 2 + 50, 50);
            d.setColor(Color.BLACK);
            d.fillRectangle(d.getWidth() / 2 - 310, d.getHeight() / 2 + 50, 110, 100);
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GAMEOVER.", 50);
            d.drawText(d.getWidth() / 2 - 30, d.getHeight() / 2 + 50, "Your Score is: " + this.score, 32);


        } else {
            d.setColor(Color.green);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.CYAN);
            d.fillCircle(d.getWidth() / 2 - 200, d.getHeight() / 2 - 50, 20);
            d.fillCircle(d.getWidth() / 2 - 300, d.getHeight() / 2 - 50, 20);
            d.drawCircle(d.getWidth() / 2 - 250, d.getHeight() / 2 + 40, 50);
            d.setColor(Color.GREEN);
            d.fillRectangle(d.getWidth() / 2 - 310, d.getHeight() / 2 - 20, 110, 50);
            d.setColor(Color.YELLOW);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "YOU WIN!", 50);
            d.drawText(d.getWidth() / 2 - 30, d.getHeight() / 2 + 50, "Your Score is: " + this.score, 32);


        }
    }

    /**
     * This method stops the animation of the endscreen.
     * @return a boolean value to whether or not stop the animation.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public String getKey() {
        return "";
    }


}
