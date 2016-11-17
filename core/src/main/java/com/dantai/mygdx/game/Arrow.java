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
    
    
    public Arrow (float x, float y, Robot robot) {
        super(new Texture("point.png"));
        this.robot = robot;
        
        setBounds(x, y, 8f, 8f);
    }
    
    public void update (float delta) {
        setPosition(robot.getX() + robot.getWidth(), robot.getY() + robot.getHeight());
    }
    
    public Vector2 getPosition () {
        return new Vector2(getX(), getY());
    }
    
}
