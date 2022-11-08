package gamebuilders;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.backgrounds.BackgroundLVL1;
import sprites.backgrounds.BackgroundLVL2;



import java.awt.Color;

/**
 * @author Itay Sova
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String levelName;

    /**
     * This method builds the pause screen by a keyboard sensor.
     * And initializing the stop to false.
     * @param k the keyboard of the game.
     * @param levelName the name of the level for the background of the pause.
     */
    public PauseScreen(KeyboardSensor k, String levelName) {
        this.keyboard = k;
        this.stop = false;
        this.levelName = levelName;
    }

    /**
     * This method draws the pause screen.
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 600);
        if (this.levelName.contains("Cherry On Top")) {
            BackgroundLVL1 lvl1Back = new BackgroundLVL1();
            lvl1Back.drawOn(d);
            d.setColor(Color.red);
            d.fillCircle(400, 125, 30);
            d.setColor(Color.red);
        } else if (this.levelName.contains("Burger after dessert")) {
            BackgroundLVL2 lvl2Back = new BackgroundLVL2();
            lvl2Back.drawOn(d);
            d.setColor(Color.BLACK);
        } else if (this.levelName.contains("Thirsty?")) {
            d.setColor(Color.RED);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.drawText(270, 205, "Coca-Cola", 54);
            d.fillRectangle(240, 212, 315, 5);
            d.fillRectangle(240, 153, 315, 5);
            d.setColor(Color.white);
            d.drawText(335, 287, "CLASSIC", 30);
            d.setColor(Color.BLACK);
        } else if (this.levelName.contains("Paycheck")) {
            d.setColor(Color.GREEN);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLACK);
            d.drawText(280, 450, "$", 400);
            d.setColor(Color.RED);

        }
        d.drawText(350, 230, "Paused", 32);
        //d.drawText(245, 300, "_________________", 32);
        d.drawText(220, 400, "press SPACE to continue", 32);

    }

    /**
     * This method stops the animation.
     * @return a boolean value to whether or not stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public String getKey() {
        return "";
    }
}