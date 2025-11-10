package main.java.gameObjects.model.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.controller.CrackController;
import main.java.gameObjects.model.crack.CrackDirection;

/**
 * Represents a Health Brick with crack management and repair capabilities.
 * 
 * @author Emily
 */
public class HealthBrick extends BrickController {

    private static final String NAME = "Health Brick";
    private static final Color DEF_INNER = Color.MAGENTA;
    private static final Color DEF_BORDER = new Color(147, 147, 147);
    private static final int HEALTH_STRENGTH = 2;

    private final CrackController crack;
    private Shape brickFace;

    /**
     * Constructor to create a Health Brick at specified position and size.
     * 
     * @param point Top-left coordinate of the brick
     * @param size  Dimensions of the brick
     */
    public HealthBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, HEALTH_STRENGTH);
        crack = new CrackController(DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    @Override
    public boolean setImpact(Point2D point, CrackDirection dir) {
        if (super.isBroken()) {
            return false;
        }
        super.impact();
        if (!super.isBroken()) {
            crack.makeCrack(getBrick(), point, dir);
            updateBrick();
            return false;
        }
        return true;
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Update the visual crack state of the health brick.
     */
    private void updateBrick() {
        if (!super.isBroken()) {
            GeneralPath gp = crack.updateView();
            gp.append(super.getBrickFace(), false);
            brickFace = gp;
        }
    }

    @Override
    public void repair() {
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
