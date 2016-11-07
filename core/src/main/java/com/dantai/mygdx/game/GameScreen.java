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
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author inman
 */
public class GameScreen extends ScreenAdapter {

    private EnergyWar game;
    private Texture robotTexture;

    public GameScreen(EnergyWar game) {
        this.game = game;
        robotTexture = new Texture("robot.png");
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(robotTexture, 100, 100);
        game.batch.end();
    }
}