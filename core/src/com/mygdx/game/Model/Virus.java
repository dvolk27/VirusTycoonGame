package com.mygdx.game.Model;

import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Map;

public class Virus {
    private long infected;
    private long power;
    private long spread;
    private long dnaPerSecond;
    private long dnaPerPeople;
    private ArrayList<Integer> upgrades;
    public final static int SPREAD_1 = 101;
    Virus(FileHandle file) {

    }
    Virus() {
        upgrades = new ArrayList<>();
        setProperties();
    }
    private void setProperties() {
        //
        infected = 0;
        power = 0;
        spread = 0;
        dnaPerSecond = 0;
        dnaPerPeople = 0;
    }
}
