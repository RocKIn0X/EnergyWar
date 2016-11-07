/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author inman
 */
public class GameScreen extends ScreenAdapter {

    private EnergyWar game;
    
    private Texture robotTexture;
    private Texture background;
    
    private Sprite robotImg;
    private Sprite bgImg;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
    Body robotBody;
    Body groundBody;
    
    World world;

    public GameScreen(EnergyWar game) {
        this.game = game;
        
        world = new World(new Vector2(0, -50), true);
        
        robotTexture = new Texture("robot.png");
        background = new Texture("Background.png");
        
        robotImg = new Sprite(robotTexture);
        bgImg = new Sprite(background);
        
        setCam();
        //Body of robot
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(100, 300);
        
        robotBody = world.createBody(bodyDef);
        
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.6f;
        
        Fixture fixture = robotBody.createFixture(fixtureDef);
        
        circle.dispose();
        
        //Body Ground
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        
        groundBody = world.createBody(groundBodyDef);
        
        PolygonShape groundBox = new PolygonShape();
        
        groundBox.setAsBox(gameCam.viewportWidth, 10.0f);
        
        groundBody.createFixture(groundBox, 0.0f);
        
        groundBox.dispose();
    }

    public void setCam(){
        gameCam = new OrthographicCamera(background.getWidth(), background.getHeight());
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(background.getWidth(), background.getHeight(), gameCam);
    }
    
    @Override
    public void render(float deltaTime) {
        SpriteBatch batch = game.batch;
        
        robotImg.setPosition(robotBody.getPosition().x, robotBody.getPosition().y);
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(bgImg, 0, 0);
        batch.draw(robotImg, robotImg.getX(), robotImg.getY());
        batch.end();
        
        world.step(1/60f, 6, 2);
    }
    
    @Override
    public void resize(int width, int height){
        gamePort.update(width, height);
    }
}