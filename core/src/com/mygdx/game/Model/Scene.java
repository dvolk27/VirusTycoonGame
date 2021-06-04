package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.MainScreen;

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
    public static final float ppuX = Gdx.graphics.getWidth()/1080;
    public static final float ppuY = Gdx.graphics.getHeight()/1920;
    private Assets assets;
    private Table dnaUpgrades;
    private Table dnapsUpgrades;
    private Table powerUpgrades;
    private Table spreadUpgrades;
    private Table activeUpgrades;
    private MainScreen mainScreen;
    private Backboard backboard;
    public Scene(Viewport viewport, Assets assets, MainScreen mainScreen) {
        super(viewport);
        this.assets = assets;
        this.mainScreen = mainScreen;
        width = viewport.getScreenWidth();
        height = viewport.getScreenHeight();
        setGameObjects();
        setUi();
    }

    private void setGameObjects(){
        background = new Background(assets.manager.get(Assets.background1));
        people = new People(assets,width/2,height/6.8f);
        backboard = new Backboard(new TextureRegion(assets.manager.get(Assets.uiAtlas).findRegion("zaglushka")));
        addActor(background);
        addActor(backboard);
        addActor(people);
        background.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                counter.inc(1);
                ParticleEffect pe = new ParticleEffect();
                pe.load(Gdx.files.internal("virusparticles.party"),Gdx.files.internal(""));
                pe.setPosition(x,y);
                pe.start();
                mainScreen.addParticles(pe);

                if(activeUpgrades != null) {
                    hideUpgrades();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        people.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                counter.inc(1);
                ParticleEffect pe = new ParticleEffect();
                pe.load(Gdx.files.internal("virusparticles.party"),Gdx.files.internal(""));
                pe.setPosition(width/2+x/4,height/6.8f+y);
                pe.start();
                mainScreen.addParticles(pe);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void setUi() {
        TextureAtlas uiAtlas = assets.manager.get(Assets.uiAtlas);

        dnaButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("dnabutton")));
        spreadButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("spreadbutton")));
        dnapsButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("dnapsbutton")));
        powerButton = new ImageButton(new TextureRegionDrawable(uiAtlas.findRegion("powerbutton")));
        spreadButton.setBounds(0,0,width/4,height/13);
        powerButton.setBounds(width/4,0,width/4,height/13);
        dnaButton.setBounds(width/2,0,width/4,height/13);
        dnapsButton.setBounds(width*3/4,0,width/4,height/13);
        counter = new Counter(new TextureRegion(uiAtlas.findRegion("dnkicon")));

        addActor(spreadButton);
        addActor(powerButton);
        addActor(dnapsButton);
        addActor(dnaButton);
        addActor(counter);

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
    private void initUpgrades() {
        TextureAtlas uiAtlas = assets.manager.get(Assets.uiAtlas);
        Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"),uiAtlas);
        Table dnaTable = new Table(skin);

        ScrollPane dnaScrollPane = new ScrollPane(dnaTable,skin,"upgrades");
        dnaUpgrades = new Table(skin);
        dnaUpgrades.add(dnaScrollPane).width(width).height(height*0.82f).row();
        dnaUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

        Table powerTable = new Table(skin);
        for (int i = 0; i < 10; i++) {
            powerTable.add(new Upgrade("Распространение по воздуху",5,1,0)).expandX().row();
        }
        ScrollPane powerScrollPane = new ScrollPane(powerTable,skin,"upgrades");
        Table table = (Table) powerScrollPane.getChild(0);
        table.removeActorAt(5,false);
        table.add("pup").row();
        powerScrollPane.setActor(table);
        powerUpgrades = new Table(skin);
        powerUpgrades.add(powerScrollPane).width(width).height(height*0.82f).row();
        powerUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

        Table dnapsTable = new Table(skin);
        dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();dnapsTable.add("hui").row();

        ScrollPane dnapsScrollPane = new ScrollPane(dnapsTable,skin,"upgrades");
        dnapsUpgrades = new Table(skin);
        dnapsUpgrades.add(dnapsScrollPane).width(width).height(height*0.82f).row();
        dnapsUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

        Table spreadTable = new Table(skin);
        spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();spreadTable.add("ADawda").row();
        ScrollPane spreadScrollPane = new ScrollPane(spreadTable,skin,"upgrades");
        spreadUpgrades = new Table(skin);
        spreadUpgrades.add(spreadScrollPane).width(width).height(height*0.82f).row();
        spreadUpgrades.setBounds(0,height/13.6f,width,height*0.82f);

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

    public void update() {
        act(Gdx.graphics.getDeltaTime());
        draw();

    }
}
