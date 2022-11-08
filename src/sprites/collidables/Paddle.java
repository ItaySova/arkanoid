package sprites.collidables;

import biuoop.DrawSurface;
import biuoop.GUI;
import gamebuilders.GameLevel;
import gamebuilders.Velocity;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class creates a paddle.
 **/
public class Paddle implements Sprite, Collidable {

    private biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Block padBlock;
    private Velocity vPad;
    private Rectangle rectangle;
    private int regionsNum = 5;
    private Line[] regions = new Line[regionsNum];
    private int[] angels = {300, 330, 0, 30, 60};
    private int[] hitAngels = {0, 80, 290};
    private int paddleSpeed;
    /**This is the constructor method.It builds a paddle from block, gui,keyboard and speed.
     * @param padBlock the block of the paddle.
     * @param gui the gui of the game.
     * @param keyboard the keyboard that will move the paddle.
     * @param paddleSpeed the speed of the paddle.
     **/
    public Paddle(Block padBlock, GUI gui, biuoop.KeyboardSensor keyboard, int paddleSpeed) {
        this.padBlock = padBlock;
        padBlock.getRec().setAmIPaddle(true);
        this.gui = gui;
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(padBlock.getRec().getUpperLeft(),
                padBlock.getRec().getWidth(), padBlock.getRec().getHeight());
        this.paddleSpeed = paddleSpeed;

    }


    /**
     * This method notify the paddle that time has passed and uses the keyboard to move the paddle.
     */
    public void timePassed() {
        if (this.keyboard.isPressed("a") || this.keyboard.isPressed("A")
                || this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            //System.out.println("the 'a' key is pressed");
            this.moveLeft();
        } else if (this.keyboard.isPressed("d") || this.keyboard.isPressed("D")
                || this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            // System.out.println("the 'd' key is pressed");
            this.moveRight();
        }
    }

    /**
     * This method moves the paddle leftwards.
     */
    public void moveLeft() {
      if (this.rectangle.getUpperLeft().getX() > 25) {
          Velocity newV = new Velocity(-this.paddleSpeed, 0);
          setvPaddle(newV);
         Point p1 = new Point(this.rectangle.getUpperLeft().getX() + newV.getDx(),
                 this.rectangle.getUpperLeft().getY());
         this.rectangle = new Rectangle(p1, this.rectangle.getWidth(), this.rectangle.getHeight());
       }

    }
    /**
     * This method moves the paddle rightwards.
     */
    public void moveRight() {
       if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < 775)  {
           Velocity newV = new Velocity(this.paddleSpeed, 0);
           setvPaddle(newV);
           Point p1 = new Point(this.rectangle.getUpperLeft().getX() + newV.getDx(),
                   this.rectangle.getUpperLeft().getY());
           this.rectangle = new Rectangle(p1, this.rectangle.getWidth(), this.rectangle.getHeight());
       }


    }

    /**
     * This method draws the paddle on the surface.
     * @param d the surface we will draw on.
     */
   public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
       d.setColor(Color.BLACK);
       d.drawRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
               (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));

    }

    /**
     * This method returns the rectangle of the paddle.
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }



    /**
     * This method creates into the geometry.Line list the line of the region on the paddle the ball hits on.
     * @param index index parameter for the list of lines.
     */
    public void assignAtRegion(int index) {
        double regionWidth = this.rectangle.getWidth() / regionsNum;
        regions[index] = new Line(this.rectangle.getUpperLeft().getX() + index * regionWidth,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + (index + 1) * regionWidth,
                this.rectangle.getUpperLeft().getY());
    }
    /**
     * This method finds the region on the paddle, assign them at the array of lines of the regions
     * and returns the right velocity for the ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return currentVelocity the new velocity for the ball.
     */
    public Velocity hitRegion(Point collisionPoint, Velocity currentVelocity) {
      for (int i = 0; i < regionsNum; i++) {
          assignAtRegion(i);
      }
      for (int i = 0; i < regionsNum; i++) {
            if (regions[i].isPointOnLine(collisionPoint)) {

               if (angels[i] != 0) {
                   return Velocity.fromAngleAndSpeed(angels[i], currentVelocity.getVelocity());
               } else {
                   return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
               }
            }
        }
        return currentVelocity;
    }
    /**
     * This method returns a new velocity by the hit conditions according to the point and velocity.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter the balls that hits the paddle.
     * @return the velocity by the hit conditions.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int index = -1;
        if (this.rectangle.getBottomLine().isPointOnLine(collisionPoint)) {
            index = 0;
         }
        if (this.rectangle.getRightLine().isPointOnLine(collisionPoint)) {
            index = 1;
         }
        if (this.rectangle.getLeftLine().isPointOnLine(collisionPoint)) {
            index = 2;
        }
       if (index != -1) {
            return Velocity.fromAngleAndSpeed(hitAngels[index], currentVelocity.getVelocity());
        } else if (this.rectangle.getTopLine().isPointOnLine(collisionPoint)) {
            return hitRegion(collisionPoint, currentVelocity);
        }
        return currentVelocity;
    }
    /**
     * This method adds the paddle to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * This method sets the gui of the game in the paddle.
     * @param gameGui the gui of the game.
     */
    public void setGui(GUI gameGui) {
        this.gui = gameGui;
    }

    /**
     * This method sets the velocity of the paddle.
     * @param vPaddle the velocity of the paddle.
     **/
    public void setvPaddle(Velocity vPaddle) {
        this.vPad = vPaddle;
    }

}