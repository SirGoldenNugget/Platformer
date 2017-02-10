package org.minhvu.platformer;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Sprite
{	
	protected Point location;
	protected Dimension dimension;

	protected double initialangle;
	protected double angle;
	
	public Sprite()
	{
		location = new Point();
		dimension = new Dimension();
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
		return dimension;
	}
	
	public void setDimensions(Dimension dimension)
	{
		this.dimension = dimension;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(location, dimension);
	}
	
	public double getInitialAngle()
	{
		return initialangle;
	}
	
	public double getAngle()
	{
		return angle;
	}
}