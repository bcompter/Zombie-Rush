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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
    Texture dirtTex;
    
    // Shape Renderer
    ShapeRenderer shapeRenderer;
    
    // Camera
    FitViewport viewPort;
    OrthographicCamera camera;
    
    // Screen size
    final int SCREEN_HEIGHT = 600;
    final int SCREEN_WIDTH = 800;
    
    // Text font handler
    BitmapFont font;
    
    // The human! A list of humans later on...
    Human human;
    
    // The zombies!  Lots of them!
    Array <Zombie> zombies;
    
    // Barricades
    Array <Barricade> barricades;
    
    // Gunshot effects
    Array <ShapeEffect> shapeEffects;
    
    // Player points
    int points = 0;
    
    // Player money
    int bucks = 0;

    @Override
    public void create () {
        
        // Create our sprite batch to render things
        batch = new SpriteBatch();
        
        // Load textures
        tileTex         = new Texture("grass.png");
        humanTex        = new Texture("human.png");
        zombieTex       = new Texture("zombie.png");
        barricadeTex    = new Texture("barricade.png");
        dirtTex         = new Texture("dirt.png");
        
        // Prepare bitmapfont
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        
        // Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        // SHape renderer, to render shapes...
        shapeRenderer = new ShapeRenderer();
        
        // Away we go!
        setScreen(new MainMenu(this));
    }
    
    /**
     * Get the sprite batch for this game
     */
    public SpriteBatch GetBatch()
    {
        return batch;
    }
    
    /**
     * Get the bitmap font for this game
     */
    public BitmapFont GetFont()
    {
        return font;
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
     * Get the nearest human
     */
    public Human GetNearestHuman()
    {
        return human;
    }
    
    /**
     * Get the nearest zombie
     */
    public Zombie GetNearestZombie(float x, float y)
    {
        Zombie retval = new Zombie();
        float closestDistance = 1000.0f;
        for (Zombie z : zombies)
        {
            float distance = GetDistance(x, y, z.xPosition, z.yPosition);
            if (distance < closestDistance)
                retval = z;
        }
        return retval;
    }
    
    /**
     * Get the nearest barricade
     */
    public Barricade GetNearestBarricade(float x, float y)
    {
        Barricade retval = new Barricade();
        float closestDistance = 1000.0f;
        for (int i = 0; i < barricades.size; i++)
        {
            Barricade b = barricades.get(i);
            float distance = GetDistance(x, y, b.xPosition, b.yPosition);
            if (distance < closestDistance)
                retval = b;
        }
        return retval;
    }
    
    /**
     * Get the distance between two entities
     */
    public float GetDistance(AbstractEntity a, AbstractEntity b)
    {
        double distance = 
                Math.pow(Math.abs(a.xPosition - b.xPosition),2) 
                + Math.pow(Math.abs(a.yPosition - b.yPosition),2);
        distance = Math.sqrt(distance);        
        
        return (float)distance;
    }
    
    /**
     * Get the distance between two coordinates
     */
    public float GetDistance(float x1, float y1, float x2, float y2)
    {
        double distance = 
                Math.pow(Math.abs(x1 - x2),2) 
                + Math.pow(Math.abs(y1 - y2),2);
        distance = Math.sqrt(distance);        
        
        return (float)distance;
    }
    
}  // end ZombieRush
