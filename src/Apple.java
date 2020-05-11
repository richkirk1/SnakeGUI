import java.awt.*;

/**
 * Object representing the apples.
 *
 * @author Rich Kirk
 */
class Apple {
    /**
     * Point object that stores position for the apple
     */
    private Point point;
    /**
     * Counter for number of times the snake has collided with the apple
     */
    private int score;
    /**
     * Snake object to determine whether a snake "ate" an apple
     */
    private Snake snake;

    /**
     *
     *
     * @param s Snake from the current game
     */
    Apple(Snake s) {
        /*
        Initialize a random point
         */
        point = new Point((int) (Math.random() * 360) + 30, (int) (Math.random() * 360) + 30);
        this.snake = s;
        this.score = 0;
    }
    // Utilities ----------------------------------------------
    void draw(Graphics g) {
        g.setColor(Color.RED);
        /*
         * Constant representing height of the apple
         */
        int APPLE_HEIGHT = 4;
        /*
         * Constant representing width of the apple
         */
        int APPLE_WIDTH = 4;
        g.fillRect(point.getX(), point.getY(), APPLE_WIDTH, APPLE_HEIGHT);
    }

    /**
     * Creates new random positions for the apple when a collision occurs
     */
    private void changePosition() {
        this.point.setX((int) (Math.random() * 360) + 30);
        this.point.setY((int) (Math.random() * 360) + 30);
    }

    /**
     * Determines whether the snake has "eaten" the apple
     */
    void snakeCollision() {
        /*
        Get coordinates of the snake head
         */
        int snakeX = snake.getHeadPosX() + 2;
        int snakeY = snake.getHeadPosY() + 2;

        /*
        Check for snake/apple overlap
         */
        if(snakeX >= point.getX() - 1 && snakeX <= point.getX() + 7 && snakeY >= point.getY() - 1 && snakeY <= point.getY() + 7) {
            /*
            Create a new apple point, increment score, and make the snake longer
             */
            changePosition();
            score++;
            snake.setElongate();
        }
    }
    // Getters --------------------------------------

    /**
     *
     * @return score of apples "eaten" by snake
     */
    int getScore() {
        return score;
    }
}
