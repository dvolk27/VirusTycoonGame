package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new SplashScreen());

        Assets assets = new Assets();
        assets.load(); //starts loading assets

        assets.manager.finishLoading();//Continues when done loading.
        setScreen(new MainScreen(assets));
    }
}
