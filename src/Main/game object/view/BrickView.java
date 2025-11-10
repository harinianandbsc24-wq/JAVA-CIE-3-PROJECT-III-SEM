package main.java.gameObjects.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.RenderingHints;

import main.java.gameObjects.controller.BrickController;

/**
 * Responsible for rendering Brick objects in the game.
 * Handles drawing filled shapes with proper colors and border.
 * 
 * @author Emily
 */
public class BrickView {

    /**
     * Default constructor.
     */
    public BrickView() {
    }

    /**
     * Draws the brick on the provided Graphics2D context.
     * Applies inner fill color and border color with rendering quality hints.
     * 
     * @param brick The BrickController instance to render.
     * @param g2d   The Graphics2D context to draw on.
     */
    public void drawBrick(BrickController brick, Graphics2D g2d) {
        if (brick == null || g2d == null) {
            throw new IllegalArgumentException("Brick and Graphics2D must not be null.");
        }

        Shape shape = brick.getBrick();
        if (shape == null) {
            return; // Nothing to draw
        }

        Color originalColor = g2d.getColor();

        try {
            // Enable antialiasing and quality rendering for smooth graphics
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setColor(brick.getInner());
            g2d.fill(shape);

            g2d.setColor(brick.getBorder());
            g2d.draw(shape);
        } finally {
            g2d.setColor(originalColor); // Restore original color
        }
    }
}
