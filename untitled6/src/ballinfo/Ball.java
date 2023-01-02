//207632795
package ballinfo;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.GameEnvironment;
import collision.Paddle;
import game.Game;
import game.Sprite;
import geomtry.Line;
import geomtry.Point;
import hit.HitListener;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ori zohar
 * ball class-this class implement the ball.
 */
public class Ball implements Sprite {

    //fields.
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int minHorizontalRange, minVerticalRange, maxHorizontalRange, maxVerticalRange;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    private List<HitListener> listeners;

    /**
     * the function create new ball-this is the constructor.
     *
     * @param center the center point of the ball.
     * @param r      the radius.
     * @param color  the color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.listeners = new ArrayList<HitListener>();
    }

    /**
     * accessors method to set a paddle.
     *
     * @param gamePaddle paddle to set.
     */
    public void setPaddle(Paddle gamePaddle) {
        this.paddle = gamePaddle;
    }

    /**
     * the function create new ball-this is the second constructor.
     *
     * @param x     x coordinate of the center
     * @param y     y coordinate of the center
     * @param r     radius size
     * @param color ball color
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * accessors method.
     *
     * @param centerP center point.
     */
    public void setCenter(Point centerP) {
        this.center = centerP;
    }

    /**
     * this is accessor method to the center x coordinate.
     *
     * @return return the x coordinate of the center (as int).
     */
    public double getX() {
        return ((int) this.center.getX());
    }

    /**
     * this is accessor method to the center y coordinate.
     *
     * @return return the y coordinate of the center (as int).
     */
    public double getY() {
        return ((int) this.center.getY());
    }

    /**
     * this is accessor method to the ball radius.
     *
     * @return return the ball radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * this is accessor method to ball color.
     *
     * @return return the ball colors.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessors method.
     *
     * @param x x to set.
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * accessors method.
     *
     * @param y y to set
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * accessor method.
     *
     * @return radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * this function draw the ball on the screen.
     *
     * @param surface the draw.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
    }

    /**
     * set the ball speed- accessors method.
     *
     * @param v -ball velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the ball speed by dx and dy parameters.
     *
     * @param dx dx field of velocity
     * @param dy dy field of velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * accessors method.
     *
     * @return ball velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this method check if the ball close to hit the paddle side while paddle moving. and take care of it.
     *
     * @return 0-if not,1-from the left, 2-from the right.
     */
    public int paddleBallInteraction() {
        //check if the paddle gonna "override" the ball (can happened only if paddle Move(speed) faster than the ball).
        if (this.getY() >= this.paddle.getCollisionRectangle().getUpperRight().getY()
                && this.getX() >= this.paddle.getCollisionRectangle().getUpperLeft().getX()
                && this.getX() <= this.paddle.getCollisionRectangle().getUpperRight().getX()) {
            double midPaddle = this.paddle.getCollisionRectangle().getWidthUp().middle().getX();
            //if it gonna happened from the left
            if (this.getX() <= midPaddle) {
                return 1;
                //if it gonna happened from the right.
            } else if (getX() > midPaddle) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * this function use to move the ball on the scren.
     */
    public void moveOneStep() {
        //check if there paddle not gonna override  the ball
        if (paddleBallInteraction() == 0) {
            Line test = new Line(this.center, this.velocity.applyToPoint(this.center));
            CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(test);
            //flag tell me if there gonna be collision on the side of the paddle while he moves.
            this.center = this.velocity.applyToPoint(this.center);
            // check if there is collision point in the way
            if (collisionInfo != null) {
                //check from where the ball hit the block
                if (this.velocity.getDx() < 0) {
                    this.center.setX(collisionInfo.collisionPoint().getX() + 0.0001);
                }
                //check from where the ball hit the block
                if (this.velocity.getDy() < 0) {
                    this.center.setY(collisionInfo.collisionPoint().getY() + 0.0001);
                }
                //check from where the ball hit the block
                if (this.velocity.getDx() > 0) {
                    this.center.setX(collisionInfo.collisionPoint().getX() - 0.0001);
                }
                //check from where the ball hit the block
                if (this.velocity.getDy() > 0) {
                    this.center.setY(collisionInfo.collisionPoint().getY() - 0.0001);
                }
                //collisionInfo.collisionObject().getCollisionRectangle().getOutOfRec(this);
                this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                        this.velocity);
            } //if the paddle gonna ovveride the ball.
        } else if (paddleBallInteraction() == 1) {
            this.velocity = this.paddle.hit(this, this.paddle.getCollisionRectangle().getUpperLeft(),
                    this.velocity);
            Velocity temp = new Velocity(this.velocity.getDx() - this.paddle.getMoveSpeed(), this.velocity.getDy());
            this.center = temp.applyToPoint(this.center);
            //check if the ball go out the left boundary.
            if (this.getX() <= this.paddle.getMinRange()) {
                // && this.getY() >= this.paddle.getCollisionRectangle().getUpperLeft().getY()) {
                this.center = new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX(),
                        this.paddle.getCollisionRectangle().getUpperLeft().getY() - this.radius);
            } //check if the paddle gonna override the ball.
        } else if (paddleBallInteraction() == 2) {
            this.velocity = this.paddle.hit(this, this.paddle.getCollisionRectangle().getUpperRight(),
                    this.velocity);
            Velocity temp = new Velocity(this.velocity.getDx() + this.paddle.getMoveSpeed(), this.velocity.getDy());
            this.center = temp.applyToPoint(this.center);
            //check if the ball go out the right boundary.
            if (this.getX() >= this.paddle.getMaxRange()) {
                // && this.getY() >= this.paddle.getCollisionRectangle().getUpperRight().getY()) {
                this.center = new Point(this.paddle.getCollisionRectangle().getUpperRight().getX(),
                        this.paddle.getCollisionRectangle().getUpperRight().getY() - this.radius);
            }

        }
    }

    /**
     * this is accessors method that set new maxHorizontalRange.
     *
     * @param range new maxHorizontalRange.
     */
    public void setMaxHorizontalRange(int range) {
        this.maxHorizontalRange = range;
    }

    /**
     * this is accessors method that set new maxVerticalRange.
     *
     * @param range new maxVerticalRange.
     */
    public void setMaxVerticalRange(int range) {
        this.maxVerticalRange = range;
    }

    /**
     * this is accessors method that set new minVerticalRange.
     *
     * @param range new minVerticalRange.
     */
    public void setMinVerticalRange(int range) {
        this.minVerticalRange = range;
    }

    /**
     * this is accessors method that set new minHorizontalRange.
     *
     * @param range new minHorizontalRange.
     */
    public void setMinHorizontalRange(int range) {
        this.minHorizontalRange = range;
    }

    /**
     * this is accessors method that set new ranges to the ball.
     *
     * @param minHorRange  min horizontal range
     * @param minVertRange min vertical range
     * @param maxHorRange  max horizontal range
     * @param maxVertRange max vertical range
     */
    public void setAllRanges(int minHorRange, int minVertRange, int maxHorRange,
                             int maxVertRange) {
        this.minHorizontalRange = minHorRange;
        this.minVerticalRange = minVertRange;
        this.maxHorizontalRange = maxHorRange;
        this.maxVerticalRange = maxVertRange;
    }

    /**
     * accessors method.
     *
     * @param theGameEnvironment game environment.
     */
    public void setGameEnvironment(GameEnvironment theGameEnvironment) {
        this.gameEnvironment = theGameEnvironment;
    }

    /**
     * time passed method- move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * this method add the ball to the game.
     *
     * @param g game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        this.setGameEnvironment(g.getEnvironment());
        this.setPaddle(g.getGamePaddle());
    }

    /**
     * this method remove the ball from the game.
     *
     * @param g the game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

}
