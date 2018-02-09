package ru.chertenok.game.acceltest.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import ru.chertenok.game.acceltest.config.GameConfig;
import ru.chertenok.game.acceltest.conponent.BoundsComponent;

public class EntityFactory {
    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void addPlayer(float x, float y) {
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.type = BoundsComponent.BoundType.Circle;
        bounds.circle.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);
        Entity entity = engine.createEntity();
        entity.add(bounds);
        engine.addEntity(entity);
    }

}
