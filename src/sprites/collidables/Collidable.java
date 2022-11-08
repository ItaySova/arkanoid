package sprites.collidables;

import gamebuilders.Velocity;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Itay Sova
 * This interface is for a collidable object.
 */
public interface Collidable {

    /**
     * This method returns the "collision shape" of the object.
     * @return the collision shape of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * This method returns a new velocity by the hit conditions according to the point and velocity.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hits the collidable.
     * @return the velocity by the hit conditions.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}