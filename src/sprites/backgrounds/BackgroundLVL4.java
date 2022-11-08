package sprites.backgrounds;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import sprites.Sprite;
import sprites.collidables.Block;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class is a sprite that holds the background for level4.
 */
public class BackgroundLVL4 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block backgroundColorBlock = new Block(0, 0, 800, 600, Color.CYAN);
        backgroundColorBlock.drawOn(d);

        //Guts
        d.setColor(Color.pink);
        //Credits box
        d.setColor(Color.black);
        d.drawRectangle(30, 560, 130, 40);
        d.setColor(Color.lightGray);
        d.fillRectangle(30, 561, 130, 30);
        d.setColor(Color.BLACK);
        d.drawText(42, 580, "Credit cards", 20);


    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
