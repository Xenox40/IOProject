package snake.game.entities.cells.snake;

import snake.assets.Assets;

public class SnakeHeadCell extends SnakeCell {
    public SnakeHeadCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.HeadTexture));
    }
    public void rotate(boolean clockWise) {
        _sprite.rotate90(clockWise);
    }
}
