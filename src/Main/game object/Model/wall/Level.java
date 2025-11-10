package main.java.gameObjects.model.wall;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

import main.java.gameObjects.controller.BrickController;
import main.java.gameObjects.model.brick.BrickFactory;
import main.java.gameObjects.model.brick.BrickType;

/**
 * Creates levels for the brick break game with different layouts.
 * Supports single type and chessboard pattern levels with some randomization.
 * 
 * @author Emily
 */
public class Level {

    private final BrickFactory brFactory;

    /**
     * Default constructor initializing brick factory.
     */
    public Level() {
        brFactory = new BrickFactory();
    }

    /**
     * Creates an array of bricks of a single type, arranged in lines.
     * May include random special or health bricks.
     * 
     * @param drawArea       The area for placing bricks.
     * @param brickCnt       Total number of bricks.
     * @param lineCnt        Number of lines.
     * @param brickSizeRatio Width to height ratio of each brick.
     * @param type           The BrickType for normal bricks.
     * @return Array of BrickController objects for the level.
     */
    public BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, BrickType type) {
        brickCnt -= brickCnt % lineCnt;
        int brickOnLine = brickCnt / lineCnt;
        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;  // Allocate for staggered rows
        BrickController[] bricks = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();
        Random rand = new Random();

        int i;
        for (i = 0; i < bricks.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt) break;

            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));  // Staggered on odd lines
            double y = line * brickHgt;
            p.setLocation(x, y);

            double r = rand.nextDouble();
            double r2 = rand.nextDouble();

            if (r < 0.3) {
                bricks[i] = (r2 < 0.3) ? brFactory.makeBrick(p, brickSize, BrickType.HEALTH) : brFactory.makeBrick(p, brickSize, BrickType.SPECIAL);
            } else {
                bricks[i] = brFactory.makeBrick(p, brickSize, type);
            }
        }

        for (double y = brickHgt; i < bricks.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            bricks[i] = (rand.nextDouble() < 0.3) ? brFactory.makeBrick(p, brickSize, BrickType.SPECIAL) : brFactory.makeBrick(p, brickSize, type);
        }
        return bricks;
    }

    /**
     * Creates an array of bricks of two types in a chessboard pattern,
     * with random chance of health bricks.
     * 
     * @param drawArea       The area for bricks.
     * @param brickCnt       Total bricks count.
     * @param lineCnt        Number of lines.
     * @param brickSizeRatio Brick width to height ratio.
     * @param typeA          First brick type.
     * @param typeB          Second brick type.
     * @return Array of BrickController instances.
     */
    public BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, BrickType typeA, BrickType typeB) {
        brickCnt -= brickCnt % lineCnt;
        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;
        BrickController[] bricks = new BrickController[brickCnt];
        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();
        Random rand = new Random();

        int i;
        for (i = 0; i < bricks.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt) break;

            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = line * brickHgt;
            p.setLocation(x, y);

            boolean b = (line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight);

            if (rand.nextDouble() < 0.1) {
                bricks[i] = brFactory.makeBrick(p, brickSize, BrickType.HEALTH);
            } else {
                bricks[i] = b ? brFactory.makeBrick(p, brickSize, typeA) : brFactory.makeBrick(p, brickSize, typeB);
            }
        }

        for (double y = brickHgt; i < bricks.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            bricks[i] = (rand.nextDouble() < 0.1) ? brFactory.makeBrick(p, brickSize, BrickType.HEALTH) : brFactory.makeBrick(p, brickSize, typeA);
        }
        return bricks;
    }
}

