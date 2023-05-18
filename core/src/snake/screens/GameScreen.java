package snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import snake.SnakeGame;
import snake.game.GameManager;
import snake.utils.Assets;

import static snake.utils.GlobalVariables.*;


public class GameScreen implements Screen {
    private final SnakeGame _game;
    private final GameManager _gameManager;

    private final Image _pointImage = new Image(Assets.getInstance().get(Assets.PointTexture));

    private final BitmapFont _font;

    private final Stage _stage;

    public GameScreen(SnakeGame game) {
        _game = game;
        _stage = new Stage();
        Gdx.input.setInputProcessor(_stage);

        _font = Assets.getInstance().get(Assets.MonoFont);
        _font.setColor(1f, 1f, 1f, 1f);
        _font.getData().setScale(0.8f);

        Skin _skin = Assets.getInstance().get(Assets.ComicSkin);

        TextButton _button = new TextButton("Play Again", _skin);
        _button.setPosition(WIDTH / 3.7f, HEIGHT / 1.6f);
        _button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _gameManager.restart();
            }
        });

        _stage.addActor(_button);

        _pointImage.setScale(1.8f);
        _pointImage.setPosition(WIDTH / 2f - 2.8f * CELL_SIZE, HEIGHT - 2.3f * CELL_SIZE);

        _gameManager = new GameManager(_game.batch());
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.6f, 0.2f, 1.0f);

        _gameManager.step(delta);

        _game.batch().begin();

        _gameManager.draw();
        _pointImage.draw(_game.batch(), 1.0f);
        _font.draw(_game.batch(), Integer.toString(_gameManager.getScore()), _pointImage.getX() + 2.3f * CELL_SIZE,
                _pointImage.getY() + 1.3f * CELL_SIZE);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(_gameManager.gameEnded()) {
            _font.draw(_game.batch(), _gameManager.gameWon() ? "YOU WON!" : "YOU LOST!", WIDTH / 3f, HEIGHT / 5f * 4);
            _stage.act();
            _stage.draw();
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                _gameManager.restart();
            }
        }

        _game.batch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        _stage.dispose();
    }
}
