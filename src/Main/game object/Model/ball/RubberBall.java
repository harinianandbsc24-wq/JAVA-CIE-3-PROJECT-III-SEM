package main.java.gameObjects.model.ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import main.java.gameObjects.controller.BallController;

/**
 * RubberBall is a specific type of BallController representing a rubber ball.
 * It provides default color, size, and drawing shape specific to a rubber ball.
 * 
 * Author: Emily
 */

public class RubberBall extends BallController {

    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * Constructor to create a rubber ball centered at the specified point,
     * applying default radius and colors.
     *
     * @param center The coordinates for the center point of the ball
     */
    public RubberBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }

    /**
     * Creates the visual shape of the rubber ball as a circle.
     *
     * @param center The center point of the ball
     * @param radius The radius of the ball
     * @return A Shape representing the ball (an Ellipse2D circle)
     */
    @Override
    public Shape makeBall(Point2D center, int radius) {
        // Adjust x, y so that the circle is centered correctly
        double x = center.getX() - radius;
        double y = center.getY() - radius;
        double diameter = 2 * radius;

        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
}
