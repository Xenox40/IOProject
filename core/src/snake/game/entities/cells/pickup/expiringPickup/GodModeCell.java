package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;
import snake.game.entities.effects.GodModeEffect;

public class GodModeCell  extends ExpiringPickupCell{
    public GodModeCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.GodModeTexture));
    }

    @Override
    public void act(GameData gameData) {
        gameData.queuedEffects.addFirst(new GodModeEffect());
    }
}
