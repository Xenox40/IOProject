package snake.factories;

import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.pickup.expiringPickup.BombCell;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;

public class BombFactory implements ExpiringPickupFactory{
    @Override
    public ExpiringPickupCell createOnPosition(Vector2 position) {
        return new BombCell(position);
    }
}
