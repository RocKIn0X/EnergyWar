/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author inman
 */
public class GameScreen extends ScreenAdapter{
    private EnergyWar energyWar;
    private Texture robotImg;
    
    public GameScreen (EnergyWar energyWar){
        this.energyWar = energyWar;
        robotImg = new Texture("Idle1.png");
    }
    
    @Override
    public void render(float delta){
        SpriteBatch batch = energyWar.batch;
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(robotImg, 240, 240, 120, 120);
        batch.end();
    }
}
