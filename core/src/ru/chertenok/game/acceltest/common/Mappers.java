package ru.chertenok.game.acceltest.common;

import com.badlogic.ashley.core.ComponentMapper;
import ru.chertenok.game.acceltest.conponent.BoundsComponent;

public class Mappers {
    public static final ComponentMapper<BoundsComponent> BOUNDS = ComponentMapper.getFor(BoundsComponent.class);

    private Mappers() {

    }

}
