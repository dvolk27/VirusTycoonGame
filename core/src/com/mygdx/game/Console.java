package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Console{
    public static void main(String[] args) {
        FileHandle file = Gdx.files.local("upgrades.json");
        file.delete();
    }
}
