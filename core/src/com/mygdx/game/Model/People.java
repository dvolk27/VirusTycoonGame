package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.Assets;

import java.util.concurrent.ConcurrentLinkedDeque;

public class People extends Actor {
    private ConcurrentLinkedDeque<String> peoples;

    private TextureAtlas people;
    private TextureAtlas peopleIll;
    private float x;
    private float y;
    private float width;
    private float height;
    private long immune;
    public static long immuneMax;
    private TextureRegion currentRegion;
    private Scene scene;
    public People(Assets assets,float x, float y,long immunMax,Scene scene) {
        peoples = new ConcurrentLinkedDeque<>();
        peoples.addFirst("adult_man");
        peoples.addFirst("businesswoman");
        peoples.addFirst("casual_man");
        peoples.addFirst("doc1");
        peoples.addFirst("vkid_girl");
        peoples.addFirst("shopping_girl");
        peoples.addFirst("fitness_boy");
        peoples.addFirst("grandfather");
        peoples.addFirst("homeless_noob");
        peoples.addFirst("doc2");
        peoples.addFirst("pharmacist");
        peoples.addFirst("grandmotherfucker");
        peoples.addFirst("student");
        peoples.addFirst("vkid_boy");
        peoples.addFirst("young_girl");
        this.x = x;
        this.y = y;
        this.scene = scene;
        immuneMax = immunMax;
        immune = immuneMax;
    }
    public void spawnPeople() {
        Thread anim = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        x = Gdx.graphics.getWidth() + 200;
                        float j = 30;
                        while(x >  50 + Gdx.graphics.getWidth()/2f-width/2) {
                            x-=500*Gdx.graphics.getDeltaTime()*j;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            j/=1.3f;
                        }
                    }
                }
        );
        anim.start();
    }
    public void changePeople() {
        Thread anim = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        float j = 1.1f;
                        x = Gdx.graphics.getWidth()/2f - width/2;
                        while(x > -getWidth()) {
                            x-=500*Gdx.graphics.getDeltaTime()*j;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            j*=1.3;
                        }
                        changeSprite();
                        spawnPeople();
                    }
                }
        );
        anim.start();
    }
    public void setPeople(TextureAtlas textureHealth,TextureAtlas textureIll){
         people = textureHealth;
         peopleIll  = textureIll;
        currentRegion = new TextureRegion(people.findRegion(peoples.getLast()));
        width = currentRegion.getRegionWidth();
        height = currentRegion.getRegionHeight();
        x = Gdx.graphics.getWidth() /2f - width /2;
    }
    public void changeSprite() {
       String last =  peoples.removeLast();
       peoples.addFirst(last);
       String next = peoples.getLast();
       currentRegion = new TextureRegion(people.findRegion(next));
        width = currentRegion.getRegionWidth();
        height = currentRegion.getRegionHeight();
        x = Gdx.graphics.getWidth() /2f - width /2;
       setBounds(x,y,width,height);
    }
    public void setIllSprite() {
        currentRegion = new TextureRegion(peopleIll.findRegion(peoples.getLast()));
    }
    public void immuneDecrement(long decrement) {
        immune-=decrement;
        if(immune <= 2 * decrement) {
            setIllSprite();
        }
        if(immune <= 0) {
            changePeople();
            immuneMax++;
            immune = immuneMax;
            Virus virus = scene.getVirus();
            virus.setInfected(virus.getInfected()+virus.getSpread());
            virus.setDna(virus.getDna()+virus.getSpread()*virus.getDnaPerPeople());
        }
    }
    public long getImmune() {
        return immune;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentRegion,x,y,Scene.ppuX*width,Scene.ppuY*height);
    }
}
