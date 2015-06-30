package com.zombierush.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

/**
 * Basic entity
 */
public abstract class AbstractEntity implements iEntity {
    
    // Sprite
    Texture tex;
    
    // Position
    float xPosition;
    float yPosition;
    
    /**
     * Default constructor
     */
    public AbstractEntity()
    {
        /* do nothing... */
    }
    
    /**
     * 
     * @param t 
     */
    public AbstractEntity(Texture t)
    {
        tex = t;
    }
    
    /**
     * Render this entity to the screen
     */
    @Override
    public void Render(SpriteBatch batch)
    {
        batch.draw(tex, xPosition, yPosition);
        
    }  // end Render
    
    /**
     * Update this entity
     */
    @Override
    public void Update(float delta)
    {
        
    }  // end Update
    
}  // end AbstractEntity
