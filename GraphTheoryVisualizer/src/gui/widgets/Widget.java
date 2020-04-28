package gui.widgets;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public abstract class Widget {
	
	protected int x,y, width, height;
	protected static JFrame window;
	
	public Widget(int x, int y) {
		this.x = x;
		this.y = y; 
		width = 100;
		height = 100;
	}
	
	public Widget(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	public abstract void render(Graphics2D g);
	
	public boolean isMouseOnMe() {
		try {
			Rectangle r1 = new Rectangle((int) window.getMousePosition().getX(),
						 				 (int) window.getMousePosition().getY(), 5, 5);
			Rectangle r2 = new Rectangle(x,y+height,width,height);
			return r1.intersects(r2);
		} catch (NullPointerException e) { return false; }
	}
	
	public abstract void OnMouseClicked(MouseEvent e);
	public abstract void OnMouseReleased(MouseEvent e);
	public abstract void OnKeyPressed(KeyEvent e);
	public abstract void OnKeyReleased(KeyEvent e);
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static void setWindowInstance(JFrame window) {
		Widget.window = window;
	}
	
}
