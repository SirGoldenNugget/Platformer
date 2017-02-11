package org.minhvu.platformer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Sprite
{	
	protected Point location;
	protected Animation animation;
	
	public Sprite()
	{
		location = new Point();
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
		g2d.drawImage(animation.getSprite(), location.x, location.y, Game.getInstance());
	}
}