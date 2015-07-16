package com.zombierush.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Basic entity
 */
public abstract class AbstractEntity implements iEntity {
    
    // Game reference
    ZombieRush game;
    
    // Sprite
    Sprite sprite;
    
    /**
     * Color tinting
     */
    float red, green, blue;
    float alpha;
    
    // Position
    float xPosition;
    float yPosition;
    
    // Health
    float health;
    float hitTimer;
    
    /**
     * Default constructor
     */
    public AbstractEntity()
    {
        /* do nothing... */
    }
    
    /**
     * Create a new entity using the supplied texture
     */
    public AbstractEntity(Texture t)
    {
        sprite = new Sprite(t);
        red = 1;
        green = 1;
        blue = 1;
        alpha = 1;
    }
    
    /**
     * Render this entity to the screen
     */
    @Override
    public void Render(SpriteBatch batch)
    {
        sprite.setColor(red, green, blue, alpha);
        sprite.setCenter(xPosition, yPosition);
        sprite.draw(batch);
        
    }  // end Render
    
    /**
     * Update this entity
     */
    @Override
    public void Update(float delta)
    {
        if (hitTimer > 0)
        {
            hitTimer -= 0;
            blue += delta;
            green += delta;
            if (blue > 1)
            {
                blue = 1;
                green = 1;
            }
        }
    }  // end Update
    
    /**
     * Take damage
     */
    public void TakeDamage(float damage, float angle, boolean pushBack)
    {
        health -= damage;
        hitTimer = 1;
        red = 1;
        blue = 0.5f;
        green = 0.5f;
        if (this instanceof Human)
        {
            System.out.println("OUCH!!!");
        }
    }
    
}  // end AbstractEntity
