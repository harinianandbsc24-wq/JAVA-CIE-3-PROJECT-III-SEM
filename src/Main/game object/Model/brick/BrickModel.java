package main.java.gameObjects.model.brick;

import java.awt.*;
import java.util.Random;

/**
 * This class stores the application data for BrickController class
 * 
 * @author Emily
 */
public class BrickModel {

    private int fullStrength;
    private int strength;
    private static final Random rnd = new Random(); // Fixed static initialization

    private String name;
    private boolean broken;
    private Shape brickFace;

    private final Color border;
    private final Color inner;

    /**
     * Constructor to initialize all fields of the BrickModel.
     * 
     * @param name     Name of the brick object
     * @param strength Strength of the brick object
     * @param border   Border color of the brick
     * @param inner    Inner color of the brick
     */
    public BrickModel(String name, int strength, Color border, Color inner) {
        this.name = name;
        this.fullStrength = this.strength = strength;
        this.border = border;
        this.inner = inner;
        this.broken = false;
    }

    /**
     * Random generator for use in all BrickModel instances
     * 
     * @return Random instance
     */
    public static Random getRandom() {
        return rnd;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    public int getFullStrength() {
        return fullStrength;
    }

    // No setter for fullStrength - it should be final or set only in constructor

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public Color getBorderColor() {
        return border;
    }

    public Color getInnerColor() {
        return inner;
    }
}

