package snake.game.controllers;

import snake.game.entities.Snake;

public abstract class SnakeController {
    protected final Snake _snake;

    protected SnakeController(Snake snake) {
        _snake = snake;
    }

    public abstract void handleInputEvent();

    public abstract void reverseDirections();
}
