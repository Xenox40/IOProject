package snake;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(GlobalVariables.WIDTH, GlobalVariables.HEIGHT);
		config.setTitle("Snake");
		config.setResizable(false);
		config.setWindowIcon("icons/window.png");
		new Lwjgl3Application(new SnakeGame(), config);
	}
}
