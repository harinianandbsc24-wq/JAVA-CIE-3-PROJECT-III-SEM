package main.java.gameObjects.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import main.java.gameObjects.controller.BrickController;

/**
 * Responsible for rendering Brick objects in the game.
 * Handles drawing filled shapes with proper colors and border.
 * 
 * @author Emily
 */
public class BrickView {

    /**
     * Default constructor
     */
    public BrickView() {
    }

    /**
     * Draws the brick on the provided Graphics2D context.
     * Applies inner fill color and border color.
     * Rendering hints for quality are applied.
     * 
     * @param brick The BrickController instance to render.
     * @param g2d The Graphics2D context to draw on.
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
            // Enable anti-aliasing for smoother edges
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                    java.awt.RenderingHints.VALUE_RENDER_QUALITY);

            // Fill the shape with inner color
            g2d.setColor(brick.getInner());
            g2d.fill(shape);

            // Draw the border with border color
            g2d.setColor(brick.getBorder());
            g2d.draw(shape);

        } finally {
            g2d.setColor(originalColor); // Restore original color
        }
    }
}

