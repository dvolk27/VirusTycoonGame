package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.files.FileHandleStream;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.MainScreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Scene extends Stage{
    private People people;
    private Background background;
    private Counter counter;
    private ImageButton spreadButton;
    private ImageButton dnaButton;
    private ImageButton dnapsButton;
    private ImageButton powerButton;
    private float width;
    private float height;
    public static final float ppuX = Gdx.graphics.getWidth()/1080f;
    public static final float ppuY = Gdx.graphics.getHeight()/1920f;
    private Assets assets;
    private Table dnaUpgrades;
    private Table dnapsUpgrades;
    private Table powerUpgrades;
    private Table spreadUpgrades;
    private Table activeUpgrades;
    private MainScreen mainScreen;
    private Backboard backboard;
    private Virus virus;
    private  JsonValue.JsonIterator iterator;
    private TextureAtlas backGroundAtlas;
    private ArrayList<UpgradeActor> upgradeActors;
    private int planet;
    private ProgressBar progressbar;
    public Scene(Viewport viewport, Assets assets, MainScreen mainScreen) {
        super(viewport);
        FileHandle file = Gdx.files.local("upgrades.json");
        Json json = new Json();
        JsonValue root = new JsonReader().parse(file.readString());
        iterator = root.iterator();
        this.assets = assets;
        this.mainScreen = mainScreen;
        width = viewport.getScreenWidth();
        height = viewport.getScreenHeight();
        planet = Integer.parseInt(iterator.next().toString().split(" ")[1]);
        background = new Background();
        people = new People(assets,width/2,height/5f,Integer.parseInt(iterator.next().toString().split(" ")[1]),this);
        virus = new Virus(1,1,1,1,1,1);

        virus = json.fromJson(Virus.class,iterator.next().child.toString());
        backGroundAtlas = assets.manager.get(Assets.backGroundAtlas);
        setPlanet(planet);
        upgradeActors = new ArrayList<>();
        setGameObjects();
        setUi();
    }

    private void setGameObjects(){

        backboard = new Backboard(new TextureRegion(assets.manager.get(Assets.uiAtlas).findRegion("zaglushka")));

        addActor(background);
        addActor(backboard);
        addActor(people);
        background.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ParticleEffect pe = new ParticleEffect();
                pe.load(Gdx.files.internal("virusparticles.party"),Gdx.files.internal(""));
                pe.setPosition(x,y);
                pe.start();
                mainScreen.addParticles(pe);
                people.immuneDecrement(virus.getPower());
                if(activeUpgrades != null) {
                    hideUpgrades();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        people.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ParticleEffect pe = new ParticleEffect();
                pe.load(Gdx.files.internal("virusparticles.party"),Gdx.files.internal(""));
                pe.setPosition(width/2+x/4,height/6.8f+y);
                pe.start();
                mainScreen.addParticles(pe);
                people.immuneDecrement(virus.getPower());
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void setUi() {
        TextureAtlas uiAtlas = assets.manager.get(Assets.uiAtlas);
        Skin skin = assets.manager.get(Assets.uiSkin);
        dnaButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("dnabutton")));
        spreadButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("spreadbutton")));
        dnapsButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("dnapsbutton")));
        powerButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("powerbutton")));
        spreadButton.setBounds(0,0,width/4,height/13);
        powerButton.setBounds(width/4,0,width/4,height/13);
        dnaButton.setBounds(width/2,0,width/4,height/13);
        dnapsButton.setBounds(width*3/4,0,width/4,height/13);
        counter = new Counter(new TextureRegion(uiAtlas.findRegion("dnkicon")));
        progressbar = new ProgressBar(0,1, (float) 0.1,false,skin);
        progressbar.setWidth((float) (Gdx.graphics.getWidth()*0.6));
        progressbar.setBounds((float)(width*0.2),(float)(height*0.8),(float)(width*0.6),(float)(height*0.05));
        addActor(spreadButton);
        addActor(powerButton);
        addActor(dnapsButton);
        addActor(dnaButton);
        addActor(counter);
        addActor(progressbar);
        spreadButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showUpgrades(1);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        powerButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               showUpgrades(2);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        dnaButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showUpgrades(3);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        dnapsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showUpgrades(4);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
       initUpgrades();
    }
    private void initUpgrades()  {
        TextureAtlas uiAtlas = assets.manager.get(Assets.uiAtlas);
        Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"),uiAtlas);
        Json json  = new Json();
        JsonValue.JsonIterator iter = iterator.next().iterator();

        Table spreadTable = new Table(skin);
        for (int i = 0; i < 10 ; i++) {
            UpgradeActor upgradeActor = new UpgradeActor(json.fromJson(Upgrade.class,iter.next().toString()),this);
            upgradeActor.setUpgradeText("Количество зараженных за раз");
            upgradeActors.add(upgradeActor);
            spreadTable.add(upgradeActor).width(width).padBottom(30).row();
        }
        ScrollPane spreadScrollPane = new ScrollPane(spreadTable,skin,"upgrades");
        spreadUpgrades = new Table(skin);
        spreadUpgrades.add(spreadScrollPane).width(width).height(height*0.82f).row();
        spreadUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

        Table powerTable = new Table(skin);

        for (int i = 0;i<10;i++) {
            UpgradeActor upgradeActor = new UpgradeActor(json.fromJson(Upgrade.class,iter.next().toString()),this);
            upgradeActor.setUpgradeText("Сила против иммунитета");
            upgradeActors.add(upgradeActor);
            powerTable.add(upgradeActor).width(width).padBottom(30).row();
        }

        ScrollPane powerScrollPane = new ScrollPane(powerTable,skin,"upgrades");

        powerScrollPane.setActor(powerTable);
        powerUpgrades = new Table(skin);
        powerUpgrades.add(powerScrollPane).width(width).height(height*0.82f).row();
        powerUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

        Table dnaTable = new Table(skin);
        for (int i = 0;i<10;i++) {
            UpgradeActor upgradeActor = new UpgradeActor(json.fromJson(Upgrade.class,iter.next().toString()),this);
            upgradeActor.setUpgradeText("Днк за заражение людей");
            upgradeActors.add(upgradeActor);
            dnaTable.add(upgradeActor).width(width).padBottom(30).row();
        }
        ScrollPane dnaScrollPane = new ScrollPane(dnaTable,skin,"upgrades");
        dnaUpgrades = new Table(skin);
        dnaUpgrades.add(dnaScrollPane).width(width).height(height*0.82f).row();
        dnaUpgrades.setBounds(0,height/13.6f,width,height*0.82f);


        Table dnapsTable = new Table(skin);
        for (int i = 0;i<10;i++) {
            Upgrade upgrade = json.fromJson(Upgrade.class,iter.next().toString());
            UpgradeActor upgradeActor = new UpgradeActor(upgrade,this);
            upgradeActors.add(upgradeActor);
            upgradeActor.setUpgradeText("Днк/c за каждого зараженного");
            dnapsTable.add(upgradeActor).width(width).padBottom(30).row();
        }
        ScrollPane dnapsScrollPane = new ScrollPane(dnapsTable,skin,"upgrades");
        dnapsUpgrades = new Table(skin);
        dnapsUpgrades.add(dnapsScrollPane).width(width).height(height*0.82f).row();
        dnapsUpgrades.setBounds(0,height/13.6f,width,height*0.82f);


    }

    public void setPlanet(int planet) {
        switch (planet) {
            case 1:
                background.setBackground(backGroundAtlas.findRegion("background1"));
                people.setPeople(assets.manager.get(Assets.planetAtlasHealthy1),assets.manager.get(Assets.planetAtlasIll1));break;
            case 2:
                background.setBackground(backGroundAtlas.findRegion("background2"));
                people.setPeople(assets.manager.get(Assets.planetAtlasHealthy2),assets.manager.get(Assets.planetAtlasIll2));break;
            case 3:
                background.setBackground(backGroundAtlas.findRegion("background3"));
                people.setPeople(assets.manager.get(Assets.planetAtlasHealthy3),assets.manager.get(Assets.planetAtlasIll3));break;
            case 4:
                background.setBackground(backGroundAtlas.findRegion("background4"));
                people.setPeople(assets.manager.get(Assets.planetAtlasHealthy4),assets.manager.get(Assets.planetAtlasIll4));break;
            case 5:
                background.setBackground(backGroundAtlas.findRegion("background5"));
                people.setPeople(assets.manager.get(Assets.planetAtlasHealthy5),assets.manager.get(Assets.planetAtlasIll5));break;
        }
    }

    private void hideUpgrades() {
        activeUpgrades.remove();
        activeUpgrades = null;
    }
    private void showUpgrades(int button){
            if(activeUpgrades != null) {
                activeUpgrades.remove();
            }
            switch (button) {
                case 1:
                    addActor(spreadUpgrades);
                    activeUpgrades = spreadUpgrades;
                    break;
                case 2:
                    addActor(powerUpgrades);
                    activeUpgrades = powerUpgrades;
                    break;
                case 3:
                    addActor(dnaUpgrades);
                    activeUpgrades = dnaUpgrades;
                    break;
                case 4:
                    addActor(dnapsUpgrades);
                    activeUpgrades = dnapsUpgrades;
                    break;
            }
        }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }
    public void update(float delta) {
        counter.setCounter(virus.getDna());
        act(delta);
        draw();
        for (UpgradeActor upgradeActor : upgradeActors) {
            upgradeActor.update();
        }
        if(virus.getInfected() >= 20000000) {
            if (planet != 5) {
                planet = 5;
                setPlanet(planet);
            }
        }
        else if(virus.getInfected() >= 2500000) {
            if (planet != 4) {
                planet = 4;
                setPlanet(planet);
            }
        }
        else if(virus.getInfected() >= 500000) {
            if(planet != 3) {
                planet = 3;
                setPlanet(planet);
            }

        }
        else if(virus.getInfected() >= 100000) {
            if (planet != 2) {
                planet = 2;
                setPlanet(planet);
            }

        }
        virus.update(delta);
    }
    public void save() {
        Json json = new Json();
        FileHandle file = Gdx.files.local("upgrades.json");
        file.writeString("{",false);
        file.writeString("planet: "+planet+","+"\n"+"peopleImmune: "+ People.immuneMax+","+"\n",true);
        file.writeString("virus:["+json.toJson(virus,Virus.class)+"],"+"\n",true);
        file.writeString("upgrades: [",true);
        for (UpgradeActor upgradeActor : upgradeActors) {
            file.writeString(json.toJson(upgradeActor.getUpgrade(),Upgrade.class)+","+"\n",true);
        }
        file.writeString("],}",true);
    }
    public Virus getVirus() {
        return virus;
    }
}
