package snake.game.entities.cells.terrain.speed;

import com.badlogic.gdx.graphics.Texture;
import snake.game.entities.cells.terrain.TerrainCell;

public abstract class SpeedTerrainCell extends TerrainCell {
    public SpeedTerrainCell(int gridX, int gridY, Texture texture) {
        super(gridX, gridY, texture);
    }
}
