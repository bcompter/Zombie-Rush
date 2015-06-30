package com.zombierush.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Entity interface
 */
public interface iEntity {
    
    /**
     * Render this entity to the screen
     */
    public void Render(SpriteBatch batch);
    
    /**
     * Update this entity
     */
    public void Update(float delta);
    
}  // end iEntity
