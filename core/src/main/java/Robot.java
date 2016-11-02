/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class Robot {
    private Vector2 position;
    private Vector2 bodyPos;
    private Vector2 gravity;
    private Rectangle body;
    
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3; //My game doesn't use it.
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    
    public static final float xSPEED = 5f;
    
    private static final float[][] DIR_DIFF = new float[][]{
        {0,0},
        {0,1f},
        {1f,0},
        {0,-1f},
        {-1f,0}
    };
    
    public Robot(float x, float y, float width, float height){
        position = new Vector2(x, y);
        body = new Rectangle(position.x, position.y, width, height);
        bodyPos = new Vector2(x, y);
    }
    
    public void update(float deltaTime){
        gravity = new Vector2(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
        position.add(gravity);
        bodyPos.add(gravity);
        body.setPosition(bodyPos);
    }
    
    public void move(int dir){
        position.add(xSPEED * DIR_DIFF[dir][0], 0);
        bodyPos.add(xSPEED * DIR_DIFF[dir][0], 0);
        body.setPosition(xSPEED * DIR_DIFF[dir][0], 0);
    }
    
    public Vector2 getPosition(){
        return position;
    }
    
    public boolean isCollided(Rectangle rect){
        System.out.println("Collision Detected"+""+body.overlaps(rect));
        return rect.overlaps(body);
    }
    
    public Rectangle getBody(){
        return body;
    }
}
