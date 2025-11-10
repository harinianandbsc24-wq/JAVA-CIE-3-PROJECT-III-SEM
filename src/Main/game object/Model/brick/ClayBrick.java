package main.java.gameObjects.model.brick;

import java.awt.*;
import main.java.gameObjects.controller.BrickController;

/**
 * ClayBrick represents a brick object of type clay
 * It inherits from BrickController and provides default colors and strength.
 * 
 * @author Emily
 */
public class ClayBrick extends BrickController {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * Constructor to create a clay brick at the specified position and size.
     *
     * @param point The top-left position of the brick
     * @param size The dimensions of the brick
     */
    public ClayBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}

