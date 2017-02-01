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
	private static final long serialVersionUID = 1898926707580001147L;
	
	private static Game instance;
	private JFrame frame;
	
	private boolean running;
	private Thread thread;
	
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
	
	private Object SPRITE_LOCK = new Object();
	
	public Game()
	{
		instance = this;
		
		state = STATE.MENU;
		
		KeyListener keylistener = new KeyListener()
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
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (state.equals(STATE.PLAY))
				{
					
				}
			}
		};
		
		MouseListener mouselistener = new MouseListener()
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

		addKeyListener(keylistener);
		addMouseListener(mouselistener);
		setFocusable(true);

		Sprite.loadSprite("/spritesheet.png");

		//Sound.BACKGROUND.loop();
		
		frame = new JFrame("Tank Conquest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Sprite.getSprite(1, 0, 84));
		frame.add(this);
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(false);
		//frame.pack();
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		menu = new Menu();
		
		Map.MAPS maps = null;

		switch ((int) (Math.random() * 4))
		{
			case 0:
				maps = Map.MAPS.FOUR_CORNERS;
				break;
			case 1:
				maps = Map.MAPS.ICE_AGE;
				break;
			case 2:
				maps = Map.MAPS.FORGOTTEN_HERO;
				break;
			case 3:
				maps = Map.MAPS.SQUAD_LIFE;
				break;
			default:
				break;
		}
		
		map = new Map(maps);
		sound = new Sound();
		
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
		this.setBackground(Color.BLACK);
		
		synchronized (SPRITE_LOCK)
		{
			if (state.equals(STATE.PLAY))
			{
				
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
		
		synchronized(SPRITE_LOCK)
		{
			map.paint(g2d);
			
			if (state.equals(STATE.PLAY) || state.equals(STATE.END))
			{
				
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
