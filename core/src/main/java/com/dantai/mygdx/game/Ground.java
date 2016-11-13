/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

/**
 *
 * @author inman
 */
public class Ground {
    private Vector2 position;
    private Body groundBody;
    private Rectangle rectGround;
    
    World world;
    Camera gameCam;
    
    FixtureDef fdef = new FixtureDef();
    
    public Ground (World world, Camera gameCam) {
        this.world = world;
        this.gameCam = gameCam;
        
        defineGround();
        rectGround = new Rectangle(groundBody.getPosition().x, groundBody.getPosition().y, gameCam.viewportWidth, 12.0f / EnergyWar.PIXELS_TO_METERS);
        System.out.println(gameCam.viewportWidth);
    }
    
    private void defineGround () {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10 / EnergyWar.PIXELS_TO_METERS));
        
        groundBody = world.createBody(groundBodyDef);
        
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(gameCam.viewportWidth, 10.0f / EnergyWar.PIXELS_TO_METERS);
        
        fdef.shape = groundBox;
        
        groundBody.createFixture(fdef);     
        groundBox.dispose();
    }
    
    void onGround() {
        System.out.println("Ground: Collision");
    }
    
    public Vector2 getPosition () {
        return position;
    }
    
    public Body getBody () {
        return groundBody;
    }
    
    public Rectangle getRectangle () {
        return rectGround;
    }
}
