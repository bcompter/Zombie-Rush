package com.zombierush.game;

import com.badlogic.gdx.graphics.Texture;

/**
 *  Generic and general purpose entity class for effects, huds, menus and the like
 */
public class GenericEntity extends AbstractEntity
{
    /**
     * Create an new entity using the provided texture
     * @param t 
     */
    public GenericEntity(Texture t)
    {
        super(t);
    }
    
}  // end GenericEntity
