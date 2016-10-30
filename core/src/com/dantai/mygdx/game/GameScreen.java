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
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class GameScreen extends ScreenAdapter{
    private EnergyWar energyWar;
    private Texture robotImg;
    private Robot robot;
    
    public GameScreen (EnergyWar energyWar){
        this.energyWar = energyWar;
        robotImg = new Texture("robot.png");
        robot = new Robot(240, 240);
        
    }
    
    @Override
    public void render(float delta){
        SpriteBatch batch = energyWar.batch;
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Vector2 pos = robot.getPosition();
        batch.draw(robotImg, pos.x, pos.y, 60, 60);
        batch.end();
    }
}
