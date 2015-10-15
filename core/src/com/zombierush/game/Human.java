package com.zombierush.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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
    
    // Our weapon
    Weapon weapon = new Weapon();
    
    // Flag used to pass "null" values
    boolean isValid = true;
    
    /**
     * Default constructor
     */
    public Human()
    {
        isValid = false;
    }
    
    /**
     * Create a new human
     */
    public Human(Texture t, ZombieRush g)
    {
        super(t);
        game = g;
        sprite.setSize(32, 32);
        xPosition = game.SCREEN_WIDTH/2;
        yPosition = game.SCREEN_HEIGHT/2;
        
        desiredX = xPosition;
        desiredY = yPosition;

        health = 10;
    }
    
    /**
     * Update this human
     * Handle movement and weapons fire
     * @param delta 
     */
    @Override
    public void Update(float delta)
    {
        super.Update(delta);
        
        // If we are not at our desired location start moving!
        float tempX = xPosition;
        float tempY = yPosition;
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
        
        /**
         * If we are out of ammo try to reload
         */
        if (weapon.currentAmmo == 0 && weapon.CanFire())
        {
            weapon.Reload();
        }
        else if (weapon.CanFire())
        {
            /**
             * Check for nearby targets and attack if able
             */
            Zombie target = game.GetNearestZombie(xPosition, yPosition);
            if (target.isValid)
                weapon.Fire(this, target);
        }
        
        /**
         * Update our weapon
         */
        weapon.Update(delta);
        
    }  // end Update
    
    public void UpdateDesiredPosition(int x, int y)
    {
        desiredX = x;
        desiredY = y;
    }
    
    
}  // end Human
