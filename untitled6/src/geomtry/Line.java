//207632795
package geomtry;
import java.util.List;

/**
 * @author ori zohar.
 * Line class.
 */
public class Line {
    /**
     * main method - use to run the class check that we aked for.
     *
     * @param args string of arguments.
     */
    public static void main(String[] args) {
        Point p1 = new Point(0, 0), p2 = new Point(0, 0), p3 = new Point(0, 0), p4 = new Point(0, 0);
        Line l1 = new Line(0, 0, 10, 10);
        Line l2 = new Line(1, 1, 1, 1);
        Point ps = l1.intersectionWith(l2);
        System.out.println(l1.isIntersecting(l2));
        System.out.print(ps);
    }

    /*
    fields- every line consist of two  point-start and end, boolean slope - true if have slope , else false(like x=n)
    and isPoint-true if the line are one point, else false.
     */
    private Point start, end;
    private boolean slope, isPoint;

    /**
     * the function get two point and create new line.
     * this is the constructor
     *
     * @param start the point that the line start from
     * @param end   the poin where the line end.
     */
    public Line(Point start, Point end) {
        //check which y coordinate is lower, and putt it as the start point.
        this.start = start;
        this.end = end;
        //check if is one point line.
        if (this.start.equals(this.end)) {
            isPoint = true;
            slope = false;
        } else {
            isPoint = false;
            //check if the line have slope.
            slope = this.start.getX() != this.end.getX();
        }
    }

    /**
     * the function get four coordination , create two points from them and create new line from this two points.
     * this is the second constructor
     *
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * the function caculated the line legnth.
     *
     * @return line length.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * this is accessor method.
     *
     * @return return start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * this is accessor method.
     *
     * @return return end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * the method check if the point is on the line (use the triangle inequality).
     *
     * @param point point to check
     * @return true-point on the line , false-else.
     */
    public boolean onSegment(Point point) {
        if (this.start.equals(this.end)) {
            return false;
        }
        double epsilon = Math.pow(10, -7);
        return Math.abs((this.start.distance(point) + this.end.distance(point)) - this.start.distance(this.end))
                < epsilon;
    }

    /**
     * this function check if other segment is sub to this.
     *
     * @param other other line
     * @return true-if sub, else - false.
     */
    public boolean subSegment(Line other) {
        return (this.onSegment(other.start) && this.onSegment(other.end) && !this.isPoint && !other.isPoint);
    }

    /**
     * the method return the intersection point between two lines.
     *
     * @param other -second  line
     * @return the intersection point , or null if they not intersect.
     */
    public Point intersectionWith(Line other) {
        double thisSlope = 0, otherSlope = 0, thisB = 0, otherB = 0, intersectX, intersectY;
        Point intersectionPoint;
        //check if the two line's are one point lines.
        if (this.isPoint && other.isPoint) {
            // check if it same point.
            if (this.start.equals(other.start)) {
                return this.start;
            }
        }
        //if the same lines.
        if ((this.equals(other) || this.subSegment(other) || other.subSegment(this))) {
            return null;
        }
        //check if one of the line's is an one point line.
        if (this.isPoint && !other.isPoint) {
            if (other.onSegment(this.start)) {
                return this.start;
            }
        }
        //check if one of the line's is one point line.
        if (!this.isPoint && other.isPoint) {
            if (this.onSegment(other.start)) {
                return other.start;
            }
        }
        //check if slope exist.
        if (this.slope) {
            thisSlope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            thisB = this.start.getY() - (thisSlope * this.start.getX());
        }
        //check if slope exist.
        if (other.slope) {
            otherSlope = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            otherB = other.start.getY() - (otherSlope * other.start.getX());
        }
        //check if both line's have not slope.
        if (!this.isPoint && !other.isPoint && (!this.slope && !other.slope || thisSlope - otherSlope == 0)) {
            //check if the lines have one(!) intersect point.
            if (this.start.equals(other.end) || this.start.equals(other.start)) {
                return this.start;
            }
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }
        }
        //check if other line are non slope line.
        if (!this.isPoint && !other.isPoint && this.slope && !other.slope) {
            intersectX = other.start.getX();
            intersectY = thisSlope * intersectX + thisB;
            intersectionPoint = new Point(intersectX, intersectY);
            if (this.onSegment(intersectionPoint) && other.onSegment(intersectionPoint)) {
                return intersectionPoint;
            }
        }
        // check if this line are  non slope line.
        if (!this.isPoint && !other.isPoint && !this.slope && other.slope) {
            intersectX = this.start.getX();
            intersectY = otherSlope * intersectX + otherB;
            intersectionPoint = new Point(intersectX, intersectY);
            if (this.onSegment(intersectionPoint) && other.onSegment(intersectionPoint)) {
                return intersectionPoint;
            }
        }
        // check if both lines are 'normal' lines. (y=ax+b)
        if (!this.isPoint && !other.isPoint && this.slope && other.slope) {
            intersectX = (otherB - thisB) / (thisSlope - otherSlope);
            intersectY = thisSlope * intersectX + thisB;
            intersectionPoint = new Point(intersectX, intersectY);
            if (this.onSegment(intersectionPoint) && other.onSegment(intersectionPoint)) {
                return intersectionPoint;
            }
        }
        return null;
    }

    /**
     * the function check if two lines are intersecting or not.
     *
     * @param other 2nd line.
     * @return true-intersecting, false-else.
     */
    public boolean isIntersecting(Line other) {
        // check if theres a intersect point.
        if (this.intersectionWith(other) != null) {
            return true;
        }
        //check if the lines are intersecting or not, and return the boolean answer.
        return other.onSegment(this.start) || other.onSegment(this.end) || this.onSegment(other.start)
                || this.onSegment(other.end);
    }

    /**
     * the function get two lines and check if they  same.
     *
     * @param other - line.
     * @return true-if lines are equals, false - else.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * the function return the middle point of the line.
     *
     * @return middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * this method get rectangle and find the closet intersection point with the line, to the start point of the line.
     *
     * @param rect rectangle.
     * @return null if no intersection point, the closet point if there is.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        double minDistance = this.start.distance(this.end);
        int index = 0;
        /*
        run all over points list.
         */
        for (int i = 0; i < points.size(); ++i) {
            //check for intersection point.
            if (points.get(i) != null) {
                //check which is the closet.
                if (Math.abs(this.start.distance(points.get(i))) <= minDistance) {
                    minDistance = Math.abs(this.start.distance(points.get(i)));
                    index = i;
                }
            }
        }
        return points.get(index);
    }
}

