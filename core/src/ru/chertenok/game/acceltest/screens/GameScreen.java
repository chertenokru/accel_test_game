package ru.chertenok.game.acceltest.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.chertenok.game.acceltest.AccelTest;
import ru.chertenok.game.acceltest.Shar;
import ru.chertenok.game.acceltest.common.EntityFactory;
import ru.chertenok.game.acceltest.config.GameConfig;
import ru.chertenok.game.acceltest.system.DebugRenderSystem;
import ru.chertenok.game.acceltest.system.RenderSystem;

import java.util.Random;

public class GameScreen implements Screen {

    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    public static final Random random = new Random();
    public Sound sound;
    private AssetManager assetManager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture img;
    private AccelTest game;

    private Texture lose;
    private float x, y;
    private float speed = 40f;
    private int sharCount = 1;
    private Shar[] shar = new Shar[sharCount];
    private float dts;
    private BitmapFont font = new BitmapFont();
    private float dtMax = 0.3f;
    private float dtCount = 0;
    float accl_y = 0;
    float accl_x = 0;

    private PooledEngine engine;
    private EntityFactory factory;


    public GameScreen(AccelTest game) {
        this.assetManager = game.getAssetManager();
        this.batch = game.getBatch();
        this.game = game;

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);


        img = assetManager.get("shar.png", Texture.class);

        lose = assetManager.get("lose.png", Texture.class);
        sound = assetManager.get("roll2.wav", Sound.class);

        for (int i = 0; i < sharCount; i++) {
            shar[i] = new Shar(new Vector2(random.nextInt(Gdx.graphics.getWidth()), random.nextInt(Gdx.graphics.getHeight())), img, sound);
            Gdx.app.log("x=", "" + shar[i].getPosition().x);
            Gdx.app.log("y=", "" + shar[i].getPosition().y);
        }

        engine = new PooledEngine();
        factory = new EntityFactory(engine);

        engine.addSystem(new RenderSystem(game,camera,viewport));
        engine.addSystem(new DebugRenderSystem(assetManager, viewport, batch));

        factory.addPlayer(100, 100);

    }

    @Override
    public void render(float delta) {

        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //  viewport.apply();
//        batch.setProjectionMatrix(viewport.getCamera().combined);
        //viewport.apply();

        batch.begin();
        engine.update(delta);
        log.debug("entities size = " + engine.getEntities().size());
//        batch.draw(lose, 300, 300);


//        for (int i = 0; i < sharCount; i++) {
//            shar[i].draw(batch);
//            Gdx.app.log("x=", "" + shar[i].getPosition().x);
//            Gdx.app.log("y=", "" + shar[i].getPosition().y);

        //      }


		/*	font.draw(batch, "x=" + Gdx.input.getAccelerometerX(), 100, 100);
			font.draw(batch, "y=" + Gdx.input.getAccelerometerY(), 200, 100);
			font.draw(batch, "z=" + Gdx.input.getAccelerometerZ(), 300, 100);
			font.draw(batch, "pos x=" + shar.getPosition().x, 100, 500);
			font.draw(batch, "pos y=" + shar.getPosition().y, 200, 500);
			font.draw(batch, "s x=" + shar.getVelosity().x, 100, 700);
			font.draw(batch, "s y=" + shar.getVelosity().y, 200, 700);
			font.draw(batch, "a x=" + shar.getVelosityAcc().x, 100, 300);
			font.draw(batch, "a y=" + shar.getVelosityAcc().y, 200, 300);
*/
        batch.end();

    }


    private void update(float dt) {
        dtCount += dt;

        if (dtCount > dtMax) {
            accl_y = -Gdx.input.getAccelerometerX();
            accl_x = Gdx.input.getAccelerometerY();
            dtCount =0;
        }
        for (int i = 0; i < sharCount; i++) {
            shar[i].update(accl_x, accl_y, dt);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }


    @Override
    public void dispose() {
        font.dispose();
    }
}
