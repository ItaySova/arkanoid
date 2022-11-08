package geometry;

/**
 * @author Itay Sova
 */

public class Point {
    private double x, y;

    /**This is the constructor method.It builds a point with (x,y) coordinates.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**This method calculates the distance between 2 points.
     * @param other the other point which we want to measure the distance from our point.
     * @return the distance of this point to the other point
     **/
    public double distance(Point other) {
        double dTotal, dX, dY;
        dX = (this.x - other.x);
        dY = (this.y - other.y);
        dTotal = Math.sqrt(dX * dX + dY * dY);
        return dTotal;
    }


    /**This method checks if the points are equal.
     * @param other the other point which we want to check if equals to ours.
     * @return true if the points are equal, false otherwise
     **/
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;

    }


    /**This method returns the x value of this point.
     * @return x value of this point.
     **/
    public double getX() {
        return this.x;
    }
    /**This method returns the y value of this point.
     * @return y value of this point.
     **/
    public double getY() {
        return this.y;
    }

    /**
     * This method sets the x value of the point.
     * @param pX the x value of the point.
     */
 public void setX(double pX) {
      this.x = pX;
    }
    /**
     * This method sets the y value of the point.
     * @param pY the y value of the point.
     */
    public void setY(double pY) {
        this.y = pY;
    }
}