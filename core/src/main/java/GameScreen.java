/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private World world;
    private WorldRenderer worldRenderer;
    
    public GameScreen (EnergyWar energyWar){
        this.energyWar = energyWar;
        robotImg = new Texture("robot.png");
        
        world = new World(energyWar);
        robot = world.getRobot();
        
        worldRenderer = new WorldRenderer(energyWar, world);
    }
    
    @Override
    public void render(float delta){
        updateMovement(delta);
        
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        worldRenderer.render(delta);
    }
    
    private void updateMovement(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            robot.move(robot.DIRECTION_RIGHT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            robot.move(robot.DIRECTION_LEFT);
        }
        world.update(delta);
    }
}
