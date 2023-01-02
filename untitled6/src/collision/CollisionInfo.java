//207632795
package collision;
import geomtry.Point;

/**
 * @author ori zohar
 * this class save the current collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * this is the constructor.
     *
     * @param collisionPoint point
     * @param collisionObject object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * accessors method.
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * accessors method.
     * @return collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
