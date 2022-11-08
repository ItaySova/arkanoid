package sprites;

import biuoop.DrawSurface;
import gamebuilders.Counter;
import gamebuilders.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class represents an object that is a score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;
    /**
     * A constructor that builds a score indicator from a score counter object.
     * @param score the given score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        Point upperLeft = new Point(0, 0);
        this.rectangle = new Rectangle(upperLeft, 800, 25);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
        d.setColor(Color.BLACK);
        d.drawRectangle((int) (this.rectangle.getUpperLeft().getX()), (int) (this.rectangle.getUpperLeft().getY()),
                (int) (this.rectangle.getWidth()), (int) (this.rectangle.getHeight()));
        d.setColor(Color.BLACK);
        d.drawText((int) (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2 - 25),
                (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 4),  "Score: " + this.score.getValue(), 18);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
