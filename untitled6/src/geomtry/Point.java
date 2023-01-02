//207632795
package geomtry;
/**
 * @author ori zohar.
 * Point class.
 */
public class Point {
    //field - every point have x and y coordination.
    private double x, y;

    /**
     * this is the constructor.
     * the function get coordination and create new point.
     * this is the constructor
     *
     * @param x -x coordination.
     * @param y -y coordination.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function get a point and return the distance between the two.
     *
     * @param other - other point.
     * @return the distance between the two
     */
    public double distance(Point other) {
        double dist = (Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y)));
        return dist;
    }

    /**
     * the  function get a point and chek if the two (current and new) are equals.
     *
     * @param other -other point.
     * @return True for equals False for not.
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        return (Math.abs(this.x - other.x) <= epsilon && Math.abs(this.y - other.y) <= epsilon);
    }

    /**
     * this is accessor method.
     *
     * @return x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * this is accessor method.
     *
     * @return y coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * this function change X coordinate.
     *
     * @param newX new x to set.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * this fucntion change Y coordinate.
     *
     * @param newY new Y to set.
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * this method print the point.
     *
     * @return the point as a string.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
