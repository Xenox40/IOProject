package snake.game.entities.cells.terrain;

import snake.assets.Assets;

public class DarkTerrainCell extends TerrainCell {
    public DarkTerrainCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.DarkTerrainTexture));
    }
}
