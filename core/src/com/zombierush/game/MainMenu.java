package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

/**
 * Main Menu
 */
public class MainMenu extends InputAdapter implements Screen{
    
    // A reference to the main game
    ZombieRush game;
    
    // Camera reference
    OrthographicCamera camera;
    
    // Sprite batch reference
    SpriteBatch batch;
    
    // Textures for the menu
    Array <GenericEntity> entities;
    
    /**
     * Default constructor
     */
    public MainMenu(ZombieRush g)
    {
        game = g;
        camera = game.GetCamera();
        batch = game.GetBatch();
        Gdx.input.setInputProcessor(this);
        
        entities = new Array();
        
        // Background texture
        GenericEntity bg = new GenericEntity(game.dirtTex);
        bg.sprite.setSize(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        bg.xPosition = game.SCREEN_WIDTH/2;
        bg.yPosition = game.SCREEN_HEIGHT/2;
        bg.sprite.setAlpha(0.8f);
        entities.add(bg);
        
        // Menu Bars
        GenericEntity menu = new GenericEntity(game.barricadeTex);
        menu.sprite.setSize(400, 100);
        menu.xPosition = game.SCREEN_WIDTH/2;
        menu.yPosition = 500;
        entities.add(menu);
    }
    
    /**
     * Show the menu
     */
    @Override
    public void show()
    {
        
    }  // end show
    
    /**
     * Render this menu
     * @param delta Elapsed time in seconds
     */
    public void render(float delta)
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.GetBatch().begin();
        
        // Render all entities
        for (GenericEntity g : entities)
        {
            g.Render(batch);
        }
        
        // Get mouse position
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        y = game.SCREEN_HEIGHT - y;
        
        game.GetFont().draw(game.GetBatch(), "New Game", game.SCREEN_WIDTH/2-25, 500);
        game.GetFont().draw(game.GetBatch(), "("+x+", "+y+")", 5, 20);
        game.GetBatch().end();
    }
    
    /**
     * Handle mouse clicks
     */
    public boolean touchUp (int x, int y, int pointer, int button) 
    {
        // Modify touch coordinates to match render axis
        y = game.SCREEN_HEIGHT - y;
        
        if (button == Input.Buttons.LEFT)
        {
            // See if we are clicking our button
            Rectangle a = entities.get(1).sprite.getBoundingRectangle();
            Rectangle b = new Rectangle(x, y, 1, 1);
            if (a.overlaps(b))
            {
                game.setScreen(new PlayGame(game));
            }
            
        }
                
        return true;
        
    }  // end touchUp
    
    /**
     * Dispose of this menu
     */
    public void dispose()
    {
        
    }
    
    /**
     * Hide this menu
     */
    public void hide()
    {
        
    }
    
    /**
     * Pause this menu
     */
    public void pause()
    {
        
    }
    
    /**
     * Resume this menu
     */
    public void resume()
    {
        
    }
    
    /**
     * Resize this menu
     * @param width
     * @param height 
     */
    public void resize(int width, int height)
    {
        
    }
    
}  // end MainMenu
