package snake.game.entities.cells.terrain;

import snake.assets.Assets;

public class BrightTerrainCell extends TerrainCell {
    public BrightTerrainCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.BrightTerrainTexture));
    }
}
