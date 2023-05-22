package snake.game.systems;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;
import snake.game.utils.CollisionChecker;

public class SnakeSelfCollisionSystem implements GameSystem {
    private final GameData _gameData;
    private Vector2 lastHeadPosition;

    public SnakeSelfCollisionSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded || _gameData.GodMode || !snakeMoved()) return;
        lastHeadPosition = _gameData.snake.getHead().getPosition();

        if(CollisionChecker.isCollidingWithItself(_gameData.snake)) {
            --_gameData.lives;
        }
    }

    private boolean snakeMoved() {
        var headPosition = _gameData.snake.getHead().getPosition();
        return !headPosition.equals(lastHeadPosition);
    }
}
