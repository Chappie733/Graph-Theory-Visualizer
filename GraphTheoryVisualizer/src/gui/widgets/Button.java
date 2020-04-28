package gui.widgets;

import Utils.Loader;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Button extends Widget {
	
	private List<BufferedImage> textures;
	private byte state; // 0 -> default, 1 -> feedback, 2 -> selected
	private Callable<Void> action_on_click;
	private Text text;
	private boolean clicked;
	
	public Button(int x, int y, String content, List<String> textures, int width, int height) {
		super(x, y, width, height);
		text = new Text(x+width/6, y+height*3/5, content);
		state = 0;
		this.textures = new ArrayList<BufferedImage>();
		for (String s : textures)
			this.textures.add(Loader.LoadImage(s));
	}
	
	public Button(int x, int y, String content, int width, int height) {
		super(x, y, width, height);
		text = new Text(x+width/6, y+height*3/5, content);
		state = 0;
		this.textures = new ArrayList<BufferedImage>();
		for (String res : Arrays.asList("/res/button.png", "/res/button_feedback.png", "/res/button_selected.png"))
			this.textures.add(Loader.LoadImage(res));
	}
	
	@Override
	public void update() {
		if (isMouseOnMe()) {
			if (clicked) {
				state = 2;
			}
			else state = 1;
		} else state = 0;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(textures.get(state), x, y, width, height, null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, height/2)); 
		text.render(g);
	}
	
	public void setActionOnClick(Callable<Void> action) {
		this.action_on_click = action;
	}

	@Override
	public void OnMouseClicked(MouseEvent e) {
		clicked = true;
		if (isMouseOnMe()) {
			try {
				action_on_click.call();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void OnMouseReleased(MouseEvent e) {
		clicked = false;
	}

	@Override
	public void OnKeyPressed(KeyEvent e) {}

	@Override
	public void OnKeyReleased(KeyEvent e) {}
	
}
