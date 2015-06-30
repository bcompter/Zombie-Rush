package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputAdapter;

/**
 * Game Over screen
 */
public class GameOver extends InputAdapter implements Screen{
    
    // A reference to the main game
    ZombieRush game;
    
    /**
     * Default constructor
     */
    public GameOver(ZombieRush g)
    {
        game = g;
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
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.GetBatch().begin();
        game.GetFont().draw(game.GetBatch(), "Game Over", 0, 0);
        game.GetBatch().end();
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
    
}  // end GameOver
