/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dantai.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 *
 * @author inman
 */
class WorldContactListener implements ContactListener{

    public WorldContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
     
        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;
            
            //if(object.getUserData() != null && )
        }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("End contact.");
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {

    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {

    }
    
}
