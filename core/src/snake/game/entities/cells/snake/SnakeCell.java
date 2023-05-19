package snake.game.entities.cells.snake;

import snake.game.entities.cells.Cell;

public abstract class SnakeCell extends Cell {
    public SnakeCell(int gridX, int gridY) {
        super(gridX, gridY);
    }

    public void setDead() {
        _sprite.setColor(0.5f, 0.0f, 0.0f, 1.0f);
    }

    public void setAlpha(float alpha) {
        _sprite.setAlpha(alpha);
    }

    public SnakeCell(float px, float py) {
        super(px, py);
    }
}
