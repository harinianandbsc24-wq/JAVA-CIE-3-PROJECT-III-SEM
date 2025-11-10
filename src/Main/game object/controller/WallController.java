public class WallController {

    private final Random rnd = new Random();

    private final BrickController[][] levels;
    private final LevelFactory levelFac = new LevelFactory();
    private final WallModel wallModel;

    public WallController(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {
        levels = levelFac.makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        wallModel = new WallModel(drawArea, ballPos);
        makeBall(ballPos);
        setRandomBallSpeed();
        wallModel.setPlayer((Point) ballPos.clone(), 150, 10, drawArea);
    }

    private void setRandomBallSpeed() {
        int speedX;
        do {
            speedX = rnd.nextInt(10) - 5;
        } while (speedX == 0);
        int speedY;
        do {
            speedY = -rnd.nextInt(7);
        } while (speedY == 0);
        getBall().setSpeed(speedX, speedY);
    }

    public void findImpacts() {
        if (getPlayer().impact(getBall())) {
            getBall().reverseY();
        } else if (impactWall()) {
            wallModel.setScore(getScore() + 50);
            wallModel.setBrickCount(getBrickCount() - 1);
        } else if (impactBorder()) {
            getBall().reverseX();
        } else {
            checkBallAboveOrBelow();
        }
    }

    private void checkBallAboveOrBelow() {
        double ballY = getBall().getPosition().getY();
        Rectangle area = wallModel.getArea();
        if (ballY < area.getY()) {
            getBall().reverseY();
        } else if (ballY > area.getY() + area.getHeight()) {
            wallModel.setBallCount(getBallCount() - 1);
            wallModel.setBallLost(true);
        }
    }

    private boolean impactWall() {
        for (BrickController br : getBricks()) {
            ImpactDirection impact = br.findImpact(getBall());
            if (impact == ImpactDirection.NO_IMPACT) continue;
            handleBallImpact(br, impact);
            return true;
        }
        return false;
    }

    private void handleBallImpact(BrickController br, ImpactDirection impact) {
        switch (impact) {
            case UP_IMPACT:
            case DOWN_IMPACT:
                getBall().reverseY();
                br.setImpact(getBall().getOppositePoint(impact), getCrackDirection(impact));
                break;
            case LEFT_IMPACT:
            case RIGHT_IMPACT:
                getBall().reverseX();
                br.setImpact(getBall().getOppositePoint(impact), getCrackDirection(impact));
                break;
            default:
                break;
        }
    }

    // Additional methods like getCrackDirection, getOppositePoint can be defined for clarity.

    // Rest of your getters/setters...
}

