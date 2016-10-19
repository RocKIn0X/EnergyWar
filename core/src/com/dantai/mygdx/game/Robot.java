/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class Robot {
    private Vector2 position;
    
    public Robot(int x, int y){
        position = new Vector2(x, y);
    }
    
    public Vector2 getPosition(){
        return position;
    }
}
