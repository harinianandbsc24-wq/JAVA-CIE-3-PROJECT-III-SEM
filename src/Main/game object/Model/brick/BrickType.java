package main.java.gameObjects.model.brick;

/**
 * Enum representing different types of bricks available in the game.
 * Each type can optionally hold additional properties or behavior.
 * 
 * Author: Emily
 */
public enum BrickType {
    
    /**
     * Clay Brick type
     */
    CLAY,
    
    /**
     * Steel Brick type
     */
    STEEL,
    
    /**
     * Cement Brick type
     */
    CEMENT,
    
    /**
     * Vibranium Brick type
     */
    VIBRANIUM,
    
    /**
     * Special Brick type
     */
    SPECIAL, 
    
    /**
     * Health Brick type
     */
    HEALTH;
    
    // You can add fields, constructors, and methods here if needed, e.g.:
    // private final int toughness;
    // private BrickType(int toughness) { this.toughness = toughness; }
    // public int getToughness() { return toughness; }
}
