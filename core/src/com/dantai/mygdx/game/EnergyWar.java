package com.dantai.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnergyWar extends Game {
	SpriteBatch batch;
	Texture robotImg;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		robotImg = new Texture("Idle1.png");
                
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(robotImg, 120, 120, 120, 120);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		robotImg.dispose();
	}
}
