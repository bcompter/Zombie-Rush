package com.zombierush.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Basic entity
 */
public abstract class AbstractEntity implements iEntity {
    
    // Sprite
    Sprite sprite;
    
    // Position
    float xPosition;
    float yPosition;
    
    // Health
    float health;
    
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
        sprite = new Sprite(t);
    }
    
    /**
     * Render this entity to the screen
     */
    @Override
    public void Render(SpriteBatch batch)
    {
        sprite.setCenter(xPosition, yPosition);
        sprite.draw(batch);
        
    }  // end Render
    
    /**
     * Update this entity
     */
    @Override
    public void Update(float delta)
    {
        
    }  // end Update
    
    /**
     * Take damage
     */
    public void TakeDamage(float damage, float angle, boolean pushBack)
    {
        health -= damage;
        if (this instanceof Human)
        {
            System.out.println("OUCH!!!");
        }
    }
    
}  // end AbstractEntity
