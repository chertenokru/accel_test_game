package ru.chertenok.game.acceltest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.scenario.Settings;

public class AccelTest extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x,y;
	private float speed = 40f;
	BitmapFont font ;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("shar.png");
		font = new BitmapFont();

	}

	@Override
	public void render () {

		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y, 100,100);
		font.draw(batch,"x="+Gdx.input.getAccelerometerX(),100,100);
		font.draw(batch,"y="+Gdx.input.getAccelerometerY(),200,100);
		font.draw(batch,"z="+Gdx.input.getAccelerometerZ(),300,100);
		batch.end();
	}

	private void update(float dt)
	{
		float accl_y = - Gdx.input.getAccelerometerX();
		float accl_x = Gdx.input.getAccelerometerY();

		if (Math.abs(accl_x) > 0.3f)
		{
			x -=  (accl_x*40) *speed *dt*-1;
			if (x < 0)  x = 0f;
			if (x>  Gdx.graphics.getWidth()- img.getWidth()/4) x = Gdx.graphics.getWidth()- img.getWidth()/4;
		}

		if (Math.abs(accl_y) > 0.3f)
		{
			y -=  (accl_y*40) *speed *dt*-1;
			if (y < 0)  y = 0;
			if (y > Gdx.graphics.getHeight()- img.getHeight()/4) y = Gdx.graphics.getHeight()- img.getHeight()/4;
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
	}
}
