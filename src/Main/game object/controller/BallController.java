package main.java.gameObjects.controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

import main.java.gameObjects.model.ball.BallModel;
import main.java.gameObjects.view.BallView;

/**
 * Abstract base class for ball controllers.
 * Handles common position, speed, and rendering logic.
 * Subclasses must implement makeBall() to specify shape.
 * 
 * @author Emily
 */
public abstract class BallController {

    private final BallModel ballModel;
    private final BallView ballView;
    private Shape ballFace;

    public BallController(Point2D center, int radius, Color inner, Color border) {
        this.ballModel = new BallModel(center, radius, inner, border);
        this.ballFace = makeBall(center, radius);
        this.ballView = new BallView();
    }

    /**
     * Creates the shape of the ball. Must be implemented by subclasses.
     */
    public abstract Shape makeBall(Point2D center, int radius);

    /**
     * Moves the ball according to its current speed and updates shape.
     */
    public void move() {
        RectangularShape rect = (RectangularShape) ballFace;
        Point2D position = getPosition();
        position.setLocation(position.getX() + getSpeedX(), position.getY() + getSpeedY());
        double width = rect.getWidth();
        double height = rect.getHeight();
        rect.setFrame(position.getX() - width / 2, position.getY() - height / 2, width, height);
        setPoints(width, height);
        ballFace = rect;
    }

    /**
     * Updates the view of the ball on the graphics context.
     */
    public void updateView(BallController ball, Graphics2D g2d) {
        ballView.drawBall(ball, g2d);
    }

    /**
     * Sets the ball's speed.
     */
    public void setSpeed(int x, int y) {
        ballModel.setXSpeed(x);
        ballModel.setYSpeed(y);
    }

    public void setXSpeed(int s) {
        ballModel.setXSpeed(s);
    }

    public void setYSpeed(int s) {
        ballModel.setYSpeed(s);
    }

    /**
     * Reverse horizontal speed.
     */
    public void reverseX() {
        ballModel.setXSpeed(-getSpeedX());
    }

    /**
     * Reverse vertical speed.
     */
    public void reverseY() {
        ballModel.setYSpeed(-getSpeedY());
    }

    public Color getBorderColor() {
        return ballModel.getBorderColor();
    }

    public Color getInnerColor() {
        return ballModel.getInnerColor();
    }

    /**
     * Gets the position of the ball.
     */
    public Point2D getPosition() {
        // Optional: return a defensive copy if external mutation is a concern
        return ballModel.getPosition();
    }

    /**
     * Gets the shape representation of the ball.
     */
    public Shape getBallFace() {
        return ballFace;
    }

    /**
     * Moves the ball to a specific point and updates shape accordingly.
     */
    public void moveTo(Point p) {
        getPosition().setLocation(p);
        RectangularShape rect = (RectangularShape) ballFace;
        double width = rect.getWidth();
        double height = rect.getHeight();
        rect.setFrame(p.getX() - width / 2, p.getY() - height / 2, width, height);
        ballFace = rect;
    }

    /**
     * Updates the directional edge points of the ball shape.
     */
    private void setPoints(double width, double height) {
        ballModel.getUp().setLocation(getPosition().getX(), getPosition().getY() - height / 2);
        ballModel.getDown().setLocation(getPosition().getX(), getPosition().getY() + height / 2);
        ballModel.getLeft().setLocation(getPosition().getX() - width / 2, getPosition().getY());
        ballModel.getRight().setLocation(getPosition().getX() + width / 2, getPosition().getY());
    }

    public int getSpeedX() {
        return ballModel.getSpeedX();
    }

    public int getSpeedY() {
        return ballModel.getSpeedY();
    }

    public Point2D getUp() {
        return ballModel.getUp();
    }

    public Point2D getDown() {
        return ballModel.getDown();
    }

    public Point2D getLeft() {
        return ballModel.getLeft();
    }

    public Point2D getRight() {
        return ballModel.getRight();
    }
}
