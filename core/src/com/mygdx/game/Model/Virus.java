package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Map;

public class Virus {
    private long infected;
    private long power;
    private long spread;
    private long dnaPerSecond;
    private long dnaPerPeople;
    private long dna;
    private long current_time;
    Virus() { }
    public Virus(long infected, long power, long spread, long dnaPerSecond, long dnaPerPeople, long dna) {
        this.infected = infected;
        this.power = power;
        this.spread = spread;
        this.dnaPerSecond = dnaPerSecond;
        this.dnaPerPeople = dnaPerPeople;
        this.dna = dna;
        current_time = 0;
    }


    public void update(float delta) {
        Gdx.app.log("dan",Float.toString(delta));
        current_time += delta*1000;
        if(current_time>=1000) {
            current_time = 0;
            dna+=dnaPerSecond;
        }
    }
    public long getInfected() {
        return infected;
    }

    public void setInfected(long infected) {
        this.infected = infected;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public long getSpread() {
        return spread;
    }

    public void setSpread(long spread) {
        this.spread = spread;
    }

    public long getDnaPerSecond() {
        return dnaPerSecond;
    }

    public void setDnaPerSecond(long dnaPerSecond) {
        this.dnaPerSecond = dnaPerSecond;
    }

    public long getDnaPerPeople() {
        return dnaPerPeople;
    }

    public void setDnaPerPeople(long dnaPerPeople) {
        this.dnaPerPeople = dnaPerPeople;
    }

    public long getDna() {
        return dna;
    }

    public void setDna(long dna) {
        this.dna = dna;
    }
}
