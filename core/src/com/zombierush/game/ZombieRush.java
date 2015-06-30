package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Zombie Rush
 * 
 * A real time strategy game of zombie survival.
 */
public class ZombieRush extends Game {
    
    // Sprite batch for rendering
    SpriteBatch batch;
    
    // Textures
    Texture humanTex;
    Texture zombieTex;
    Texture tileTex;
    Texture barricadeTex;
    
    // Camera
    FitViewport viewPort;
    OrthographicCamera camera;
    
    // Text font handler
    BitmapFont font;
    
    // The human! A list of humans later on...
    Human human;
    
    // The zombies!  Lots of them!
    Array <Zombie> zombies;

    @Override
    public void create () {
        batch = new SpriteBatch();
        
        // Load textures
        tileTex = new Texture("grass.png");
        humanTex = new Texture("human.png");
        zombieTex = new Texture("zombie.png");
        barricadeTex = new Texture("barricade.png");
        
        // Prepare bitmapfont
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
        // Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        
        // Away we go!
        setScreen(new MainMenu(this));
    }
    
    /**
     * 
     * @return 
     */
    public SpriteBatch GetBatch()
    {
        return batch;
    }
    
    /**
     * 
     * @return 
     */
    public BitmapFont GetFont()
    {
        return font;
    }
    
    /**
     * 
     * @return 
     */
    public Texture GetTileTex()
    {
        return tileTex;
    }
    
    /**
     * 
     * @return 
     */
    public Texture GetHumanTex()
    {
        return humanTex;
    }
    
    /**
     * 
     * @return 
     */
    public Texture GetZombieTex()
    {
        return zombieTex;
    }
          
    /**
     * 
     * @return 
     */
    public Texture GetBarricadetex()
    {
        return barricadeTex;
    }
    
    /**
     * 
     * @return 
     */
    public OrthographicCamera GetCamera()
    {
        return camera;
    }
    
    /**
     * 
     * @return 
     */
    public Human GetNearestHuman()
    {
        return human;
    }
    
}  // end ZombieRush
