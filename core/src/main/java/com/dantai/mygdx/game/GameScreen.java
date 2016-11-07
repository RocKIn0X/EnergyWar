/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author inman
 */
public class GameScreen extends ScreenAdapter {

    private EnergyWar game;
    private Texture robotTexture;
    private Texture background;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public GameScreen(EnergyWar game) {
        this.game = game;
        robotTexture = new Texture("robot.png");
        background = new Texture("Background.png");
        gameCam = new OrthographicCamera(background.getWidth(), background.getHeight());
        gameCam.translate(background.getWidth() / 2, background.getHeight() / 2, 0);
        gameCam.update();
        gamePort = new FitViewport(800, 480, gameCam);
    }

    @Override
    public void render(float deltaTime) {
        SpriteBatch batch = game.batch;
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(robotTexture, 100, 100);
        batch.end();
    }
}