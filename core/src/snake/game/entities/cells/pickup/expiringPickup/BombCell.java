package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;
import snake.game.data.GameData;

public class BombCell extends ExpiringPickupCell{
    public BombCell(Vector2 position) {
        super(position, Assets.getInstance().get(Assets.BombTexture));
    }

    @Override
    public void act(GameData gameData) {
        if(!gameData.GodMode) {
            gameData.lives = -666;
        }
    }
}
