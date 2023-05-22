package snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import snake.SnakeGame;
import snake.assets.Assets;
import snake.enums.ScreenType;

import static snake.GlobalVariables.BACKGROUND_COLOR;

public class ControlsScreen implements Screen {
    private final SnakeGame _game;

    private final Stage _stage = new Stage();

    private final Table _table = new Table();

    public ControlsScreen(SnakeGame game) {
        _game = game;
        var skin = Assets.getInstance().get(Assets.ComicSkin);

        TextButton goBack = new TextButton("Go back", skin);
        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _game.changeScreen(ScreenType.Menu);
            }
        });

        Label controls = new Label("Controls", skin, "title");
        var controlLabels = new Label[4][3];
        var secondColStyle = new Label.LabelStyle();
        secondColStyle.font = skin.getFont("button");
        secondColStyle.fontColor = Color.BLUE;
        var thirdColStyle = new Label.LabelStyle();
        thirdColStyle.font = skin.getFont("button");
        thirdColStyle.fontColor = Color.GOLD;

        controlLabels[0][0] = new Label("Up", skin, "big");
        controlLabels[0][1] = new Label("W", secondColStyle);
        controlLabels[0][2] = new Label("UP arrow", thirdColStyle);
        controlLabels[1][0] = new Label("Right", skin, "big");
        controlLabels[1][1] = new Label("D", secondColStyle);
        controlLabels[1][2] = new Label("Right arrow", thirdColStyle);
        controlLabels[2][0] = new Label("Down", skin, "big");
        controlLabels[2][1] = new Label("S", secondColStyle);
        controlLabels[2][2] = new Label("Down arrow", thirdColStyle);
        controlLabels[3][0] = new Label("Left", skin, "big");
        controlLabels[3][1] = new Label("A", secondColStyle);
        controlLabels[3][2] = new Label("Left arrow", thirdColStyle);

        _table.setFillParent(true);
        _table.add(controls).colspan(3).center();
        _table.row().pad(50, 0, 0, 0);
        for(int i = 0; i < 4; ++i) {
            _table.add(controlLabels[i][0]).left();
            _table.add(controlLabels[i][1]).padLeft(50.0f);
            _table.add(controlLabels[i][2]).right().padLeft(50.0f);
            _table.row().pad(50, 0, 0, 0);
        }

        _table.row().pad(50, 0, 0, 0);
        _table.add(goBack).colspan(3).center();
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
