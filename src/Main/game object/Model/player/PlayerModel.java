package main.java.gameObjects.model.player;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;

/**
 * Stores the pure data for the player paddle controller.
 * It includes the ball contact point on the paddle and the movement boundaries.
 * 
 * @author Emily
 */
public class PlayerModel {

    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private Point ballPoint;
    private int minBoundary;
    private int maxBoundary;

    /**
     * Constructs a PlayerModel instance.
     * 
     * @param ballPoint The point on the paddle where the ball contacts
     * @param paddleWidth The width of the player's paddle
     * @param container The rectangular boundary container for the paddle movement
     */
    public PlayerModel(Point ballPoint, int paddleWidth, Rectangle container) {
        this.ballPoint = new Point(ballPoint); // Defensive copy to avoid external mutation
        this.minBoundary = container.x + (paddleWidth / 2);
        this.maxBoundary = container.x + container.width - (paddleWidth / 2);
    }

    public Point getBallPoint() {
        return new Point(ballPoint); // Defensive copy for immutability
    }

    public void setBallPoint(Point ballPoint) {
        this.ballPoint = new Point(ballPoint); // Defensive copy
    }

    public int getMinBoundary() {
        return minBoundary;
    }

    public void setMinBoundary(int minBoundary) {
        this.minBoundary = minBoundary;
    }

    public int getMaxBoundary() {
        return maxBoundary;
    }

    public void setMaxBoundary(int maxBoundary) {
        this.maxBoundary = maxBoundary;
    }
}

