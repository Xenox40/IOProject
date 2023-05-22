package snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import snake.SnakeGame;
import snake.assets.Assets;
import snake.enums.ScreenType;

import static snake.GlobalVariables.BACKGROUND_COLOR;

public class MenuScreen implements Screen {
    private final SnakeGame _game;
    private final Stage _stage;

    private final Table _table;

    public MenuScreen(SnakeGame game) {
        _game = game;

        _stage = new Stage();

        BitmapFont font = Assets.getInstance().get(Assets.MonoFont);

        font.setColor(1f, 1f, 1f, 1f);
        font.getData().setScale(0.8f);

        Skin skin = Assets.getInstance().get(Assets.ComicSkin);
        _table = new Table();
        _table.setFillParent(true);

        TextButton play = new TextButton("Play", skin);
        TextButton options = new TextButton("Options", skin);
        TextButton controls = new TextButton("Controls", skin);
        TextButton exit = new TextButton("Exit", skin);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _game.changeScreen(ScreenType.Game);
            }
        });

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _game.changeScreen(ScreenType.Options);
            }
        });

        controls.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _game.changeScreen(ScreenType.Controls);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        _table.add(play).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
        _table.add(options).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
        _table.add(controls).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
        _table.add(exit).fillX().uniform().width(250);
        _table.row().pad(50, 0, 0, 0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(_stage);
        _stage.addActor(_table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND_COLOR);

        _stage.act(delta);
        _stage.draw();
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
