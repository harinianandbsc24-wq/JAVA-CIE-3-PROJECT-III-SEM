package main.java.gameObjects.controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import main.java.gameObjects.model.brick.BrickModel;
import main.java.gameObjects.model.crack.CrackDirection;
import main.java.gameObjects.model.crack.CrackModel;
import main.java.gameObjects.view.CrackView;

/**
 * Controller class that handles crack logic and coordinates between model and view.
 * 
 * @author Emily
 */
public class CrackController {

    private final CrackModel crackModel;
    private final CrackView crackView;

    /**
     * Constructor to initialize model and view with crack parameters.
     * 
     * @param crackDepth Depth of cracks
     * @param steps Number of steps for crack generation
     */
    public CrackController(int crackDepth, int steps) {
        this.crackModel = new CrackModel(crackDepth, steps);
        this.crackView = new CrackView(crackDepth, steps);
    }

    /**
     * Updates and returns the crack's graphical representation.
     * 
     * @return Updated GeneralPath of the crack
     */
    public GeneralPath updateView() {
        return crackView.draw();
    }

    /**
     * Resets the crack path.
     */
    public void reset() {
        crackView.getCrack().reset();
    }

    /**
     * Creates a crack on the brick based on impact point and direction.
     * 
     * @param brickFace The shape of the brick
     * @param point Impact location
     * @param direction Crack direction
     */
    public void makeCrack(Shape brickFace, Point2D point, CrackDirection direction) {
        Rectangle bounds = brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();

        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, CrackDirection.VERTICAL);
                makeCrack(impact, tmp);
                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, CrackDirection.VERTICAL);
                makeCrack(impact, tmp);
                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, CrackDirection.HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, CrackDirection.HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            default:
                throw new IllegalArgumentException("Unknown Direction: " + direction);
        }
    }

    /**
     * Creates a crack path between two points.
     * 
     * @param start Starting point
     * @param end Ending point
     */
    public void makeCrack(Point start, Point end) {
        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double widthStep = (end.x - start.x) / (double) getSteps();
        double heightStep = (end.y - start.y) / (double) getSteps();

        int bound = getCrackDepth();
        int jump = bound * 5;

        for (int i = 1; i < getSteps(); i++) {
            double x = (i * widthStep) + start.x;
            double y = (i * heightStep) + start.y + randomInBounds(bound);

            if (inMiddle(i, crackModel.CRACK_SECTIONS, getSteps())) {
                y += jumps(jump, crackModel.JUMP_PROBABILITY);
            }
            path.lineTo(x, y);
        }

        path.lineTo(end.x, end.y);
        crackView.getCrack().append(path, true);
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return BrickModel.getRnd().nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);
        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {
        if (BrickModel.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;
    }

    private Point makeRandomPoint(Point from, Point to, CrackDirection vertical) {
        Point out = new Point();
        int pos;

        switch (vertical) {
            case HORIZONTAL:
                pos = BrickModel.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = BrickModel.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
            default:
                throw new IllegalArgumentException("Unknown Vertical Direction: " + vertical);
        }
        return out;
    }

    public int getCrackDepth() {
        return crackModel.getCrackDepth();
    }

    public int getSteps() {
        return crackModel.getSteps();
    }
}

