//207632795
package collision;

import geomtry.Line;
import geomtry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ori zohar
 * this class for the game environment.
 */
public class GameEnvironment {
    //const
    static final double MIN = 999999;

    //fields.
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * this method add collidable item to the list.
     *
     * @param c collidable item
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * this method return the closest collision to line.start.
     *
     * @param trajectory line
     * @return closest collision point to the start of the line.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double min = MIN;
        CollisionInfo collisionInfo = null;
        /*
        run all over the collidable list.
         */
        for (int i = 0; i < this.collidables.size(); ++i) {
            Point p = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            //if there is a collision point.
            if (p != null) {
                //check for the minimum value.
                if (min > trajectory.start().distance(p)) {
                    min = trajectory.start().distance(p);
                    collisionInfo = new CollisionInfo(p, this.collidables.get(i));
                }
            }
        }
        return collisionInfo;
    }

    /**
     * this method delete item from the list.
     *
     * @param c item to delete.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
