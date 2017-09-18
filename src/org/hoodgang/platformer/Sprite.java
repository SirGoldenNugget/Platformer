package org.hoodgang.platformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Sprite
{	
	protected Point location;
	protected Animation animation;
	
	protected enum DIRECTION
	{
		LEFT,
		RIGHT
	}
	
	protected DIRECTION direction;
	
	public Sprite()
	{
		location = new Point();
		
		direction = DIRECTION.RIGHT;
	}
	
	public Point getLocation()
	{
		return location;
	}
	
	public void setLocation(Point location)
	{
		this.location = location;
	}
	
	public Dimension getDimensions()
	{
		return new Dimension(animation.getSprite().getWidth(), animation.getSprite().getHeight());
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location, new Dimension(animation.getSprite().getWidth(), animation.getSprite().getHeight()));
	}

	public abstract void update();
	
	public void paint(Graphics2D g2d)
	{
		if (direction == DIRECTION.RIGHT)
		{
			g2d.drawImage(animation.getSprite(), location.x, location.y, Game.getInstance());
		}
		
		if (direction == DIRECTION.LEFT)
		{
			g2d.drawImage(animation.getSprite(), location.x + animation.getSprite().getWidth(), location.y, -animation.getSprite().getWidth(), animation.getSprite().getHeight(), Game.getInstance());
		}
	}
}