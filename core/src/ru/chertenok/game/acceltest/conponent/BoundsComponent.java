package ru.chertenok.game.acceltest.conponent;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent implements Component {
    public enum BoundType {Circle, Rectangle}

    ;

    public Circle circle = new Circle();
    public Rectangle rectangle = new Rectangle();
    public BoundType type;

}
