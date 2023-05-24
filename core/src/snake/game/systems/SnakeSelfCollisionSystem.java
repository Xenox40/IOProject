package snake.game.systems;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;
import snake.game.utils.CollisionChecker;

public class SnakeSelfCollisionSystem extends GameSystem {
    private Vector2 lastHeadPosition;

    public SnakeSelfCollisionSystem(GameData gameData) {
        super(gameData);
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
