package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Assets;

public class People extends Actor {

    private Texture people;
    public People(Assets assets,float x, float y) {
        setPeople(assets.manager.get(Assets.adult_man));
        setWidth(people.getWidth());
        setHeight(people.getHeight());
        setPosition(x-getWidth()/2,y);
    }

    public void setPeople(Texture texture){
        people = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(people,getX(),getY(),Scene.ppuX*getWidth(),Scene.ppuY*getHeight());
    }
}
