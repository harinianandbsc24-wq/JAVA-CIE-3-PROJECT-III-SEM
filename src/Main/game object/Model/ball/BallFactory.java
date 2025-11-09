package main.java.gameObjects.model.ball;

import java.awt.geom.Point2D;
import main.java.gameObjects.controller.BallController;

public class BallFactory {

    // Enum for ball types (replace String to avoid typos and bugs)
    public enum BallType {
        RUBBER,
        STEEL // Example: add more types here as needed
    }

    public BallFactory() { }

    /**
     * Factory method to create different types of balls.
     * @param ballType The type of the ball (RUBBER, STEEL, etc.)
     * @param center The point of the center of the ball
     * @return A BallController instance for the specified ball type
     * @throws IllegalArgumentException if ballType is null or unsupported
     */
    public BallController makeBallType(BallType ballType, Point2D center) {
        if (ballType == null) {
            throw new IllegalArgumentException("Ball type must not be null");
        }

        switch (ballType) {
            case RUBBER:
                return new RubberBall(center);
            case STEEL:
                return new SteelBall(center);
            // Add more ball types here, e.g., case GLASS: return new GlassBall(center);
            default:
                throw new IllegalArgumentException("Unsupported ball type: " + ballType);
        }
    }
}
