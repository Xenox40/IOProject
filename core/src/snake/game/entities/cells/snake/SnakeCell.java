package snake.game.entities.cells.snake;

import com.badlogic.gdx.graphics.Texture;
import snake.game.entities.cells.Cell;

import static snake.GlobalVariables.DEAD_COLOR;

public abstract class SnakeCell extends Cell {
    public SnakeCell(int gridX, int gridY, Texture texture) {
        super(gridX, gridY, texture);
    }

    public void setDead() {
        _sprite.setColor(DEAD_COLOR);
    }

    public void setAlpha(float alpha) {
        _sprite.setAlpha(alpha);
    }

    public SnakeCell(float px, float py, Texture texture) {
        super(px, py, texture);
    }
}
