package Main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Render.RenderNode;
import gui.widgets.Widget;

public class Main implements Runnable, KeyListener, MouseListener {
	
	public static final int w_Width = 800, w_Height = 600;
	
	private JFrame window;
	private Canvas canvas;
	
	private final long actions_delay = 1/60; // delay between each rendering and updating
	
	private BufferStrategy buffers;
	private Graphics2D graphics;
	
	private Thread thread;
	private boolean running = false;
	
	private Visualizer vis;
	
	public Main() {
		createWindow();
		vis = new Visualizer();
		vis.init();
	}
	
	@Override
	public void run() {
		
		
		long curr = 0, last = 0;
		while (running) {
			
			if (curr >= actions_delay) {
				update();
				render();
				curr = 0;
			}
			
			curr += System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
		}
		
		stop();
	}
	
	private void update() {
		vis.update();
	}
	
	private void render() {
		buffers = canvas.getBufferStrategy();
		if (buffers == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		graphics = (Graphics2D) buffers.getDrawGraphics();
		graphics.clearRect(0, 0, w_Width, w_Height);
		
		vis.render(graphics);
		
		graphics.dispose();
		buffers.show();
	}
	
	private void createWindow() {
		window = new JFrame("Graph Theory Visualization");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(w_Width, w_Height);
		window.setAlwaysOnTop(true);
		window.setResizable(false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(w_Width, w_Height));
		canvas.addMouseListener(this);
		canvas.addKeyListener(this);
		
		window.add(canvas);
		canvas.setVisible(true);
		window.setVisible(true);
		Widget.setWindowInstance(window);
		RenderNode.setWindowInstance(window);
	}
	
	public void stop() {
		if (!running) return;
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		if (running) return;
		if (thread == null) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		vis.onMouseClick(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		vis.onMouseRelease(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		vis.onKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		vis.onKeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	
}
