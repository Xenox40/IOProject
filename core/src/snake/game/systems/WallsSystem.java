package snake.game.systems;

import snake.game.data.GameData;
import snake.game.entities.cells.terrain.WallCell;
import snake.utils.CollisionChecker;

public class WallsSystem implements GameSystem {
    private final GameData _gameData;

    public WallsSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        handleWalls();
        while(canBeWallPlaced()) {
            addNewWall();
        }
    }

    private void handleWalls() {
        if(_gameData.gameEnded) return;
        var head = _gameData.snake.getHead();
        var walls = _gameData.walls;
        for(var wall : walls.toArray(WallCell.class)) {
            if(CollisionChecker.AnyPointPickupContains(_gameData.pointPickups, wall) ||
                CollisionChecker.AnyTeleportContains(_gameData.teleports, wall)) {
                walls.removeValue(wall, true);
                continue;
            }
            if(!_gameData.GodMode && head.collidesWith(wall)) {
                --_gameData.lives;
                walls.removeValue(wall, true);
            }
        }
    }
    private void addNewWall() {
        var position = CollisionChecker.getFreePosition(_gameData,
                false, true, false, false);
        _gameData.walls.add(new WallCell(position));
    }
    private boolean canBeWallPlaced() {
        if(_gameData.maxWalls == _gameData.walls.size) return false;
        var boardSize = _gameData.board.getWidth() * _gameData.board.getHeight();
        var snakeSize = _gameData.snake.getSize();
        return snakeSize + _gameData.maxPointPickups +  _gameData.maxTeleports + _gameData.walls.size < boardSize;
    }
}
