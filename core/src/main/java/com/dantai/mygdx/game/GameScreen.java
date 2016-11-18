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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    private Robot[] robots;
    private Arrow[] arrows;
    private Energy energy;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
    private GameWorld gameWorld;
    private WorldRenderer worldRenderer;
    
    private float levelBoost;
    private float boostCount;
    private float time;
    
    private int state;
    private float limitTime;
    private boolean triggerTurn;
    
    public static final float MAX_VELOCITY = 2f;
    
    public static final int STATE_PLAYER1 = 0;
    public static final int STATE_PLAYER2 = 1;
    
    public static final int DRIVE_TIME = 3;

    public GameScreen(EnergyWar game) {
        this.game = game;
        
        setCam();
        gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);
        
        gameWorld = new GameWorld(gameCam);
        worldRenderer = new WorldRenderer(game, gameWorld, gameCam);
        
        energy = gameWorld.getEnergy();
        initRobot();
        initArrow();
    
        state = STATE_PLAYER1;
        triggerTurn = false;
    }
    
    public void update (float delta) {
        updateMove(delta);
        updateCam();
        win();
    }
    
    public void setCam () {
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, background.getWidth() / EnergyWar.PIXELS_TO_METERS, background.getHeight() / EnergyWar.PIXELS_TO_METERS);
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(background.getWidth(), background.getHeight(), gameCam);
    }
    
    public void initRobot () {
        robots = new Robot[2];
        
        robots[0] = gameWorld.getRobot();
        robots[1] = gameWorld.getRobot2();
    }
    
    public void initArrow () {
        arrows = new Arrow[2];
        
        arrows[0] = gameWorld.getArrow();
        arrows[1] = gameWorld.getArrow2();
    }
    
    public void updateMove (float delta) {
        Vector2 velocity = robots[state].getBody().getLinearVelocity();
        robots[state].move(Robot.Direction.STILL);
        
        if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x >= -MAX_VELOCITY) {
            robots[state].move(Robot.Direction.LEFT);
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x <= MAX_VELOCITY) {
            robots[state].move(Robot.Direction.RIGHT);
        }
            
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            triggerTurn = true;
            onDriveMode(); 
        } else {    
            offDriveMode();
        }
    }
    
    public void updateCam () {
        gameCam.position.x = (robots[state].getBody().getPosition().x * EnergyWar.PIXELS_TO_METERS);
        gameCam.position.y = (robots[state].getBody().getPosition().y * EnergyWar.PIXELS_TO_METERS);
        gameCam.update();
    }
    
    public void onDriveMode () {
        time += Gdx.graphics.getDeltaTime();
        boostCount += Gdx.graphics.getDeltaTime();
                
        if (boostCount >= 0.125f) {
            boostCount = 0;
            levelBoost += 2;
        }
                
        if (time >= DRIVE_TIME) {
            robots[state].drive(arrows[state].getRotation(), levelBoost);
            time = 0;
            levelBoost = 0;
                   
            switchState();
        }
    }
    
    public void offDriveMode () {
        robots[state].drive(arrows[state].getRotation(), levelBoost);
        levelBoost = 0;
        boostCount = 0;
        time = 0;
            
        if (triggerTurn) {
            switchState();
            triggerTurn = false;
        }  
    }
    
    public void timeLimit () {
        limitTime -= Gdx.graphics.getDeltaTime();
        
        if (limitTime <= 0) {
            switchState();
            limitTime = 8;
        }
    }
    
    public void switchState () {
        if (state == STATE_PLAYER1) {
            state = STATE_PLAYER2;
        } else {
            state = STATE_PLAYER1;
        }
    }

    public void win () {
        if (checkEnergy()) {
            if (state == STATE_PLAYER1) {
                System.out.println("Yellow robot win!!!");
            } else {
                System.out.println("Red robot win!!!");
            }
        }
    }
    
    public boolean checkEnergy () {
        return robots[state].getBoundingRectangle().overlaps(energy.getBoundingRectangle());
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