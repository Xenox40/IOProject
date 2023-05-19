package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

import static snake.utils.GlobalVariables.DEAD_COLOR;

public class HPDecreaseCell extends ExpiringPickupCell{
    public HPDecreaseCell(Vector2 position) {
        super(position);
        _sprite.setColor(DEAD_COLOR);
    }

    @Override
    public void act(GameData gameData) {
        --gameData.lives;
    }
}
