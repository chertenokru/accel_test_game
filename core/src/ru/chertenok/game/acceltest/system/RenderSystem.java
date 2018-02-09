package ru.chertenok.game.acceltest.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.chertenok.game.acceltest.AccelTest;
import ru.chertenok.game.acceltest.config.GameConfig;

public class RenderSystem extends EntitySystem {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private AssetManager assetManager;
    private Texture fon;


    public RenderSystem( AccelTest game, OrthographicCamera camera, Viewport viewport) {
        super(0);
        this.batch = game.getBatch();
        this.camera = camera;
        this.viewport = viewport;
        this.assetManager = game.getAssetManager();
        fon = assetManager.get("fon.jpg", Texture.class);
    }

    /**
     * The update method called every tick.
     *
     * @param deltaTime The time passed since last frame in seconds.
     */
    @Override
    public void update(float deltaTime) {


        batch.draw(fon, 0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);


    }
}
