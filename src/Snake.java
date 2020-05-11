import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Object representing snake for the game snake.
 *
 * @author Rich Kirk
 */
class Snake {
    /**
     * Points that represent the snake when drawing
     */
    private List<Point> snakePoints;
    /**
     * Booleans determining whether the snake has moved or the snake needs to add a point
     */
    private boolean isStart, elongate;
    /**
     * Useful constants for the creation of the snake object
     */
    private final static int SIZE = 20, START_X = 150, START_Y = 150;
    /**
     * Game board width
     */
    private int width;
    /**
     * Game board height
     */
    private int height;

    /**
     * Integers to specify direction snake is moving in.
     * IF {@code xDirection} =  1: Snake is moving right
     * IF {@code xDirection} = -1: Snake is moving left
     * IF {@code yDirection} = -1: Snake is moving up
     * IF {@code yDirection} =  1: Snake is moving down
     * IF {@code xDirection} OR {@code yDirection} = 0: Snake is not moving in either of those directions
     */
    private int xDirection, yDirection;
    /**
     * Constant representing width of the snake
     */
    private final static int SNAKE_WIDTH = 4;
    /**
     * Constant representing height of the snake
     */
    private final static int SNAKE_HEIGHT = 4;

    /**
     * Snake Constructor
     * @param w
     *      Width of game display
     * @param h
     *      Height of game display
     */
    Snake(int w, int h) {
        width = w;
        height = h;
        snakePoints = new ArrayList<>();
        xDirection = 1;
        yDirection = 0;
        isStart = true;
        elongate = false;
        snakePoints.add(new Point(START_X, START_Y));
        for (int i = 1; i < SIZE; i++) {
            snakePoints.add(new Point(START_X - i * 4, START_Y));
        }
    }



    // Getters ------------------------------------------------------------------

    /**
     *
     * @return horizontal direction of snake movement
     */
    int getXDirection() {
        return xDirection;
    }

    /**
     *
     * @return vertical direction of snake movement
     */
    int getYDirection() {
        return yDirection;
    }

    /**
     *
     * @return x coordinate of snake head
     */
    int getHeadPosX() {
        return snakePoints.get(0).getX();
    }

    /**
     *
     * @return y coordinate of snake head
     */
    int getHeadPosY() {
        return snakePoints.get(0).getY();
    }

    /**
     *
     * @return if the game has been started/received input from user
     */
    boolean isStart() {
        return isStart;
    }

    // Setters ------------------------------------------------------------------

    /**
     *
     * @param xDirection new horizontal direction of snake movement
     */
    void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    /**
     *
     * @param yDirection new vertical direction of snake movement
     */
    void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    /**
     * Set isStart to false as the game received valid user input
     */
    void setIsStart() {
        isStart = false;
    }

    /**
     * Set elongate to true as an apple was eaten
     */
    void setElongate() {
        elongate = true;
    }

    // Misc/Utilities ------------------------------------------------------------

    /**
     * Takes current directions and moves the head to that point,
     * the rest of the points are shifted forward one to match the head
     */
    void move() {
        if(!isStart) {
            Point oldStart = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size() - 1);
            Point newStart = new Point(oldStart.getX() + (xDirection * 4), oldStart.getY() + (yDirection * 4));
            for (int index = snakePoints.size() - 1; index >= 1; index--) {
                snakePoints.set(index, snakePoints.get(index - 1));
            }
            snakePoints.set(0, newStart);
            if(elongate) {
                snakePoints.add(last);
                elongate = false;
            }
        }
    }

    /**
     *
     * @return whether or not this has collided into itself or a wall
     */
    boolean isCollision() {
        int x = this.getHeadPosX();
        int y = this.getHeadPosY();
        if(this.getHeadPosX() < SNAKE_WIDTH || this.getHeadPosY() < SNAKE_HEIGHT * 7 || this.getHeadPosX() > width - SNAKE_WIDTH || this.getHeadPosY() > height - SNAKE_HEIGHT) {
            return true;
        }
        for (int i = 1; i < snakePoints.size() - 1; i++) {
            if(snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }


    void draw(Graphics g) {
        g.setColor(Color.CYAN);
        for(Point point : snakePoints) {
            g.fillRect(point.getX(), point.getY(), SNAKE_WIDTH, SNAKE_HEIGHT);
        }
    }
}
