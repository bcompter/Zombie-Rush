package com.zombierush.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/**
 * Zombie
 */
public class Zombie extends AbstractEntity{
    
    // Game reference
    ZombieRush game;
    
    // Commanded posiitons
    float desiredX;
    float desiredY;
    
    // Speed in pixels per second
    float normalSpeed = 75;
    
    /**
     * Create a new zombie
     */
    public Zombie(Texture t, ZombieRush g)
    {
        super(t);
        game = g;
        
        int position = MathUtils.random(2800);
        if (position < 600)
        {
            xPosition = 0;
            yPosition = position;
        }
        else if (position < 1400)
        {
            position -= 600;
            xPosition = position;
            yPosition = 600;
        }
        else if (position < 2000)
        {
            position -= 1400;
            xPosition = 800;
            yPosition = position;
        }
        else
        {
            position -= 2000;
           xPosition = position;
           yPosition = 0; 
        }
        
        
        desiredX = xPosition;
        desiredY = yPosition;
    }
    
    /**
     * 
     * @param delta 
     */
    public void Update(float delta)
    {
        // Set the desired position equal to the nearest human
        Human h = game.GetNearestHuman();
        desiredX = h.xPosition;
        desiredY = h.yPosition;
        
        float dx = desiredX - xPosition;
        float dy = desiredY - yPosition;
        
        float totalDelta = Math.abs(dx) + Math.abs(dy);
            
        if (totalDelta < normalSpeed * delta)
        {
            xPosition = desiredX;
            yPosition = desiredY;
        }
        else
        {
            xPosition += normalSpeed * (dx / totalDelta) * delta;
            yPosition += normalSpeed * (dy / totalDelta) * delta;
        }
        
    }
    
    
    
}  // end Zombie
