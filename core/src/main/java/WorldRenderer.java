/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class WorldRenderer {

    private EnergyWar energyWar;
    private SpriteBatch batch;
    private World world;
    private Rectangle boxRec;
    private Rectangle robotRec;
    
    public static Texture robotImg;
    public static Texture boxImg;
    public static Texture background;
    
    public WorldRenderer (EnergyWar energyWar, World world){
        this.energyWar = energyWar;
        batch = energyWar.batch;
        
        this.world = world;
        
        robotImg = new Texture("robot.png");
        boxImg = new Texture("Box.png");
        background = new Texture("Background.png");
        
        boxRec = world.getBox().getBody();
        robotRec = world.getRobot().getBody();
    }
    
    public void render(float delta){
        batch.begin();
        Vector2 posRobot = world.getRobot().getPosition();
        Vector2 posBox = world.getBox().getPosition();
        batch.draw(background, 0, 0, 1280,800);
        batch.draw(robotImg, posRobot.x, posRobot.y, robotImg.getWidth(), robotImg.getHeight());
        batch.draw(boxImg, posBox.x, posBox.y, boxImg.getWidth() / 2, boxImg.getHeight() / 2);
        batch.end(); 
    }
}
