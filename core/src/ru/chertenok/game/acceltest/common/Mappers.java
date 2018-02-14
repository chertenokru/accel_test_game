package ru.chertenok.game.acceltest.common;

import com.badlogic.ashley.core.ComponentMapper;
import ru.chertenok.game.acceltest.conponent.BoundsComponent;
import ru.chertenok.game.acceltest.conponent.MovementComponent;

public class Mappers {
    public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT = ComponentMapper.getFor(MovementComponent.class);


    private Mappers() {

    }

}
