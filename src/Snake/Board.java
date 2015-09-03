
package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener, Runnable
{
	private final int	square		= 30;
	private int			score		= 0;
	private Form		form;
	private boolean		playable	= false;
	private Snake		snake		= new Snake(30, 30);
	private Thread		thread		= new Thread(this);
	private int			food_x, food_y;
	private JLabel		about		= new JLabel("Made by Thuy Ninh - Team 10");
	private int			level;
	private int size=4;
	public Board(Form form)
	{
		this.form = form;
		setVisible(true);
		setLayout(null);
		setBackground(Color.black);
		addKeyListener(this);
		add(about);
		about.setBounds(530, 570, 250, 20);
		setRandomX_Y();
		setFocusable(true);
		thread.start();

	}

	private int chooseLevel(int l)
	{
		switch (l)
		{
			case 1:
				return 300;
			case 2:
				return 150;
			default:
				return 100;
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		paintSnake(g);
		paintGridView(g);
		paintFood(g);
		paintString(g);
	}

	private void paintString(Graphics g)
	{
		if (playable == false)
		{
			g.setFont(new Font("Consolas", Font.BOLD, 30));
			g.setColor(Color.orange);
			g.drawString("Choose one level to Start", 90, 235);
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			g.drawString("Number key 1: Easy", 130, 300);
			g.drawString("Number key 2: Normal", 130, 350);
			g.drawString("Number key 3: Hard", 130, 400);
			g.drawString("ESC key to quit game", 130, 450);
		}
		if (gOverCondition(snake.getBody().get(0).x, snake.getBody().get(0).y) == false)
		{
			g.setFont(new Font("Consolas", Font.BOLD, 80));
			g.setColor(Color.magenta);
			g.drawString("Game Over", 50, 250);
			g.setFont(new Font("Consolas", Font.BOLD, 30));
			g.setColor(Color.orange);
			g.drawString("Press Enter to Restart", 120, 355);
		}
		g.setFont(new Font("Consolas", Font.BOLD, 30));
		g.setColor(Color.cyan);
		g.drawString("Scores:", 620, 250);
		g.setFont(new Font("Consolas", Font.BOLD, 80));
		g.drawString(score + "", 630, 350);
	}

	private void paintGridView(Graphics g)
	{
		g.setColor(Color.gray);
		for (int i = 30; i < form.getWidth() - 200; i += 30)
		{
			g.drawLine(i, 30, i, form.getHeight() - 30);
		}

		for (int i = 30; i < form.getHeight(); i += 30)
		{
			g.drawLine(30, i, form.getWidth() - 230, i);
		}

	}

	private boolean checkRan(int x, int y)
	{
		for (int i = 0; i < snake.getBody().size(); i++)
		{
			if (x == snake.getBody().get(i).x && y == snake.getBody().get(i).y)
			{
				return false;
			}
		}
		return true;
	}

	private void setRandomX_Y()
	{
		Random ran = new Random();
		int k = 540 / 30;
		int temp_x, temp_y;
		do
		{
			temp_x = ran.nextInt(k) * 30 + 30;
			temp_y = ran.nextInt(k) * 30 + 30;
		}
		while (checkRan(temp_x, temp_y) == false);
		food_x = temp_x;
		food_y = temp_y;
	}

	private void paintFood(Graphics g)
	{
		if (eatFood(food_x, food_y) == false)
		{
			g.setColor(Color.green);
			g.fillRect(food_x, food_y, square, square);
		}
		else
			setRandomX_Y();
	}

	private void paintSnake(Graphics g)
	{
		g.setColor(Color.pink);
		for (int i = snake.getBody().size() - 1; i > 0; i--)
		{
			g.fillRect(snake.getBody().get(i).x, snake.getBody().get(i).y, square, square);
		}
		g.setColor(Color.red);
		g.fillRect(snake.getBody().get(0).x, snake.getBody().get(0).y, square, square);
	}

	public boolean gOverCondition(int x, int y)
	{
		for (int i = snake.getBody().size() - 1; i >= 4; i--)
		{

			if (snake.getBody().get(i).x == x && snake.getBody().get(i).y == y)
			{
				return false;
			}
		}
		if (x == 570 || x == 0 || y == 0 || y == 570)
		{
			playable = false;
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		if (gOverCondition(snake.getBody().get(0).x, snake.getBody().get(0).y) == true && playable == true)
		{
			if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if (!snake.isLastLeft())
				{
					snake.setStatus(1);
					snake.move();
					repaint();
					snake.setRight(false);
				}
			}

			if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			{
				if (!snake.isLastRight())
				{
					snake.setStatus(2);
					snake.move();
					repaint();
					snake.setLeft(false);
				}
			}

			if (ke.getKeyCode() == KeyEvent.VK_UP)
			{
				if (!snake.isLastDown())
				{
					snake.setStatus(3);
					snake.move();
					repaint();
					snake.setUp(false);
				}
			}

			if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			{
				if (!snake.isLastUp())
				{
					snake.setStatus(4);
					snake.move();
					repaint();
					snake.setDown(false);
				}
			}
		}
		else if (gOverCondition(snake.getBody().get(0).x, snake.getBody().get(0).y) == false)
		{
			if (ke.getKeyCode() == KeyEvent.VK_ENTER)
			{
				snake = new Snake(30, 30);
				playable = false;
				score = 0;
				size=4;
				repaint();
			}
		}
		else if (playable == false)
		{
			if (ke.getKeyCode() == KeyEvent.VK_1)
			{
				level = 1;
				playable = true;
				repaint();
			}

			if (ke.getKeyCode() == KeyEvent.VK_2)
			{
				level = 2;
				playable = true;
				repaint();
			}

			if (ke.getKeyCode() == KeyEvent.VK_3)
			{
				level = 3;
				playable = true;
				repaint();
			}
		}

		setFocusable(true);
	}

	public boolean eatFood(int food_x, int food_y)
	{
		int x, y, new_x, new_y;
		x = snake.getBody().get(0).x;
		y = snake.getBody().get(0).y;
		new_x = snake.getBody().get(snake.getBody().size() - 1).x;
		new_y = snake.getBody().get(snake.getBody().size() - 1).y;
		/*
		 * if ((snake.isLastRight() && (food_x - x == 30) && (food_y == y)) ||
		 * (snake.isLastLeft() && (x - food_x == 30)) && (food_y == y) ||
		 * (snake.isLastUp() && (y - food_y == 30)) && (food_x == x) ||
		 * (snake.isLastDown() && (food_y - y == 30) && (food_x == x)))
		 */

		if (food_x == x && food_y == y)
		{
			/*
			 * snake.getBody().add(snake.getBody().size(),new Rectangle());
			 * System.out.println(snake.getBody().size()); for( int
			 * i=snake.getBody().size()-1; i>0;i--) { snake.getBody().set(i,
			 * snake.getBody().get(i-1)); } snake.getBody().set(0,new
			 * Rectangle(new_x ,new_y , square, square));
			 */
			snake.getBody().add(size, new Rectangle(new_x, new_y, square, square));
			score = score + 1;
			size =size+1;
			return true;
		}
		else
			return false;
	}

	@Override
	public void run()
	{
		while (true)
		{
			while (gOverCondition(snake.getBody().get(0).x, snake.getBody().get(0).y) == true)
			{
				if (playable == true)
				{
					if (!snake.isLastLeft() && !snake.isLastUp() && !snake.isLastDown())
					{
						snake.setStatus(1);
						snake.move();
						repaint();
						snake.setRight(false);
					}

					else if (!snake.isLastRight() && !snake.isLastUp() && !snake.isLastDown())
					{
						snake.setStatus(2);
						snake.move();
						repaint();
						snake.setLeft(false);
					}

					else if (!snake.isLastDown() && !snake.isLastRight() && !snake.isLastLeft())
					{
						snake.setStatus(3);
						snake.move();
						repaint();
						snake.setUp(false);
					}

					else if (!snake.isLastUp() && !snake.isLastRight() && !snake.isLastLeft())
					{
						snake.setStatus(4);
						snake.move();
						repaint();
						snake.setDown(false);
					}
					try
					{
						Thread.sleep(chooseLevel(level));
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{

	}

}
