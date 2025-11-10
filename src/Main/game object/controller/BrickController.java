package main.java.gameObjects.controller;

import java.awt.*;
import java.awt.geom.Point2D;

import main.java.gameObjects.model.brick.BrickModel;
import main.java.gameObjects.model.brick.ImpactDirection;
import main.java.gameObjects.model.crack.CrackDirection;
import main.java.gameObjects.view.BrickView;

/**
 * Abstract class that provides base behavior and common data for bricks.
 * Subclasses must implement shape creation and specific brick drawing.
 * 
 * @author Emily
 */
public abstract class BrickController {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    private final BrickModel brickModel;
    private final BrickView brickView;
    private Shape brickFace;

    /**
     * Constructs a BrickController with specified parameters.
     * 
     * @param name     Brick name
     * @param pos      Position of the brick (top-left corner)
     * @param size     Size of the brick
     * @param border   Border color
     * @param inner    Inner color
     * @param strength Strength of the brick (hit points)
     */
    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength) {
        this.brickModel = new BrickModel(name, strength, border, inner);
        this.brickFace = makeBrickFace(pos, size);
        this.brickView = new BrickView();
    }

    /**
     * Creates a shape representing the brick.
     * 
     * @param pos  Position of the brick
     * @param size Dimension of the brick
     * @return The shape of the brick
     */
    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    /**
     * Returns the shape of the brick for collision and rendering.
     * Must be implemented by subclasses to define brick geometry.
     * 
     * @return Shape of the brick
     */
    public abstract Shape getBrick();

    /**
     * Registers impact on the brick at specified point and crack direction.
     * Default behavior reduces strength and may break the brick.
     * 
     * @param point Impact point coordinate
     * @param dir   Direction of crack
     * @return true if the brick is broken after impact, false otherwise
     */
    public boolean setImpact(Point2D point, CrackDirection dir) {
        if (isBroken()) {
            return false;
        }
        impact();
        return isBroken();
    }

    /**
     * Detects the impact side relative to the ball controller.
     * 
     * @param ballController The ball instance colliding with brick
     * @return ImpactDirection indicating side of impact or NO_IMPACT if none
     */
    public final ImpactDirection findImpact(BallController ballController) {
        if (isBroken()) {
            return ImpactDirection.NO_IMPACT;
        }
        if (getBrickFace().contains(ballController.getRight())) {
            return ImpactDirection.LEFT_IMPACT;
        } else if (getBrickFace().contains(ballController.getLeft())) {
            return ImpactDirection.RIGHT_IMPACT;
        } else if (getBrickFace().contains(ballController.getUp())) {
            return ImpactDirection.DOWN_IMPACT;
        } else if (getBrickFace().contains(ballController.getDown())) {
            return ImpactDirection.UP_IMPACT;
        }
        return ImpactDirection.NO_IMPACT;
    }

    /**
     * Returns whether the brick is considered broken.
     * 
     * @return true if strength is zero or less
     */
    public final boolean isBroken() {
        return brickModel.isBroken();
    }

    /**
     * Repairs the brick by resetting strength to full and marking as unbroken.
     */
    public void repair() {
        brickModel.setBroken(false);
        brickModel.setStrength(brickModel.getFullStrength());
    }

    /**
     * Reduces the brick strength by one unit and marks broken if strength is zero.
     */
    public void impact() {
        int newStrength = brickModel.getStrength() - 1;
        brickModel.setStrength(newStrength);
        brickModel.setBroken(newStrength <= 0);
    }

    /**
     * Updates the graphical representation of the brick.
     * 
     * @param brick The brick instance
     * @param g2d   Graphics context for drawing
     */
    public void updateView(BrickController brick, Graphics2D g2d) {
        brickView.drawBrick(brick, g2d);
    }

    // Accessors for color, shape, and name

    public Color getBorder() {
        return brickModel.getBorderColor();
    }

    public Color getInner() {
        return brickModel.getInnerColor();
    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    public String getName() {
        return brickModel.getName();
    }

    public void setName(String name) {
        brickModel.setName(name);
    }
}

