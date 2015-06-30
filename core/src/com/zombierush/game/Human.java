package com.zombierush.game;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author briancompter1
 */
public class Human extends AbstractEntity{
    
    // Commanded posiitons
    float desiredX;
    float desiredY;
    
    // Speed in pixels per second
    float normalSpeed = 150;
    
    /**
     * Create a new human
     */
    public Human(Texture t)
    {
        super(t);
        xPosition = 700;
        yPosition = 500;
        
        desiredX = xPosition;
        desiredY = yPosition;
    }
    
    /**
     * 
     * @param delta 
     */
    @Override
    public void Update(float delta)
    {
        // If we are not at our desired location start moving!
        if (((int)desiredX) != ((int)xPosition) || ((int)desiredY) != ((int)yPosition))
        {
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

        }  // end if not at desired
        
    }  // end Update
    
    public void UpdateDesiredPosition(int x, int y)
    {
        desiredX = x;
        desiredY = y;
    }
    
    
}  // end Human
