package main.java.gameObjects.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import main.java.gameObjects.controller.PlayerController;
import main.java.gameObjects.model.player.PlayerModel;

/**
 * Responsible for rendering the Player object in the game.
 * Implements visual rendering of the paddle shape with colors.
 * 
 * @author Emily
 */
public class PlayerView {

    /**
     * Default constructor.
     */
    public PlayerView() {
    }

    /**
     * Draws the player paddle on the provided Graphics2D context.
     * Applies the inner fill color and border with rendering hints for quality.
     * 
     * @param player The PlayerController instance for paddle information.
     * @param g2d The Graphics2D context to draw on.
     */
    public void drawPlayer(PlayerController player, Graphics2D g2d) {
        if (player == null || g2d == null) {
            throw new IllegalArgumentException("Player and Graphics2D must not be null.");
        }

        Shape paddleShape = player.getPlayerFace();
        if (paddleShape == null) {
            return; // Nothing to draw
        }

        Color originalColor = g2d.getColor();
        try {
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                                 java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                                 java.awt.RenderingHints.VALUE_RENDER_QUALITY);

            g2d.setColor(PlayerModel.INNER_COLOR);
            g2d.fill(paddleShape);

            g2d.setColor(PlayerModel.BORDER_COLOR);
            g2d.draw(paddleShape);
        } finally {
            g2d.setColor(originalColor); // Restore original color
        }
    }
}

