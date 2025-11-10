package main.java.gameObjects.model.brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.controller.CrackController;
import main.java.gameObjects.model.crack.CrackDirection;

/**
 * VibraniumBrick represents a rare, high-strength brick with
 * damage resistance and visual crack updates.
 * 
 * Author: Emily
 */
public class VibraniumBrick extends BrickController {

    private static final String NAME = "Vibranium Brick";
    private static final Color DEF_INNER = Color.BLUE.brighter().brighter();
    private static final Color DEF_BORDER = Color.WHITE;
    private static final int VIB_STRENGTH = 3;
    private static final double VIB_PROBABILITY = 0.6;

    private final CrackController crack;
    private final Random rnd;
    private Shape brickFace;

    /**
     * Constructor creating a VibraniumBrick at given position and size.
     * @param point The top-left coordinate of the brick
     * @param size The dimensions of the brick
     */
    public VibraniumBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, VIB_STRENGTH);
        rnd = new Random();
        crack = new CrackController(DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
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
        return super.isBroken();
    }

    /**
     * Updates the visual crack state of the Vibranium brick.
     */
    private void updateBrick() {
        if (!super.isBroken()) {
            GeneralPath gp = crack.updateView();
            gp.append(super.getBrickFace(), false);
            brickFace = gp;
        }
    }

    /**
     * Probabilistic impact to reflect tough material.
     */
    @Override
    public void impact() {
        if (rnd.nextDouble() < VIB_PROBABILITY) {
            super.impact();
        }
    }
}
