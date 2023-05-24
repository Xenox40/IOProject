package snake.game.systems;

import snake.game.data.GameData;

public abstract class GameSystem {
    protected final GameData _gameData;

    public GameSystem(GameData gameData) {
        _gameData = gameData;
    }

    public abstract void act(float delta);
}
