package ru.chertenok.game.acceltest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hole {
    private Vector2 position;
    private Texture texture;

    public Hole(Vector2 position, Texture texture) {
        this.position = position;
        this.texture = texture;
    }

    public void update(float dt) {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }
}
