package Render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import GraphTheory.Primitive.Node;
import Utils.Loader;
import Utils.Utils;

public class RenderNode extends Node {
	
	private Point screen_pos;
	private boolean selected;
	
	private static BufferedImage node_texture;
	public static final int SIZE = 32;
	private static JFrame window;
	
	public RenderNode(int deg, Point screen_pos) {
		super(deg);
		this.screen_pos = screen_pos;
		selected = false;
		if (node_texture == null) node_texture = Loader.LoadImage("/res/node.png");
	}
	
	public RenderNode(int deg) {
		super(deg);
		selected = false;
		this.screen_pos = new Point(100, 100);
		if (node_texture == null) node_texture = Loader.LoadImage("/res/node.png");
	}
	
	public void update() {
		if (selected) screen_pos.setCoords((int) window.getMousePosition().getX()-SIZE/2, (int) window.getMousePosition().getY()-SIZE-8);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(node_texture, screen_pos.getX(), screen_pos.getY(), SIZE, SIZE, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, SIZE/2));
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf(deg), screen_pos.getX()+SIZE*5/14, screen_pos.getY()+SIZE*2/3);
	}
	
	public boolean onMouseClick(MouseEvent e) {
		Rectangle mousePos = new Rectangle((int) window.getMousePosition().getX(),
										   (int) window.getMousePosition().getY(),5,5);
		Rectangle n = new Rectangle(screen_pos.getX(), screen_pos.getY()+SIZE, SIZE, SIZE);
		if (Utils.Intersect(mousePos, n))  // if the click is on this node
			return true;
		return false;
	}
	
	public Point getScreen_pos() {
		return screen_pos;
	}

	public void setScreen_pos(Point screen_pos) {
		this.screen_pos = screen_pos;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public static void setWindowInstance(JFrame window) {
		RenderNode.window = window;
	}
	
}
