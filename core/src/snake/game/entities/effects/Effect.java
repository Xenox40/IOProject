package snake.game.entities.effects;

import snake.game.data.GameData;

public abstract class Effect {
    protected float _timeLeft;

    public Effect(float timeLeft) {
        _timeLeft = timeLeft;
    }
    public abstract void apply(GameData gameData);
    public abstract void expire(GameData gameData);
    final public void timePassed(float delta) {
        _timeLeft -= delta;
    }

    final public float timeLeft() {
        return _timeLeft;
    }
}
