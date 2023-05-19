package snake.game.systems;

import snake.game.data.GameData;

public class EndGameSystem implements GameSystem {
    private final GameData _gameData;

    public EndGameSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        if(_gameData.lives <= 0) {
            _gameData.snake.setDead();
            _gameData.gameEnded = true;
        }
    }
}
