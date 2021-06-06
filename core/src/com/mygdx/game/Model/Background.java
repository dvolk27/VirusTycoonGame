package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Assets;

public class Background extends Actor {
    private TextureRegion background;
    public Background(){
        setPosition(0,0);
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background,getX(),getY(),getWidth(),getHeight());

    }
    public void setBackground(TextureRegion texture){
        background = texture;
    }
}
