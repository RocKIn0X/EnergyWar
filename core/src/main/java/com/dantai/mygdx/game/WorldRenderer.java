/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author inman
 */
public class WorldRenderer {
    private EnergyWar game;
    private GameWorld gameWorld;
    private Camera gameCam;
    
    private SpriteBatch batch;
    private Texture robotTexture;
    private Texture background;
    private Sprite robotImg;
    private Sprite bgImg;
    
    private Robot robot;
    private Ground ground;
   
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    
    public WorldRenderer(EnergyWar game, GameWorld gameWorld, Camera gameCam){
        this.game = game;
        this.gameWorld = gameWorld;
        this.gameCam = gameCam;
        
        robotTexture = new Texture("robot.png");
        background = new Texture("Background.png");
        
        robotImg = new Sprite(robotTexture);
        bgImg = new Sprite(background);
        
        robot = gameWorld.getRobot();
        ground = gameWorld.getGround();
    }
    
    
    
    public void render(float delta){
        batch = game.batch;
        
        robotImg.setPosition(robot.getBody().getPosition().x - robotImg.getWidth() / 2, robot.getBody().getPosition().y - robotImg.getHeight() / 2);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.draw(robotImg, robotImg.getX(), robotImg.getY());
        batch.end();
        
        debugRenderer.render(gameWorld.getWorld(), gameCam.combined);
        
        gameWorld.getWorld().step(1/60f, 6, 2);
    }
}
