package main.java.gameObjects.model.brick;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import main.java.gameObjects.controller.BrickController;

/**
 * SpecialBrick represents a brick object of special type
 * It provides default colors, strength, and shape definitions.
 * 
 * @author Emily
 */
public class SpecialBrick extends BrickController {

    private static final String NAME = "Special Brick";
    private static final Color DEF_INNER = new Color(255, 215, 0);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int SPECIAL_STRENGTH = 1;

    /**
     * Constructs a SpecialBrick at specified position and size.
     * 
     * @param point The coordinate of the brick's top-left corner
     * @param size  The dimensions of the brick
     */
    public SpecialBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, SPECIAL_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle2D.Double(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}

