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
    
    public GameWorld (Camera gameCam) {  
        this.gameCam = gameCam;
        
        world = new World(new Vector2(0, -10), true);
        robot = new Robot(100f, 300f, world);
        ground = new Ground(world, gameCam);
        
        world.setContactListener(new WorldContactListener());
    }
    
    public boolean checkJump () {
        //System.out.println(robot.getRectangle().getX() + " | " + robot.getRectangle().getY());
        //System.out.println((ground.getRectangle().getX() + ground.getRectangle().getWidth()) + " | " + (ground.getRectangle().getY() + ground.getRectangle().getHeight()));
        
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
}
