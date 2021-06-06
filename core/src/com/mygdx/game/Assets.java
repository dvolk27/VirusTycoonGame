package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Model.Background;

public class Assets {
    public AssetManager manager = new AssetManager();
    public static final AssetDescriptor<TextureAtlas> uiAtlas = new AssetDescriptor<TextureAtlas>("UI/uiskin.atlas",TextureAtlas.class);
    public static final AssetDescriptor<Skin> uiSkin = new AssetDescriptor<Skin>("UI/uiskin.json",Skin.class,new SkinLoader.SkinParameter("UI/uiskin.atlas"));
    public static final AssetDescriptor<TextureAtlas> planetAtlasHealthy1 = new AssetDescriptor<TextureAtlas>("sprites/planet1/planet1_healthy.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasIll1 = new AssetDescriptor<TextureAtlas>("sprites/planet1/planet1_ill.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasHealthy2 = new AssetDescriptor<TextureAtlas>("sprites/planet2/planet2_healthy.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasIll2 = new AssetDescriptor<TextureAtlas>("sprites/planet2/planet2_ill.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasHealthy3 = new AssetDescriptor<TextureAtlas>("sprites/planet3/planet3_healthy.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasIll3 = new AssetDescriptor<TextureAtlas>("sprites/planet3/planet3_ill.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasHealthy4 = new AssetDescriptor<TextureAtlas>("sprites/planet4/planet4_healthy.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasIll4 = new AssetDescriptor<TextureAtlas>("sprites/planet4/planet4_ill.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasHealthy5 = new AssetDescriptor<TextureAtlas>("sprites/planet5/planet5_healthy.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> planetAtlasIll5 = new AssetDescriptor<TextureAtlas>("sprites/planet5/planet5_ill.atlas",TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> backGroundAtlas = new AssetDescriptor<TextureAtlas>("backgrounds/planets.atlas",TextureAtlas.class);
    public void load()
    {

        manager.load(uiAtlas);
        manager.load(uiSkin);
        manager.load(planetAtlasHealthy1);
        manager.load(planetAtlasHealthy2);
        manager.load(planetAtlasHealthy3);
        manager.load(planetAtlasHealthy4);
        manager.load(planetAtlasHealthy5);
        manager.load(planetAtlasIll1);
        manager.load(planetAtlasIll2);
        manager.load(planetAtlasIll3);
        manager.load(planetAtlasIll4);
        manager.load(planetAtlasIll5);
        manager.load(backGroundAtlas);
    }

    public void dispose()
    {
        manager.dispose();
    }
}
