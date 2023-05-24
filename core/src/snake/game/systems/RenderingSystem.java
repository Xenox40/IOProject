package snake.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import snake.game.data.GameData;

public class RenderingSystem extends GameSystem{

    private final SpriteBatch _batch;

    public RenderingSystem(GameData gameData, SpriteBatch batch) {
        super(gameData);
        _batch = batch;
    }

    @Override
    public void act(float delta) {
        _gameData.board.draw(_batch);
        for(var pickup : _gameData.pointPickups) {
            pickup.draw(_batch);
        }
        for(var pickup : _gameData.expiringPickups) {
            pickup.draw(_batch);
        }
        for(var wall : _gameData.walls) {
            wall.draw(_batch);
        }
        for(var teleport : _gameData.teleports) {
            teleport.draw(_batch);
        }
        _gameData.snake.draw(_batch);
    }
}
