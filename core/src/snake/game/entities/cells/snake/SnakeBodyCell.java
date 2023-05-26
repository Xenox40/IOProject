package snake.game.entities.cells.snake;

import snake.assets.Assets;

public class SnakeBodyCell extends SnakeCell {
    public SnakeBodyCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.BodyTexture));
    }
    public SnakeBodyCell(float px, float py) {
        super(px, py, Assets.getInstance().get(Assets.BodyTexture));
    }
}
