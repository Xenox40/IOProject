package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;

public class SpeedDownCell extends ExpiringPickupCell {
    public SpeedDownCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.SpeedDownTexture));
    }

    @Override
    public void act(GameData gameData) {
        gameData.moveCoolDown = Math.min(gameData.maxMoveCoolDown, gameData.moveCoolDown + 0.01f);
    }
}
