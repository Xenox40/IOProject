package snake.game.systems;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import snake.game.data.GameData;
import snake.game.entities.cells.terrain.TerrainCell;
import snake.game.entities.cells.terrain.speed.IceCell;
import snake.game.entities.cells.terrain.speed.SandCell;
import snake.game.entities.cells.terrain.speed.SpeedTerrainCell;

public class SpeedTerrainSystem extends GameSystem {
    private final Array<SpeedTerrainCell> _speedCells = new Array<>();

    private float currentBonus;

    public SpeedTerrainSystem(GameData gameData) {
        super(gameData);
        init();
    }

    private void init() {
        var speedAreaLength = _gameData.speedAreaLength;
        var speedAresCount = _gameData.speedAreaCount;
        for(int i = 0; i < speedAresCount; ++i) {
            var horizontal = MathUtils.randomBoolean();
            if(horizontal) {
                createHorizontalSpeedArea(speedAreaLength);
            } else {
                createVerticalSpeedArea(speedAreaLength);
            }
        }
    }

    private void createHorizontalSpeedArea(int length) {
        var board = _gameData.board;
        var row = MathUtils.random(board.getHeight() - 1);
        var startPoint = MathUtils.random(board.getWidth() - length);
        var speedUp = MathUtils.randomBoolean();
        for(int i = 0; i < length; ++i) {
            var col = startPoint + i;
            SpeedTerrainCell newCell;
            if(speedUp) {
                newCell = new IceCell(col, row);
            } else {
                newCell = new SandCell(col, row);
            }
            _speedCells.add(newCell);
            board.getCells().get(row).set(col, newCell);
        }
    }

    private void createVerticalSpeedArea(int length) {
        var board = _gameData.board;
        var col = MathUtils.random(board.getWidth() - 1);
        var startPoint = MathUtils.random(board.getHeight() - length);
        var speedUp = MathUtils.randomBoolean();
        for(int i = 0; i < length; ++i) {
            var row = startPoint + i;
            SpeedTerrainCell newCell;
            if(speedUp) {
                newCell = new IceCell(col, row);
            } else {
                newCell = new SandCell(col, row);
            }
            _speedCells.add(newCell);
            board.getCells().get(row).set(col, newCell);
        }
    }

    @Override
    public void act(float delta) {
        if(_gameData.gameEnded) return;
        var normalCoolDown = _gameData.moveCoolDown - currentBonus;
        var currentSpeedCell = currentSpeedCell();
        float bonus = 0.0f;
        if(currentSpeedCell != null) {
            if(currentSpeedCell instanceof SandCell) {
                bonus = 0.05f;
            } else {
                bonus = -0.05f;
            }
        }
        var newCoolDown = Math.max(0.01f, normalCoolDown + bonus);
        currentBonus = newCoolDown - normalCoolDown;
        _gameData.moveCoolDown = newCoolDown;
    }


    private TerrainCell currentSpeedCell() {
        var head = _gameData.snake.getHead();
        for(var cell : _speedCells) {
            if(cell.collidesWith(head)) {
                return cell;
            }
        }
        return null;
    }
}
