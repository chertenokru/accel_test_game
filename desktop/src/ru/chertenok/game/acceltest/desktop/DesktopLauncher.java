package ru.chertenok.game.acceltest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.chertenok.game.acceltest.AccelTest;
import ru.chertenok.game.acceltest.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)GameConfig.WORLD_WIDTH;
		config.height = (int)GameConfig.WORLD_HEIGHT;
		new LwjglApplication(new AccelTest(), config);
	}
}
