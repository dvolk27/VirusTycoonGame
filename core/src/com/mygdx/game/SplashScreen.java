package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

import sun.text.SupplementaryCharacterData;

public class SplashScreen  implements Screen {
    private SpriteBatch batch;
    private Texture splashScreen;
    private Assets assets;
    private Game myGame;
    private Stage stage;
    private ProgressBar progressBar;
    SplashScreen(Game game) {
        myGame =  game;
        stage = new Stage();
        //progressBar = new ProgressBar();
        batch = new SpriteBatch();
        splashScreen = new Texture("splashscreen.png");
        assets = new Assets();
        assets.load();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(splashScreen,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        Float progress = assets.manager.getProgress();
        Gdx.app.log("dan",String.valueOf(progress));
        assets.manager.update();
        if(assets.manager.update()) {
            assets.manager.finishLoading();
            myGame.setScreen(new MainScreen(assets));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashScreen.dispose();
        batch.dispose();
    }
}
