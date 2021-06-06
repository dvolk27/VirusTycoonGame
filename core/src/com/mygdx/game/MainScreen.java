package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Model.Scene;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;


public class MainScreen implements Screen,InputProcessor {
    private Scene scene;
    private SpriteBatch batch;
    private Assets assets;
    private ConcurrentLinkedDeque<ParticleEffect> particles;
    public MainScreen(Assets assets){
        this.assets = assets;
    }
    @Override
    public void show() {
        scene = new Scene(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),assets,this);
        Gdx.input.setInputProcessor(scene);
        batch = new SpriteBatch();
        particles = new ConcurrentLinkedDeque<>();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        scene.update(delta);
        batch.begin();

        for (ParticleEffect particle : particles) {
            particle.draw(batch,Gdx.graphics.getDeltaTime());
            if(particle.isComplete()) {
                particle.dispose();
                particles.remove(particle);
            }
        }
        batch.end();

    }

    public void addParticles(ParticleEffect particle) {
        particles.add(particle);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        scene.save();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        scene.save();
        batch.dispose();
        scene.dispose();
        assets.dispose();
        for (ParticleEffect particle : particles) {
            particle.dispose();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

