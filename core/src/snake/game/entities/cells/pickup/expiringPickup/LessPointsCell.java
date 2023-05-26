package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;

public class LessPointsCell extends ExpiringPickupCell{
    public LessPointsCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.LessPointsTexture));
    }

    @Override
    public void act(GameData gameData) {
        gameData.maxPointPickups = Math.max(gameData.minPointPickups, gameData.maxPointPickups - 1);
    }
}
