package snake.game.entities.cells.terrain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.Cell;

public abstract class TerrainCell extends Cell {
    public TerrainCell(int gridX, int gridY, Texture texture) {
        super(gridX, gridY, texture);
    }
    public TerrainCell(Vector2 position, Texture texture) {
        super(position, texture);
    }
}
