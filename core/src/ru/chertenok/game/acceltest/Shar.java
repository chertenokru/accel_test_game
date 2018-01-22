package ru.chertenok.game.acceltest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class Shar {
    private Vector2 position;
    private Vector2 positionOld;
    private Vector2 velosity;
    private Vector2 velosityAcc;
    private float maxSpeed = 1500f;
    private float speedDt = 40f;
    private Texture texture;
    private float textureWidth;
    private float textureHeight;
    private float screenWidth;
    private float screenHeight;
    private float dtVibrate;
    private float stoping = 10f;
    private long sound_id;
    private Sound sound;

    public Shar(Vector2 position, Texture texture,Sound sound) {
        this.position = position;
        this.texture = texture;
        textureHeight = 100;
        textureWidth = 100;
        velosity = new Vector2(0, 0);
        velosityAcc = new Vector2(0, 0);
        positionOld = new Vector2(position);
        this.sound=sound;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelosity() {
        return velosity;
    }

    public void setVelosity(Vector2 velosity) {
        this.velosity = velosity;
    }

    public Vector2 getVelosityAcc() {
        return velosityAcc;
    }

    public void update(float x, float y, float dt) {
        positionOld.set(position);

        if (velosity.x > 0) {
            if (velosity.x - stoping < 0) velosity.x = 0;
            else velosity.x -= stoping;
        }
        if (velosity.x < 0) {
            if (velosity.x + stoping > 0) velosity.x = 0;
            else velosity.x += stoping;
        }

        if (velosity.y > 0)
            if (velosity.y - stoping < 0) velosity.y = 0;
            else velosity.y -= stoping;
        if (velosity.y < 0)
            if (velosity.y + stoping > 0) velosity.y = 0;
            else velosity.y += stoping;


        if (abs(x) < 0.3f) x = 0;
        if (abs(y) < 0.3f) y = 0;

        if (x != 0) velosityAcc.x = x * speedDt; else velosityAcc.x = 0;
        if (y != 0) velosityAcc.y = y * speedDt; else velosityAcc.y = 0;
        if (abs(velosity.x + velosityAcc.x) < maxSpeed) {
            velosity.x += velosityAcc.x;
        }
        position.x += velosity.x * dt;
        if (position.x < 0) {
            position.x = 0f;
            velosity.x = -velosity.x;
            if (positionOld.x !=position.x)
            sound.play(0.3f);
        }
        if (position.x > screenWidth - textureWidth) {
            position.x = screenWidth - textureWidth;
            velosity.x = -velosity.x;
            if (positionOld.x !=position.x)
            sound.play(0.3f);
        }


        if (abs(velosity.y + velosityAcc.y) < maxSpeed) {
            velosity.y += velosityAcc.y;
        }
        position.y += velosity.y * dt;
        if (position.y < 0) {
            position.y = 0;
            velosity.y = -velosity.y;
            if (positionOld.y !=position.y)
            sound.play(0.3f);
        }
        if (position.y > screenHeight - textureHeight) {
            position.y = screenHeight - textureHeight;
            velosity.y = -velosity.y;
            if (positionOld.y !=position.y)
            sound.play(0.3f);
        }




        // if (!positionOld.epsilonEquals(position))
//                Gdx.input.vibrate(1);

        //position.add(velosity);

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, textureWidth, textureHeight);
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();

    }
}
