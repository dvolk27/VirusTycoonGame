package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Counter extends Label {
    private long counter;
    private TextureRegion dnaicon;
    private float width;
    private float height;
    Counter(TextureRegion dnaicon) {
        super("0",new Skin(Gdx.files.internal("UI/uiskin.json")),"default");
        counter = 0;
        this.dnaicon = dnaicon;
        width = getWidth();
        height = getHeight();
        setX(Gdx.graphics.getWidth()/2f-getWidth()/2);
        setY(Gdx.graphics.getHeight()-2f*getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(dnaicon,Gdx.graphics.getWidth()/2-getWidth()/2 - 150, Gdx.graphics.getHeight()-2*getHeight(),128,128);
    }

    public void setCounter(long delta) {
        counter=delta;
        setWidth(Long.toString(counter).length()*45);
        setX((Gdx.graphics.getWidth()/2f-getWidth()/2)+64);
        setText(Long.toString(counter));

    }
}
