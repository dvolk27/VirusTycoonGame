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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


public class UpgradeActor extends Table {

    private Label upgradeName;
    private String upgradeNametxt;
    private Label upgradeValue;
    private String upgradeText;
    private Label cost;
    private ImageTextButton buyButton;
    private Upgrade upgrade;
    private Label purchased;
    private Table nested1;
    private Table nested2;
    private Scene scene;
    private Virus virus;
    public UpgradeActor(Upgrade upgrade, Scene scene) {
        this.upgrade = upgrade;
        TextureAtlas uiAtlas = new TextureAtlas(Gdx.files.internal("UI/uiskin.atlas"));
        Skin skin = new Skin(Gdx.files.internal("UI/uiskin.json"),uiAtlas);
        purchased = new Label("Куплено: "+Integer.toString(upgrade.getPurchased()),skin,"upgrades-small");
        cost = new Label(Long.toString((long)(upgrade.getBasecost() * Math.pow(1.07,upgrade.getPurchased()))),skin);
        upgradeName = new Label(upgrade.getUpgradeName(),skin,"upgrades-big");
        upgradeName.setFontScale(0.45f);
        upgradeValue = new Label(Long.toString(upgrade.getUpgradeValue()),skin,"upgrades-small");
        upgradeValue.setFontScale(0.45f);
        this.scene = scene;
        virus = scene.getVirus();
        purchased.setFontScale(0.45f);
        buyButton = new ImageTextButton(Long.toString((long)(upgrade.getBasecost() * Math.pow(1.07,upgrade.getPurchased()))),skin);
        cost.setFontScale(0.45f);
        buyButton.clearChildren();
        buyButton.add(cost);
        setBackground(new TextureRegionDrawable(uiAtlas.findRegion("darkgrey")));
        buyButton.setBackground(new TextureRegionDrawable(uiAtlas.findRegion("buybutton")));

        nested1 = new Table(skin);
        nested2 = new Table(skin);
        initTable();
    }
    private void initTable() {
        buyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(virus.getDna() >= (long)(upgrade.getBasecost()*Math.pow(1.15f,upgrade.getPurchased()))) {

                    virus.setDna(virus.getDna()-(long)(upgrade.getBasecost()*Math.pow(1.15f,upgrade.getPurchased())));
                    if(upgrade.getUpgradeName().equals("Передача рукопожатием") || upgrade.getUpgradeName().equals("Передача воздушно-капельным путем") || upgrade.getUpgradeName().equals("Передача через предметы") || upgrade.getUpgradeName().equals("Вирус в воздухе вокруг больного") || upgrade.getUpgradeName().equals("Передача через домашних животных") || upgrade.getUpgradeName().equals("Передача через насекомых") || upgrade.getUpgradeName().equals("Передача через еду") || upgrade.getUpgradeName().equals("Распространение по водопроводу") || upgrade.getUpgradeName().equals("Распространение по гидросфере") || upgrade.getUpgradeName().equals("Распространение по атмосфере")) {
                        virus.setSpread(virus.getSpread()+upgrade.getUpgradeValue());
                    }
                    else if(upgrade.getUpgradeName().equals("Способы проникновение в организм") || upgrade.getUpgradeName().equals("Подавление бдительности организм") || upgrade.getUpgradeName().equals("Распространение по организму") || upgrade.getUpgradeName().equals("Защита от воздействия лекарств") || upgrade.getUpgradeName().equals("Скорость перестройки генома") || upgrade.getUpgradeName().equals("Подавление усваивания витаминов") || upgrade.getUpgradeName().equals("Подавление лейкоцитов") || upgrade.getUpgradeName().equals("Разрушние спинного мозга") || upgrade.getUpgradeName().equals("Подавлене нервной системы") || upgrade.getUpgradeName().equals("Нарушение работы мозга")) {
                        virus.setPower(virus.getPower()+upgrade.getUpgradeValue());
                    }
                    else if(upgrade.getUpgradeName().equals("Проникновение в организм") || upgrade.getUpgradeName().equals("Разрушение клеточной мембраны") || upgrade.getUpgradeName().equals("Разрушение более плотных тканей") || upgrade.getUpgradeName().equals("Количество выделенного ДНК") || upgrade.getUpgradeName().equals("Целостность выделенного ДНК") || upgrade.getUpgradeName().equals("Снижение деградации ДНК") || upgrade.getUpgradeName().equals("Ферменты для сохранения ДНК") || upgrade.getUpgradeName().equals("Проникновение в кости организма") || upgrade.getUpgradeName().equals("Проникновение в мозг организма") || upgrade.getUpgradeName().equals("Абcорбция ДНК")) {
                        virus.setDnaPerPeople(virus.getDnaPerPeople()+upgrade.getUpgradeValue());
                    }
                    else if(upgrade.getUpgradeName().equals("Подавление сопротивления организма") || upgrade.getUpgradeName().equals("Сегрегация клеток") || upgrade.getUpgradeName().equals("Срок жизни вируса в организме") || upgrade.getUpgradeName().equals("Мутации вируса в организме") || upgrade.getUpgradeName().equals("Стимуляция деления ДНК - клеток") || upgrade.getUpgradeName().equals("Принудительное деление клеток") || upgrade.getUpgradeName().equals("Контроль обмена веществ") || upgrade.getUpgradeName().equals("Контроль разума заражённого") || upgrade.getUpgradeName().equals("Стимуляция выработки ДНК") || upgrade.getUpgradeName().equals("Клонирование ДНК")) {
                        virus.setDnaPerSecond(virus.getDnaPerSecond()+upgrade.getUpgradeValue());
                    }
                    upgrade.setPurchased(upgrade.getPurchased()+1);
                    }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        upgradeName.setAlignment(Align.left);
        nested1.add().height(40).row();
        nested1.add(upgradeName).padBottom(30).row();
        nested1.add(upgradeValue).bottom().left();
        add(nested1).expandX().left().padRight(30).padLeft(30);

        nested2.add(buyButton).row();
        nested2.add(purchased).left();
        add(nested2);

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    public ImageTextButton getBuyButton() {
        return buyButton;
    }
    public void update() {
        cost.setText((int) (upgrade.getBasecost()*Math.pow(1.15f,upgrade.getPurchased())));
        upgradeValue.setText(upgradeText+" +"+upgrade.getUpgradeValue());
        purchased.setText("Куплено: "+upgrade.getPurchased());
        if(virus.getDna() < upgrade.getBasecost()*Math.pow(1.15f,upgrade.getPurchased())) {
            buyButton.getLabel().setColor(Color.RED);
        }
    }
    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgradeText(String upgradeText) {
        this.upgradeText = upgradeText;
        upgradeValue.setText(upgradeText+" +"+upgrade.getUpgradeValue());
    }
}
