package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class MorePickupsCell extends ExpiringPickupCell {
    public MorePickupsCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.maxExpiringPickups++;
    }
}
