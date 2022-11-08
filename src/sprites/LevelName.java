package sprites;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class is a sprite that holds the level name.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * This method builds the level name sprite by a string.
     * @param levelName the name of the level.
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(510,
                (int) (25 / 2 + 4),  "Level Name: " + this.levelName, 18);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    @Override
    public void timePassed() {

    }

    /**
     * Accessor method which returns the level name.
     * @return the level name as a string.
     */
    public String getLevelName() {
        return levelName;
    }
}
