package org.minhvu.platformer;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Sprite
{
	private static BufferedImage[] moveRight = {
			Game.playerSpriteSheet.getSprite(0, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(73, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(146, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(0, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(73, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(146, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(219, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(292, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(219, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(365, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(292, 98, 72, 97) };

	private static BufferedImage[] moveLeft = {
			Game.playerSpriteSheet.getSprite(0, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(73, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(146, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(0, 386, 72, 97),
			Game.playerSpriteSheet.getSprite(73, 386, 72, 97),
			Game.playerSpriteSheet.getSprite(146, 386, 72, 97),
			Game.playerSpriteSheet.getSprite(219, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(292, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(219, 386, 72, 97),
			Game.playerSpriteSheet.getSprite(365, 288, 72, 97),
			Game.playerSpriteSheet.getSprite(292, 386, 72, 97) };

	private static BufferedImage[] standingRight = { Game.playerSpriteSheet.getSprite(67, 196, 66, 92) };
	private static BufferedImage[] standingLeft = { Game.playerSpriteSheet.getSprite(375, 484, 66, 92) };
	private static BufferedImage[] duckRight = { Game.playerSpriteSheet.getSprite(67, 196, 66, 92) };
	private static BufferedImage[] duckLeft = { Game.playerSpriteSheet.getSprite(375, 484, 66, 92) };
	private static BufferedImage[] jumpRight = { Game.playerSpriteSheet.getSprite(438, 93, 67, 94) };
	private static BufferedImage[] jumpLeft = { Game.playerSpriteSheet.getSprite(438, 381, 67, 94) };
	private static BufferedImage[] hurtRight = { Game.playerSpriteSheet.getSprite(438, 0, 69, 92) };
	private static BufferedImage[] hurtLeft = { Game.playerSpriteSheet.getSprite(0, 0, 67, 92) };

	private boolean uppressed;
	private boolean downpressed;
	private boolean leftpressed;
	private boolean rightpressed;

	private int speed;

	public Player()
	{
		super();

		animation = new Animation(standingLeft, 5);
		animation.start();

		location = new Point(0, 0);

		uppressed = false;
		downpressed = false;
		leftpressed = false;
		rightpressed = false;

		speed = 5;
	}

	@Override
	public void update()
	{
		boolean moving = false;

		if (uppressed)
		{
			location.y -= speed;
			moving = true;
		}

		if (downpressed)
		{
			location.y += speed;
			moving = true;
		}

		if (leftpressed)
		{
			location.x -= speed;
			moving = true;
		}

		if (rightpressed)
		{
			location.x += speed;
			moving = true;
		}

		if (moving)
		{
			animation.update();
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			uppressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downpressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftpressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightpressed = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			uppressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downpressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftpressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightpressed = true;
		}
	}
}
