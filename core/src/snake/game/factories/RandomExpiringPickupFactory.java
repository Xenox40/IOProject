package snake.game.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;

public class RandomExpiringPickupFactory implements ExpiringPickupFactory {
    private final Array<ExpiringPickupFactory> _pickupFactories = new Array<>();
    public RandomExpiringPickupFactory(ExpiringPickupFactory...factories) {
        for(var factory : factories) {
            _pickupFactories.add(factory);
        }
    }

    @Override
    public ExpiringPickupCell createOnPosition(Vector2 position) {
        return _pickupFactories.random().createOnPosition(position);
    }
}
