package snake.factories;

import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;
import snake.game.entities.cells.pickup.expiringPickup.MoreFrequentPickupsCell;

public class MoreFrequentPickupsFactory implements ExpiringPickupFactory{
    @Override
    public ExpiringPickupCell createOnPosition(Vector2 position) {
        return new MoreFrequentPickupsCell(position);
    }
}
