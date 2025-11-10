package main.java.gameObjects.model.crack;

/**
 * Enum representing the possible directions of cracks.
 * Includes specific directions and orientations for convenience.
 * 
 * @author Emily
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
     * Returns the opposite crack direction where applicable.
     * For example, LEFT's opposite is RIGHT, UP's opposite is DOWN,
     * VERTICAL's opposite is HORIZONTAL, etc.
     * 
     * @return The opposite CrackDirection, or null if no opposite exists.
     */
    public CrackDirection opposite() {
        switch (this) {
            case LEFT:      return RIGHT;
            case RIGHT:     return LEFT;
            case UP:        return DOWN;
            case DOWN:      return UP;
            case VERTICAL:  return HORIZONTAL;
            case HORIZONTAL:return VERTICAL;
            default:        return null;
        }
    }

    /**
     * Checks if this crack direction is considered vertical.
     * Includes UP, DOWN, and VERTICAL as vertical.
     * 
     * @return True if vertical-oriented; false otherwise.
     */
    public boolean isVertical() {
        return this == UP || this == DOWN || this == VERTICAL;
    }

    /**
     * Checks if this crack direction is considered horizontal.
     * Includes LEFT, RIGHT, and HORIZONTAL as horizontal.
     * 
     * @return True if horizontal-oriented; false otherwise.
     */
    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT || this == HORIZONTAL;
    }
}

