package com.zombierush.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * Weapons used by humans to kill off zombies
 */
public class Weapon {
    
    /**
     * Member variables
     */
    public float range;
    public float accuracy;
    public float damage;
    public int ammoPerClip;
    public int currentAmmo;
    public float reloadTime;
    public float shotCoolDown;
    
    /**
     * Timers
     */
    public float currentCoolDown;
    public float currentReload;
    
    /**
     * Create a new weapon, by default a handgun 
     */
    public Weapon()
    {
        range = 500;
        accuracy = 60;
        damage = 1;
        ammoPerClip = 8;
        currentAmmo = 8;
        reloadTime = 3;
        shotCoolDown = 1;
    }
    
    /**
     * Update this weapon
     */
    public void Update(float delta)
    {
        if (currentReload > 0)
        {
            currentReload -= delta;
            if (currentReload < 0)
                currentReload = 0;
        }
        if (currentCoolDown > 0)
        {
            currentCoolDown -= delta;
            if (currentCoolDown < 0)
                currentCoolDown = 0;
        }
        
    }
    
    /**
     * Fire this weapon at an entity
     */
    public void Fire(AbstractEntity shooter, AbstractEntity target)
    {
        double distance = 
                Math.pow(Math.abs(shooter.xPosition - target.xPosition),2) 
                + Math.pow(Math.abs(shooter.yPosition - target.yPosition),2);
        distance = Math.sqrt(distance);        
        
        if (distance < range)
        {
            if (shooter instanceof Human)
                System.out.println("Pew Pew!");
            else
                System.out.println("Bite Bite!");
            int tohit = MathUtils.random(100);
            if (tohit < accuracy)
            {
                System.out.println("HIT!!!");
                target.TakeDamage(damage, 0, false);
            }
            currentAmmo -= 1;
            currentCoolDown = shotCoolDown;
            
            /**
             * Add a gunshot effect to the game
             */
            if (shooter instanceof Human)
                shooter.game.shapeEffects.add(new ShapeEffect(
                    shooter.game,
                    shooter.xPosition, shooter.yPosition,
                    target.xPosition, target.yPosition
                ));
            
        }  // end if distance < range
        
    }  // end Fire
    
    /**
     * Reload this weapon
     */
    public void Reload()
    {
        System.out.println("Reloading...");
        currentAmmo = ammoPerClip;
        currentReload = reloadTime;
    }
    
    /**
     * Checks to see if this weapon can fire
     */
    public boolean CanFire()
    {
        return currentCoolDown == 0 && currentReload == 0;
    }
    
    
}  // end Weapon
