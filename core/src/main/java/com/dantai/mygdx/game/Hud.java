/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    
    public Hud (SpriteBatch batch, int state, float driveCount, float limitTime) {
        viewport = new FitViewport(EnergyWar.V_WIDTH, EnergyWar.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);
        
        Label playerTopic;
        Label driveTopic;
        Label limitTimeTopic;
        Label playerLabel;
        Label driveLabel;
        Label limitTimeLabel;
        
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        
        playerTopic = new Label(String.format("Player"), new Label.LabelStyle(new BitmapFont(), Color.RED));
        driveTopic = new Label(String.format("drivePercent"), new Label.LabelStyle(new BitmapFont(), Color.RED));
        limitTimeTopic= new Label(String.format("PlayerTime"), new Label.LabelStyle(new BitmapFont(), Color.RED));
        playerLabel = new Label(String.format("Player %d", state), new Label.LabelStyle(new BitmapFont(), Color.RED));
        driveLabel = new Label(String.format("%02f", driveCount), new Label.LabelStyle(new BitmapFont(), Color.RED));
        limitTimeLabel = new Label(String.format("%02f", limitTime), new Label.LabelStyle(new BitmapFont(), Color.RED));
        
        table.add(playerTopic).expandX().padTop(10);
        table.add(driveTopic).expandX().padTop(10);
        table.add(limitTimeTopic).expandX().padTop(10);
        table.row();
        table.add(playerLabel).expandX();
        table.add(driveLabel).expandX();
        table.add(limitTimeLabel).expandX();
        
        stage.addActor(table);
    }
}
