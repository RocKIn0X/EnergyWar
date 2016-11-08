/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
    
    private Texture background = new Texture("Background.png");
    
    private Sprite robotImg;
    private Sprite bgImg;
    
    //Box2d variables
    Body robotBody;
    Body groundBody;    
    World world;

    private Robot robot;
    private Ground ground;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
    private GameWorld gameWorld;
    private WorldRenderer worldRenderer;
    
    public static final float MAX_VELOCITY = 100f;

    public GameScreen(EnergyWar game) {
        this.game = game;
        
        setCam();
        
        world = new World(new Vector2(0, -50), true);
        
        gameWorld = new GameWorld(world, gameCam);
        worldRenderer = new WorldRenderer(game, gameWorld, gameCam);
        
        robot = gameWorld.getRobot();
    }
    
    public void setCam(){
        gameCam = new OrthographicCamera(background.getWidth(), background.getHeight());
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(background.getWidth(), background.getHeight(), gameCam);
    }
    
    public void updateMove(){
        Vector2 velocity = robot.getBody().getLinearVelocity();
        Vector2 position = robot.getBody().getPosition();
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x > -MAX_VELOCITY) {
                robot.getBody().applyLinearImpulse(-50f, 0, position.x, position.y, true);
            }
        
            if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x < MAX_VELOCITY) {
                robot.getBody().applyLinearImpulse(50f, 0, position.x, position.y, true);
            }
        } else {
            robot.getBody().applyLinearImpulse(0, 0, position.x, position.y, true);
        }
        
        
    }
    
    @Override
    public void render(float deltaTime) {
        updateMove();
        
        worldRenderer.render(deltaTime);
        
    }
    
    @Override
    public void resize(int width, int height){
        gamePort.update(width, height);
    }
}