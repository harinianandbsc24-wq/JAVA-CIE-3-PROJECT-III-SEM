package main.java.gameObjects.model.crack;

/**
 * Enum representing the different possible crack directions for bricks.
 * This can represent both specific directions (LEFT, RIGHT, UP, DOWN)
 * as well as orientations (VERTICAL, HORIZONTAL).
 * 
 * Author: Emily
 */
public enum CrackDirection {

    /** Left crack direction */
    LEFT,

    /** Right crack direction */
    RIGHT,

    /** Up crack direction */
    UP,

    /** Down crack direction */
    DOWN,

    /** Vertical crack direction */
    VERTICAL,

    /** Horizontal crack direction */
    HORIZONTAL;

    /**
     * Returns the opposite crack direction if applicable.
     * For example, LEFT's opposite is RIGHT, UP's opposite is DOWN,
     * VERTICAL's opposite is HORIZONTAL, etc.
     * 
     * @return opposite CrackDirection or null if no logical opposite
     */
    public CrackDirection opposite() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case VERTICAL:
                return HORIZONTAL;
            case HORIZONTAL:
                return VERTICAL;
            default:
                return null;
        }
    }

    /**
     * Checks if this CrackDirection is a vertical orientation.
     * 
     * @return true if VERTICAL, UP or DOWN; false otherwise
     */
    public boolean isVertical() {
        return this == VERTICAL || this == UP || this == DOWN;
    }

    /**
     * Checks if this CrackDirection is a horizontal orientation.
     * 
     * @return true if HORIZONTAL, LEFT or RIGHT; false otherwise
     */
    public boolean isHorizontal() {
        return this == HORIZONTAL || this == LEFT || this == RIGHT;
    }
}

