package org.minhvu.platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable
{
	public static void main(String[] args)
	{
		new Game();
	}
	
	private static final long serialVersionUID = 1898926707580001147L;
	
	private static Game instance;
	private JFrame frame;
	
	private boolean running;
	private Thread thread;
	
	public static final SpriteSheet enemySpriteSheet = new SpriteSheet();
	public static final SpriteSheet hudSpriteSheet = new SpriteSheet();
	public static final SpriteSheet itemsSpriteSheet = new SpriteSheet();
	public static final SpriteSheet tilesSpriteSheet = new SpriteSheet();
	public static final SpriteSheet playerSpriteSheet = new SpriteSheet();
	
	public static enum STATE
	{
		MENU,
		PLAY,
		HELP,
		END
	};
	
	private STATE state;
	private Menu menu;
	private Map map;
	private Sound sound;
	private Player player;
	
	private Object synchronization = new Object();
	
	public Game()
	{
		instance = this;
		
		state = STATE.MENU;
		
		KeyListener keyListener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (state.equals(STATE.PLAY))
				{
					player.keyPressed(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (state.equals(STATE.PLAY))
				{
					player.keyReleased(e);
				}
			}
		};
		
		MouseListener mouseListener = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
				{
					menu.mousePressed(e);
				}
				
				if (state.equals(STATE.END))
				{
					
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
			}
		};

		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		setFocusable(true);
		
		enemySpriteSheet.loadSprite("/enemies_spritesheet.png");
		hudSpriteSheet.loadSprite("/hud_spritesheet.png");
		itemsSpriteSheet.loadSprite("/items_spritesheet.png");
		tilesSpriteSheet.loadSprite("/tiles_spritesheet.png");
		playerSpriteSheet.loadSprite("/p1_spritesheet.png");
		
		frame = new JFrame("Tank Conquest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(playerSpriteSheet.getSprite(67, 196, 66, 92));
		frame.add(this);
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setVisible(true);
		
		menu = new Menu();
		map = new Map(Map.MAPS.BLANK);
		sound = new Sound();
		player = new Player();
		
		start();
	}
	
	private synchronized void start()
	{
		if (running)
		{
			return;
		}
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		
		running = false;
		
		try
		{
			thread.join();
		}
		
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.exit(ABORT);
	}
	
	@Override
	public void run()
	{
		long lasttime = System.nanoTime();
		final double ticks = 60.0;
		double nanoseconds = 1000000000 / ticks;
		double delta = 0;
		
		while (running)
		{
			long time = System.nanoTime();
			delta += (time - lasttime) / nanoseconds;
			lasttime = time;
			
			if (delta >= 1)
			{
				update();
				--delta;
			}
		}
		
		stop();
	}
	
	private void update()
	{
		setBackground(new Color(0, 204, 255));
		
		synchronized (synchronization)
		{
			if (state.equals(STATE.PLAY))
			{
				player.update();
			}
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		super.paintComponent(g2d);
		
		synchronized(synchronization)
		{
			map.paint(g2d);
			
			if (state.equals(STATE.PLAY) || state.equals(STATE.END))
			{
				player.paint(g2d);
			}
			
			else if (state.equals(STATE.MENU) || state.equals(STATE.HELP))
			{
				menu.paint(g2d);
			}
		}
		
		g2d.dispose();
	}

	public void end()
	{
		sound.GAMEOVER.stop();
		sound.GAMEOVER.setFramePosition(0);
		sound.GAMEOVER.start();
		state = STATE.END;
	}
	
	public void restart()
	{
		state = STATE.PLAY;
	}

	public static Game getInstance()
	{
		return instance;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void setState(STATE state)
	{
		this.state = state;
	}
	
	public STATE getState()
	{
		return state;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public Sound getSound()
	{
		return sound;
	}
}
