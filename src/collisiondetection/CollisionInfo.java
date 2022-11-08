package collisiondetection;

import geometry.Point;
import sprites.collidables.Collidable;

/**
 * @author Itay Sova
 */
public class CollisionInfo {
    private Point collisionP;
    private Collidable rec;
    /**
     * This constructor builds the info about a collision object,
     * from the collision point and collision object itself.
     * @param collisionP the collision point.
     * @param rec the collision object.
     */
    public CollisionInfo(Point collisionP, Collidable rec) {
        this.collisionP = collisionP;
        this.rec = rec;
    }
    /**
     * This method returns the point at which the collision occured.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * This method returns the collidable object involved in the collision.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.rec;
    }
}