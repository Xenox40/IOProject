package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;
import snake.game.entities.effects.ReversedDirectionsEffect;

public class DirectionReverseCell extends ExpiringPickupCell{
    public DirectionReverseCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.DirectionsReverseTexture));
    }

    @Override
    public void act(GameData gameData) {
        gameData.queuedEffects.addFirst(new ReversedDirectionsEffect());
    }
}
