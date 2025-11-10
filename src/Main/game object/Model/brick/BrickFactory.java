package main.java.gameObjects.model.brick;

import java.awt.Dimension;
import java.awt.Point;

import main.java.gameObjects.controller.BrickController;

/**
 * Factory class to create Brick objects following the Factory design pattern.
 * It supports multiple Brick types for extensibility.
 * 
 * @author Emily
 */
public class BrickFactory {

    /**
     * Default constructor
     */
    public BrickFactory() {
        // No special initialization needed currently
    }

    /**
     * Factory method to create brick objects of specified type, position, and size.
     *
     * @param point The top-left point of the brick
     * @param size  The dimension (width and height) of the brick
     * @param type  The BrickType enum specifying the kind of brick
     * @return A BrickController instance of the specified type
     * @throws IllegalArgumentException if an unknown BrickType is specified
     */
    public BrickController makeBrick(Point point, Dimension size, BrickType type) {
        if (type == null) {
            throw new IllegalArgumentException("BrickType cannot be null");
        }
        
        switch (type) {
            case CLAY:
                return new ClayBrick(point, size);
            case STEEL:
                return new SteelBrick(point, size);
            case CEMENT:
                return new CementBrick(point, size);
            case SPECIAL:
                return new SpecialBrick(point, size);
            case VIBRANIUM:
                return new VibraniumBrick(point, size);
            case HEALTH:
                return new HealthBrick(point, size);
            default:
                throw new IllegalArgumentException("Unknown BrickType: " + type);
        }
    }
}
