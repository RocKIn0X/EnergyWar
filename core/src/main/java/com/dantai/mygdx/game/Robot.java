/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author inman
 */
public class Robot {
    private Vector2 position;
    private Body robotBody;
    
    private World world;
    
    public Robot(float x, float y, World world){
        position = new Vector2(x, y);
        //Body of robot
        this.world = world;
        defineRobot();
    }
    
    public void defineRobot(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);
        
        robotBody = world.createBody(bodyDef);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 10f;
        fixtureDef.restitution = 0.6f;
        
        robotBody.createFixture(fixtureDef);
        
        circle.dispose();
    }
    
    public Vector2 getPosition(){
        return position;
    }
    
    public Body getBody(){
        return robotBody;
    }
}
