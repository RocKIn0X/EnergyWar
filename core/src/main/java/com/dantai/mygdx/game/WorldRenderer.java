/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;

/**
 *
 * @author inman
 */
public class WorldRenderer {
    private EnergyWar game;
    private GameWorld gameWorld;
    private OrthographicCamera gameCam;
    
    private SpriteBatch batch;
    private Sprite robotImg;
    private Sprite bgImg;
    private Sprite bgImg2;
    private Sprite bgImg3;
    private Sprite bgImg4;
    
    private Robot robot;
    private Ground ground;
    
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
   
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    
    
    public WorldRenderer(EnergyWar game, GameWorld gameWorld, OrthographicCamera gameCam){
        this.game = game;
        this.gameWorld = gameWorld;
        this.gameCam = gameCam;

        robotImg = new Sprite(new Texture("robot.png"));
        
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getY() + rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            System.out.println(rect.getWidth() + " | " + rect.getHeight());
            
            body = gameWorld.getWorld().createBody(bdef);
            
            shape.setAsBox((rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getY() + rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            
            body = gameWorld.getWorld().createBody(bdef);
            
            shape.setAsBox((rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getY() + rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            System.out.println(rect.getWidth() + " | " + rect.getHeight());
            
            body = gameWorld.getWorld().createBody(bdef);
            
            shape.setAsBox((rect.getWidth() / 2) / EnergyWar.PIXELS_TO_METERS, (rect.getHeight() / 2) / EnergyWar.PIXELS_TO_METERS);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
    
    
    
    public void render(float delta){
        batch = game.batch;
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        renderer.setView(gameCam);
        renderer.render();
        batch.setProjectionMatrix(gameCam.combined);
        
        batch.begin();
       // batch.draw(bgImg, 0, 0);
        //batch.draw(bgImg2, -1920, 0);
        //batch.draw(bgImg3, 0, -1080);
        //batch.draw(bgImg4, -1920, -1080);
        gameWorld.getRobot().draw(batch);
        batch.end();
        
        Matrix4 cameraCopy = gameCam.combined.cpy();
        debugRenderer.render(gameWorld.getWorld(), cameraCopy.scl(EnergyWar.PIXELS_TO_METERS));
    }
}
