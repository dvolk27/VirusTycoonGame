package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Background extends Actor {
    private Texture background;
    public Background(Texture background){
        this.background = background;
        setPosition(0,0);
        setWidth(Gdx.graphics.getWidth());
        setHeight(Gdx.graphics.getHeight());

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background,getX(),getY(),getWidth(),getHeight());

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }
    public void setTexture(Texture texture){
        background = texture;
    }
}
