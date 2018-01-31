package ru.chertenok.game.acceltest;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class AccelTest extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture fon;
	Texture lose;
	float x,y;
	private float speed = 40f;
	BitmapFont font ;
	int sharCount = 1;
	Shar[] shar = new Shar[sharCount];
	float dts;
	public Sound sound;

	private PooledEngine engine;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("shar.png");
		fon = new Texture("fon.jpg");
		lose = new Texture("lose.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("roll2.wav"));

		font = new BitmapFont();
		Random random = new Random();
		for (int i = 0; i < sharCount; i++) {
			shar[i] = new Shar(new Vector2(random.nextInt(Gdx.graphics.getWidth()), random.nextInt(Gdx.graphics.getHeight())), img, sound);
			Gdx.app.log("x=", "" + shar[i].getPosition().x);
			Gdx.app.log("y=", "" + shar[i].getPosition().y);
		}
	}

	@Override
	public void render () {

		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(lose, 300, 300);

		for (int i = 0; i < sharCount; i++) {
			shar[i].draw(batch);
			Gdx.app.log("x=", "" + shar[i].getPosition().x);
			Gdx.app.log("y=", "" + shar[i].getPosition().y);

		}


		/*	font.draw(batch, "x=" + Gdx.input.getAccelerometerX(), 100, 100);
			font.draw(batch, "y=" + Gdx.input.getAccelerometerY(), 200, 100);
			font.draw(batch, "z=" + Gdx.input.getAccelerometerZ(), 300, 100);
			font.draw(batch, "pos x=" + shar.getPosition().x, 100, 500);
			font.draw(batch, "pos y=" + shar.getPosition().y, 200, 500);
			font.draw(batch, "s x=" + shar.getVelosity().x, 100, 700);
			font.draw(batch, "s y=" + shar.getVelosity().y, 200, 700);
			font.draw(batch, "a x=" + shar.getVelosityAcc().x, 100, 300);
			font.draw(batch, "a y=" + shar.getVelosityAcc().y, 200, 300);
*/
        batch.end();
	}

	private void update(float dt)
	{
			float accl_y = -Gdx.input.getAccelerometerX();
			float accl_x = Gdx.input.getAccelerometerY();
		for (int i = 0; i < sharCount; i++) {
			shar[i].update(accl_x, accl_y, dt);
		}


	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose () {
	    font.dispose();

		batch.dispose();
		img.dispose();
		fon.dispose();
		sound.dispose();
	}
}
