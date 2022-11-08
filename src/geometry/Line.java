package geometry;

/**
 * @author Itay Sova
 */
public class Line {
   private Point p1, p2;

    /**This is the constructor method.It builds a line from 2 points.
     * @param start the first point of the line.
     * @param end the second point of the line.
     **/
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }
    /**This is another constructor method.It builds a line from 2 coordinates, which will be built as points.
     * @param x1 the x value of the first point.
     * @param y1 the y value of the first point.
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point.
     **/
    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**This method calculates the length of the line by the distance method of point.
     * @return the length of the line.
     **/
    public double length() {
        double length;
        length = this.p1.distance(this.p2);
        return length;
    }

    /**This method calculates the middle of the line.
     * @return the middle point of the line.
     **/
    public Point middle() {
        double midX = (this.p1.getX() + this.p2.getX()) / 2;
        double midY = (this.p1.getY() + this.p2.getY()) / 2;
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**This method returns the start point of the line.
     * @return the start point of the line.
     **/
    public Point start() {
        return this.p1;
    }

    /**This method returns the end point of the line.
     * @return the end point of the line.
     **/
    public Point end() {
        return this.p2;
    }

    /**
     * This method calculates the intersection point between two lines.
     * @param other the other point which we want to calculate the intersection with our point.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double isInThis1 = this.p2.getY() - this.p1.getY();
        double isInThis2 = this.p1.getX() - this.p2.getX();
        double isInThis3  = isInThis1 * this.p1.getX() + isInThis2 * this.p1.getY();
        double isOther1 = other.p2.getY() - other.p1.getY();
        double isOther2 = other.p1.getX() - other.p2.getX();
        double isOther3 = isOther1 * other.p1.getX() + isOther2 * other.p1.getY();
        double m = isInThis1 * isOther2 - isOther1 * isInThis2;
        if (m == 0) {
              return null;
        } else {
            double interX = (isOther2 * isInThis3 - isInThis2 * isOther3) / m;
            double interY = (isInThis1 * isOther3 - isOther1 * isInThis3) / m;
            Point interP = new Point(interX, interY);
            if (this.isAtLine(interP) && other.isAtLine(interP)) {
                return interP;
            } else {
                return null;
            }
        }
    }
    /**This method checks if the lines are equal.
     * @param other the other point which we want to check if equals to ours.
     * @return true is the lines are equal, false otherwise.
     **/
    public boolean equals(Line other) {
        return this.p1.equals(other.p1) && this.p2.equals(other.p2);
    }

    /**Private method, calculates the slope of the line.
     * @param l the line which we want to calculate the slope for.
     * @return the slope of the line equation
     **/
    private double slopeOfLine(Line l) {
        double m = (l.p1.getY() - l.p2.getY()) / (l.p1.getX() - l.p2.getX());
        return m;
    }

    /**Private method, calculates the constant of the line equation. This helps to calculate the intersection.
     * @param l the line which we want to calculate the constant for.
     * @param m the slope of the line.
     * @return the constant of the line equation.
     **/
    private double consOfLine(Line l, double m) {
        double cons = l.p1.getY() - (m * l.p1.getX());
        return cons;
    }

    /**
     *This method returns the intersection point between one of the lines of the rectangle.
     * @param rec the rectangle we intersect with.
     * @return the intersection point, else it returns null.
     **/
    public Point findTheInter(Rectangle rec) {
        if (this.intersectionWith(rec.getTopLine()) != null) {
            return (this.intersectionWith(rec.getTopLine()));
        }
        if (this.intersectionWith(rec.getBottomLine()) != null) {
            return (this.intersectionWith(rec.getBottomLine()));
        }
        if (this.intersectionWith(rec.getLeftLine()) != null) {
            return (this.intersectionWith(rec.getLeftLine()));
        }
        if (this.intersectionWith(rec.getRightLine()) != null) {
            return (this.intersectionWith(rec.getRightLine()));
        }
        return null;
    }
    /**
     *This method first checks if there's intersection between the trajectory and the rectangle
     * and if so it returns the closest intersection point to the start of the line of the rectangle.
     * @param rec the rectangle we suspect for intersecting with.
     * @return the closest intersection point to the start of the line, null otherwise.
     **/
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        int counter = 0;
        if (this.intersectionWith(rec.getTopLine()) != null) {
            counter++;
        }
        if (this.intersectionWith(rec.getBottomLine()) != null) {
            counter++;
        }
        if  (this.intersectionWith(rec.getLeftLine()) != null) {
            counter++;
        }
        if  (this.intersectionWith(rec.getRightLine()) != null) {
            counter++;
        }
        if (counter == 0) {
            return null;
        } else {
            if (counter == 1) {
                return findTheInter(rec);
            }
            Point point1 = this.intersectionWith(rec.getTopLine());
            Point point2 = this.intersectionWith(rec.getBottomLine());
            Point point3 = this.intersectionWith(rec.getLeftLine());
            Point point4 = this.intersectionWith(rec.getRightLine());
            Point theClosePoint = findTheInter(rec);
            if (point1 != null && theClosePoint.distance(this.p1) > point1.distance(this.p1)) {
                theClosePoint = point1;
            }
            if (point2 != null && theClosePoint.distance(this.p1) > point2.distance(this.p1)) {
                theClosePoint = point2;
            }
            if (point3 != null && theClosePoint.distance(this.p1) > point3.distance(this.p1)) {
                theClosePoint = point3;
            }
            if (point4 != null && theClosePoint.distance(this.p1) > point4.distance(this.p1)) {
                theClosePoint = point4;
            }

            return theClosePoint;
        }
    }
    /**
     * This methoad checks if a point is on a certain line.
     * @param p a given point.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isPointOnLine(Point p) { // isContainingPoint
        if (p == null) {
            return false;
        }
        Line lineInter = new Line(this.p1, p);
        Point interPoint = lineInter.intersectionWith(this);
        return interPoint == null && isAtLine(p);
    }
    /**
     * This method is a calculating method for the isPointOnLine method.
     * it checks if the point is between the start point and end point of the line.
     * @param p a given point.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isAtLine(Point p) {
      return p.getX() >= (Math.min(this.p1.getX(), this.p2.getX()))
              && p.getX() <= (Math.max(this.p1.getX(), this.p2.getX()))
         && p.getY() >= (Math.min(this.p1.getY(), this.p2.getY()))
              && p.getY() <= (Math.max(this.p1.getY(), this.p2.getY()));
    }



}