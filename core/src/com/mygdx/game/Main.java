package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Main extends Game {
    private Game myGame;
    @Override
    public void create() {

        FileHandle file = Gdx.files.local("upgrades.json");
        FileHandle file2 = Gdx.files.internal("upgrades.json");
        //Gdx.app.log("dan",file2.readString());
        if (!file.exists()) {
            file.writeString(file2.readString(), false);
        }

        myGame = this;

        myGame.setScreen(new SplashScreen(myGame));

    }
}
