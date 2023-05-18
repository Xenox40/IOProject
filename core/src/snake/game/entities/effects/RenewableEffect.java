package snake.game.entities.effects;

public abstract class RenewableEffect extends Effect {
    public RenewableEffect(float timeLeft) {
        super(timeLeft);
    }

    public void renew() {
        _timeLeft = 10f;
    }
}
