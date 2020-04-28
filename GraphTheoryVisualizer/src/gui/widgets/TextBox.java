package gui.widgets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextBox extends Widget {
	
	private Text content;
	private boolean selected;
	
	public TextBox(int x, int y, int width, int height, String content) {
		super(x, y, width, height);
		this.content = new Text(x+10, y+15, content);
	}

	@Override
	public void update() {}

	@Override
	public void render(Graphics2D g) {
		g.setColor(new Color(175, 175, 175));
		g.drawRect(x, y, width, height);
		if (!selected) g.setColor(new Color(230, 230, 230));
		else g.setColor(new Color(240, 240, 240));
		g.fillRect(x+5, y+5, width-10, height-10);
		g.setColor(Color.black);
		content.render(g);
	}
	
	@Override
	public boolean isMouseOnMe() {
		Rectangle r1 = new Rectangle((int) window.getMousePosition().getX(),
					 				 (int) window.getMousePosition().getY(), 5, 5);
		Rectangle r2 = new Rectangle(x,y+height/2,width-10,height-10);
		return r1.intersects(r2);
	}
	
	@Override
	public void OnMouseClicked(MouseEvent e) {
		selected = isMouseOnMe();
	}

	@Override
	public void OnMouseReleased(MouseEvent e) {
		
	}

	public void OnKeyPressed(KeyEvent e) {
		if (selected) {
			if (e.getKeyCode() == 8) content.cancelCharacters(1);
			else if (Character.isAlphabetic(e.getKeyChar()) || Character.isDigit(e.getKeyChar())
					|| e.getKeyChar() == '.') content.concat(String.valueOf(e.getKeyChar()));
		}
	}
	
	public void OnKeyReleased(KeyEvent e) {	}
	
	public String getContent() {
		return this.content.getContent();
	}
	
}
