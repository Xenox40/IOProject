package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class LessPickupsCell extends ExpiringPickupCell{
    public LessPickupsCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.maxExpiringPickups = Math.max(gameData.minExpiringPickups, gameData.maxExpiringPickups - 1);
    }
}
