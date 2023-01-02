//207632795

package game;

import ballinfo.Ball;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import collision.Block;
import collision.Collidable;
import collision.GameEnvironment;
import collision.Paddle;
import geomtry.Point;
import geomtry.Rectangle;
import hit.BallRemover;
import hit.BlockRemover;
import hit.Counter;
import hit.ScoreTrackingListener;
import hit.ScoreIndicator;

import java.awt.Color;


/**
 * @author ori zohar
 * game class.
 */
public class Game {

    //const to replace magic numbers.
    static final int ROWS = 6, COL = 12, X = 280, Y = 100,
            WIDTH = 40, HEIGHT = 20, MIN_HOR = 0, MIN_VERT = 0, MAX_HOR = 800, VERTICAL = 600, WIN = 100;
    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle gamePaddle;
    private GUI gameGui;
    private Counter remainingBlocks, remainingBalls, score;


    /**
     * this is the constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gamePaddle = null;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }

    /**
     * accessors method to add collidable item.
     *
     * @param c collidable item.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * accessors method to add sprite item.
     *
     * @param s sprite item.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * accessors method to environment.
     *
     * @return environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * accessors method to gamePaddle.
     *
     * @return gamePaddle
     */
    public Paddle getGamePaddle() {
        return this.gamePaddle;
    }

    /**
     * this method create the ball\s , and add them to the environment.
     */
    public void ballCreate() {
        Ball ball = new Ball(50, 50, 5, Color.black);
        ball.addToGame(this);
        Ball ball2 = new Ball(150, 100, 5, Color.red);
        ball2.addToGame(this);
        Ball ball3 = new Ball(160, 100, 5, Color.yellow);
        ball3.addToGame(this);
        ball.setVelocity(9, 9);
        ball2.setVelocity(9, 9);
        ball3.setVelocity(9, 9);
        ball.setAllRanges(MIN_HOR, MIN_VERT, MAX_HOR, VERTICAL);
        ball2.setAllRanges(MIN_HOR, MIN_VERT, MAX_HOR, VERTICAL);
        ball3.setAllRanges(MIN_HOR, MIN_VERT, MAX_HOR, VERTICAL);
        ball.setGameEnvironment(this.environment);
        ball2.setGameEnvironment(this.environment);
        ball3.setGameEnvironment(this.environment);
        this.remainingBalls.increase(3);
    }

    /**
     * this function define the boundaries.
     * @param ballRemover listener the the DEATH REGION .
     */
    public void boundriesCrate(BallRemover ballRemover) {
        Block topBlock = new Block(new Rectangle(new Point(MIN_HOR + HEIGHT, MIN_VERT), MAX_HOR - WIDTH,
                2 * HEIGHT));
        topBlock.setColor(Color.gray);
        topBlock.addToGame(this);
        Block deathRegion = new Block(new Rectangle(new Point(MIN_HOR + HEIGHT, VERTICAL - 2 * HEIGHT),
                MAX_HOR - WIDTH, 2 * HEIGHT));
        deathRegion.setColor(Color.gray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        Block rightBlock = new Block(new Rectangle(new Point(MIN_HOR, MIN_VERT), WIDTH, VERTICAL));
        rightBlock.setColor(Color.gray);
        rightBlock.addToGame(this);
        Block leftBlock = new Block(new Rectangle(new Point(MAX_HOR - WIDTH, MIN_VERT), WIDTH, VERTICAL));
        leftBlock.setColor(Color.gray);
        leftBlock.addToGame(this);
    }

    /**
     * this method create the background of the screen.
     */
    public void createBackground() {
        Block backGround = new Block(new Rectangle(new Point(MIN_HOR, MIN_VERT), MAX_HOR, VERTICAL));
        backGround.setColor(Color.blue);
        backGround.addToGame(this);
    }

    /**
     * this function create the blocks.
     * @param remover listener to the blocks.
     * @param scoreListener listener to the score.
     */
    public void createTheBlocks(BlockRemover remover, ScoreTrackingListener scoreListener) {
        for (int i = 1; i <= ROWS; ++i) {
            for (int j = i; j < COL; ++j) {
                Block block = new Block(new Rectangle(new Point(X + j * WIDTH, Y + i * HEIGHT), WIDTH, HEIGHT));
                block.addToGame(this);
                block.addHitListener(remover);
                block.addHitListener(scoreListener);
                this.remainingBlocks.increase(1);
                //block.setColor(Color.green);
                if (i == 1) {
                    block.setColor(Color.gray);
                } else if (i == 2) {
                    block.setColor(Color.red);
                } else if (i == 3) {
                    block.setColor(Color.yellow);
                } else if (i == 4) {
                    block.setColor(Color.blue);
                } else if (i == 5) {
                    block.setColor(Color.pink);
                } else if (i == 6) {
                    block.setColor(Color.green);
                }
            }
        }
    }

    /**
     * This method initialize the game.
     */
    public void initialize() {
        this.gameGui = new GUI("Game Window", MAX_HOR, VERTICAL);
        biuoop.KeyboardSensor keyboard = gameGui.getKeyboardSensor();
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        Rectangle rectangle = new Rectangle(new Point(X, MIN_VERT), X, HEIGHT);
        ScoreIndicator scoreIndicator = new ScoreIndicator(rectangle, this.score);
        BlockRemover remover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        createBackground();
        //PrintingHitListener p = new PrintingHitListener();
        this.gamePaddle = new Paddle(MIN_HOR + WIDTH, MAX_HOR - WIDTH,
                VERTICAL - 2 * HEIGHT, keyboard, Color.cyan);
        this.sprites.addSprite(this.gamePaddle);
        this.environment.addCollidable(this.gamePaddle);
        ballCreate();
        boundriesCrate(ballRemover);
        createTheBlocks(remover, scoreListener);
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * this method run the game.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        /*
        run until theres no blocks lefted.
         */
        while (this.remainingBlocks.getValue() != 0 && this.remainingBalls.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gameGui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gameGui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(WIN);
            }
        }
        //System.out.println(this.score.getValue());
        gameGui.close();
    }

    /**
     * this method remove item from the Collidables list.
     *
     * @param c item to delete.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this method remove item from the spirits list.
     *
     * @param s item to delete.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeItem(s);
    }
}
