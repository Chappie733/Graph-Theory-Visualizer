package gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import gui.widgets.Widget;

public class Gui {
	
	private List<Widget> widgets;
	private BufferedImage background;
	private int x,y,width,height;
	
	public Gui(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		widgets = new ArrayList<Widget>();
	}
	
	public Gui(int x, int y, int width, int height, BufferedImage background) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		widgets = new ArrayList<Widget>();
	}
	
	public Gui(int x, int y, int width, int height, BufferedImage background,
			List<Widget> widgets) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		this.widgets = widgets;
	}
	
	public void update() {
		for (Widget w : widgets)
			w.update();
	}
	
	public void render(Graphics2D g) {
		g.drawImage(background, x, y, width, height, null);
		for (Widget w : widgets)
			w.render(g);
	}
	
	public void onKeyPress(KeyEvent e) {
		for (Widget w : widgets)
			w.OnKeyPressed(e);
	}
	
	public void onKeyRelease(KeyEvent e) {
		for (Widget w : widgets)
			w.OnKeyReleased(e);
	}
	
	public void onMouseClick(MouseEvent e) {
		for (Widget w : widgets)
			w.OnMouseClicked(e);
	}
	
	public void onMouseRelease(MouseEvent e) {
		for (Widget w : widgets)
			w.OnMouseReleased(e);
	}

	public void removeWidget(Widget widget) { this.widgets.remove(widget); }
	public void addWidget(Widget widget) { this.widgets.add(widget); }
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	
}
