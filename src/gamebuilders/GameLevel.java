package gamebuilders;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisiondetection.GameEnvironment;
import collisiondetection.SpriteCollection;
import gamebuilders.gamelevels.LevelInformation;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallAdder;
import listeners.BlockRemover;
import listeners.BallRemover;
import listeners.ScoreTrackingListener;
import listeners.PrintingHitListener;
import listeners.HitListener;
import sprites.LevelName;
import sprites.collidables.Block;
import sprites.collidables.Collidable;
import sprites.Sprite;
import sprites.collidables.Paddle;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Sova
 */
public class GameLevel implements Animation {
    private double scrH = 600;
    private double scrW = 800;
   //private GUI gameGui;
    private biuoop.KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private double borderBlockWidth = 35;
    private Counter remainingBlocksInGame;
    private Counter remainingBallsInGame;
    private Counter score;
    private Counter numberOfLives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private BallAdder ballAdder;
    //private LivesIndicator lifeOfTheGame;
    private ScoreTrackingListener scoreTracker;
   // private ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    private HitListener hl = new PrintingHitListener();
    private List<Block> blocksOfGame = new ArrayList<>();
    //private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private LevelName levelName;
    private Image background;



   /* public GameLevel(LevelInformation level, GUI gameGui) {
        this.level = level;
        this.gameGui = gameGui;
        this.runner = new AnimationRunner(this.gameGui, 60);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocksInGame = new Counter(this.level.numberOfBlocksToRemove());
        this.remainingBallsInGame = new Counter(this.level.numberOfBalls());
        this.sprites.addSprite(this.level.getBackground());
       // this.keyboard = gameGui.getKeyboardSensor();
        this.levelName = new LevelName(this.level.levelName());
        this.running = true;
    }*/

    /**
     * This method builds a the level by its info, keyboard to play with, animation to run, number of lives and score.
     * @param level the level info to create.
     * @param kr the keyboard of the game.
     * @param ar the animation to run.
     * @param numberOfLives number of lives to start with.
     * @param score the score to start with.
     * @param background the background image.
     */
    public GameLevel(LevelInformation level, KeyboardSensor kr, AnimationRunner ar, int numberOfLives, int score,
                     Image background) {
        this.level = level;
        this.runner = ar;
        this.keyboard = kr;
        this.numberOfLives = new Counter(numberOfLives);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocksInGame = new Counter(this.level.numberOfBlocksToRemove());
        this.remainingBallsInGame = new Counter(this.level.numberOfBalls());
       // this.sprites.addSprite(this.level.getBackground());
        this.background = background;
        this.levelName = new LevelName(this.level.levelName());
        this.score = new Counter(score);
        this.running = true;
    }


    /**
     * This method add a collidable object to the collidables list in the environment of the game.
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * This method add a sprite object to the sprites list in the environment of the game.
     * @param s the sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * This method initializes a new game. it creates the Blocks, ball and sprites.collidables.Paddle,
     * and adds them to the game.
     */
    public void initialize() {
       Line trajectory = new Line(220, scrH - 200, 220, scrH - 200); //The ball's trajectory.600

        //Creating and adding the blocks to Collidables.//
        for (Block b : this.level.blocks()) {
            if (!b.getRec().isAmIBorder()) {
                blocksOfGame.add(b);
            }
            b.addToGame(this);
        }


        /////////Creating and Adding the paddle//////
        Point upperLeft = new Point(350, scrH - borderBlockWidth - 15);
        Rectangle rec = new Rectangle(upperLeft, this.level.paddleWidth(), 15);
        rec.setColorOfRectangle(Color.BLUE);
        rec.setColorOfFrame(Color.BLACK);
        rec.setAmIPaddle(true);
        Block padBlock = new Block(rec, Color.BLUE);
        Paddle thePaddle = new Paddle(padBlock, this.runner.getGui(), keyboard, this.level.paddleSpeed());
        thePaddle.addToGame(this);
        ////////////////////////////////////////////////

        /////Creating and adding the border blocks/////
        upperLeft = new Point(0, 0);
        rec = new Rectangle(upperLeft, scrW, borderBlockWidth);
        //rec.setColorOfRectangle(Color.CYAN);
        rec.setColorOfFrame(Color.BLACK);
        Block upBlock = new Block(rec, Color.BLACK);
        rec.setAmIBorder(true);
        upBlock.setHitCounter(0);
        upBlock.addToGame(this);
        //
        upperLeft = new Point(scrW - borderBlockWidth, 0);
        rec = new Rectangle(upperLeft, borderBlockWidth, scrH);
        //rec.setColorOfRectangle(Color.CYAN);
        rec.setColorOfFrame(Color.BLACK);
        Block rightBlock = new Block(rec, Color.BLACK);
        rec.setAmIBorder(true);
        rightBlock.setHitCounter(0);
        rightBlock.addToGame(this);

        //
        upperLeft = new Point(0, 0);
        rec = new Rectangle(upperLeft, borderBlockWidth, scrH);
        //rec.setColorOfRectangle(Color.CYAN);
        rec.setColorOfFrame(Color.BLACK);
        Block leftBlock = new Block(rec, Color.BLACK);
        rec.setAmIBorder(true);
        leftBlock.setHitCounter(0);
        leftBlock.addToGame(this);
        //


        //DEATH BLOCK
        upperLeft = new Point(0, scrH - borderBlockWidth + 20);
        rec = new Rectangle(upperLeft, scrW, borderBlockWidth + 20);
        rec.setColorOfRectangle(Color.red);
        rec.setColorOfFrame(Color.BLACK);
        Block downBlock = new Block(rec, Color.red);
        rec.setAmIBorder(true);
        downBlock.setHitCounter(0);
        ballRemover = new BallRemover(this, remainingBallsInGame);
        downBlock.addHitListener(ballRemover);
        downBlock.addToGame(this);


        ///Adding the listeners.BlockRemover to all collidable blocks///
        blockRemover = new BlockRemover(this, remainingBlocksInGame);
        ballAdder = new BallAdder(this, remainingBallsInGame);
        scoreTracker = new ScoreTrackingListener(score);
        for (int i = 0; i < blocksOfGame.size(); i++) {
            if (blocksOfGame.get(i).getRec().getUpperLeft().getY() == 150
                    && level.levelName().contains("Burger after dessert")) {
                blocksOfGame.get(i).addHitListener(ballAdder);
            } else if (blocksOfGame.get(i).getRec().getHeight() == 160
                    && level.levelName().contains("Thirsty?")) {
                //if (blocksOfGame.get(i).getRec().getUpperLeft().getY() ==
                // 400 && level.levelName().contains("Thirsty?")) {
                blocksOfGame.get(i).addHitListener(ballAdder);

            } else if (blocksOfGame.get(i).getRec().getUpperLeft().getY() == 400
                    && level.levelName().contains("Paycheck")) {
                blocksOfGame.get(i).addHitListener(ballAdder);
            } else if (blocksOfGame.get(i).getOtherColor() == "Safebox" && level.levelName().contains("Paycheck")) {
                blocksOfGame.get(i).addHitListener(ballAdder);
            }
            blocksOfGame.get(i).addHitListener(hl);
            blocksOfGame.get(i).addHitListener(scoreTracker);
            blocksOfGame.get(i).addHitListener(blockRemover);
        }
    }

    /**
     * This is a private method that repositions the paddle and balls to the center of the game when needed.
     */
    private void createBallsOnTopOfPaddle() {
        //Repositioning paddle
        for (Collidable c : this.getEnvironment().getCollidables()) {
            if (c.getCollisionRectangle().getUpperLeft().getY() == scrH - borderBlockWidth - 15) {
                Point upperLeft = new Point(400 - (this.level.paddleWidth() / 2), scrH - borderBlockWidth - 15);  //400
                c.getCollisionRectangle().getCollisionRec().setUpperLeft(upperLeft);
            }
        }

        //Creating balls
        for (int i = 0; i  < this.level.numberOfBalls(); i++) {
            Ball ball;
          //==20.6.19  ball = new Ball(400, (int) (scrH - borderBlockWidth - 16), 5, Color.WHITE, this.environment);
            ball = new Ball(400, (int) (scrH - borderBlockWidth - 16), 5, Color.WHITE, this.environment);
            if (i < this.level.initialBallVelocities().size()) {
                ball.setVelocity(this.level.initialBallVelocities().get(i));
            } else {
                ball.setVelocity(this.level.initialBallVelocities().get(0));
            }
            if (this.remainingBallsInGame.getValue() == 0) {
                this.remainingBallsInGame.increase(this.level.numberOfBalls());
            }
            ball.addToGame(this);
        }
    }

    /**
     * This method runs the turns in the level.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
      //  this.runner.run(new CountdownAnimation(6, 3, this.sprites));
       // this.runner.run(new CountdownAnimation(1500, 3, this.sprites));
        this.runner.run(new CountdownAnimation(1500, 3, this.sprites, this.background, this.levelName.getLevelName()));

        if (this.numberOfLives.getValue() == 0) {
            this.running = false;
            return;
        }
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This method runs the level's turns and closes it when there are no more blocks left or no more lives.
     */
  /*  public void run() {
        while (this.numberOfLives.getValue() > 0) {
            playOneTurn();
        }
        return;
    }*/

    /**
     * This method removes an collidable object from the collidables list.
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * This method removes an sprite object from the sprites list.
     * @param s the sprites object to remove.
     */
    public void removeSprite(Sprite s) {
                this.sprites.getSprites().remove(s);
    }
    /**
     * This is an accessor method which returns the game environment.
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * This method draws the level's sprites and is keeping track of the remaining balls and blocks in the level.
     * Also it's responsible for the pause screen.
     * @param d the surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawImage(0, 0, background);
       //this.gui.show(d);


        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            PauseScreen pauseScreen = new PauseScreen(this.keyboard, this.levelName.getLevelName());
            pauseScreen.doOneFrame(d);  //20.6.19
            Animation pauseStopper =
                    new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, pauseScreen);
            this.runner.run(pauseStopper);
        }

            if (remainingBlocksInGame.getValue() == 0) {
                this.scoreTracker.getCurrentScore().increase(100);
                this.running = false;
            } else if (remainingBallsInGame.getValue() == 0) {
                this.numberOfLives.decrease(1);
                this.running = false;

        }
    }

    /**
     * This method stops the animation of the level.
     * @return a boolean value to stop or not the animation.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Accessor method which returns the level's information.
     * @return level's information.
     */
    public LevelInformation getLevel() {
        return level;
    }

    /**
     * Accessor method which returns the number of lives in the game.
     * @return the number of lives of the player in the game.
     */
    public Counter getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * Accessor method which returns the remaining blocks in the game.
     * @return the remaining blocks in the game.
     */
    public Counter getRemainingBlocksInGame() {
        return remainingBlocksInGame;
    }

    /**
     * Accessor method which returns the score of the game.
     * @return the score of the game.
     */
    public Counter getScore() {
        return score;
    }
    @Override
    public String getKey() {
        return "";
    }
}