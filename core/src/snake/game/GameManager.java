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
    private static final int boardWidth = 16, boardHeight = 16, initialSnakeSize = 3, initialLives = 3, maxWalls = 5,
            initialMaxPointPickups = 3, initialMaxExpiringPickups = 3, minPointPickups = 1, minExpiringPickups = 1,
            maxTeleports = 3, speedAreaLength = 3, speedAreaCount = 3;
    private static final float initialMoveCoolDown = 0.12f, initialPickupCoolDown = 5f, pickupChance = 0.7f,
            minMoveCoolDown = 0.05f, maxMoveCoolDown = 0.2f, minPickupCoolDown = 1.0f;

    private GameData _gameData;

    private final ExpiringPickupFactory _pickupFactory;

    private final Array<GameSystem> systems = new Array<>();
    public GameManager(SpriteBatch batch) {
        _batch = batch;
        _pickupFactory = new RandomExpiringPickupFactory(
                new SpeedDownFactory(), new SpeedUpFactory(), new DirectionReverseFactory(),
                new GodModeFactory(), new MorePickupsFactory(), new LessPickupsFactory(),
                new NegativePointFactory(), new HPIncreaseFactory(), new HPDecreaseFactory(),
                new BombFactory(), new MorePointsFactory(), new LessPointsFactory(),
                new MoreFrequentPickupsFactory(), new LessFrequentPickupsFactory()
        );
        init();
    }

    private void init() {
        var board = new Board(boardWidth, boardHeight);
        var snake = new Snake(initialSnakeSize - 1, boardHeight / 2, initialSnakeSize);
        var snakeController = new SnakeKeyboardController(snake);

        _gameData = new GameData(
                board, snake, snakeController, initialMaxExpiringPickups, initialMaxPointPickups,
                minExpiringPickups, minPointPickups, maxWalls, speedAreaLength, initialMoveCoolDown, initialPickupCoolDown,
                pickupChance, initialLives, maxTeleports, speedAreaCount, minMoveCoolDown, maxMoveCoolDown,
                minPickupCoolDown
        );

        systems.add(new SnakeMovementSystem(_gameData));
        systems.add(new PointPickupSystem(_gameData));
        systems.add(new TeleportSystem(_gameData));
        systems.add(new WallsSystem(_gameData));
        systems.add(new ExpiringPickupSystem(_gameData, _pickupFactory));
        systems.add(new EffectsSystem(_gameData));
        systems.add(new SnakeSelfCollisionSystem(_gameData));
        systems.add(new SpeedTerrainSystem(_gameData));
        systems.add(new EndGameSystem(_gameData));
        systems.add(new RenderingSystem(_gameData, _batch));
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
        return _gameData.gameWon;
    }
    public void restart() {
        systems.clear();
        init();
    }

    public int getScore() {
        return _gameData.score;
    }

    public int getLives() {
        return _gameData.lives;
    }
}
