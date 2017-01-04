/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author inman
 */
public class Energy extends Sprite{
    
    public Energy () {
        super(new Texture(Gdx.files.internal("../core/assets/Energy.png")));
        setBounds(1850, 50, 40f, 40f);
    }
}
