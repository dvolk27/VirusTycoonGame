package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public AssetManager manager = new AssetManager();
    public static final AssetDescriptor<Texture> background1 = new AssetDescriptor<Texture>("backgrounds/background1.png",Texture.class);
    public static final AssetDescriptor<Texture> adult_man = new AssetDescriptor<Texture>("sprites/adult_man.png",Texture.class);
    public static final AssetDescriptor<TextureAtlas> uiAtlas = new AssetDescriptor<TextureAtlas>("UI/uiskin.atlas",TextureAtlas.class);
    public static final AssetDescriptor<Skin> uiSkin = new AssetDescriptor<Skin>("UI/uiskin.json",Skin.class,new SkinLoader.SkinParameter("UI/uiskin.atlas"));

    public void load()
    {
        manager.load(background1);
        manager.load(adult_man);
        manager.load(uiAtlas);
        manager.load(uiSkin);
    }

    public void dispose()
    {
        manager.dispose();
    }
}
