package geometry;
import collisiondetection.GameEnvironment;
import gamebuilders.GameLevel;
import sprites.collidables.Collidable;
import sprites.Sprite;
import gamebuilders.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * @author Itay Sova
 */
public class Ball implements Sprite {
    private Point pCenter;
    private int radius;
    private Color ballColor;
    private String colorOfTheBall;
    private Velocity vBall;
    private GameEnvironment gameEnvironment;
    private int widthBound = 800, heightBound = 600;
    //the bound are set as default to (0,0) to (800,600)
    private int minXBound = 0, minYBound = 0;

    /**This is a constructor method.It builds a ball according to a center point, radius and color parameters.
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     **/
    public Ball(Point center, int r, Color color) {
        this.pCenter = center;
        this.radius = r;
        this.ballColor = color;
        this.vBall = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }
    /**This is a constructor method.It builds a ball according to a center x and y values, radius and color parameters.
     * @param pX the X value of the center point of the ball.
     * @param pY the Y value of the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     **/
    public Ball(int pX, int pY, int r, Color color) {
        this.pCenter = new Point((double) pX, (double) pY);
        this.radius = r;
        this.ballColor = color;
        this.vBall = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * This constructor builds a ball according to a point, radius, color and a game environment.
     * @param center the initial location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(Point center, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.pCenter = center;
        this.radius = r;
        this.ballColor = color;
        this.vBall = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * This constructor builds a ball according to center X and center Y values, radius, color and game environment.
     * @param pX the X value of the center point of the ball.
     * @param pY the Y value of the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param gameEnvironment the ball's game environment
     */
    public Ball(int pX, int pY, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.pCenter = new Point((double) pX, (double) pY);
        this.radius = r;
        this.ballColor = color;
        this.vBall = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * This constructor builds a ball according to center X and center Y values, radius, color and game environment.
     * @param pX the X value of the center point of the ball.
     * @param pY the Y value of the center point of the ball.
     * @param r the radius of the ball.
     * @param colorOfTheBall the color of the ball.
     * @param gameEnvironment the ball's game environment
     */
    public Ball(int pX, int pY, int r, String colorOfTheBall,
                GameEnvironment gameEnvironment) {
        this.pCenter = new Point((double) pX, (double) pY);
        this.radius = r;
        this.colorOfTheBall = colorOfTheBall;
        this.vBall = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * Accessor method which returns the X value of the center of the ball.
     * @return the X value of the center.
     **/
    public int getX() {
        return (int) Math.round(this.pCenter.getX());
    }
    /**
     * Accessor method which returns the Y value of the center of the ball.
     * @return the Y value of the center.
     **/
    public int getY() {
        return (int) Math.round(this.pCenter.getY());
    }
    /**
     * Accessor method which returns the center point of the ball.
     * @return the ball's center point.
     */
    public Point getCenter() {
        return this.pCenter;
    }
    /**
     * Accessor method which returns the radius of the ball.
     * @return the radius of the ball.
     **/
    public int getSize() {
        return this.radius;
    }
    /**
     * Accessor method which returns the color of the ball.
     * @return the color of the ball.
     **/
    public Color getColor() {
        return this.ballColor;
    }
    /**Accessor method which returns the max X value of the bounds of the ball.
     * @return the widthBound of the ball's frame.
     **/
    public int getWidthBound() {
        return this.widthBound;
    }
    /**
     * Accessor method which returns the max Y value of the bounds of the ball.
     * @return the heightBound of the ball's frame.
     **/
    public int getHeightBound() {
        return this.heightBound;
    }

    /**Accessor method which returns the min X value of the bounds of the ball.
     * @return the minXBound of the ball's frame.
     **/
    public int getMinXBound() {
        return this.minXBound;
    }

    /**Accessor method which returns the min Y value of the bounds of the ball.
     * @return the minYBound of the ball's frame.
     **/
    public int getMinYBound() {
        return this.minYBound;
    }
    /**
     * A method which draws the ball on a given DrawSurface.
     * @param surface the surface we want to draw on.
     **/
    public void drawOn(DrawSurface surface) {
        if (colorOfTheBall == "Credit") {
            surface.setColor(Color.lightGray);
            surface.fillCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.BLACK);
            surface.drawCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.BLACK);
            surface.drawText((int) this.pCenter.getX() - this.radius, (int) this.pCenter.getY() + this.radius, "$", 20);
        } else if (colorOfTheBall == "Safebox") {
            surface.setColor(Color.GREEN);
            surface.fillCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.BLACK);
            surface.drawCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.BLACK);
            surface.drawText((int) this.pCenter.getX() - this.radius, (int) this.pCenter.getY() + this.radius, "$", 20);
        } else {
            surface.setColor(this.ballColor);
            surface.fillCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.BLACK);
            surface.drawCircle(this.getX(), this.getY(), this.radius);
            surface.setColor(Color.RED);
            surface.drawCircle((int) this.pCenter.getX(), (int) this.pCenter.getY(), 1);
        }
    }
    /**
     * A method that sets the velocity of the ball.
     * @param v a velocity parameter for this ball.
     **/
    public void setVelocity(Velocity v) {
        this.vBall = v;
    }
    /**
     * A method that creates a velocity according to a dX and dY parameters and then sets it to the ball.
     * @param dX the change in the X value of the ball.
     * @param dY the change in the Y value of the ball.
     **/
    public void setVelocity(double dX, double dY) {

        this.vBall = new Velocity(dX, dY);

    }
    /**
     * Accessor method which returns the velocity of the ball.
     * @return the velocity of the ball.
     **/
    public Velocity getVelocity() {
        return this.vBall;
    }
    /**
     * A method which sets the new center of the ball.
     * @param pX the x coordinate of the new ball's center.
     * @param pY the y coordinate of the new ball's center.
     */
    public void setCenter(double pX, double pY) {
        this.pCenter = new Point(pX, pY);
    }
    /**
     * Accessor method which returns the game environment of the ball.
     * @return the game environment of the ball.
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }



    /**
     * A method that creates the ball trajectory.
     * @return the ball's trajectory.
     */
    public Line trajectory() {
        Point t1 = new Point(this.pCenter.getX(), this.pCenter.getY());
        Point t2 = new Point(this.pCenter.getX() + this.vBall.getDx(), this.pCenter.getY() + this.vBall.getDy());
        return new Line(t1, t2);

    }
    /**This method apply the velocity of the ball on its center,
     * and by that moves the ball by the change of the velocity values.
     * In addition the method call another method that checks if the ball won't exceed from the bounds of the frame.
     * Also added for Ass3 the movement on the trajectory.
     **/
    public void moveOneStep() {
        if (this.gameEnvironment.getClosestCollision(this.trajectory()) == null) {
            return;
        }
        this.setVelocity(this.gameEnvironment.getClosestCollision(this.trajectory()).collisionObject().hit(this,
            this.gameEnvironment.getClosestCollision(this.trajectory()).collisionPoint(), this.getVelocity()));

        this.pCenter = this.getVelocity().applyToPoint(this.pCenter);

        //boundCheck();

    }
    /*
    This method checks if the ball's edge(center+radius point) didn't exceed from bounds of the frame.
     If the edge of the ball equals to the values of the bounds, then it just changes the direction of the velocity.
     If the edge of the ball is greater or lesser than the bounds,
     then calls a position fixer method and changes direction.
    public void boundCheck() {
        //edge of ball equals the max X value of the frame || edge of ball equals the min X value of the frame.
        if ((this.getX() + this.getSize() == this.widthBound) || (this.getX() - this.getSize() == this.minXBound)) {
            this.setVelocity(-this.vBall.getDx(), this.vBall.getDy());
        //edge of ball greater from the max X value of the frame
        // || edge of ball lesser than the min X value of the frame.
    } else if ((this.getX() + this.getSize() > this.widthBound) || (this.getX() - this.getSize() < this.minXBound)) {
            //positionFixer();
            this.setVelocity(-this.vBall.getDx(), this.vBall.getDy());
        }
        //edge of ball equals the max Y value of the frame || edge of ball equals the min Y value of the frame.
        if ((this.getY() + this.getSize() == this.heightBound) || (this.getY() - this.getSize() == this.minYBound)) {
            this.setVelocity(this.vBall.getDx(), -this.vBall.getDy());

            //edge of ball greater from the max Y value of the frame
            // || edge of ball lesser than the min Y value of the frame.
        } else if ((this.getY() + this.getSize() > this.heightBound)
                || (this.getY() - this.getSize() < this.minYBound)) {
            // positionFixer();
            this.setVelocity(this.vBall.getDx(), -this.vBall.getDy());
        }

        }*/

    /**
     * A method that notify the ball that time has passed by calling moveOneStep.
     * Also added exceptions where the ball is on the edges of the blocks, so it changes position.
     */
    public void timePassed() {
        this.moveOneStep();
        this.stuckFix();
    }
    /**
     * A method that adds the ball to the sprite list of the game.
     * @param game the game we ran.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * This method fixes a bug of which the ball enters into the blocks.
     * this bug is fixed by repositioning the ball and slightly changing the ball's velocity
     * so it will seemlessly recognize and recalculate the closest block to collide with.
     */
    public void stuckFix() {
        if (this.gameEnvironment.getCollidables().isEmpty()) {
            return;
        }
        Random r = new Random();
        for (Collidable c : this.gameEnvironment.getCollidables()) {
            if (c.getCollisionRectangle().isPointInRec(this.getCenter())) {
            //    this.setCenter(this.getX() - this.getVelocity().getDx() * 2 + 0.5,
                     //   this.getY() - this.getVelocity().getDy() * 2);
                    this.setCenter(this.getX() - (this.getVelocity().getDx() * 2 + 1),
                            this.getY() - this.getVelocity().getDy() * 2);
              //  } else if (this.getVelocity().getDx() > 0 && this.getVelocity().getDy() < 0)
                this.setVelocity(this.getVelocity().getDx() + 0.0000000000001,
                        this.getVelocity().getDy() + 0.0000000000001);
            }
        }
    }

    /**
     * this method removes the ball from the game's sprite list.
     * @param game the game that is played.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


}
