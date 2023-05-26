package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;

public class MoreFrequentPickupsCell extends ExpiringPickupCell{
    public MoreFrequentPickupsCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.MoreFrequentPickupsTexture));
    }

    @Override
    public void act(GameData gameData) {
        gameData.pickupCoolDown = Math.max(gameData.minPickupCoolDown, gameData.pickupCoolDown - 1.0f);
    }
}
