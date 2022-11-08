package sprites;

import biuoop.DrawSurface;
import gamebuilders.Counter;
import gamebuilders.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class represents a lives indicator object.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private Rectangle rectangle;
    /**
     * A constructor method which creates a lives indicator from a given lives counter object.
     * @param lives the given lives counter.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
        //Point upperLeft = new Point(0, 25);
        Point upperLeft = new Point(0, 0);
        this.rectangle = new Rectangle(upperLeft, 800, 25);    }

    @Override
    public void drawOn(DrawSurface d) {
       /* d.setColor(Color.red);
        d.fillRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
        d.setColor(Color.BLACK);
        d.drawRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));*/

        d.setColor(Color.BLACK);
        d.drawText((int) (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2 - 300),
                (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 4),  "Lives: " + this.lives.getValue(), 18);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
