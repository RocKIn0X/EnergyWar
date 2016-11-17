/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author inman
 */
public class GameWorld {
    private World world;
    private Camera gameCam;
    
    private Robot robot;
    private Ground ground;
    private Arrow arrow;
    
    public GameWorld (Camera gameCam) {  
        this.gameCam = gameCam;
        
        world = new World(new Vector2(0, -10), true);
        robot = new Robot(100f, 1100f, this);
        ground = new Ground(world, gameCam);
        arrow = new Arrow(robot.getPosition().x + robot.getWidth(), robot.getPosition().y + robot.getHeight(),
                          robot.getPosition().x + robot.getWidth() / 2, robot.getPosition().y + robot.getHeight() / 2, robot);
        
        arrow.setRotation(0);
        
        world.setContactListener(new WorldContactListener());
    }
    
    public void update (float delta) {
        world.step(1 / 60f, 6, 2);
        
        robot.update(delta);
        updateArrow();
        
        gameCam.update();
    }
    
    public void updateArrow () {
        arrow.update();
    }
    
    public boolean checkJump () {
        return robot.getRectangle().overlaps(ground.getRectangle());
    }
    
    public World getWorld () {
        return world;
    }
    
    public Robot getRobot () {
        return robot;
    }
    
    public Ground getGround () {
        return ground;
    }
    
    public Arrow getArrow () {
        return arrow;
    }
}
