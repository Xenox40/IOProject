package snake.game.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import snake.game.controllers.SnakeController;
import snake.game.entities.Board;
import snake.game.entities.Snake;
import snake.game.entities.cells.pickup.PointCell;
import snake.game.entities.cells.pickup.expiringPickup.ExpiringPickupCell;
import snake.game.entities.cells.terrain.WallCell;
import snake.game.entities.effects.Effect;

public class GameData {
    public final Board board;
    public final Snake snake;
    public final SnakeController controller;

    public final Array<WallCell> walls = new Array<>();
    public final Array<PointCell> pointPickups = new Array<>();
    public final Array<ExpiringPickupCell> expiringPickups = new Array<>();
    public final Array<Effect> appliedEffects = new Array<>();
    public final Queue<Effect> queuedEffects = new Queue<>();
    public boolean GodMode, gameEnded, gameWon;
    public int maxExpiringPickups, maxPointPickups, score, lives;

    public final int minExpiringPickups, minPointPickups, maxWalls;
    public float moveCoolDown, pickupCoolDown;
    public final float pickupChance, minMoveCoolDown, maxMoveCoolDown, minPickupCoolDown;



    public GameData(Board board, Snake snake, SnakeController controller, int maxPickups, int maxPointPickups,
                    int minExpiringPickups, int minPointPickups, int maxWalls, float moveCoolDown, float pickupCoolDown, float pickupChance, int lives, float minMoveCoolDown, float maxMoveCooldown, float minPickupCoolDown) {
        this.board = board;
        this.snake = snake;
        this.controller = controller;
        this.maxExpiringPickups = maxPickups;
        this.maxPointPickups = maxPointPickups;
        this.minExpiringPickups = minExpiringPickups;
        this.minPointPickups = minPointPickups;
        this.maxWalls = maxWalls;
        this.moveCoolDown = moveCoolDown;
        this.pickupCoolDown = pickupCoolDown;
        this.pickupChance = pickupChance;
        this.lives = lives;
        this.minMoveCoolDown = minMoveCoolDown;
        this.maxMoveCoolDown = maxMoveCooldown;
        this.minPickupCoolDown = minPickupCoolDown;
    }
}
