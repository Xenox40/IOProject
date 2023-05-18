package snake.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import snake.enums.Direction;
import snake.game.entities.cells.snake.SnakeBodyCell;
import snake.game.entities.cells.snake.SnakeCell;
import snake.game.entities.cells.snake.SnakeHeadCell;

import static snake.enums.Direction.Right;
import static snake.enums.Direction.prev;
import static snake.utils.GlobalVariables.CELL_SIZE;

public class Snake {
    /*
    head at index 0
     */
    private final Array<SnakeCell> _cells = new Array<>();
    private Direction _direction, _directionIn1Step = null, _directionIn2Steps = null;

    private float lastPosX, lastPosY;

    public Snake(int startX, int startY, int initialSize) {
        _cells.add(new SnakeHeadCell(startX, startY));
        for(int i = 1; i <= initialSize - 1; ++i) {
            _cells.add(new SnakeBodyCell(startX - i, startY));
        }
        _direction = Right;
    }

    public void move(int width, int height) {
        var lastBodyCell = _cells.get(_cells.size - 1);
        lastPosX = lastBodyCell.getX();
        lastPosY = lastBodyCell.getY();

        width *=  CELL_SIZE;
        height *= CELL_SIZE;

        for(int i = _cells.size - 1; i > 0; --i) {
            _cells.get(i).setPosition(_cells.get(i - 1));
        }

        var head = (SnakeHeadCell)_cells.get(0);

        if(_directionIn1Step != null) {
            head.rotate(prev(_directionIn1Step) == _direction);
            _direction = _directionIn1Step;
            _directionIn1Step = _directionIn2Steps;
            _directionIn2Steps = null;
        }

        float x = head.getX(), y = head.getY();
        switch (_direction) {
            case Up -> y += CELL_SIZE;
            case Right -> x += CELL_SIZE;
            case Down -> y -= CELL_SIZE;
            case Left -> x -= CELL_SIZE;
        }
        if(x < 0) {
            x = width - CELL_SIZE;
        } else if(x >= width) {
            x = 0;
        } else if(y < 0) {
            y = height - CELL_SIZE;
        } else if(y >= height) {
            y = 0;
        }
        head.setPosition(x, y);

    }

    public void increaseSize() {
        _cells.add(new SnakeBodyCell(lastPosX, lastPosY));
    }

    public int getSize() {
        return _cells.size;
    }

    public Direction getDirection() {
        return _direction;
    }

    public Direction getDirectionIn1Step() {
        return _directionIn1Step;
    }

    public void setDirectionIn1Step(Direction direction) {
        _directionIn1Step = direction;
    }

    public void setDirectionIn2Steps(Direction direction) {
        _directionIn2Steps = direction;
    }
    public Direction getDirectionIn2Steps() {
        return _directionIn2Steps;
    }

    public void setDead() {
        for(var cell : _cells) {
            cell.setDead();
        }
    }

    public SnakeHeadCell getHead() {
        return (SnakeHeadCell) _cells.get(0);
    }

    public Array<SnakeCell> getCells() {
        return _cells;
    }
    public void draw(SpriteBatch batch) {
        for(int i = _cells.size - 1; i >= 0; --i) {
            _cells.get(i).draw(batch);
        }
    }
}
