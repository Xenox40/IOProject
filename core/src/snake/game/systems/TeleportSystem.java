package snake.game.systems;

import com.badlogic.gdx.math.Vector2;
import snake.game.data.GameData;
import snake.game.entities.cells.terrain.TeleportCell;
import snake.game.utils.CollisionChecker;

public class TeleportSystem extends GameSystem{
    private Vector2 _lastHeadPosition;

    public TeleportSystem(GameData gameData) {
        super(gameData);
    }


    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;
        var head = _gameData.snake.getHead();
        var headPosition = head.getPosition();
        if(headPosition.equals(_lastHeadPosition)) {
            return;
        }
        handleTeleports();
        while(canBeTeleportPlaced()) {
            addNewTeleport();
        }
        _lastHeadPosition = head.getPosition();
    }

    private void handleTeleports() {
        var head = _gameData.snake.getHead();
        var teleports = _gameData.teleports;
        for(var teleport : teleports.toArray(TeleportCell.class)) {
            if(CollisionChecker.AnyPointPickupContains(_gameData.pointPickups, teleport)) {
                teleports.removeValue(teleport, true);
                continue;
            }
            if(head.collidesWith(teleport)) {
                var targetTeleport = teleports.random();
                head.setPosition(targetTeleport);
                return;
            }
        }
    }
    private void addNewTeleport() {
        var position = CollisionChecker.getFreePosition(_gameData,
                false, true, true, false);
        _gameData.teleports.add(new TeleportCell(position));
    }
    private boolean canBeTeleportPlaced() {
        if(_gameData.maxTeleports == _gameData.teleports.size) return false;
        var boardSize = _gameData.board.getWidth() * _gameData.board.getHeight();
        var snakeSize = _gameData.snake.getSize();
        return snakeSize + _gameData.maxPointPickups + _gameData.teleports.size < boardSize;
    }
}
