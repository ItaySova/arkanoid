package geometry;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

/**
 * @author Itay Sova
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    private Line bottomLine;
    private Line topLine;
    private Line leftLine;
    private Line rightLine;
    private List<Point> listOfPoints = new LinkedList<Point>();
    private Rectangle collisionRec;
    private Color colorOfFrame; //The color of the frame of the rectangle.
    private Color colorOfRectangle; //The color that fills the rectangle.
    private boolean amIPaddle = false;
    private boolean amIBorder = false;
   /// private boolean amIScore = false;
    //private  boolean inFront = false;

    /**
     * This method creates a new rectangle by a upperleft point, width and height.
     * @param upperLeft the point in the upperleft of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
       this.bottomLine = new Line(upperLeft.getX(), upperLeft.getY() + height,
               upperLeft.getX() + width, upperLeft.getY() + height);
        this.topLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(),
                upperLeft.getY() + height);
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.collisionRec = this;
    }

    /**
     * This method construct a rectangle by x,y axis for upperleft, width and height of the rectangle.
     * @param x the x axis of the upperleft point of the rectangle.
     * @param y the y axis of the upperleft point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.bottomLine = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.topLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(),
                upperLeft.getY() + height);
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.collisionRec = this;
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * This method returns a list of intersection points.
     * @param line the line of intersection with the rectangle.
     * @return a list of intersection point.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point interPoint = line.closestIntersectionToStartOfLine(this);
        if (interPoint != null) {
            collisionRec = this;
        }
        listOfPoints.add(interPoint);
        return listOfPoints;
    }
    /**
     * Accessor method which returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Accessor method which returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Accessor method which returns the color of the frame of the rectangle.
     * @return the frame color of the rectangle.
     */
    public Color getColorOfFrame() {
        return this.colorOfFrame;
    }
    /**
     * Accessor method which returns the color of the fill of the rectangle.
     * @return the fill color of the rectangle.
     */
    public Color getColorOfRectangle() {
        return this.colorOfRectangle;
    }

    /**
     * Accessor method which returns the upperleft point of the rectangle.
     * @return the upperleft point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Accessor method which returns the rectangle for collision check.
     * @return the rectangle.
     */
    public Rectangle getCollisionRec() {
        return collisionRec;
    }
    /**
     * Accessor method which returns the bottom line of the rectangle.
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }
    /**
     * Accessor method which returns the top line of the rectangle.
     * @return the top line of the rectangle.
     */
    public Line getTopLine() {
        return this.topLine;
    }
    /**
     * Accessor method which returns the left line of the rectangle.
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * Accessor method which returns the right line of the rectangle.
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * This method sets the color of the frame of the rectangle.
     * @param frameColor the color of the frame of the rectangle.
     */
    public void setColorOfFrame(Color frameColor) {
        this.colorOfFrame = frameColor;
    }
    /**
     * This method sets the color of the fill of the rectangle.
     * @param rectangleColor the color of the fill of the rectangle.
     */
    public void setColorOfRectangle(Color rectangleColor) {
        this.colorOfRectangle = rectangleColor;
    }
    /**
     * This method sets the upperleft point of the rectangle.
     * @param upLeft the upperleft point of the rectangle.
     */
    public void setUpperLeft(Point upLeft) {
        this.upperLeft = upLeft;
        this.collisionRec.upperLeft = upLeft;
    }
    /**
     * This method returns if the point is on the rectangle.
     * @param p we want to check.
     * @return true if the point is on the rectangle, false otherwise.
     */
    public boolean isPointInRec(Point p) {
        return p.getX() >= this.getUpperLeft().getX()
                && p.getX() <= this.getUpperLeft().getX() + this.getWidth()
                && p.getY() >= this.getUpperLeft().getY()
                && p.getY() <= this.getUpperLeft().getY() + this.getHeight();
    }

    /**
     * This method returns if the rectangle is paddle.
     * @return true if it is, false otherwise.
     */
    public boolean isPaddle() {
        return this.amIPaddle;
    }
    /**
     * This method sets if the rectangle is paddle.
     * @param amIPad true if it is peddle, false otherwise.
     **/
   public void setAmIPaddle(boolean amIPad) {
        this.amIPaddle = amIPad;
    }
    /**
     * This method returns if the rectangle is border.
     * @return true if it is, false otherwise.
     */
    public boolean isAmIBorder() {
        return amIBorder;
    }
    /**
     * This method sets if the rectangle is border.
     * @param isBorder true if it is border, false otherwise.
     **/
    public void setAmIBorder(boolean isBorder) {
        this.amIBorder = isBorder;
    }
}