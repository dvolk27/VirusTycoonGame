package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Backboard extends Actor {
    TextureRegion backboard;
    Backboard(TextureRegion backboard){
        super();
        this.backboard = backboard;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backboard,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/12.9f);
    }
}
