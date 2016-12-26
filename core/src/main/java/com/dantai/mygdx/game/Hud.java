/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author inman
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    
    Label timeLabel;
    
    public Hud (SpriteBatch batch, String state) {
        viewport = new FitViewport(EnergyWar.V_WIDTH, EnergyWar.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        
        Label playerLabel;
        Label driveLabel;
        Label limitTimeLabel;
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        
    }
}
