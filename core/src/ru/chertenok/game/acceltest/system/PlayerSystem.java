package ru.chertenok.game.acceltest.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import ru.chertenok.game.acceltest.common.Mappers;
import ru.chertenok.game.acceltest.config.GameConfig;
import ru.chertenok.game.acceltest.conponent.MovementComponent;
import ru.chertenok.game.acceltest.conponent.PlayerComponent;

public class PlayerSystem extends IteratingSystem {

    private static final Logger log = new Logger(PlayerSystem.class.getName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(PlayerComponent.class, MovementComponent.class).get();
    private float accl_y = 0;
    private float accl_x = 0;
    private float dtMax;


    public PlayerSystem(Family family) {
        super(FAMILY);
        dtMax = GameConfig.ACC_TIME_UPDATE;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        movement.updateTime += deltaTime;

        if (movement.updateTime > dtMax) {
            accl_y = -Gdx.input.getAccelerometerX();
            accl_x = Gdx.input.getAccelerometerY();
            movement.updateTime = 0;
        }
        // for (int i = 0; i < sharCount; i++) {
        //   shar[i].update(accl_x, accl_y, deltaTime);


    }
}
