package main.java.gameObjects.model.wall;

import java.awt.Rectangle;

import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.model.brick.BrickType;

/**
 * Factory class to create multiple game levels consisting of bricks.
 * Uses Level class to produce various level layouts.
 * 
 * @author Emily
 */
public class LevelFactory {

    private static final int LEVELS_COUNT = 8;
    private final Level level;

    /**
     * Default constructor initializing internal Level instance.
     */
    public LevelFactory() {
        level = new Level();
    }

    /**
     * Creates a 2D array of BrickController arrays representing multiple levels.
     * Each level has distinct brick types and patterns.
     * 
     * @param drawArea            The bounding area for the brick wall
     * @param brickCount          The total number of bricks per level
     * @param lineCount           The number of lines of bricks
     * @param brickDimensionRatio The width-to-height ratio of bricks
     * @return 2D BrickController arrays with levels
     */
    public BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        BrickController[][] levels = new BrickController[LEVELS_COUNT][];

        levels[0] = level.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.CLAY);
        levels[1] = level.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.CLAY, BrickType.CEMENT);
        levels[2] = level.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.CEMENT);
        levels[3] = level.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.CLAY, BrickType.STEEL);
        levels[4] = level.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.VIBRANIUM, BrickType.CEMENT);
        levels[5] = level.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.STEEL);
        levels[6] = level.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.VIBRANIUM, BrickType.STEEL);
        levels[7] = level.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, BrickType.VIBRANIUM);

        return levels;
    }
}

