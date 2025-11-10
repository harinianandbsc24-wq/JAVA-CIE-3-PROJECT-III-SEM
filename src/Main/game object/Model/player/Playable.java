package main.java.gameObjects.model.player;

import java.awt.Point;

/**
 * Interface defining controllable player paddle movements.
 * 
 * @author Emily
 */
public interface Playable {
    
    /**
     * Moves the player's paddle by a default or previously set amount.
     */
    void move();

    /**
     * Moves the player's paddle by the specified amount.
     * 
     * @param distance The amount to move the paddle
     */
    void move(int distance);

    /**
     * Moves the paddle to the left.
     */
    void moveLeft();

    /**
     * Moves the paddle to the right.
     */
    void moveRight();

    /**
     * Stops the paddle's movement.
     */
    void stop();

    /**
     * Moves the paddle to a specific point.
     * 
     * @param point The point to move the paddle to
     */
    void moveTo(Point point);
}
