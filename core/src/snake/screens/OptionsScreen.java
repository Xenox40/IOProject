package snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import snake.SnakeGame;
import snake.enums.ScreenType;
import snake.utils.Assets;
import snake.utils.PreferencesManager;

import java.text.DecimalFormat;

import static snake.utils.GlobalVariables.BACKGROUND_COLOR;

public class OptionsScreen implements Screen {
    private final SnakeGame _game;
    private final PreferencesManager _preferencesManager;
    private final Stage _stage = new Stage();
    private final Table _table = new Table();
    private final Slider _minCoolDownSlider, _maxCoolDownSlider, _initialCoolDownSlider;
    private static final DecimalFormat _decimalFormat = new DecimalFormat("0.00");

    private final Label _minSliderMinValueLabel, _minSliderMaxValueLabel, _minSliderValueLabel,
            _maxSliderMinValueLabel, _maxSliderMaxValueLabel, _maxSliderValueLabel,
            _initialSliderMinValueLabel, _initialSliderMaxValueLabel, _initialSliderValueLabel;

    public OptionsScreen(SnakeGame game) {
        _game = game;
        _preferencesManager = PreferencesManager.getInstance();

        var skin = Assets.getInstance().get(Assets.ComicSkin);
        var buttonFont = skin.getFont("button");
        var minSliderColor = Color.BLUE;
        var maxSliderColor = Color.CORAL;
        var initialSliderColor = Color.GOLD;
        var minSliderLabelsStyle = new Label.LabelStyle(buttonFont, minSliderColor);
        var maxSliderLabelsStyle = new Label.LabelStyle(buttonFont, maxSliderColor);
        var initialSliderLabelsStyle = new Label.LabelStyle(buttonFont, initialSliderColor);

        _minSliderMinValueLabel = new Label("", minSliderLabelsStyle);
        _minSliderMaxValueLabel = new Label("", minSliderLabelsStyle);
        _minSliderValueLabel = new Label("", minSliderLabelsStyle);
        _maxSliderMinValueLabel = new Label("", maxSliderLabelsStyle);
        _maxSliderMaxValueLabel = new Label("", maxSliderLabelsStyle);
        _maxSliderValueLabel = new Label("", maxSliderLabelsStyle);
        _initialSliderMinValueLabel = new Label("", initialSliderLabelsStyle);
        _initialSliderMaxValueLabel = new Label("", initialSliderLabelsStyle);
        _initialSliderValueLabel = new Label("", initialSliderLabelsStyle);
        var titleLabel = new Label("Options", skin, "title");

        var minSliderTitle = new Label("Min move cool down", minSliderLabelsStyle);
        var maxSliderTitle = new Label("Max move cool down", maxSliderLabelsStyle);
        var initialSliderTitle = new Label("Initial move cool down", initialSliderLabelsStyle);

        _minCoolDownSlider = new Slider(0, 0, 0.01f,
                false, skin);
        _maxCoolDownSlider = new Slider(0, 0, 0.01f,
                false, skin);
        _initialCoolDownSlider = new Slider(0, 0, 0.01f,
                false, skin);

        _minCoolDownSlider.setColor(minSliderColor);
        _maxCoolDownSlider.setColor(maxSliderColor);
        _initialCoolDownSlider.setColor(initialSliderColor);
        _minCoolDownSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                var value = _minCoolDownSlider.getValue();
                _maxCoolDownSlider.setRange(value, _preferencesManager.maxCoolDownLimit);
                _initialCoolDownSlider.setRange(value, _maxCoolDownSlider.getValue());
                refreshLabels();
            }
        });
        setSliderValuesFromPreferences();

        _maxCoolDownSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                var value = _maxCoolDownSlider.getValue();
                _minCoolDownSlider.setRange(_preferencesManager.minCoolDownLimit, value);
                _initialCoolDownSlider.setRange(_minCoolDownSlider.getValue(), value);
                refreshLabels();
            }
        });

        _initialCoolDownSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                refreshLabels();
            }
        });

        var discardExit = new TextButton("Discard", skin);
        var saveExit = new TextButton("Save", skin);
        var restoreDefaults = new TextButton("Restore defaults", skin);
        discardExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                _game.changeScreen(ScreenType.Menu);
            }
        });
        saveExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                var minCoolDown = _minCoolDownSlider.getValue();
                var maxCoolDown = _maxCoolDownSlider.getValue();
                var initialCoolDown = _initialCoolDownSlider.getValue();
                _preferencesManager.setMinCoolDown(minCoolDown);
                _preferencesManager.setMaxCoolDown(maxCoolDown);
                _preferencesManager.setInitialCoolDown(initialCoolDown);
                _game.changeScreen(ScreenType.Menu);
            }
        });
        restoreDefaults.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setDefaultsForSliders();
            }
        });
        refreshLabels();

        _table.setFillParent(true);
        _table.add(titleLabel).colspan(3).center();
        _table.row().padTop(10);
        _table.add(minSliderTitle).colspan(3).center();
        _table.row().padTop(10);
        _table.add(_minSliderMinValueLabel);
        _table.add(_minCoolDownSlider).padLeft(50).padRight(50);
        _table.add(_minSliderMaxValueLabel);
        _table.row();
        _table.add(_minSliderValueLabel).colspan(3).center();
        _table.row().padTop(10);
        _table.add(maxSliderTitle).colspan(3).center();
        _table.row().padTop(10);
        _table.add(_maxSliderMinValueLabel);
        _table.add(_maxCoolDownSlider);
        _table.add(_maxSliderMaxValueLabel);
        _table.row();
        _table.add(_maxSliderValueLabel).colspan(3).center();
        _table.row().padTop(10);
        _table.add(initialSliderTitle).colspan(3).center();
        _table.row().padTop(10);
        _table.add(_initialSliderMinValueLabel);
        _table.add(_initialCoolDownSlider);
        _table.add(_initialSliderMaxValueLabel);
        _table.row();
        _table.add(_initialSliderValueLabel).colspan(3).center();
        _table.row().padTop(15);
        _table.add(saveExit).colspan(3).center().fillX().uniform();
        _table.row().padTop(10);
        _table.add(restoreDefaults).colspan(3).center().fillX().uniform();
        _table.row().padTop(10);
        _table.add(discardExit).colspan(3).center().fillX().uniform();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(_stage);
        setSliderValuesFromPreferences();

        _stage.addActor(_table);
    }

    private void setSliderValuesFromPreferences() {
        var minCoolDown = _preferencesManager.getMinCoolDown();
        var maxCoolDown = _preferencesManager.getMaxCoolDown();
        var initialCoolDown = _preferencesManager.getInitialCoolDown();
        setValuesWithWithDefaultRanges(minCoolDown, maxCoolDown, initialCoolDown);
    }

    private void setDefaultsForSliders() {
        var minCoolDown = _preferencesManager.minCoolDownDefault;
        var maxCoolDown = _preferencesManager.maxCoolDownDefault;
        var initialCoolDown = _preferencesManager.initialCoolDownDefault;
        setValuesWithWithDefaultRanges(minCoolDown, maxCoolDown, initialCoolDown);
    }

    private void setValuesWithWithDefaultRanges(float minCoolDown, float maxCoolDown, float initialCoolDown) {
        _minCoolDownSlider.setRange(_preferencesManager.minCoolDownLimit, maxCoolDown);
        _minCoolDownSlider.setValue(minCoolDown);
        _maxCoolDownSlider.setRange(minCoolDown, _preferencesManager.maxCoolDownLimit);
        _maxCoolDownSlider.setValue(maxCoolDown);
        _initialCoolDownSlider.setRange(minCoolDown, maxCoolDown);
        _initialCoolDownSlider.setValue(initialCoolDown);
    }

    private void refreshLabels() {
        _minSliderMinValueLabel.setText(_decimalFormat.format(_minCoolDownSlider.getMinValue()));
        _minSliderMaxValueLabel.setText(_decimalFormat.format(_minCoolDownSlider.getMaxValue()));
        _minSliderValueLabel.setText(_decimalFormat.format(_minCoolDownSlider.getValue()));
        _maxSliderMinValueLabel.setText(_decimalFormat.format(_maxCoolDownSlider.getMinValue()));
        _maxSliderMaxValueLabel.setText(_decimalFormat.format(_maxCoolDownSlider.getMaxValue()));
        _maxSliderValueLabel.setText(_decimalFormat.format(_maxCoolDownSlider.getValue()));
        _initialSliderMinValueLabel.setText(_decimalFormat.format(_initialCoolDownSlider.getMinValue()));
        _initialSliderMaxValueLabel.setText(_decimalFormat.format(_initialCoolDownSlider.getMaxValue()));
        _initialSliderValueLabel.setText(_decimalFormat.format(_initialCoolDownSlider.getValue()));
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
