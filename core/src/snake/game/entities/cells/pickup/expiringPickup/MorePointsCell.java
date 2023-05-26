package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;

public class MorePointsCell extends ExpiringPickupCell {
    public MorePointsCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.MorePointsTexture));
    }

    @Override
    public void act(GameData gameData) {
        ++gameData.maxPointPickups;
    }
}
