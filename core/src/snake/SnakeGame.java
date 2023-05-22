package snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import snake.assets.Assets;
import snake.enums.ScreenType;
import snake.screens.ControlsScreen;
import snake.screens.GameScreen;
import snake.screens.MenuScreen;
import snake.screens.OptionsScreen;


public class SnakeGame extends Game {
	private Assets _assets;
	private SpriteBatch _batch;
	private MenuScreen _menuScreen;
	private OptionsScreen _optionsScreen;
	private ControlsScreen _controlsScreen;
	private GameScreen _gameScreen;

	@Override
	public void create () {
		_batch = new SpriteBatch();

		_assets = Assets.getInstance();

		_assets.load();
		_assets.finishLoading();

		_menuScreen = new MenuScreen(this);
		setScreen(_menuScreen);
	}

	public void changeScreen(ScreenType screenType) {
		switch (screenType) {
			case Menu -> setScreen(_menuScreen);
			case Options -> {
				if (_optionsScreen == null) _optionsScreen = new OptionsScreen(this);
				setScreen(_optionsScreen);
			}
			case Controls -> {
				if(_controlsScreen == null) _controlsScreen = new ControlsScreen(this);
				setScreen(_controlsScreen);
			}
			case Game -> {
				if (_gameScreen == null) _gameScreen = new GameScreen(this);
				setScreen(_gameScreen);
			}
		}
	}

	public SpriteBatch batch() {
		return _batch;
	}
	@Override
	public void dispose () {
		_assets.dispose();
		_batch.dispose();
		_menuScreen.dispose();
		if(_optionsScreen != null) _optionsScreen.dispose();
		if(_controlsScreen != null) _controlsScreen.dispose();
		if(_gameScreen != null) _gameScreen.dispose();
	}
}
