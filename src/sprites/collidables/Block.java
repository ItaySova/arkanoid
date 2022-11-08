package sprites.collidables;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import gamebuilders.Velocity;
import geometry.Ball;
import geometry.Point;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

/**
 * @author Itay Sova
 * this class creates a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private int hitCounter = 1;
    private Color blockColor;
    private Color strokeColor;
    private String otherColor;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Image iconImage = null;
    /**
     * This is a constructor that builds a block from a rectangle and a color parameters.
     * @param rec the rectangle of the block.
     * @param color the rectangle's color.
     * */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.blockColor = color;
        this.strokeColor = Color.BLACK;

    }

    /**
     * This method builds a block by the x,y axis of the upperleft, width,height and color.
     * @param x the x axis of the upperleft point of the rectangle.
     * @param y the y axis of the upperleft point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color of the block
     */
    public Block(double x, double y, int width, int height, Color color) {
        this.rec = new Rectangle(x, y, width, height);
        this.blockColor = color;
        this.strokeColor = Color.BLACK;

    }

    /**
     * This method builds a block by the x,y axis of the upperleft, width,height and color.
     * @param x the x axis of the upperleft point of the rectangle.
     * @param y the y axis of the upperleft point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color the color of the block by a string name.
     */
    public Block(double x, double y, int width, int height, String color) {
        this.rec = new Rectangle(x, y, width, height);
        this.otherColor = color;
        this.strokeColor = Color.BLACK;

    }

    /**
     * This method builds a block by the x,y axis of the upperleft, width,height and color.
     * @param x the x axis of the upperleft point of the rectangle.
     * @param y the y axis of the upperleft point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param fillColor of the block.
     * @param strokeColor of the block.
     * @param color string type color.
     */
    public Block(double x, double y, int width, int height, Color fillColor, Color strokeColor,
                 String color) {
        this.rec = new Rectangle(x, y, width, height);
        this.blockColor = fillColor;
        this.iconImage = iconImage;
        this.otherColor = color;

    }


    /**
     * This method notify the sprite object that time has passed.
     */
    public void timePassed() { }

    /**
     * This method returns the rectangle of the block.
     * @return the rectangle of the block.
     **/
    public Rectangle getCollisionRectangle() {
        return rec.getCollisionRec();
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * A method that checks if the hit of the ball on the block is vertical.
     * @param collisionPoint the point of collision.
     * @return true if it is vertical, false otherwise.
     */
   public boolean checkIsItVerticalHit(Point collisionPoint) {
        if (this.rec.getTopLine().isPointOnLine(collisionPoint)
                || this.rec.getBottomLine().isPointOnLine(collisionPoint)) {
            return true;
        }
            return false;
    }

    /**
     * A method that checks if the hit of the ball on the block is horizontaly.
     * @param collisionPoint the point of collision.
     * @return true if it is horizontal, false otherwise.
     */
    public boolean checkIsItHorizontalHit(Point collisionPoint) {
        if (this.rec.getLeftLine().isPointOnLine(collisionPoint)
                || this.rec.getRightLine().isPointOnLine(collisionPoint)) {
            return true;
        }
        return false;
    }
    /**
     * This method gets the collision point and the current velocity of the ball,
     * and then calculates and returns the new velocity of the ball according to the hit angle.
     * @param collisionPoint the collision point between the ball and the rectangle.
     * @param currentVelocity the current velocity of the ball.
     * @param hitter the ball that hits the block.
     * @return new velocity of the ball by the angle it hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (checkIsItHorizontalHit(collisionPoint)
                || checkIsItVerticalHit(collisionPoint)) {
            this.notifyHit(hitter);
            if (hitCounter > 0) {
                hitCounter--;
            }
        }
        if (checkIsItHorizontalHit(collisionPoint)
                && checkIsItVerticalHit(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(),
                    -currentVelocity.getDy());
        } else {
            if (checkIsItHorizontalHit(collisionPoint)) {
                return new Velocity(-currentVelocity.getDx(),
                        currentVelocity.getDy());
            }
            if (checkIsItVerticalHit(collisionPoint)) {
                return new Velocity(currentVelocity.getDx(),
                        -currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }
    /**
     * This method draws the block on the surface.
     * @param d the given surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int s1 = 4;
        String txt;
        if (this.otherColor == "Burger") {
            d.setColor(new Color(97, 67, 7));
        } else if (this.otherColor == "Paycheck") {
            d.setColor(Color.green);
        } else if (this.otherColor == "Safebox") {
            d.setColor(Color.gray);
        } else if (this.otherColor == "Cola") {
            d.setColor(Color.red);
        } else {
            try {
                d.setColor(this.blockColor);
            } catch (NullPointerException e) {

                d.setColor(Color.white);
            }
        }
        d.fillRectangle((int) (this.rec.getUpperLeft().getX()), (int) (this.rec.getUpperLeft().getY()),
                (int) (this.rec.getWidth()), (int) (this.rec.getHeight()));
        if (strokeColor != null) {
            d.setColor(this.strokeColor);
        } else {
            d.setColor(Color.black);
        }
        d.drawRectangle((int) (this.rec.getUpperLeft().getX()), (int) (this.rec.getUpperLeft().getY()),
                (int) (this.rec.getWidth()), (int) (this.rec.getHeight()));
        if (this.iconImage != null) {
            d.drawImage((int) this.rec.getUpperLeft().getX() + (int) (this.rec.getWidth()) / 3,
                    (int) (this.rec.getUpperLeft().getY() + (int) (this.rec.getHeight()) / 3), this.iconImage);
        }
        if (this.otherColor == "Paycheck") {
            txt = "$";
            d.setColor(Color.BLACK);
            d.drawText((int) (this.rec.getUpperLeft().getX() + this.rec.getWidth() / 2 - s1),
                    (int) (this.rec.getUpperLeft().getY()
                            + this.rec.getHeight() / 2 + s1),  txt, 15);
        } else if (this.otherColor == "Safebox") {
            txt = "SAFEBOX";
            //Safebox text
            d.setColor(Color.BLACK);
            d.drawText((int) this.rec.getUpperLeft().getX() + 5, 52,  txt, 20);
            d.drawLine((int) this.rec.getUpperLeft().getX() + 5, 52, (int) this.rec.getUpperLeft().getX() + 95, 52);
            //Safebox lock
            d.drawCircle((int) this.rec.getTopLine().middle().getX(),
                    (int) this.rec.getTopLine().middle().getY() + 50, 20);
            d.drawLine((int) this.rec.getTopLine().middle().getX() + 20,
                    (int) this.rec.getTopLine().middle().getY() + 50,
                        (int) this.rec.getTopLine().middle().getX() + 30,
                    (int) this.rec.getTopLine().middle().getY() + 50);
            d.drawLine((int) this.rec.getTopLine().middle().getX(),
                    (int) this.rec.getTopLine().middle().getY() + 30,
                    (int) this.rec.getTopLine().middle().getX(),
                    (int) this.rec.getTopLine().middle().getY() + 20);
            d.drawLine((int) this.rec.getTopLine().middle().getX(),
                    (int) this.rec.getTopLine().middle().getY() + 70,
                    (int) this.rec.getTopLine().middle().getX(),
                    (int) this.rec.getTopLine().middle().getY() + 80);
            d.drawLine((int) this.rec.getTopLine().middle().getX() - 20,
                    (int) this.rec.getTopLine().middle().getY() + 50,
                    (int) this.rec.getTopLine().middle().getX() - 33,
                    (int) this.rec.getTopLine().middle().getY() + 50);
            d.setColor(Color.green);

        } else if (this.otherColor == "Cola") {
            //The sign of the cola
            d.setColor(Color.RED);
            d.fillRectangle(240, 150, 315, 70);
            d.setColor(Color.white);  //this.strokeColor);
            d.drawRectangle(240, 149, 315, 70);
            d.setColor(Color.white);
            d.drawText(270, 205, "Coca-Cola", 54);
            d.fillRectangle(240, 212, 315, 5);
            d.fillRectangle(240, 153, 315, 5);

            //"CLASSIC" SIGN
            d.setColor(Color.white);
            d.drawText(335, 287, "CLASSIC", 30);

        }
    }

    /**
     * This method adds the block to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * Accessor method that returns the rectangle of the block.
     * @return the rectangle of the block.
     */
    public Rectangle getRec() {
        return this.rec;
    }
    /**
     * This method decremets the number of hits on the block.
     */
    public void decHitCounter() {
        this.hitCounter = this.hitCounter - 1;
    }
    /**
     * Accessor method that returns the counter of hits of the block.
     * @return the number of hits on the block.
     */
    public int getHitCounter() {
        return hitCounter;
    }
    /**
     * This method sets the hit counting of the block.
     * @param hits number of hits on the block.
     */
    public void setHitCounter(int hits) {
        this.hitCounter = hits;
    }

    /**
     * This method removes the block from the entire game(from the collidables and sprites lists).
     * @param game the game currently played.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notifies all listeners that a hit was occurred.
     * @param hitter the ball that hitted.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This method returns the special color for the block by the name of the level.
     * @return the special color.
     */
    public String getOtherColor() {
        return otherColor;
    }

    /**
     * This method sets the icon image for the block.
     * @param iconImageParam the icon image to set.
     */
    public void setIconImage(Image iconImageParam) {
        this.iconImage = iconImageParam;
    }
}
