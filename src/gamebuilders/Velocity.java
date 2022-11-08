package gamebuilders;

import geometry.Point;

/**
 * @author Itay Sova
 */
public class Velocity {
    private double dx, dy;
    /**This is a constructor method.It builds the change on the x and y values.
     * @param dx the change in the 'x' axes.
     * @param dy the change in the 'y' axes.
     **/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**This method takes a point with position(x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point we want to apply to the change in the x and y axes.
     * @return the new point after the change.
     **/
    public Point applyToPoint(Point p) {
        Point newP = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newP;
    }

    /**This method converts angle and speed values to a change in 'X' and 'Y' axes.
     * @param angle the angle we want the ball to move by.
     * @param velocity the speed we want the ball to move by in the direction of the angle.
     * @return a velocity object with dx and dy values.
     **/
    public static Velocity fromAngleAndSpeed(double angle, double velocity) {
        double radAngle = Math.toRadians(angle - 90);
        double dx = velocity * Math.cos(radAngle);
        double dy = velocity * Math.sin(radAngle);
        return new Velocity(dx, dy);
    }
    /**Accessor method which returns the change in the 'X' value.
     * @return the dx parameter.
     **/
        public double getDx() {
         return this.dx;
        }
    /**Accessor method which returns the change in the 'Y' value.
     * @return the dy parameter.
     **/
    public double getDy() {
        return this.dy;
        }

    /**
     * Accessor method which returns a velocity.
     * @return the velocity after calculating.
     */
    public double getVelocity() {
            return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        }
    }
