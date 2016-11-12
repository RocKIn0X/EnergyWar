/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author inman
 */
public class Robot extends Sprite {
    private Vector2 position;
    private World world;
    private Body robotBody;
    private Rectangle robotRect;
    
    public static final float SPEED = 1f;
    
    public Robot (float x, float y, World world) {
        super(new Sprite(new Texture("robot.png")));
        
        position = new Vector2(x / EnergyWar.PIXELS_TO_METERS, y / EnergyWar.PIXELS_TO_METERS);
        this.world = world;
        
        defineRobot();
        
        setBounds(robotBody.getPosition().x, robotBody.getPosition().y, 40f, 40f);
        
        robotRect = new Rectangle(robotBody.getPosition().x, robotBody.getPosition().y, 40f, 40f);
    }
    
    public enum Direction {
        RIGHT, LEFT, UP, STILL
    }
    
    public void update(float delta){
        setPosition((getBody().getPosition().x * EnergyWar.PIXELS_TO_METERS) - getWidth() / 2, (getBody().getPosition().y * EnergyWar.PIXELS_TO_METERS) - getHeight() / 2);
        robotRect.setPosition(getX() / EnergyWar.PIXELS_TO_METERS, getY() / EnergyWar.PIXELS_TO_METERS);
        //System.out.println(robotBody.getPosition().y);
        //System.out.println(robotRect.getY());
        //System.out.println("---------------");
    }
    
    public void defineRobot(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        
        robotBody = world.createBody(bodyDef);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(20f / EnergyWar.PIXELS_TO_METERS);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        
        robotBody.createFixture(fixtureDef);
        
        EdgeShape head =  new EdgeShape();
        head.set(new Vector2(-10f / EnergyWar.PIXELS_TO_METERS, -20f / EnergyWar.PIXELS_TO_METERS), new Vector2(10f / EnergyWar.PIXELS_TO_METERS, -20f / EnergyWar.PIXELS_TO_METERS));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;
        
        robotBody.createFixture(fixtureDef).setUserData("under");
        
        circle.dispose();
    }
    
    public void move (Direction dir) {
        switch (dir) {
            case RIGHT:
                robotBody.applyLinearImpulse(new Vector2(SPEED, 0), robotBody.getWorldCenter(), true);
                break;
            case LEFT:
                robotBody.applyLinearImpulse(new Vector2(-SPEED, 0), robotBody.getWorldCenter(), true);
                break;
            case UP:
                robotBody.applyLinearImpulse(new Vector2(0, SPEED * 5), robotBody.getWorldCenter(), true);
                break;
            default:
                robotBody.applyLinearImpulse(new Vector2(0, 0), robotBody.getWorldCenter(), true);
                break;
        }      
    }
    
    public Vector2 getPosition () {
        return position;
    }
    
    public Body getBody () {
        return robotBody;
    }
    
    public Rectangle getRectangle() {
        return robotRect;
    }
}
