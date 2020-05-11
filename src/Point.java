/**
 * @author Rich Kirk
 */
class Point {
    private int x, y;
    // Constructors -----------------------------
    /**
     *
      * @param x
     * @param y
     */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Getters -----------------------------
    /**
     *
     * @return x position
     */
    int getX() {
        return x;
    }

    /**
     *
     * @return y position
     */
    int getY() {
        return y;
    }
    // Setters -----------------------------

    /**
     *
     * @param y new y position
     */
    void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param x new x position
     */
    void setX(int x) {
        this.x = x;
    }
}
