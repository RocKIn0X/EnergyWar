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
public class Box {
    private Vector2 position;
    private Rectangle body;
    
    public Box(float x, float y, float width, float height){
        position = new Vector2(x, y);
        body = new Rectangle(position.x, position.y, width, height);
    }
    
    public Rectangle getBody(){
        return body;
    }

    public Vector2 getPosition() {
        return position;
    }
}
