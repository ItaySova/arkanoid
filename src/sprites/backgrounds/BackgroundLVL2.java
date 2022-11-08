package sprites.backgrounds;

import biuoop.DrawSurface;
import gamebuilders.GameLevel;
import sprites.Sprite;
import sprites.collidables.Block;
import java.awt.Color;
/**
 * @author Itay Sova
 * This class is a sprite that holds the background for level2.
 */
public class BackgroundLVL2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block backgroundColorBlock = new Block(0, 0, 800, 600, Color.green);
        backgroundColorBlock.drawOn(d);
        //Creating the ketchup bottle
        d.setColor(Color.RED);
        d.fillRectangle(365, 25, 70, 50);
        d.fillRectangle(365, 50, 70, 50);
        d.fillRectangle(365, 100, 30, 30);
        d.fillRectangle(370, 100, 30, 30);
        d.fillRectangle(390, 100, 30, 30);
        d.fillRectangle(405, 100, 30, 30);

        d.fillRectangle(370, 120, 20, 20);
        d.fillRectangle(380, 120, 20, 20);
        d.fillRectangle(390, 120, 20, 20);
        d.fillRectangle(400, 120, 20, 20);
        d.fillRectangle(410, 120, 20, 20);

        d.fillRectangle(380, 130, 20, 20);
        d.fillRectangle(390, 130, 20, 20);
        d.fillRectangle(400, 130, 20, 20);

        //Heinz sign
        d.setColor(Color.WHITE);
        d.fillRectangle(370, 25, 60, 60);
        d.fillRectangle(370, 65, 60, 60);

        //Heinz text
        d.setColor(Color.BLACK);
       // d.drawText(373, 50, "HEINZ", 20);
        d.drawText(373, 55, "HEINZ", 20);
        d.drawText(372, 70, "TOMATO", 13);
        d.drawText(371, 85, "KETCHUP", 12);
        d.setColor(Color.red);
        d.fillRectangle(394, 95, 10, 10);
        d.setColor(Color.green);
        d.drawRectangle(370, 26, 60, 98);

        //Buns of the burger
        d.setColor(Color.ORANGE);
        //bottom bun
        d.fillRectangle(70, 320, 660, 60);
        //top bun
        d.fillRectangle(70, 250, 660, 35);
        d.fillRectangle(70, 230, 660, 20);
        d.fillRectangle(90, 220, 620, 20);
        d.fillRectangle(110, 210, 580, 20);
        d.fillRectangle(130, 200, 540, 20);
        d.fillRectangle(150, 190, 500, 20);
        //Sesame
        d.setColor(Color.WHITE);
        d.fillRectangle(170, 240, 15, 15);
        d.fillRectangle(190, 215, 15, 15);
        d.fillRectangle(210, 225, 15, 15);
        d.fillRectangle(230, 210, 15, 15);
        d.fillRectangle(250, 230, 15, 15);
        d.fillRectangle(270, 240, 15, 15);
        d.fillRectangle(290, 220, 15, 15);
        d.fillRectangle(350, 220, 15, 15);
        d.fillRectangle(390, 240, 15, 15);
        d.fillRectangle(410, 210, 15, 15);
        d.fillRectangle(430, 215, 15, 15);
        d.fillRectangle(480, 220, 15, 15);
        d.fillRectangle(500, 240, 15, 15);
        d.fillRectangle(520, 220, 15, 15);
        d.fillRectangle(550, 230, 15, 15);
        d.fillRectangle(590, 210, 15, 15);
        d.fillRectangle(610, 230, 15, 15);

        //Fries
        for (int i = 0; i < 4; i++) {
            d.setColor(Color.BLACK);
            d.drawRectangle(665 + i * 25, 430, 20, 100);
            d.setColor(Color.yellow);
            d.fillRectangle(666 + i * 25, 431, 19, 100);
        }

        //More fries
        for (int i = 0; i < 4; i++) {
            d.setColor(Color.BLACK);
            d.drawRectangle(670 + i * 30, 440, 20, 100);
            d.setColor(Color.yellow);
            d.fillRectangle(671 + i * 30, 441, 19, 100);
        }

        //More fries
        for (int i = 0; i < 4; i++) {
            d.setColor(Color.BLACK);
            d.drawRectangle(650 + i * 30, 470, 20, 100);
            d.setColor(Color.yellow);
            d.fillRectangle(651 + i * 30, 471, 19, 100);
        }

        //fries holder
        d.setColor(Color.RED);
        d.fillRectangle(650, 500, 200, 200);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
