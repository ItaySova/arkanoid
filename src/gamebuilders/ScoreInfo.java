package gamebuilders;

import java.io.Serializable;
/**
 * @author Itay Sova
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
    /**
     * This constructor builds the score info.
     * @param name the name of the player.
     * @param score the score the player got.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * Returns the name of the player with this score.
     * @return the name of the player with this score.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the score of the player.
     * @return the score of the player.
     */
    public int getScore() {
        return this.score;
    }
}