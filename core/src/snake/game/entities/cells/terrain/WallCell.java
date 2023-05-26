package snake.game.entities.cells.terrain;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;

public class WallCell extends TerrainCell {
    public WallCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.WallTexture));
    }
}
