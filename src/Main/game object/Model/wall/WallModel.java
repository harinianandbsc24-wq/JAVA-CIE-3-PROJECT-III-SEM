package main.java.gameObjects.model.wall;

import java.awt.Point;
import java.awt.Rectangle;

import main.java.gameObjects.controller.BallController;
import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.controller.PlayerController;

/**
 * Stores pure application data for the WallController.
 * Maintains state such as bricks, ball, player, levels, score, and boundaries.
 *
 * @author Emily
 */
public class WallModel {

    private Rectangle area;
    private BrickController[] bricks;
    private BallController ball;

    private int level;
    private int stage = 0;
    private int score = 0;
    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * Constructs a new WallModel.
     *
     * @param drawArea The rectangular area of the wall.
     * @param ballPos  The initial position of the ball.
     */
    public WallModel(Rectangle drawArea, Point ballPos) {
        this.area = new Rectangle(drawArea);
        this.startPoint = new Point(ballPos);
        this.level = 0;
        this.ballCount = 3;
        this.ballLost = false;
    }

    // Getters and Setters for encapsulated fields with defensive copying where appropriate

    public int getBrickCount() {
        return brickCount;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    public Rectangle getArea() {
        return new Rectangle(area);
    }

    public void setArea(Rectangle area) {
        this.area = new Rectangle(area);
    }

    public Point getStartPoint() {
        return new Point(startPoint);
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = new Point(startPoint);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BrickController[] getBricks() {
        return bricks != null ? bricks.clone() : null;
    }

    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks != null ? bricks.clone() : null;
    }

    public BallController getBall() {
        return ball;
    }

    public void setBall(BallController ball) {
        this.ball = ball;
    }

    /**
     * Convenience setters to change ball speed on horizontal and vertical axes.
     */
    public void setBallXSpeed(int speed) {
        if (ball != null) {
            ball.setXSpeed(speed);
        }
    }

    public void setBallYSpeed(int speed) {
        if (ball != null) {
            ball.setYSpeed(speed);
        }
    }

    /**
     * Retrieves the singleton player controller instance.
     *
     * @return PlayerController instance
     */
    public PlayerController getPlayer() {
        return PlayerController.getUniquePlayer();
    }

    /**
     * Initializes or updates the singleton player controller instance.
     *
     * @param ballPoint The ball position point where the paddle may interact
     * @param width     Paddle width
     * @param height    Paddle height
     * @param container Paddle bounding container rectangle
     */
    public void setPlayer(Point ballPoint, int width, int height, Rectangle container) {
        PlayerController.getUniquePlayer(ballPoint, width, height, container);
    }
}

