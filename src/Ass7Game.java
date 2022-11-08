import biuoop.DialogManager;
import biuoop.GUI;

import gamebuilders.AnimationRunner;
import gamebuilders.gamelevels.LevelInformation;
import gamebuilders.screens.Menu;
import gamebuilders.screens.Task;
import gamebuilders.HighScoresTable;
import gamebuilders.gamelevels.Level1;
import gamebuilders.gamelevels.Level2;
import gamebuilders.gamelevels.Level3;
import gamebuilders.gamelevels.Level4;
import gamebuilders.GameFlow;
import gamebuilders.Animation;
import gamebuilders.KeyPressStoppableAnimation;
import gamebuilders.screens.MenuAnimation;
import gamebuilders.screens.HighScoresAnimation;
import gamebuilders.gamelevels.LevelSpecificationReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Itay Sova
 * This class calls methods of game class to create and run the game.
 */
public class Ass7Game {
    /*private Menu<Task<Void>>  menuCurrentRun = null;
    private List<LevelInformation> levels2 = new ArrayList<LevelInformation>();
    private String keySaver = "";
    */
    private static Menu<Task<Void>>  menueCurrentRun = null;
    private static List<LevelInformation> levels2 = new ArrayList<LevelInformation>();
    private static String keySaver = "";

    /**
     * This is main method which calls the methods of game class.
     * @param args the levels to play.
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {
        GUI gameGui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gameGui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gameGui, 60);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        String cwd = System.getProperty("user.dir");
        System.out.println("current directory : " + cwd);
        File lvlSets = new File("resources//" + "level_sets.txt");
        InputStreamReader lvlSetsreader = new InputStreamReader(new FileInputStream(lvlSets));
        BufferedReader bufferReader = new BufferedReader(lvlSetsreader);
        List<String> menueSymbols = new ArrayList<String>();
        List<String> menueFiles = new ArrayList<String>();
        List<String> menuePaths = new ArrayList<String>();
        String line = "";
        while ((line = bufferReader.readLine()) != null) {
            if (line.indexOf(':') >= 0) {
                menueSymbols.add(line.substring(0, 1));
                menueFiles.add(line.trim().substring(line.indexOf(':') + 1, line.trim().length()));
            } else {
                menuePaths.add(line.trim());
            }
        }
        File file = new File("highscores");
        DialogManager dialog = gameGui.getDialogManager();
        HighScoresTable highScoresTable = null;
        if (file.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(file);
        } else {
            try {
                highScoresTable = new HighScoresTable(5);
                highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println("failed saving the new table");
            }
        }
        for (int i = 0; i < args.length; i++) {
            try {
                switch (Integer.parseInt(args[i])) {
                    case 1:
                        levels.add(new Level1());
                        break;
                    case 2:
                        levels.add(new Level2());
                        break;
                    case 3:
                        levels.add(new Level3());
                        break;
                    case 4:
                        levels.add(new Level4());
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard, 7, highScoresTable, dialog);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, animationRunner);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(keyboard, animationRunner);
        Task<Void> gameTask = new Task<Void>() {
            @Override
            public Void run() {
                File lvlDefinition = null;
                keySaver = menueCurrentRun.getKey();
                for (int i = 0; i < menueSymbols.size(); i++) {
                       if (keySaver == menueSymbols.get(i)) {
                           lvlDefinition = new File(menuePaths.get(i).trim() + "//" + menueFiles.get(i).trim());
                           break;
                       }
                 }
                 if (lvlDefinition == null) {
                     lvlDefinition = new File("resources//" + "level_definition.txt");
                 }
                try {
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(lvlDefinition));
                    LevelSpecificationReader read = new LevelSpecificationReader();
                    levels2 = read.fromReader(reader);
                    if (levels.isEmpty()) {
                        if (levels2.size() > 0) {
                            LevelInformation level1 = levels2.get(0);
                            levels.add(level1);
                        }
                        if (levels2.size() > 1) {
                            LevelInformation level2 = levels2.get(1);
                            levels.add(level2);
                        }
                        if (levels2.size() > 2) {
                            LevelInformation level3 = levels2.get(2);
                            levels.add(level3);
                        }
                        if (levels2.size() > 3) {
                            LevelInformation level4 = levels2.get(3);  // new Level4();
                            levels.add(level4);
                        }
                    }
                    gameFlow.runLevels(levels);
                } catch (IOException e) {
                    System.out.println("failed to open levels definitions file.");
                }
                return null;
            }
        };
      Task<Void> gameSelect = new Task<Void>() {
            @Override
            public Void run() {
                String subMenueMessage = menueCurrentRun.getsubMenuMess1();
                menueCurrentRun = subMenu;
                menueCurrentRun.setsubMenuMess1(subMenueMessage);
                return null;
            }
        };
        menu.addSelection("s", "startGame", gameSelect);
         String[] messages = {"HardGame", "EasyGame"};
        for (int mi = 0; mi < menueSymbols.size(); mi++) {
            subMenu.addSelection(menueSymbols.get(mi), messages[mi], gameTask);
        }
        menu.addSubMenu("s", "Game Options", subMenu);
        HighScoresAnimation scoresScreen = new HighScoresAnimation(highScoresTable, keyboard);
        Animation highScoreAnimation =
                new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, scoresScreen);

        Task<Void> hiTask = new Task<Void>() {
            @Override
            public Void run() {
                ((KeyPressStoppableAnimation) highScoreAnimation).setStop(false);
                animationRunner.run(highScoreAnimation);
                return null;
            }
        };
        menu.addSelection("h", "hiscore", hiTask);
        Task<Void> quitTask = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };
        menu.addSelection("q", "Quit", quitTask);
        subMenu.addSelection("q", "Quit", quitTask);  // subMenuQuit);
        menueCurrentRun = menu;
        while (true) {
           animationRunner.run(menueCurrentRun);
           Task<Void> task;
           task = menueCurrentRun.getStatus();
           task.run();
        }
    }
}
