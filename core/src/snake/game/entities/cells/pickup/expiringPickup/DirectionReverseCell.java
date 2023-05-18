package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;
import snake.game.entities.effects.ReversedDirectionsEffect;

public class DirectionReverseCell extends ExpiringPickupCell{
    public DirectionReverseCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        gameData.queuedEffects.addFirst(new ReversedDirectionsEffect());
    }
}
