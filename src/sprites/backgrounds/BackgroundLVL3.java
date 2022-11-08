package sprites.backgrounds;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import sprites.Sprite;
import sprites.collidables.Block;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class is a sprite that holds the background for level3.
 */
public class BackgroundLVL3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Block backgroundColorBlock = new Block(0, 0, 800, 600, Color.orange);
        backgroundColorBlock.drawOn(d);

        //Straws box
        d.setColor(Color.black);
        d.drawRectangle(26, 560, 130, 40);
        d.setColor(Color.lightGray);
        d.fillRectangle(25, 561, 130, 30);
        d.setColor(Color.red);
        d.drawText(55, 580, "Straws", 20);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
