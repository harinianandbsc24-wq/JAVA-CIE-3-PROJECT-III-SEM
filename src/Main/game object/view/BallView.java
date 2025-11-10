package main.java.gameObjects.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import main.java.gameObjects.controller.BallController;

/**
 * Responsible for rendering the ball object on screen.
 * 
 * @author Emily
 */
public class BallView {

    /**
     * Default constructor.
     */
    public BallView() {
    }

    /**
     * Draws the given ball using its shape and colors.
     * 
     * @param ball The ball controller with model and shape data, must not be null.
     * @param g2d  The Graphics2D context to draw on, must not be null.
     */
    public void drawBall(BallController ball, Graphics2D g2d) {
        if (ball == null || g2d == null) {
            throw new IllegalArgumentException("Ball and Graphics2D must not be null.");
        }
        Shape ballShape = ball.getBallFace();

        if (ballShape == null) {
            return; // Nothing to draw
        }

        Color originalColor = g2d.getColor();
        try {
            g2d.setColor(ball.getInnerColor());
            g2d.fill(ballShape);

            g2d.setColor(ball.getBorderColor());
            g2d.draw(ballShape);
        } finally {
            g2d.setColor(originalColor);
        }
    }

}
