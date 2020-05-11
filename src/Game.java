import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

/**
 * @author Rich Kirk
 */
public class Game extends JFrame implements Runnable, KeyListener {
    /**
     * Constant for the width of the JFrame and drawing
     */
    private final static int WIDTH = 400;
    /**
     * Constant for the height of the JFrame and drawing
     */
    private final static int HEIGHT = 400;
    /**
     * Declare a thread to run to create ticks perceived as snake motion
     */
    private Thread thread;
    /**
     * Snake object initialization
     */
    private Snake snake = new Snake(WIDTH, HEIGHT);
    /**
     * Apple object initialization
     */
    private Apple apple = new Apple(snake);
    /**
     * Integer to keep track of the high score for a single session
     */
    private static int sessionHighScore = 0;

    /**
     * JFrame extension constructor
     */
    private Game() {
        /*
        JFrame initializations
         */
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setTitle("Snake");
        this.setFocusable(true);
        /*
        Initializations for start of game
         */
        this.addKeyListener(this);
        thread = new Thread(this);
        thread.start();
    }


    public void paint(Graphics g) {
        /*
        Clear the current scene and paint over with black
         */
        g.clearRect(0, 20, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 20, WIDTH, HEIGHT);
        /*
        Draw the objects
         */
        snake.draw(g);
        apple.draw(g);
        apple.snakeCollision();
    }

    @Override
    public void run() {
        while(true) {
            /*
            If the snake hits its own body or hits any of the sides
             */
            if(snake.isCollision()) {
                /*
                Pause the game briefly
                 */
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int score = apple.getScore();
                int option;
                /*
                Display if a new session high score was released and give an option to play again with a dialog box.
                 */
                if(score > sessionHighScore) {
                    sessionHighScore = score;
                    option = JOptionPane.showOptionDialog(this, "Game Over\nNew High Score: "
                                    + apple.getScore() + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                } else {
                    option = JOptionPane.showOptionDialog(this, "Game Over\nScore: "
                                    + apple.getScore() + "\nHigh Score: " + sessionHighScore + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                }

                if(option == 0) /* Restarting the game */ {
                    snake = new Snake(WIDTH, HEIGHT);
                    apple = new Apple(snake);
                } else /* Quitting the loop to quit the game */ {
                    break;
                }
            }
            /*
            Move the snake's array of points and redraw the screen
             */
            snake.move();
            repaint();
            /*
            Add pauses so movement can be perceived
             */
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        Exiting the JFrame
         */
        thread.interrupt();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        new Game();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        /*
        If snake is not moving/game is at the start, only accept input going up, down, or right
         */
        if(snake.isStart()) {
            if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W
                    || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D
                    || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                snake.setIsStart();
            }

        }
        /*
        Up Key Press
         */
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            /*
            If the snake is not moving vertically, set x direction to 0 and y direction to up
             */
            if(snake.getYDirection() == 0) {
                snake.setyDirection(-1);
                snake.setxDirection(0);
            }
        }
        /*
        Right Key Press
         */
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            /*
            If the snake is not moving horizontally, set y direction to 0 and x direction to right
             */
            if(snake.getXDirection() == 0) {
                snake.setxDirection(1);
                snake.setyDirection(0);
            }
        }
        /*
        Left Key Press
         */
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            /*
            If the snake is not moving horizontally, set y direction to 0 and x direction to left
             */
            if(snake.getXDirection() == 0) {
                snake.setxDirection(-1);
                snake.setyDirection(0);
            }
        }
        /*
        Down Key Press
         */
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            /*
            If the snake is not moving vertically, set x direction to 0 and y direction to down
             */
            if(snake.getYDirection() == 0) {
                snake.setyDirection(1);
                snake.setxDirection(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
