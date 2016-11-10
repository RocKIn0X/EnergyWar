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

    private Robot robot;
    private Ground ground;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
    private GameWorld gameWorld;
    private WorldRenderer worldRenderer;
    
    public static final float MAX_VELOCITY = 2f;

    public GameScreen(EnergyWar game) {
        this.game = game;
        
        setCam();
        
        gameWorld = new GameWorld(gameCam);
        worldRenderer = new WorldRenderer(game, gameWorld, gameCam);
        
        robot = gameWorld.getRobot();
    }
    
    public void setCam(){
        gameCam = new OrthographicCamera(background.getWidth() / EnergyWar.PIXELS_TO_METERS, background.getHeight() / EnergyWar.PIXELS_TO_METERS);
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(background.getWidth(), background.getHeight(), gameCam);
    }
    
    public void update(float delta){
        updateMove(delta);
        
        gameWorld.getWorld().step(1 / 60f, 6, 2);
        
        robot.update(delta);
        
        gameCam.update();
    }
    
    public void updateMove(float delta){
        Vector2 velocity = robot.getBody().getLinearVelocity();
        
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            
            if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x >= -MAX_VELOCITY) {
                robot.move(Robot.Direction.LEFT);
            }
        
            if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x <= MAX_VELOCITY) {
                robot.move(Robot.Direction.RIGHT);
            }
            
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                robot.move(Robot.Direction.UP);
            }
        
        } else {
            robot.move(Robot.Direction.STILL);
        }
        
        System.out.println(robot.getPosition());
        System.out.println(robot.getBody().getPosition());
    }
    
    @Override
    public void render(float deltaTime) {
        update(deltaTime);
        
        worldRenderer.render(deltaTime);
    }
    
    @Override
    public void resize(int width, int height){
        gamePort.update(width, height);
    }
}