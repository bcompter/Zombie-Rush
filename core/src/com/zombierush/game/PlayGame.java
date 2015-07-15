package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.FPSLogger;

/**
 * Main game play screen
 */
public class PlayGame extends InputAdapter implements Screen {
    
    // A reference to the main game
    ZombieRush game;
    
    // Camera reference
    OrthographicCamera camera;
    
    // Sprite batch reference
    SpriteBatch batch;
    
    // Counting FPS
    FPSLogger fpsLogger = new FPSLogger();
    
    // FPS counter
    int fps = 0;
    int numFrames = 0;
    float deltaT = 0.0f;

    /**
     * Default constructor
     */
    public PlayGame(ZombieRush g)
    {
        game        = g;
        camera      = game.GetCamera();
        batch       = game.GetBatch();
        Gdx.input.setInputProcessor(this);
        
        game.human                  = new Human(game.GetHumanTex(), g);
        game.zombies                = new Array();
        game.barricades             = new Array();
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
        // Count our FPS
        numFrames++;
        deltaT += delta;
        if (deltaT > 1.0f)
        {
            fps = numFrames;
            deltaT = 0.0f;
            numFrames = 0;
        }
        
        // Update the game
        Update(delta);
        
        // Update the camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        RenderBackground();
        RenderHuman();
        RenderZombies();
        RenderBarricades();
        
        game.GetFont().draw(game.GetBatch(), "FPS: " + fps, 100, 100);
        
        batch.end();
    }
    
    /**
     * Update all entities
     * @param delta 
     */
    private void Update(float delta)
    {
        /**
         * Update all game elements
         */
        game.human.Update(delta);
        for (Zombie z : game.zombies)
        {
            z.Update(delta);
        }
        for (Barricade b : game.barricades)
        {
            b.Update(delta);
        }
        
        /**
         * Remove any dead zombies
         */
        Array <Zombie> removeList = new Array();
        for (Zombie z : game.zombies)
        {
            if (z.health <= 0)
                removeList.add(z);
        }
        game.zombies.removeAll(removeList, true);
        
        /**
         * Check for dead humans
         */
        if (game.human.health <= 0)
        {
            System.out.println("Human is DEAD!!!");
        }
        
    }  // end Update
    
    /**
     * Draw the background to the screen
     */
    private void RenderBackground()
    {
        batch.disableBlending();
        for (int x = 0; x < 800;)
        {
            for (int y = 600; y > -128;)
            {
                batch.draw(game.GetTileTex(), x, y);
                y -= 128;
            }
            x += 128;
        }
        batch.enableBlending();
        
    }  // end RenderBackground
    
    /**
     * Render all human characters
     */
    private void RenderHuman()
    {
        game.human.Render(batch);
        
    }  // end RenderHuman
    
    /**
     * Render all zombies to the screen
     */
    private void RenderZombies()
    {
        for (Zombie z : game.zombies)
        {
            z.Render(batch);
        }
    }
    
    /**
     * Render all barricades to the screen
     */
    private void RenderBarricades()
    {
        for(Barricade b : game.barricades)
        {
            b.Render(batch);
        }
    }
    
    /**
     * Handle mouse clicks
     */
    public boolean touchUp (int x, int y, int pointer, int button) 
    {
        // Modify touch coordinates to match render axis
        y = 600 - y;
        
        if (button == Input.Buttons.LEFT)
        {
            // Update human's desired position to be the current click
            game.human.UpdateDesiredPosition(x, y);
        }
        else if (button == Input.Buttons.RIGHT)
        {
            // Build a new barricade at this coordinate
            game.barricades.add(new Barricade(game.barricadeTex, x, y, game.human));
        }
        
        return true;
    }
    
    /**
     * 
     * @param keycode
     * @return 
     */
    public boolean keyUp (int keycode) 
    {
        if (keycode == Input.Keys.Z)
        {
            // Spawn a new zombie!
            game.zombies.add(new Zombie(game.GetZombieTex(), game));
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
    
}  // end PlayGame
