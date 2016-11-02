/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author inman
 */
public class Asset {
    private Texture robotImg;
    private Texture boxImg;
    
    public Asset(){
        robotImg = new Texture("robot.png");
        boxImg = new Texture("Box.png");
    }
    
    public float getWidthRobot(){
        return robotImg.getWidth();
    }
    
    public float getHeightRobot(){
        return robotImg.getHeight();
    }
    
    public float getWidthBox(){
        return boxImg.getWidth();
    }
    
    public float getHeightBox(){
        return boxImg.getHeight();
    }
}
