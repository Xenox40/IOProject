package snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import snake.SnakeGame;
import snake.enums.ScreenType;
import snake.game.GameManager;
import snake.utils.Assets;

import static snake.utils.GlobalVariables.*;


public class GameScreen implements Screen {
    private final SnakeGame _game;
    private GameManager _gameManager;

    private final Image _pointImage = new Image(Assets.getInstance().get(Assets.PointTexture));
    private final Image _hpImage = new Image(Assets.getInstance().get(Assets.HeadTexture));

    private final BitmapFont _font;

    private final Stage _stage = new Stage();
    private final Table _table = new Table();
    private final Label _endLabel;

    public GameScreen(SnakeGame game) {
        _game = game;
        Gdx.input.setInputProcessor(_stage);

        _font = Assets.getInstance().get(Assets.MonoFont);
        _font.setColor(1f, 1f, 1f, 1f);
        _font.getData().setScale(0.8f);

        Skin skin = Assets.getInstance().get(Assets.ComicSkin);

        TextButton playAgain = new TextButton("Play Again", skin);
        TextButton menu = new TextButton("Menu", skin);
        TextButton exit = new TextButton("Exit", skin);

        playAgain.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _stage.clear();
                _gameManager.restart();
            }
        });
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _stage.clear();
                _game.changeScreen(ScreenType.Menu);
            }
        });
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _stage.clear();
                Gdx.app.exit();
            }
        });

        _endLabel = new Label("", skin);
        _endLabel.setFontScale(2.2f);

        _table.setFillParent(true);
        _table.add(_endLabel).center();
        _table.row().pad(20, 0, 0, 0);
        _table.add(playAgain).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
        _table.add(menu).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
        _table.add(exit).fillX().uniform().width(250);

        _pointImage.setScale(1.8f);
        _pointImage.setPosition(WIDTH / 2f - 2.8f * CELL_SIZE, HEIGHT - 2.3f * CELL_SIZE);

        _hpImage.setScale(1.4f);
        _hpImage.setPosition(WIDTH / 1.5f - 1.0f * CELL_SIZE, HEIGHT - 2.2f * CELL_SIZE);

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(_stage);
        _gameManager = new GameManager(_game.batch());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOR);
        _game.batch().begin();

        _gameManager.step(delta);

        _pointImage.draw(_game.batch(), 1.0f);
        _font.draw(_game.batch(), Integer.toString(_gameManager.getScore()), _pointImage.getX() + 2.3f * CELL_SIZE,
                _pointImage.getY() + 1.3f * CELL_SIZE);

        _hpImage.draw(_game.batch(), 1.0f);
        _font.draw(_game.batch(), Integer.toString(_gameManager.getLives()), _hpImage.getX() + 2.2f * CELL_SIZE,
                _hpImage.getY() + 1.1f * CELL_SIZE);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if(_gameManager.gameEnded()) {
            _endLabel.setText(_gameManager.gameWon() ? "YOU WON!" : "YOU LOST!");
            if(_stage.getActors().size < 1) _stage.addActor(_table);
            _stage.act(delta);
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
        _stage.clear();
    }

    @Override
    public void dispose() {
        _stage.dispose();
    }
}
