package ru.chertenok.game.acceltest.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.chertenok.game.acceltest.common.Mappers;
import ru.chertenok.game.acceltest.conponent.BoundsComponent;

public class DebugRenderSystem extends IteratingSystem {

    private static final Logger log = new Logger(DebugRenderSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(BoundsComponent.class).get();
    private final Viewport viewport;
    private final Batch batch;
    private final AssetManager assetManager;


    public DebugRenderSystem(AssetManager assetManager, Viewport viewport, Batch batch) {
        super(FAMILY);
        this.viewport = viewport;
        this.batch = batch;
        this.assetManager = assetManager;
    }

    @Override
    public void update(float deltaTime) {

        log.debug("update()");
        super.update(deltaTime);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        log.debug("processEntity= " + entity);
        BoundsComponent b = Mappers.BOUNDS.get(entity);//entity.getComponent(BoundsComponent.class);
        float x = 0, y = 0, w = 0, h = 0;
        switch (b.type) {
            case Circle: {
                x = b.circle.x;
                y = b.circle.y;
                w = b.circle.radius * 2;
                h = w;
                break;
            }
            case Rectangle: {
                x = b.rectangle.x;
                y = b.rectangle.y;
                w = b.rectangle.width;
                h = b.rectangle.height;
                break;
            }
        }
        batch.draw(assetManager.get("shar.png", Texture.class), x, y, w, h);

    }
}
