package Snake;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JTable;

public class Snake extends JTable {
	private int speed = 1;
	private int square =30;
	private ArrayList<Rectangle> body = new ArrayList<Rectangle>();

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private boolean lastUp;
	private boolean lastDown;
	private boolean lastLeft;
	private boolean lastRight;
	
	public Snake(int x, int y) {	
		body.add(new Rectangle(x+90	, y, 30, 30));
		body.add(new Rectangle(x+60, y, 30, 30));
		body.add(new Rectangle(x+30, y, 30, 30));
		body.add(new Rectangle(x, y, 30, 30));
	}
	
	
	public void move() {		
		for (int i = body.size() - 1; i >0 ; i--) {
				body.set(i, body.get(i - 1));
		}
		
		if (right) {
			body.set(0, new Rectangle(body.get(0).x + square, body.get(0).y, square, square));
		}
		
		if (left) {
			body.set(0, new Rectangle(body.get(0).x - square, body.get(0).y, square, square));
		}
		
		if (up) {
			body.set(0, new Rectangle(body.get(0).x, body.get(0).y - square, square, square));
		}
		
		if (down) {
			body.set(0, new Rectangle(body.get(0).x, body.get(0).y + square, square, square));
		}
	}
	
	public void setStatus(int a)
	{
		switch (a)
		{
		case 1:
			setRight(true);
			setLastRight(true);
			setLastUp(false);
			setLastDown(false);
			setLastLeft(false);
			break;
		case 2: 
			setLeft(true);
			setLastLeft(true);
			setLastUp(false);
			setLastDown(false);
			setLastRight(false);
			break;
		case 3:
			setUp(true);
			setLastUp(true);
			setLastRight(false);
			setLastLeft(false);
			setLastDown(false);
			break;
		case 4:
			setDown(true);
			setLastDown(true);
			setLastRight(false);
			setLastLeft(false);
			setLastUp(false);
			break;
		}
	}
	
	public int getSquare() {
		return square;
	}
	
	public boolean isLastUp() {
		return lastUp;
	}

	public void setLastUp(boolean lastUp) {
		this.lastUp = lastUp;
	}

	public boolean isLastDown() {
		return lastDown;
	}

	public void setLastDown(boolean lastDown) {
		this.lastDown = lastDown;
	}
	
	public boolean isLastLeft() {
		return lastLeft;
	}

	public void setLastLeft(boolean lastLeft) {
		this.lastLeft = lastLeft;
	}

	public boolean isLastRight() {
		return lastRight;
	}

	public void setLastRight(boolean lastRight) {
		this.lastRight = lastRight;
	}

	public void setSquare(int square) {
		this.square = square;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public ArrayList<Rectangle> getBody() {
		return body;
	}
	
	public void setBody(ArrayList<Rectangle> arrayList) {
		this.body = arrayList;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public boolean isDown() {
		return down;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
}
