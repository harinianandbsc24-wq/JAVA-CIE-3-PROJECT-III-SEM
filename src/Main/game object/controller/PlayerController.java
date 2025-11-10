public class PlayerController implements Playable {

    private static PlayerController player;

    private final Rectangle paddleBounds;
    private final PlayerModel playerModel;
    private final PlayerView playerView;

    private int moveAmount = 0;
    private static final int DEF_MOVE_AMOUNT = 5;

    private PlayerController(Point ballPoint, int width, int height, Rectangle container) {
        playerModel = new PlayerModel(ballPoint, width, container);
        paddleBounds = makeRectangle(width, height);
        playerView = new PlayerView();
    }

    public static synchronized PlayerController getUniquePlayer(Point ballPoint, int width, int height, Rectangle container) {
        if (player == null) {
            player = new PlayerController(ballPoint, width, height, container);
        }
        return player;
    }

    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (playerModel.getBallPoint().getX() - width / 2), (int) playerModel.getBallPoint().getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    public boolean impact(BallController ballController) {
        if (paddleBounds == null) return false;
        Point pos = ballController.getPosition();
        Point down = new Point((int) ballController.getDown().getX(), (int) ballController.getDown().getY());
        return paddleBounds.contains(pos) && paddleBounds.contains(down);
    }

    public void move() {
        moveBy(moveAmount);
    }

    public void move(int delta) {
        moveBy(delta);
    }

    private void moveBy(int delta) {
        double nextX = playerModel.getBallPoint().getX() + delta;
        if (nextX < playerModel.getMin() || nextX > playerModel.getMax()) return;
        playerModel.getBallPoint().setLocation(nextX, playerModel.getBallPoint().getY());
        updatePaddleBounds();
    }

    private void updatePaddleBounds() {
        if (paddleBounds == null) return;
        paddleBounds.setLocation(playerModel.getBallPoint().x - (int) paddleBounds.getWidth() / 2, playerModel.getBallPoint().y);
    }

    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop() {
        moveAmount = 0;
    }

    public void moveTo(Point p) {
        playerModel.getBallPoint().setLocation(p);
        updatePaddleBounds();
    }

    public void updateView(PlayerController p, Graphics2D g2d) {
        playerView.drawPlayer(p, g2d);
    }

    public Rectangle getPaddleBounds() {
        return paddleBounds;
    }
}

