/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author inman
 */
public class World {
    private Robot robot;
    private Box box;
    private EnergyWar energyWar;
    private Asset asset;
    
    public static final Vector2 gravity = new Vector2(0, -1);
    
    World(EnergyWar energyWar){
        this.energyWar = energyWar;
        
        asset = new Asset();
        box = new Box(100f, 0, asset.getWidthBox() / 2, asset.getHeightBox() / 2);
        robot = new Robot(240f, 800f, asset.getWidthRobot(), asset.getHeightRobot(), this);
        
        //System.out.println(robot.getBody().getWidth() + " , " + robot.getBody().getHeight());
        //System.out.println(box.getBody().getWidth() + " , " + box.getBody().getHeight());
    }
    
    public void update(float deltaTime){
        if(!robot.isCollided(box.getBody())){
            robot.update(deltaTime);
        }
        System.out.println(robot.getBody().x + " , " + robot.getBody().y);
        System.out.println(box.getBody().x + " , " + box.getBody().y);
        //System.out.println(asset.getWidthBox()/2 + " , " + asset.getHeightBox() / 2);
    }
    
    public Robot getRobot(){
        return robot;
    }

    public Box getBox() {
        return box;
    }
}
