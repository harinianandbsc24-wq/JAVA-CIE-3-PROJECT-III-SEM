package main.java.gameObjects.view;

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * Represents the graphical path for a brick crack.
 * Holds and manages the crack shape for rendering.
 * 
 * @author Emily
 */
public class CrackView {

    private final GeneralPath crack;

    /**
     * Constructs a CrackView with specified crack depth and steps,
     * initializing the internal path with NON_ZERO winding rule.
     * 
     * @param crackDepth Estimated crack depth (used for capacity estimation)
     * @param steps Number of steps in crack path (used for capacity estimation)
     */
    public CrackView(int crackDepth, int steps) {
        // Initial capacity set to steps for expected segments
        crack = new GeneralPath(Path2D.WIND_NON_ZERO, steps);
    }

    /**
     * Returns the current crack path for rendering.
     * 
     * @return The crack GeneralPath
     */
    public GeneralPath draw() {
        return crack;
    }

    /**
     * Returns the crack GeneralPath.
     * Direct access to the path; may expose internal state.
     * Use carefully to avoid corruption.
     * 
     * @return The crack GeneralPath
     */
    public GeneralPath getCrack() {
        return crack;
    }

    /**
     * Clears the crack path, resetting to empty.
     */
    public void reset() {
        crack.reset();
    }

    /**
     * Appends another path to this crack's path.
     * 
     * @param path Path to append
     * @param connect Whether to connect current path with appended path
     */
    public void append(GeneralPath path, boolean connect) {
        crack.append(path, connect);
    }
}
s
