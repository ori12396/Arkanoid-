//207632795
package geomtry;
import java.util.List;
import java.util.ArrayList;

/**
 * @author ori zohar
 * rectangle class.
 */
public class Rectangle {
    //fields.
    private Point upperLeft, upperRight, bottomLeft, bottomRight;
    private double width, height;
    private Line heightLeft, widthUp, heightRight, widthBottom;

    /**
     * this is the constructor.
     *
     * @param upperLeft location
     * @param width     width range
     * @param height    height range.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.bottomRight = new Point(this.upperRight.getX(), this.bottomLeft.getY());
        this.heightLeft = new Line(this.upperLeft, bottomLeft);
        this.widthUp = new Line(this.upperLeft, upperRight);
        this.heightRight = new Line(upperRight, bottomRight);
        this.widthBottom = new Line(bottomLeft, bottomRight);
    }

    /**
     * this method check the intersection points between the line and the rectangle sides.
     *
     * @param line line to check
     * @return all the intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        /*Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomRight = new Point(upperRight.getX(), bottomLeft.getY());
        List<Point> points = new ArrayList<Point>();
        points.add(line.intersectionWith(new Line(this.upperLeft, bottomLeft)));
        //Line heightLeft = new Line(this.upperLeft, bottomLeft);
        points.add(line.intersectionWith(new Line(this.upperLeft, upperRight)));
        //Line widthUp = new Line(this.upperLeft, upperRight);
        points.add(line.intersectionWith(new Line(upperRight, bottomRight)));
        //Line heightRight = new Line(upperRight, bottomRight);
        points.add(line.intersectionWith(new Line(bottomLeft, bottomRight)));
        //Line widthBottom = new Line(bottomLeft, bottomRight);
        return
         */
        List<Point> points = new ArrayList<Point>();
        points.add(line.intersectionWith(this.heightLeft));
        points.add(line.intersectionWith(this.heightRight));
        points.add(line.intersectionWith(this.widthBottom));
        points.add(line.intersectionWith(this.widthUp));
        return points;
    }

    /**
     * accessors method .
     *
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * accessors method.
     *
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * accessors method.
     *
     * @return upperLeft point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * accessors method.
     *
     * @return upperLeft point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * accessors method.
     *
     * @return upperLeft point
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * accessors method.
     *
     * @return upperLeft point
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }

    /**
     * accessors method.
     *
     * @return HeightLeft
     */
    public Line getHeightLeft() {
        return this.heightLeft;
    }

    /**
     * accessors method.
     *
     * @return getWidthUp
     */
    public Line getWidthUp() {
        return this.widthUp;
    }

    /**
     * accessors method.
     *
     * @return HeightRight
     */
    public Line getHeightRight() {
        return this.heightRight;
    }

    /**
     * accessors method.
     *
     * @return WidthBottom
     */
    public Line getWidthBottom() {
        return this.widthBottom;
    }
}
