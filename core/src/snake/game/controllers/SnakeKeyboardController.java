package snake.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import snake.enums.Direction;
import snake.game.entities.Snake;

import static snake.enums.Direction.opposite;

public class SnakeKeyboardController extends SnakeController {
    private final Array<Array<Integer>> _keys;
    public SnakeKeyboardController(Snake snake) {
        super(snake);
        _keys = new Array<>();
        var upKeys = new Array<Integer>();
        var rightKeys = new Array<Integer>();
        var downKeys = new Array<Integer>();
        var leftKeys = new Array<Integer>();
        upKeys.add(Input.Keys.W, Input.Keys.UP);
        rightKeys.add(Input.Keys.D, Input.Keys.RIGHT);
        downKeys.add(Input.Keys.S, Input.Keys.DOWN);
        leftKeys.add(Input.Keys.A, Input.Keys.LEFT);
        _keys.add(upKeys, rightKeys, downKeys, leftKeys);
    }


    public void handleInputEvent() {
        for(int i = 0; i < 4; ++i) {
            if(keyPressed(_keys.get(i))) {
                changeDirection(Direction.values()[i]);
            }
        }
    }

    private boolean keyPressed(Array<Integer> keys) {
        for (var key: keys) {
            if(Gdx.input.isKeyJustPressed(key)) return true;
        }
        return false;
    }

    @Override
    public void reverseDirections() {
        _keys.swap(0, 2);
        _keys.swap(1, 3);
    }

    private void changeDirection(Direction direction) {
        var currentDirection = _snake.getDirection();
        var directionIn1Step = _snake.getDirectionIn1Step();
        var directionIn2Steps = _snake.getDirectionIn2Steps();
        if(directionIn1Step != null) {
            if(directionIn2Steps == null && direction != directionIn1Step && ! opposite(direction, directionIn1Step)) {
                _snake.setDirectionIn2Steps(direction);
            }
        } else if(direction != currentDirection && !opposite(currentDirection, direction)) {
            _snake.setDirectionIn1Step(direction);
        }
    }
}
