//207632795
package ballinfo;


import geomtry.Point;

/**
 * @author ori zohar
 * velocity class.
 */
public class Velocity {
    private double dx, dy;

    /**
     * this is the constructor.
     *
     * @param dx x distance
     * @param dy y distance
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this function change the point place.
     *
     * @param p point
     * @return new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * accessors method.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessors method.
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set new dx.
     *
     * @param deltaX -new dx.
     */
    public void setDx(double deltaX) {
        this.dx = deltaX;
    }

    /**
     * set new  dy.
     *
     * @param deltaY -new dy.
     */
    public void setDy(double deltaY) {
        this.dy = deltaY;
    }

    /**
     * calculate velocity by angle and speed.
     *
     * @param angle - the angle that the ball should go
     * @param speed - the speed that the ball have.
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double delx = Math.sin(Math.toRadians(angle)) * speed;
        double dely = -(Math.cos(Math.toRadians(angle)) * speed);
        return new Velocity(delx, dely);
    }
}
