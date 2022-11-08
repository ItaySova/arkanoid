package gamebuilders;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import gamebuilders.gamelevels.LevelInformation;
import gamebuilders.screens.EndScreen;
import gamebuilders.screens.HighScoresAnimation;
import sprites.LevelName;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Itay Sova
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int numberOflives;
    private int score;
    private boolean win;
    private HighScoresTable highScoresTable;
    private DialogManager dialogManager;

    /**
     * This method builds the gameflow by the animation to run, the keyboard of the game ,and number of lives.
     * it initializes win to true and score to zero.
     * @param ar the animation to run.
     * @param ks the keyboard of the game.
     * @param numOflives the number of lives of the player through the levels.
     * @param highScoresTable the highscores table.
     * @param dialog the dialog manager.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int numOflives,
                    HighScoresTable highScoresTable, DialogManager dialog) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.numberOflives = numOflives;
        this.win = true;
        this.score = 0;
        this.highScoresTable = highScoresTable;
        this.dialogManager = dialog;
    }

    /**
     * This method run the levels of the game and keeping track of the score and num of lives,
     * throughout the levels it runs.
     * @param levels array of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
               this.numberOflives, this.score, levelInfo.getBackground());

            level.initialize();

            LivesIndicator li = new LivesIndicator(level.getNumberOfLives());
            ScoreIndicator si = new ScoreIndicator(level.getScore());
            LevelName levelName = new LevelName(level.getLevel().levelName());
            level.addSprite(si);
            level.addSprite(li);
            level.addSprite(levelName);


            while (level.getNumberOfLives().getValue() > 0 && level.getRemainingBlocksInGame().getValue() > 0) {
                level.playOneTurn();
            }

            if (level.getNumberOfLives().getValue() == 0) {
                this.win = false;
                this.score = level.getScore().getValue();
                break;
            }
            this.animationRunner.run(level);
            this.score = level.getScore().getValue();
            this.numberOflives = level.getNumberOfLives().getValue();
        }
        EndScreen endScreen = new EndScreen(this.keyboardSensor, score, win);
        Animation endScreenStopper =
                new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY, endScreen);

        if (this.highScoresTable.getRank(this.score) <= this.highScoresTable.size()) {
            String name = dialogManager.showQuestionDialog("Name", "What is your name?", "");
            this.highScoresTable.add(new ScoreInfo(name, this.score));
            try {
                this.highScoresTable.save(new File("highscores"));
            } catch (IOException e) {
                System.out.println("Error opening score file");
            }
        }
        HighScoresAnimation scoresScreen = new HighScoresAnimation(this.highScoresTable, this.keyboardSensor);
        Animation highScoreAnimation =
                new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY, scoresScreen);
        /*for (ScoreInfo s : this.highScoresTable.getHighScores()) {
            System.out.println(s.getName() + " : " + s.getScore());
        }*/
        this.animationRunner.run(endScreenStopper);
        this.animationRunner.run(highScoreAnimation);

    }
}