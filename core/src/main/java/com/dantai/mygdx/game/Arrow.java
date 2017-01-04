/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class Arrow extends Sprite {
    private Robot robot;
    
    private float posX;
    private float posY;
    private float originX;
    private float originY;
    private float rotation;
    
    public Arrow (float x, float y, float originX, float originY, Robot robot) {
        super(new Texture(Gdx.files.internal("assets/point.png")));
        this.robot = robot;
        
        setBounds(x, y, 8f, 8f);
        setOrigin(originX, originY);
    }
    
    public void update () {
        setArrow();
    }
    
    public void setArrow () {     
        originX = robot.getX() + robot.getWidth() / 2;
        originY = robot.getY() + robot.getHeight() / 2;

        posX = (float) (originX + (40f * Math.cos(Math.toRadians(rotation))));
        posY = (float) (originY + (40f * Math.sin(Math.toRadians(rotation))));
        
        setOrigin(originX, originY);
        setPosition(posX, posY);
        
        if (rotation > 360) {
           rotation = 0; 
        } else {
           rotation += 100 * Gdx.graphics.getDeltaTime();  
        }   
    }
    
    public void setRotation (float rotation) {
        this.rotation = rotation;
    }
    
    public float getRotation () {
        return rotation;
    }
    
    public Vector2 getPosition () {
        return new Vector2(getX(), getY());
    }
}
