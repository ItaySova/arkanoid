package gamebuilders;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collisiondetection.SpriteCollection;
import java.awt.Image;
import java.awt.Color;

/**
 * @author Itay Sova
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private Image background = null;
    private String levelName = "";
    /**
     * This is a constructor which builds the countdownanimation by numOfSeconds of the animation,
     * from what number to count from(countFrom), and the sprite collection.
     * @param numOfSeconds the num of seconds the animation runs by.
     * @param countFrom the number we count from in the animation itself.
     * @param gameScreen the sprites state at the moment.
     * @param background the background image.
     * @param levelName the name of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              Image background, String levelName) {
        this.numOfSeconds = numOfSeconds / countFrom;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
        this.background = background;
        this.levelName = levelName;

    }

    /**
     * This method draws the animation (3,2,1,GO!) on the screen.
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {

        //===18.6.19==========
        if (background != null) {
            d.drawImage(0, 0, background);

          //  this.gui.show(d);
        }
        //========================
        if (this.countFrom != 3) {
            this.sleeper.sleepFor((int) this.numOfSeconds);
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.blue);

        if (this.countFrom == 0) {
            d.drawText(350, 350, "GO!", 100);
        } else if (countFrom > 0) {
            d.drawText(350, 350, Integer.toString(countFrom), 100);
        }
      /*  if (levelName.contains("Thirsty?"))  {
            // if (background.getProperty().)
            d.setColor(Color.BLACK);
            d.drawText(270, 205, "Coca-Cola", 54);
            d.setColor(Color.white);
            d.fillRectangle(240, 212, 315, 5);
            d.fillRectangle(240, 153, 315, 5);
            d.setColor(Color.white);
            d.drawText(335, 287, "CLASSIC", 30);
            d.setColor(Color.BLACK);
        }*/
        this.countFrom--;

        if (countFrom == -2) {
            this.stop = true;
            return;
        }



    }

    /**
     * A boolean method which stops the animation.
     * @return the right boolean value to stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public String getKey() {
        return "";
    }


}