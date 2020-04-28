package gui.widgets;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Text extends Widget {

	private String content;
	
	public Text(int x, int y, String content) {
		super(x, y);
		this.content = content;
	}

	public void update() {}
	
	@Override
	public void render(Graphics2D g) {
		g.drawString(content, x, y);
	}

	@Override
	public void OnMouseClicked(MouseEvent e) {}

	@Override
	public void OnMouseReleased(MouseEvent e) {}
	
	public void concat(String text) { 
		content = content.concat(text);
	}
	
	public void cancelCharacters(int characters) { 
		if (content.length() <= 1) {
			content = "";
			return;
		}
		this.content = content.substring(0, content.length()-characters); 
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}

	@Override
	public void OnKeyPressed(KeyEvent e) {}

	@Override
	public void OnKeyReleased(KeyEvent e) {}
	
}
