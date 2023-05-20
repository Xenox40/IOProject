package snake.game.entities.cells.terrain;

import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.Cell;

public abstract class TerrainCell extends Cell {
    public TerrainCell(int gridX, int gridY) {
        super(gridX, gridY);
    }
    public TerrainCell(Vector2 position) {
        super(position);
    }
}
