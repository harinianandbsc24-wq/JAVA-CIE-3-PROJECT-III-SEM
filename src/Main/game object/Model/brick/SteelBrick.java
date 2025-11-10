package main.java.gameObjects.model.brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.model.crack.CrackDirection;

/**
 * SteelBrick represents a brick object of type steel,
 * with probabilistic damage resistance.
 * 
 * @author Emily
 */
public class SteelBrick extends BrickController {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random rnd;
    private Shape brickFace;

    /**
     * Constructs a SteelBrick at the given position and size.
     * 
     * @param point The origin point of the brick
     * @param size The size dimensions of the brick
     */
    public SteelBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
