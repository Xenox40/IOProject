package snake.game.entities.cells.terrain.speed;

import snake.assets.Assets;

public class SandCell extends SpeedTerrainCell {
    public SandCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.SandTexture));
    }
}
