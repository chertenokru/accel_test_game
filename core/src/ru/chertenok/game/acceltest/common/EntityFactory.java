package ru.chertenok.game.acceltest.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import ru.chertenok.game.acceltest.config.GameConfig;
import ru.chertenok.game.acceltest.conponent.BoundsComponent;
import ru.chertenok.game.acceltest.conponent.MovementComponent;
import ru.chertenok.game.acceltest.conponent.PlayerComponent;

public class EntityFactory {
    private final PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public void addPlayer(float x, float y) {
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.type = BoundsComponent.BoundType.Circle;
        bounds.circle.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        movement.xSpeed = 0;
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        Entity entity = engine.createEntity();
        entity.add(bounds);
        entity.add(movement);
        entity.add(player);
        engine.addEntity(entity);
    }

}
