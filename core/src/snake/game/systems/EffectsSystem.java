package snake.game.systems;

import com.badlogic.gdx.utils.Array;
import snake.game.data.GameData;
import snake.game.entities.effects.NonRenewableEffect;
import snake.game.entities.effects.RenewableEffect;

public class EffectsSystem implements GameSystem {
    private final GameData _gameData;

    public EffectsSystem(GameData gameData) {
        _gameData = gameData;
    }

    @Override
    public void act(float delta) {
        handleActiveEffects(delta);
        handleQueue();
    }

    private void handleActiveEffects(float delta) {
        for(var effect : new Array<>(_gameData.appliedEffects)) {
            effect.timePassed(delta);
            if(effect.timeLeft() <= 0) {
                effect.expire(_gameData);
                _gameData.appliedEffects.removeValue(effect, true);
            }
        }
    }

    private void handleQueue() {
        while(_gameData.queuedEffects.notEmpty()) {
            var newEffect = _gameData.queuedEffects.removeFirst();
            var newEffectClass = newEffect.getClass();
            var forAddition = true;
            for(int i = 0; i < _gameData.appliedEffects.size; ++i) {
                var effect = _gameData.appliedEffects.get(i);
                if(effect.getClass() == newEffectClass) {
                    if(effect instanceof RenewableEffect renewableEffect) {
                        renewableEffect.renew();
                        forAddition = false;
                    } else if(effect instanceof NonRenewableEffect nonRenewableEffect) {
                        nonRenewableEffect.expire(_gameData);
                        _gameData.appliedEffects.removeIndex(i);
                        forAddition = false;
                    }
                    break;
                }
            }
            if(forAddition) {
                newEffect.apply(_gameData);
                _gameData.appliedEffects.add(newEffect);
            }
        }
    }
}
