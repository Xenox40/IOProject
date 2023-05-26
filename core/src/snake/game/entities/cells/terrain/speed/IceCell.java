package snake.game.entities.cells.terrain.speed;

import snake.assets.Assets;

public class IceCell extends SpeedTerrainCell  {
    public IceCell(int gridX, int gridY) {
        super(gridX, gridY, Assets.getInstance().get(Assets.IceTexture));
    }
}
