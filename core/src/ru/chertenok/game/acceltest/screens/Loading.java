package ru.chertenok.game.acceltest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.chertenok.game.acceltest.AccelTest;
import ru.chertenok.game.acceltest.config.GameConfig;

public class Loading extends ScreenAdapter {

    // == constants ==
    private static final Logger log = new Logger(Loading.class.getName(), Logger.DEBUG);

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f; // world units
    private static final float PROGRESS_BAR_HEIGHT = 60; // world units

    // == attributes ==
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float progress;
    private float waitTime = 0.75f;
    private boolean changeScreen;

    private final AccelTest game;
    private final AssetManager assetManager;


    // == constructors ==
    public Loading(AccelTest game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    // == public methods ==
    @Override
    public void show() {
        log.debug("show");
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera);
        renderer = new ShapeRenderer();

//        assetManager.load(AssetDescriptors.FONT);
//        assetManager.load(AssetDescriptors.GAME_PLAY);
//        assetManager.load(AssetDescriptors.UI_SKIN);
//        assetManager.load(AssetDescriptors.HIT_SOUND);
        assetManager.load("shar.png", Texture.class);
        assetManager.load("fon.jpg", Texture.class);
        assetManager.load("lose.png", Texture.class);
        assetManager.load("roll2.wav", Sound.class);


    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        draw();

        renderer.end();

        if (changeScreen) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        log.debug("hide");
        // NOTE: screens dont dispose automatically
        dispose();
    }

    @Override
    public void dispose() {
        log.debug("dispose");
        renderer.dispose();
        renderer = null;
    }

    // == private methods ==
    private void update(float delta) {
        // progress is between 0 and 1
        progress = assetManager.getProgress();

        // update returns true when all assets are loaded
        if (assetManager.update()) {
            waitTime -= delta;

            if (waitTime <= 0) {
                changeScreen = true;
            }
        }
    }

    private void draw() {
        float progressBarX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;

        renderer.rect(progressBarX, progressBarY,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT
        );
    }

    private static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
