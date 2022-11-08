package gamebuilders.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamebuilders.Animation;
import gamebuilders.HighScoresTable;
import gamebuilders.ScoreInfo;
import java.awt.Color;
import java.util.List;

/**
 * @author Itay Sova
 */
public class HighScoresAnimation implements Animation {
    private List<ScoreInfo> highScoresTable;
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * This method builds the highscores animation by the table of the highscores and the keyboard of the game.
     * @param highScoresTable highscores table.
     * @param keyboard keyboard of the game.
     */
    public HighScoresAnimation(HighScoresTable highScoresTable, KeyboardSensor keyboard) {
        this.highScoresTable = highScoresTable.getHighScores();
        this.keyboard = keyboard;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        int i = 0;
        for (ScoreInfo s : this.highScoresTable) {
            d.drawText(200, 200 + i * 50, s.getName(), 50);
            d.drawText(400, 200 + i * 50, " " + s.getScore(), 50);
            i++;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public String getKey() {
        return "";
    }



}