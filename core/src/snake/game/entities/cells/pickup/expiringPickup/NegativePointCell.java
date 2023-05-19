package snake.game.entities.cells.pickup.expiringPickup;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;

public class NegativePointCell extends ExpiringPickupCell {
    public NegativePointCell(Vector2 position) {
        super(position);
    }

    @Override
    public void act(GameData gameData) {
        var snake = gameData.snake;
        if(snake.getSize() == 1) {
            snake.setDead();
            gameData.gameEnded = true;
        } else {
            --gameData.score;
            snake.decreaseSize();
        }
    }
}
