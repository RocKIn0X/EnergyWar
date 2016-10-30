/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

/**
 *
 * @author inman
 */
public class World {
    private Robot robot;
    private EnergyWar energyWar;
    
    World(EnergyWar energyWar){
        this.energyWar = energyWar;
        
        robot = new Robot(240, 240);
    }
    
    Robot getRobot(){
        return robot;
    }
}
