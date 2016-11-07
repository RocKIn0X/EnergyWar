/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author inman
 */
public class Ground {
    private Vector2 position;
    private Body groundBody;
    
    public Ground(World world, Camera gameCam){
        //Body Ground
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        
        groundBody = world.createBody(groundBodyDef);
        
        PolygonShape groundBox = new PolygonShape();
        
        groundBox.setAsBox(gameCam.viewportWidth, 10.0f);
        
        groundBody.createFixture(groundBox, 0.0f);
        
        groundBox.dispose();
    }
    
     public Vector2 getPosition(){
        return position;
    }
    
    public Body getBody(){
        return groundBody;
    }
}
