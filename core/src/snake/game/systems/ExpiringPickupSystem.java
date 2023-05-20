package snake.game.systems;

import com.badlogic.gdx.math.MathUtils;
import snake.factories.ExpiringPickupFactory;
import snake.game.data.GameData;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;
import snake.utils.CollisionChecker;

public class ExpiringPickupSystem implements GameSystem {
    private final GameData _gameData;
    private final ExpiringPickupFactory _factory;

    private float _lastPickup = 0.0f;

    public ExpiringPickupSystem(GameData gameData, ExpiringPickupFactory factory) {
        _gameData = gameData;
        _factory = factory;
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;

        handlePickups(delta);

        _lastPickup += delta;
        if(canBePickupAdded()) {
            tryToAddNewPickup();
            _lastPickup = 0.0f;
        }
    }

    private void handlePickups(float delta) {
        var head = _gameData.snake.getHead();
        var pickups = _gameData.expiringPickups;
        for(var pickup : pickups.toArray(ExpiringPickupCell.class)) {
            pickup.addTime(delta);
            if(pickup.expired() ||
                    CollisionChecker.AnyPointPickupContains(_gameData.pointPickups, pickup) ||
                    CollisionChecker.AnyWallContains(_gameData.walls, pickup) ||
                    CollisionChecker.AnyTeleportContains(_gameData.teleports, pickup)) {
                pickups.removeValue(pickup, true);
                continue;
            }
            if(head.collidesWith(pickup)) {
                pickup.act(_gameData);
                pickups.removeValue(pickup, true);
            }
        }
    }

    private void tryToAddNewPickup() {
        if(MathUtils.random.nextFloat() > _gameData.pickupChance) return;
        var position = CollisionChecker.getFreePosition(_gameData,
                false, false, false, false);
        _gameData.expiringPickups.add(_factory.createOnPosition(position));
    }

    private boolean canBePickupAdded() {
        if(_lastPickup < _gameData.pickupCoolDown ||
                _gameData.expiringPickups.size == _gameData.maxExpiringPickups) return false;

        var boardSize = _gameData.board.getWidth() * _gameData.board.getHeight();
        var snakeSize = _gameData.snake.getSize();

        return snakeSize + _gameData.maxPointPickups + _gameData.maxWalls + _gameData.maxTeleports +
                _gameData.expiringPickups.size < boardSize;
    }
}
