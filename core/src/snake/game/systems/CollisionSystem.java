package snake.game.systems;

import snake.game.data.GameData;
import snake.utils.CollisionChecker;

public class CollisionSystem implements GameSystem {
    private final GameData _gameData;

    public CollisionSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded || _gameData.GodMode) return;

        if(CollisionChecker.isCollidingWithItself(_gameData.snake)) {
            _gameData.snake.setDead();
            _gameData.gameEnded = true;
        }
    }
}
