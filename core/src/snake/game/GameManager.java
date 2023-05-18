package snake.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import snake.factories.*;
import snake.game.controllers.SnakeKeyboardController;
import snake.game.data.GameData;
import snake.game.entities.Board;
import snake.game.entities.Snake;
import snake.game.systems.*;


public class GameManager {
    private final SpriteBatch _batch;
    private static final int boardWidth = 16, boardHeight = 16, maxPointPickups = 2, maxExpiringPickups = 2;
    private static final int initialSnakeSize = 3;
    private static final float initialMoveCoolDown = 0.12f, initialPickupCoolDown = 5f, pickupChance = 0.7f;
    private GameData _gameData;

    private final ExpiringPickupFactory _pickupFactory;

    private final Array<GameSystem> systems = new Array<>();
    public GameManager(SpriteBatch batch) {
        _batch = batch;
        _pickupFactory = new RandomExpiringPickupFactory(new SpeedDownFactory(), new SpeedUpFactory(),
                new DirectionReverseFactory());
        init();
    }

    private void init() {
        var board = new Board(boardWidth, boardHeight);
        var snake = new Snake(initialSnakeSize - 1, boardHeight / 2, initialSnakeSize);
        var snakeController = new SnakeKeyboardController(snake);

        _gameData = new GameData(board, snake,
                snakeController, maxExpiringPickups, maxPointPickups,
                initialMoveCoolDown, initialPickupCoolDown, pickupChance);

        systems.add(new SnakeMovementSystem(_gameData));
        systems.add(new CollisionSystem(_gameData));
        systems.add(new PointPickupSystem(_gameData));
        systems.add(new ExpiringPickupSystem(_gameData, _pickupFactory));
        systems.add(new EffectsSystem(_gameData));
    }

    public void step(float delta) {
        for(var system : systems) {
            system.act(delta);
        }
    }

    public boolean gameEnded() {
        return _gameData.gameEnded;
    }

    public boolean gameWon() {
        return _gameData.snake.getSize() == boardWidth * boardHeight;
    }
    public void restart() {
        systems.clear();
        init();
    }

    public int getScore() {
        return _gameData.score;
    }
    public void draw() {
        _gameData.board.draw(_batch);
        _gameData.snake.draw(_batch);
        for(var pickup : _gameData.pointPickups) {
            pickup.draw(_batch);
        }
        for(var pickup : _gameData.expiringPickups) {
            pickup.draw(_batch);
        }
    }
}
