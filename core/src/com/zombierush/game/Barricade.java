package com.zombierush.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Barricade defense
 * Blocks movement
 */
public class Barricade extends AbstractEntity{
    
    /**
     * The survivor building this barricade
     */
    Human thePersonBuildingMe;
    
    /**
     * A flag to indicate if this barricade is being built
     */
    boolean beingBuilt;
    
    /**
     * Time remaining to build this barricade
     */
    float constructionTimeRemaining;
    
    /**
     * Build a new barricade at a specified coordinates 
     * The builder must finish construction by being close by
     */
    public Barricade(Texture t, int x, int y, Human h)
    {
        super(t);

        xPosition = x;
        yPosition = y;
        
        thePersonBuildingMe = h;
        beingBuilt = true;
        constructionTimeRemaining = 5;
        health = 50;
        sprite.setSize(40, 40);
        sprite.setAlpha(0.5f);
        
    }  // end constructor
    
    /**
     * Update this barricade
     * Handle construction
     * @param delta
     */
    @Override
    public void Update(float delta)
    {
        /**
         * Nothing to do unless we are under construction
         */
        if (!beingBuilt)
            return;
        
        /**
         * If the human building me is in range, decrement construction time
         */
        double distance = Math.sqrt(
                Math.pow(
                        Math.abs(thePersonBuildingMe.xPosition-xPosition), 2) 
                + Math.pow( 
                        Math.abs(thePersonBuildingMe.yPosition-yPosition), 2));
        if (distance < 50)
        {
            constructionTimeRemaining -= delta;
        }
        
        /**
         * If construction is complete, flip the flag and finish construction
         */
        if (constructionTimeRemaining <= 0)
        {
            beingBuilt = false;
            constructionTimeRemaining = 0;
            sprite.setAlpha(1.0f);
        }
        
    }  // end Update
    
}  // end barricade
