package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    
    /**
     * Default constructor
     */
    public MainMenu(ZombieRush g)
    {
        game = g;
        camera = game.GetCamera();
        batch = game.GetBatch();
        Gdx.input.setInputProcessor(this);
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
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.GetBatch().begin();
        game.GetFont().draw(game.GetBatch(), "Main Menu, press enter...", 100, 100);
        game.GetBatch().end();
    }
    
    
    /**
     * 
     * @param keycode
     * @return 
     */
    public boolean keyUp (int keycode) 
    {
        if (keycode == Keys.ENTER)
        {
            game.setScreen(new PlayGame(game));
        }
        return true;
    }
    
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
