package collisiondetection;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Itay Sova
 */
public class SpriteCollection {
   //private List<sprites.Sprite> sprites = new ArrayList<sprites.Sprite>();
   private List<Sprite> sprites;

    /**
     * A constructor that builds a new arraylist of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * this method adds a new sprite to the list of sprites.
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * This method calls timepassed on all sprites.
     */
    public void notifyAllTimePassed() {
       List<Sprite> spritesList = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    /**
     * This method calls drawnOn on all sprites.
     * @param d the shared surface for the sprites to be draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * An accessor method which return the sprites list.
     * @return the sprites list.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}