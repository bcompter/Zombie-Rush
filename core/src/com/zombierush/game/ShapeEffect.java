package com.zombierush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * A shape that fades over time
 */
public class ShapeEffect implements iEntity{
    
    ZombieRush game;
    float x1, x2, y1, y2;
    float width;
    float timeToLive;
    float red, green, blue, alpha;
    
    /**
     * Create a new Gunshot effect
     */
    public ShapeEffect(ZombieRush g, float a, float b, float c, float d)
    {
        x1 = a;
        y1 = b;
        x2 = c;
        y2 = d;
        red = 1;
        green = 1;
        blue = 1;
        alpha = 1;
        game = g;
        timeToLive = 1;
    }  // end constructor
    
    /**
     * Render this entity to the screen
     */
    public void Render(SpriteBatch batch)
    {
        game.shapeRenderer.begin(ShapeType.Line);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        game.shapeRenderer.setColor(red, green, blue, alpha);
        game.shapeRenderer.line(x1, y1, x2, y2);
        game.shapeRenderer.end();
    }
    
    /**
     * Update this entity
     */
    public void Update(float delta)
    {
        alpha = timeToLive;
        timeToLive -= delta;
        if (timeToLive < 0)
            timeToLive = 0;
    }
    
}  // end ShapeEffect
