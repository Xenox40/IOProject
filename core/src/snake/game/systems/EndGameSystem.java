package snake.game.systems;

import snake.game.data.GameData;

public class EndGameSystem extends GameSystem {
    public EndGameSystem(GameData gameData) {
        super(gameData);
    }


    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;
        if(snakeDied()) {
            _gameData.gameEnded = true;
            _gameData.snake.setDead();
            _gameData.gameWon = false;
            return;
        }
        if(allPointPickedUp()) {
            _gameData.gameEnded = true;
            _gameData.gameWon = true;
        }
    }

    private boolean snakeDied() {
        return _gameData.lives <= 0;
    }

    private boolean allPointPickedUp() {
        var boardSize = _gameData.board.getHeight() * _gameData.board.getWidth();
        var snakeSize = _gameData.snake.getSize();
        return snakeSize == boardSize;
    }
}
