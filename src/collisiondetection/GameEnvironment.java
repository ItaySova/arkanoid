package collisiondetection;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.collidables.Collidable;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Itay Sova
 */
public class GameEnvironment {
    private Rectangle thePaddle;
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * add the given collidable to the environment.
     * @param c the collidable object.
      **/
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * This method finds the index of the collidable that intersects with the trajectory.
     * @param trajectory the trajectory of the ball.
     * @return the index of the collidable.
     */
    public int findClosestInex(Line trajectory) {
        int count = 0;
        while (trajectory.closestIntersectionToStartOfLine(this.collidables.get(count).getCollisionRectangle()) == null
                && count < this.collidables.size() - 1) {
            count++;
        }
        return count;
    }
    /**
     * This method returns the closest collision info according to the trajectory line it gets
     * If no collision occurs, it returns null.
     * @param trajectory the trajectory line of the ball.
     * @return the collision info or null if no collision occured.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        int i = findClosestInex(trajectory);
        Point closest = trajectory.closestIntersectionToStartOfLine(
                this.collidables.get(i).getCollisionRectangle());
        int collisionIndex = i;

        for (Collidable c : collidables) {
          /* if (c.getCollisionRectangle().isPaddle()) {
               this.setThePaddle(c.getCollisionRectangle());
            }*/
            int counter = 0;
            if (trajectory.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle()) != null) {
                if (closest.distance(trajectory.start())
                        > trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()).
                        distance(trajectory.start())) {
                    closest = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

                    collisionIndex = counter;
                }
            }
            counter++;
        }
        return new CollisionInfo(closest,
                this.collidables.get(collisionIndex));
    }

    /**
     * This method returns the collidables list.
     * @return the collidables list.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
    /*public geometry.Rectangle getThePaddle() {
        return thePaddle;
    }*/
/*
    public void setThePaddle(geometry.Rectangle thePaddle) {
        this.thePaddle = thePaddle;
    }*/
}




