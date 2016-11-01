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
    private Texture robotImg;
    private Texture boxImg;
    private Rectangle box;
    
    public WorldRenderer (EnergyWar energyWar, World world){
        this.energyWar = energyWar;
        batch = energyWar.batch;
        
        this.world = world;
        
        robotImg = new Texture("robot.png");
        boxImg = new Texture("Box.png");
        
        box = new Rectangle(100, 0, boxImg.getWidth(), boxImg.getHeight());
    }
    
    public void render(float delta){
        batch.begin();
        Vector2 pos = world.getRobot().getPosition();
        batch.draw(robotImg, pos.x, pos.y, 60, 60);
        batch.draw(boxImg, box.x, box.y, 128, 128);
        batch.end();
    }
}
