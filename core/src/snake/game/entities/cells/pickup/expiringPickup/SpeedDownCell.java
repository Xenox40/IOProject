package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class SpeedDownCell extends ExpiringPickupCell {
    public SpeedDownCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.moveCoolDown = Math.min(gameData.maxMoveCoolDown, gameData.moveCoolDown + 0.01f);
    }
}
