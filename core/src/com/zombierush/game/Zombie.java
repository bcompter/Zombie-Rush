package com.zombierush.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

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
    
    // Our weapon, bites and claws
    Weapon weapon = new Weapon();
    
    // Flag used to pass "null" values
    boolean isValid = true;
    
    /**
     * Default constructor to pass "null" value zombies
     */
    public Zombie()
    {
        isValid = false;
    }
    
    /**
     * Create a new zombie
     */
    public Zombie(Texture t, ZombieRush g)
    {
        super(t);
        game = g;
        health = 3;
        sprite.setSize(32, 32);
        
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
        
        // adjust our weapon to represent biting and clawing
        weapon.range = 70;
        weapon.accuracy = 70;
        weapon.damage = 1;
        weapon.ammoPerClip = 3;
        weapon.currentAmmo = 3;
        weapon.reloadTime = 2;
        weapon.shotCoolDown = 1;
        
    }  // end constructor
    
    /**
     * Update this zombie
     * Perform movement, collision detection, and attacks
     */
    public void Update(float delta)
    {
        super.Update(delta);
        
        // Set the desired position equal to the nearest human
        Human h = game.GetNearestHuman(xPosition, yPosition);
        desiredX = h.xPosition;
        desiredY = h.yPosition;
        
        float dx = desiredX - xPosition;
        float dy = desiredY - yPosition;
        
        float totalDelta = Math.abs(dx) + Math.abs(dy);
        
        float tempX = xPosition;
        float tempY = yPosition;
        
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
        sprite.setCenter(xPosition, yPosition);
        
        /**
         * Check for collisions with humans, barricades, and other zombies
         */
        boolean collision = false;
        Rectangle a = sprite.getBoundingRectangle();
        for (int i = 0; i < game.zombies.size; i++)
        {
            Zombie z = game.zombies.get(i);
            if (z.equals(this))
                break;
            Rectangle b = z.sprite.getBoundingRectangle();
            if (a.overlaps(b))
            {
                collision = true;
            }
        }
        for (int i = 0; i < game.barricades.size; i++)
        {
            Barricade c = game.barricades.get(i);
            Rectangle b = c.sprite.getBoundingRectangle();
            if (a.overlaps(b))
            {
                collision = true;
            }
        }
        
        for (int i = 0; i < game.humans.size; i++)
        {
            Human tempHuman = game.humans.get(i);
            Rectangle b = tempHuman.sprite.getBoundingRectangle();
            if (a.overlaps(b))
            {
                collision = true;
            }
            if (collision)
            {
                xPosition = tempX;
                yPosition = tempY;
                sprite.setCenter(xPosition, yPosition);
            }
        }

        /**
         * Handle attacks
         */
        Attack();
        
        /**
         * Update our weapon
         */
        weapon.Update(delta);
        
    }  // end Update
    
    /**
     * Try to attack any humans close enough
     */
    public void Attack()
    {
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
            AbstractEntity target = game.GetNearestHuman(xPosition, yPosition);
            weapon.Fire(this, target);
            target = game.GetNearestBarricade(xPosition, yPosition);
            
            if (target.health > 0)
                weapon.Fire(this, target);
        }
        
        
    }  // end Attack
    
}  // end Zombie
