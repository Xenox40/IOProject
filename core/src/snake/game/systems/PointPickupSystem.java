package snake.game.systems;

import snake.game.data.GameData;
import snake.game.entities.cells.pickup.PointCell;
import snake.game.utils.CollisionChecker;

public class PointPickupSystem extends GameSystem {


    public PointPickupSystem(GameData gameData) {
        super(gameData);
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;

        handlePickups();

        if(canBePickupAdded()) {
            addNewPickup();
        }
    }

    private void handlePickups() {
        var head = _gameData.snake.getHead();
        var pickups = _gameData.pointPickups;
        for(var pickup : pickups.toArray(PointCell.class)) {
            if(head.collidesWith(pickup)) {
                pickup.act(_gameData);
                pickups.removeValue(pickup, true);
            }
        }
        while(_gameData.maxPointPickups < _gameData.pointPickups.size) {
            _gameData.pointPickups.removeValue(_gameData.pointPickups.random(), true);
        }
    }

    private void addNewPickup() {
        var position = CollisionChecker.getFreePosition(_gameData,
                false, true, true, true);
        _gameData.pointPickups.add(new PointCell(position));
    }

    private boolean canBePickupAdded() {
        if(_gameData.pointPickups.size == _gameData.maxPointPickups) return false;

        var boardSize = _gameData.board.getWidth() * _gameData.board.getHeight();
        var snakeSize = _gameData.snake.getSize();

        return snakeSize + _gameData.pointPickups.size < boardSize;
    }
}
