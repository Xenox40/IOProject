package snake.game.entities.effects;

import com.badlogic.gdx.math.MathUtils;
import snake.game.data.GameData;

public class GodModeEffect extends RenewableEffect implements ActingEffect {
    public GodModeEffect() {
        super(10f);
    }

    @Override
    public void apply(GameData gameData) {
        gameData.GodMode = true;
    }

    @Override
    public void expire(GameData gameData) {
        gameData.GodMode = false;
        gameData.snake.setAlpha(1.0f);
    }

    @Override
    public void renew() {
        _timeLeft = 10f;
    }

    @Override
    public void act(GameData gameData) {
        var alpha = Math.max(0.1f, Math.abs(MathUtils.sin(_timeLeft * 4)));
        gameData.snake.setAlpha(alpha);
    }
}
