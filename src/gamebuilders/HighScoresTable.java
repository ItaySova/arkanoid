package gamebuilders;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.List;
/**
 * @author Itay Sova
 */
public class HighScoresTable implements Serializable {
    private int tableSize;
    private List<ScoreInfo> highScoresTable;
    //
    // The size means that the table holds up to size top scores.

    /**
     * This constructor creates an empty high-scores table with the specified size.
     * @param size the size table holds up to.
     */
    public HighScoresTable(int size) {
        this.tableSize = size;
        highScoresTable = new ArrayList<ScoreInfo>(this.tableSize);
    }
    /**
     * This constructor builds the table of highscores (Default size of 5).
     */
    public HighScoresTable() {
        this.tableSize = 5;
        this.highScoresTable = new ArrayList<ScoreInfo>(tableSize);
    }
    /**
     * This method adds a new highscore to the table.
     * @param score the score to add.
     */
    public void add(ScoreInfo score) {
        int index = this.getRank(score.getScore());
        if (index > this.tableSize) {
            return;
        }
        this.highScoresTable.add(index - 1, score);
        if (this.highScoresTable.size() == (this.tableSize + 1)) {
            this.highScoresTable.remove(this.tableSize - 1);
        }
    }

    /**
     * Returns the size of the table.
     * @return the size of the table.
     */
    public int size() {
        return this.tableSize;
    }



    /**
     * This method returns the current high scores.
     * @return the table of highscores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }
    /**
     * This method returns the rank of the current score.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     * @param score the score we want to know the rank of.
     * @return the rank of the score.
     **/
    public int getRank(int score) {
        if (this.highScoresTable.isEmpty()) {
            return 1;
        }
        int i = 1;
        for (ScoreInfo s : this.highScoresTable) {
            if (score > s.getScore()) {
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * Clears the highscores table.
     */
    public void clear() {
        this.highScoresTable.clear();
    }
    /**
     * This method loads table data from file.
     * @param filename the file with the data of the table.
     * @throws IOException IOException.
     * @throws ClassNotFoundException ClassNotFoundException.
     */
    public void load(File filename) throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(filename));
        HighScoresTable tempTable = (HighScoresTable) oi.readObject();
        this.highScoresTable = tempTable.highScoresTable;
        this.tableSize = tempTable.size();

        oi.close();

    }
    /**
     * This method saves table data to a specified file.
     * @param filename the file we want to save the data on.
     * @throws IOException IOException.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename.getName()));
        os.writeObject(this);
        os.close();
    }
    /**
     * This method read a table from file and return it.
     * If the file does not exist, or there is a problem with reading it, an empty table is returned.
     * @param filename the file we read from.
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream oi = null;
        HighScoresTable temp = new HighScoresTable();
        try {
            oi = new ObjectInputStream(new FileInputStream(filename));
            temp = (HighScoresTable) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading or opening the file");
            e.printStackTrace();
            return temp;
        }
        return temp;
    }
}