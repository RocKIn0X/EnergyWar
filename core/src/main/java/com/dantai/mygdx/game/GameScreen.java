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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
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
    
    private Texture background = new Texture("city2.jpg");

    private Robot robot;
    private Arrow arrow;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
    private GameWorld gameWorld;
    private WorldRenderer worldRenderer;
    
    private float levelBoost;
    private float boostCount;
    private float time;
    
    public static final float MAX_VELOCITY = 2f;
    
    public static final int STATE_PLAYER1 = 1;
    public static final int STATE_PLAYER2 = 2;

    public GameScreen(EnergyWar game) {
        this.game = game;
        
        setCam();
        
        gameWorld = new GameWorld(gameCam);
        worldRenderer = new WorldRenderer(game, gameWorld, gameCam);
        robot = gameWorld.getRobot();
        arrow = gameWorld.getArrow();
        
        gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);
    }
    
    public void setCam () {
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, background.getWidth() / EnergyWar.PIXELS_TO_METERS, background.getHeight() / EnergyWar.PIXELS_TO_METERS);
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(background.getWidth(), background.getHeight(), gameCam);
    }
    
    public void update (float delta) {
        updateMove(delta);
        updateCam();
    }
    
    public void updateMove (float delta) {
        Vector2 velocity = robot.getBody().getLinearVelocity();
        
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            
            if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x >= -MAX_VELOCITY) {
                robot.move(Robot.Direction.LEFT);
            }
        
            if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x <= MAX_VELOCITY) {
                robot.move(Robot.Direction.RIGHT);
            }
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                if(gameWorld.checkJump()) {
                    System.out.println("Jump!");
                    robot.move(Robot.Direction.UP);
                }              
                System.out.println("Can't jump!");
            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                time += Gdx.graphics.getDeltaTime();
                boostCount += Gdx.graphics.getDeltaTime();
                if (boostCount >= 0.125f) {
                   boostCount = 0;
                   levelBoost += 2;
                   System.out.println(time);
                   System.out.println(boostCount);
                   System.out.println(levelBoost);
                   System.out.println("---------");
                }
                
                if (time >= 3) {
                   System.out.println(time);
                   System.out.println(boostCount);
                   System.out.println(levelBoost);
                   System.out.println("---------");
                   robot.drive(arrow.getRotation(), levelBoost);
                   time = 0;
                   levelBoost = 0;
                }
            }
        
        } else {    
            robot.move(Robot.Direction.STILL);
            robot.drive(arrow.getRotation(), levelBoost);
            levelBoost = 0;
            boostCount = 0;
            time = 0;
        }
        
    }
    
    public void updateCam(){
        gameCam.position.x = (robot.getBody().getPosition().x * EnergyWar.PIXELS_TO_METERS);
        gameCam.position.y = (robot.getBody().getPosition().y * EnergyWar.PIXELS_TO_METERS);
        gameCam.update();
    }
    
    @Override
    public void render (float deltaTime) {
        update(deltaTime);
              
        gameWorld.update(deltaTime);
        worldRenderer.render(deltaTime);
    }
    
    @Override
    public void resize (int width, int height) {
        gamePort.update(width, height);
    }
}