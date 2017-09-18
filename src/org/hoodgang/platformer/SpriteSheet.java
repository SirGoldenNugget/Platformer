package org.hoodgang.platformer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	private BufferedImage spritesheet;
	
	public void loadSprite(String file)
	{
		try
		{
			spritesheet = ImageIO.read(Game.getInstance().getClass().getResourceAsStream(file));
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height)
	{
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public BufferedImage getSprite(int x, int y, int size)
	{
		return spritesheet.getSubimage(x, y, size, size);
	}
}
