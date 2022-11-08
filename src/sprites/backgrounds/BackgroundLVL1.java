package sprites.backgrounds;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import sprites.Sprite;
import sprites.collidables.Block;

import java.awt.Color;
/**
 * @author Itay Sova
 * This class is a sprite that holds the background for level1.
 */
public class BackgroundLVL1 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

        Block backgroundColorBlock = new Block(0, 0, 800, 600, Color.cyan);

        backgroundColorBlock.drawOn(d);
        d.setColor(Color.white);
        d.fillCircle(345, 200, 75);
        d.fillCircle(455, 200, 75);
        d.setColor(Color.BLACK);
        d.drawLine(400, 100, 415, 70);
        d.setColor(Color.ORANGE);
        //Four orange pixels in the right of the cupcake
        d.fillRectangle(490, 250, 40, 40);
        d.fillRectangle(470, 270, 40, 40);
        d.fillRectangle(450, 290, 40, 40);
        d.fillRectangle(430, 310, 40, 40);

        //pixels at the buttom of the cupcake
        d.fillRectangle(410, 330, 40, 40);
        d.fillRectangle(350, 330, 40, 40);
        d.fillRectangle(370, 330, 40, 40);
        d.fillRectangle(390, 330, 40, 40);

        //Four pixels in the left of the cupcake
        d.fillRectangle(270, 250, 40, 40);
        d.fillRectangle(290, 270, 40, 40);
        d.fillRectangle(310, 290, 40, 40);
        d.fillRectangle(330, 310, 40, 40);

        //Pixels in the middle
        d.fillRectangle(450, 250, 40, 40);
        d.fillRectangle(410, 250, 40, 40);
        d.fillRectangle(370, 250, 40, 40);
        d.fillRectangle(330, 250, 40, 40);
        d.fillRectangle(290, 250, 40, 40);
        d.fillRectangle(410, 270, 40, 40);
        d.fillRectangle(370, 270, 40, 40);
        d.fillRectangle(330, 270, 40, 40);
        d.fillRectangle(410, 310, 40, 40);
        d.fillRectangle(370, 310, 40, 40);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    @Override
    public void timePassed() {

    }
}
