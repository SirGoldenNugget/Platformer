package org.minhvu.platformer;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Sprite
{
	private static final int animationDelay = 5;

	private static BufferedImage[] move = { Game.playerSpriteSheet.getSprite(0, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(73, 0, 72, 97), Game.playerSpriteSheet.getSprite(146, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(0, 98, 72, 97), Game.playerSpriteSheet.getSprite(73, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(146, 98, 72, 97), Game.playerSpriteSheet.getSprite(219, 0, 72, 97),
			Game.playerSpriteSheet.getSprite(292, 0, 72, 97), Game.playerSpriteSheet.getSprite(219, 98, 72, 97),
			Game.playerSpriteSheet.getSprite(365, 0, 72, 97), Game.playerSpriteSheet.getSprite(292, 98, 72, 97) };

	private static BufferedImage[] front = { Game.playerSpriteSheet.getSprite(0, 196, 66, 92) };
	private static BufferedImage[] stand = { Game.playerSpriteSheet.getSprite(67, 196, 66, 92) };
	private static BufferedImage[] duck = { Game.playerSpriteSheet.getSprite(365, 98, 69, 71) };
	private static BufferedImage[] jump = { Game.playerSpriteSheet.getSprite(438, 93, 67, 94) };
	private static BufferedImage[] hurt = { Game.playerSpriteSheet.getSprite(438, 0, 69, 92) };

	private static final Animation moving = new Animation(move, animationDelay);
	private static final Animation facing = new Animation(front, animationDelay);
	private static final Animation standing = new Animation(stand, animationDelay);
	private static final Animation ducking = new Animation(duck, animationDelay);
	private static final Animation jumping = new Animation(jump, animationDelay);
	private static final Animation hurting = new Animation(hurt, animationDelay);

	private boolean upPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean rightPressed;

	private int speed;
	private int gravity;
	private int vertical;

	public Player()
	{
		super();

		animation = facing;

		location = new Point(0, 0);

		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;

		speed = 5;

		gravity = 1;
		vertical = 0;
	}

	@Override
	public void update()
	{
		if (!upPressed && location.y + animation.getSprite().getHeight() < Game.getInstance().getHeight() - (70 * 2) - 40)
		{
			location.y += gravity;
			gravity++;
		}

		else
		{
			gravity = 1;
		}

		if (leftPressed)
		{
			location.x -= speed;
			animation = moving;
			direction = DIRECTION.LEFT;
		}

		if (rightPressed)
		{
			location.x += speed;
			animation = moving;
			direction = DIRECTION.RIGHT;
		}

		if (upPressed)
		{
			location.y -= speed;
			animation = jumping;
		}

		if (downPressed)
		{
			location.y += speed;
			animation = ducking;
		}

		if (!leftPressed && !rightPressed && !upPressed && !downPressed)
		{
			animation = standing;
		}

		animation.update();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftPressed = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightPressed = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			leftPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			rightPressed = true;
		}
	}
}
