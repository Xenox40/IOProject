package snake.game.factories;

import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;
import snake.game.entities.cells.pickup.expiringPickup.SpeedUpCell;

public class SpeedUpFactory implements ExpiringPickupFactory {
    @Override
    public ExpiringPickupCell createOnPosition(Vector2 position) {
        return new SpeedUpCell(position);
    }
}
