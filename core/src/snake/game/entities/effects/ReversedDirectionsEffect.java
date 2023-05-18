package snake.game.entities.effects;

import snake.game.data.GameData;

public class ReversedDirectionsEffect extends NonRenewableEffect {
    public ReversedDirectionsEffect() {
        super(5);
    }

    @Override
    public void apply(GameData gameData) {
        gameData.controller.reverseDirections();
    }

    @Override
    public void expire(GameData gameData) {
        gameData.controller.reverseDirections();
    }
}
