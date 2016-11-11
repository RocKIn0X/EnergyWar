package com.dantai.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dantai.mygdx.game.EnergyWar;
public class DesktopLauncher {
    public static void main (String[] arg) {
   	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Energy war";
        config.width = 800;
        config.height = 480;
        
        new LwjglApplication(new EnergyWar(), config);
    }
}
