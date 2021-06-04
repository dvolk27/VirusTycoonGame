package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;



public class Upgrade extends Table {
    private Image upgradeIcon;
    private Label upgradeName;
    private Label upgradeValue;
    private String upgradeText;
    private Label costTxt;
    private ImageTextButton buyButton;
    private long cost;
    private int purchased;
    private Label purchasedTxt;
    private Table nested1;
    private Table nested2;
    public Upgrade(String upgradeName,long basecost,int upgradeValue,int purchased) {
        this.purchased = purchased;
        this.cost = (int)(basecost * Math.pow(1.07,purchased));
        Pixmap pixmap = new Pixmap(256,256,Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        setWidth(Gdx.graphics.getWidth());
        TextureRegion textureRegion = new TextureRegion(new Texture(pixmap));
        upgradeIcon = new Image(textureRegion);
        Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"),new TextureAtlas(Gdx.files.internal("UI/uiskin.atlas")));
        this.upgradeName = new Label(upgradeName,skin,"upgrades-big");

        this.upgradeName.setFontScale(0.5f);
        this.upgradeValue = new Label(Integer.toString(upgradeValue),skin,"upgrades-small");
        this.upgradeValue.setFontScale(0.45f);
        this.purchasedTxt = new Label(Integer.toString(purchased),skin,"upgrades-small");
        this.purchasedTxt.setFontScale(0.45f);
        buyButton = new ImageTextButton(Long.toString(cost),skin);
        costTxt = new Label(Long.toString(cost),skin);
        costTxt.setFontScale(0.45f);
        buyButton.setLabel(costTxt);
        nested1 = new Table(skin);
        nested2 = new Table(skin);
        initTable();
    }
    private void initTable() {
        pad(20);
        add(upgradeIcon).width(128).height(300).padRight(30);
        nested1.add(upgradeName).row();
        nested2.add(upgradeValue).row();
        add(nested1).width(Gdx.graphics.getWidth()/2f).padRight(30);
        nested2.add(buyButton).row();
        nested2.add(purchasedTxt).row();
        add(nested2);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void addPurchased() {
        this.purchased += 1;
    }

    public void setUpgradeIcon(Image upgradeIcon) {
        this.upgradeIcon = upgradeIcon;
    }

    public long getCost() {
        return cost;
    }
}
