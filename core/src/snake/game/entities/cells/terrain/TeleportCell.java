package snake.game.entities.cells.terrain;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;

public class TeleportCell extends  TerrainCell{
    public TeleportCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.TeleportTexture));
    }
}
