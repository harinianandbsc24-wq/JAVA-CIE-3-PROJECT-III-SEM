package main.java.gameObjects.model.ball;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This class stores the pure application data for BallController class
 * Improved with encapsulation, consistent boundary calculation, and movement support.
 * 
 * @author Emily
 */
public class BallModel {

    private Point2D center;
    private final int radius;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private int speedX;
    private int speedY;

    private final Color border;
    private final Color inner;

    /**
     * Constructor of Ball class
     * 
     * @param center The coordinates of the center of the ball
     * @param radius The radius of the ball
     * @param inner  The color code for the inner color of the ball object
     * @param border The color code for the border color of the ball object
     */
    public BallModel(Point2D center, int radius, Color inner, Color border) {
        this.center = center;
        this.radius = radius;
        this.inner = inner;
        this.border = border;

        calculateBoundaryPoints();

        speedX = 0;
        speedY = 0;
    }

    private void calculateBoundaryPoints() {
        up = new Point2D.Double(center.getX(), center.getY() - radius);
        down = new Point2D.Double(center.getX(), center.getY() + radius);
        left = new Point2D.Double(center.getX() - radius, center.getY());
        right = new Point2D.Double(center.getX() + radius, center.getY());
    }

    public void setSpeed(int x, int y) {
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s) {
        speedX = s;
    }

    public void setYSpeed(int s) {
        speedY = s;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public Point2D getPosition() {
        return center;
    }

    public void move() {
        center.setLocation(center.getX() + speedX, center.getY() + speedY);
        calculateBoundaryPoints();
    }

    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }

    public int getRadius() {
        return radius;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }

}
