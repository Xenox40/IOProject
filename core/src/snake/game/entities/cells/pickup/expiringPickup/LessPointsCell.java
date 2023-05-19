package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class LessPointsCell extends ExpiringPickupCell{
    public LessPointsCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.maxPointPickups = Math.max(gameData.minPointPickups, gameData.maxPointPickups - 1);
    }
}
