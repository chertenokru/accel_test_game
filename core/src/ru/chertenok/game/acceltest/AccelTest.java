package ru.chertenok.game.acceltest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.scenario.Settings;

public class AccelTest extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x,y;
	private float speed = 40f;
	BitmapFont font ;
	Shar shar;
	float dts;
	public Sound sound;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("shar.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("roll2.wav"));

		font = new BitmapFont();
		shar = new Shar(new Vector2(0,0),img,sound);

	}

	@Override
	public void render () {

		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, x, y, 100,100);
		shar.draw(batch);


			font.draw(batch, "x=" + Gdx.input.getAccelerometerX(), 100, 100);
			font.draw(batch, "y=" + Gdx.input.getAccelerometerY(), 200, 100);
			font.draw(batch, "z=" + Gdx.input.getAccelerometerZ(), 300, 100);
			font.draw(batch, "pos x=" + shar.getPosition().x, 100, 500);
			font.draw(batch, "pos y=" + shar.getPosition().y, 200, 500);
			font.draw(batch, "s x=" + shar.getVelosity().x, 100, 700);
			font.draw(batch, "s y=" + shar.getVelosity().y, 200, 700);
			font.draw(batch, "a x=" + shar.getVelosityAcc().x, 100, 300);
			font.draw(batch, "a y=" + shar.getVelosityAcc().y, 200, 300);

        batch.end();
	}

	private void update(float dt)
	{
			float accl_y = -Gdx.input.getAccelerometerX();
			float accl_x = Gdx.input.getAccelerometerY();
			shar.update(accl_x, accl_y, dt);

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
		sound.dispose();
	}
}
