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
    private EnergyWar energyWar;
    
    public static final Vector2 gravity = new Vector2(0, -100);
    
    World(EnergyWar energyWar){
        this.energyWar = energyWar;
        
        robot = new Robot(240f, 240f);
    }
    
    public void updateFall(float deltaTime){
        robot.update(deltaTime);
    }
    
    Robot getRobot(){
        return robot;
    }
}
