package snake.game.systems;

import snake.game.data.GameData;

public class SnakeMovementSystem extends GameSystem {
    private float _timeFromLastMove = 0.0f;

    public SnakeMovementSystem(GameData gameData) {
        super(gameData);
    }


    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;

        _gameData.controller.handleInputEvent();
        _timeFromLastMove += delta;
        if(_timeFromLastMove >= _gameData.moveCoolDown) {
            makeAMove();
            _timeFromLastMove = 0.0f;
        }
    }

    private void makeAMove() {
        _gameData.snake.move(_gameData.board.getWidth(), _gameData.board.getHeight());
    }
}
