package snake.game.entities.cells.snake;

import snake.game.entities.cells.Cell;

import static snake.GlobalVariables.DEAD_COLOR;

public abstract class SnakeCell extends Cell {
    public SnakeCell(int gridX, int gridY) {
        super(gridX, gridY);
    }

    public void setDead() {
        _sprite.setColor(DEAD_COLOR);
    }

    public void setAlpha(float alpha) {
        _sprite.setAlpha(alpha);
    }

    public SnakeCell(float px, float py) {
        super(px, py);
    }
}
