package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class LessFrequentPickupsCell extends ExpiringPickupCell{
    public LessFrequentPickupsCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.pickupCoolDown += 1.0f;
    }
}
