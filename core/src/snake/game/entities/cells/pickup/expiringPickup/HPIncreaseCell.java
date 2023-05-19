package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class HPIncreaseCell extends ExpiringPickupCell{
    public HPIncreaseCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        ++gameData.lives;
    }
}
