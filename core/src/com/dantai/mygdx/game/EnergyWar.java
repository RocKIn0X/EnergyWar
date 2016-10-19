package com.dantai.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnergyWar extends Game {
	public SpriteBatch batch;
	Texture robotImg;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
            super.render();
	}
	
	@Override
	public void dispose () {
            batch.dispose();
	}
}
