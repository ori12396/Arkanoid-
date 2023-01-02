//207632795
package collision;

import ballinfo.Ball;
import ballinfo.Velocity;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import game.Game;
import game.Sprite;
import geomtry.Point;
import geomtry.Rectangle;

import java.awt.Color;

/**
 * this is paddle class-paddle implement.
 */
public class Paddle implements Sprite, Collidable {
    //const.
    static final double PADDLE_WIDTH = 200, PADDLE_HEIGHT = 20, MOVE = 8, REGION = 40;

    //fields.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private double minRange, maxRange, verticalRange;
    private java.awt.Color color;
    // paddle game speed
    private double moveSpeed;

    /**
     * this is the constructor.
     *
     * @param minRange      min horizontal range.
     * @param maxRange      max horizontal range.
     * @param verticalRange vertical range.
     * @param keyboard      keyboard sens.
     * @param color         color.
     */
    public Paddle(double minRange, double maxRange, double verticalRange,
                  biuoop.KeyboardSensor keyboard, java.awt.Color color) {
        this.paddle = new Rectangle(new Point((minRange + maxRange) / 2 - PADDLE_WIDTH / 2,
                verticalRange - PADDLE_HEIGHT),
                PADDLE_WIDTH, PADDLE_HEIGHT);
        this.keyboard = keyboard;
        this.verticalRange = verticalRange;
        this.maxRange = maxRange;
        this.minRange = minRange;
        this.color = color;
        //default paddle speed , can be change easily.
        this.moveSpeed = MOVE;
    }

    /**
     * accessors method.
     *
     * @param speed paddle speed.
     */
    public void setMoveSpeed(double speed) {
        this.moveSpeed = speed;
    }

    /**
     * accessors method.
     *
     * @return paddle move speed
     */
    public double getMoveSpeed() {
        return this.moveSpeed;
    }

    /**
     * accessors method to min range.
     *
     * @return min range
     */
    public double getMinRange() {
        return this.minRange;
    }

    /**
     * accessors method to max range.
     *
     * @return max range
     */
    public double getMaxRange() {
        return this.maxRange;
    }

    /**
     * accessors method to vertical range.
     *
     * @return range.
     */
    public double getVerticalRange() {
        return this.verticalRange;
    }

    /**
     * this method move the paddle to the left.
     */
    public void moveLeft() {
        //check if the paddle try to mvoe out of the screen.
        if (this.paddle.getUpperLeft().getX() - this.moveSpeed < minRange) {
            this.paddle = new Rectangle(new Point(minRange, verticalRange - PADDLE_HEIGHT),
                    PADDLE_WIDTH, PADDLE_HEIGHT);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - this.moveSpeed,
                    verticalRange - PADDLE_HEIGHT), PADDLE_WIDTH, PADDLE_HEIGHT);
        }
    }

    /**
     * this method move the paddle to the right.
     */
    public void moveRight() {
        //check if the paddle try to mvoe out of the screen.
        if (this.paddle.getUpperRight().getX() + this.moveSpeed > maxRange) {
            this.paddle = new Rectangle(new Point(maxRange - PADDLE_WIDTH, verticalRange - PADDLE_HEIGHT),
                    PADDLE_WIDTH, PADDLE_HEIGHT);
        } else {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + this.moveSpeed,
                    verticalRange - PADDLE_HEIGHT), PADDLE_WIDTH, PADDLE_HEIGHT);
        }
    }

    /**
     * this method check if special key are pressed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * this method draw the paddle.
     *
     * @param d the draw
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * this is accessors method.
     *
     * @return collisionRec-paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * this method take care what happened when the  ball hit the paddle.
     *
     * @param collisionPoint  collision point
     * @param currentVelocity current velocity.
     * @param hitter          ball hitter.
     * @return new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //check if the ball hit the upper  part of the rec
        if (this.paddle.getWidthUp().onSegment(collisionPoint)) {
            double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy());
            Velocity v = new Velocity(0, 0);
            double whereItsHit = this.paddle.getUpperLeft().distance(collisionPoint);
            //check which part the ball hit
            if (whereItsHit <= REGION) {
                v = Velocity.fromAngleAndSpeed(300, speed);
            } else if (whereItsHit <= 2 * REGION) {
                v = Velocity.fromAngleAndSpeed(330, speed);
            } else if (whereItsHit <= 3 * REGION) {
                v = Velocity.fromAngleAndSpeed(360, speed);
            } else if (whereItsHit <= 4 * REGION) {
                v = Velocity.fromAngleAndSpeed(30, speed);
            } else if (whereItsHit <= 5 * REGION) {
                v = Velocity.fromAngleAndSpeed(60, speed);
            }
            return v;
        }
        // if the ball touch the left\right side of the paddle.
        Block temp = new Block(this.paddle);
        return temp.hit(hitter, collisionPoint, currentVelocity);
    }


    /**
     * this method add the paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * accessors method.
     *
     * @return color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }


}
