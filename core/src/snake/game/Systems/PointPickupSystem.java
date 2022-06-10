package snake.game.Systems;

import snake.game.data.GameData;
import snake.game.entities.cells.pickup.PointCell;
import snake.utils.CollisionChecker;

public class PointPickupSystem implements GameSystem {
    private final GameData _gameData;

    public PointPickupSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;

        handlePickups();

        if(_gameData.snake.getSize() == _gameData.board.getHeight() * _gameData.board.getWidth()) {
            _gameData.gameEnded = true;
            return;
        }

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
    }

    private void addNewPickup() {
        var position = CollisionChecker.getFreePosition(_gameData, false, true);
        _gameData.pointPickups.add(new PointCell(position));
    }

    private boolean canBePickupAdded() {
        if(_gameData.pointPickups.size + 1 > _gameData.maxPointPickups) return false;

        return _gameData.snake.getSize() + _gameData.pointPickups.size + _gameData.expiringPickups.size + 1
                <= _gameData.board.getWidth() * _gameData.board.getHeight();
    }
}
