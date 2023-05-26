package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import snake.game.entities.cells.pickup.PickupCell;

public abstract class ExpiringPickupCell extends PickupCell {
    protected final float _expirationTime = 10.0f;
    protected float _currentTime;
    public ExpiringPickupCell(Vector2 position, Texture texture) {
        super(position, texture);
    }

    public boolean expired() {
        return _currentTime >= _expirationTime;
    }

    public void addTime(float delta) {
        _currentTime += delta;
    }
}
