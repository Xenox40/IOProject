package snake.game.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import snake.game.controllers.SnakeController;
import snake.game.entities.Board;
import snake.game.entities.Snake;
import snake.game.entities.cells.pickup.PointCell;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;
import snake.game.entities.effects.Effect;

public class GameData {
    public final Board board;
    public final Snake snake;
    public final SnakeController controller;

    public boolean GodMode;
    public int lives;
    public final Array<Effect> appliedEffects = new Array<>();
    public final Queue<Effect> queuedEffects = new Queue<>();
    public int score;
    public int maxExpiringPickups, maxPointPickups;

    public final int minExpiringPickups, minPointPickups;
    public float moveCoolDown;
    public float pickupCoolDown, pickupChance;

    public boolean gameEnded;

    public final Array<PointCell> pointPickups = new Array<>();
    public final Array<ExpiringPickupCell> expiringPickups = new Array<>();

    public GameData(Board board, Snake snake, SnakeController controller, int maxPickups, int maxPointPickups,
                    int minExpiringPickups, int minPointPickups, float moveCoolDown, float pickupCoolDown, float pickupChance, int lives) {
        this.board = board;
        this.snake = snake;
        this.controller = controller;
        this.maxExpiringPickups = maxPickups;
        this.maxPointPickups = maxPointPickups;
        this.minExpiringPickups = minExpiringPickups;
        this.minPointPickups = minPointPickups;
        this.moveCoolDown = moveCoolDown;
        this.pickupCoolDown = pickupCoolDown;
        this.pickupChance = pickupChance;
        this.lives = lives;
    }
}
