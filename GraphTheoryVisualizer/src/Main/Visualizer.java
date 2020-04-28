package Main;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.Callable;
import Render.Point;
import Render.RenderGraph;
import Render.RenderNode;
import Utils.Loader;
import gui.Gui;
import gui.widgets.Button;
import gui.widgets.Text;

public class Visualizer {
	
	private RenderGraph graph;
	private Gui gui;
	
	public Visualizer() {}
	
	public void init() {
		graph = new RenderGraph();
		gui = new Gui(30, 400, 200, 150, Loader.LoadImage("/res/gui_bg.png"));
		gui.addWidget(new Text(40, 415, "Menu"));
		
		Button add_node = new Button(40, 435, "New Node", 75, 25);
		Callable<Void> add_node_action = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				graph.addNode(new RenderNode(0, new Point(50, 100)));
				return null;
			}
			
		};
		add_node.setActionOnClick(add_node_action);
		
		gui.addWidget(add_node);
		
		Button add_edge = new Button(40, 475, "New Edge", 75, 25);
		
		Callable<Void> add_edge_action = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				if (graph.getVerticesCount() >= 2) graph.setState(RenderGraph.CREATING_EDGE);
				return null;
			}
			
		};
		
		add_edge.setActionOnClick(add_edge_action);
		
		gui.addWidget(add_edge);
		
		Button del_node = new Button(145, 435, "Del Node", 75, 25);
		
		Callable<Void> del_node_action = new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				if (graph.getVerticesCount() != 0) graph.setState(RenderGraph.DELETING_NODE);
				return null;
			}
		};
		
		del_node.setActionOnClick(del_node_action);
		
		gui.addWidget(del_node);
		
		Button del_edge = new Button(145, 475, "Del Edge", 75, 25);
		
		Callable<Void> del_edge_action = new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				if (graph.getEdgesCount() != 0) graph.setState(RenderGraph.DELETING_EDGE);
				return null;
			}
		};
		del_edge.setActionOnClick(del_edge_action);
	
		gui.addWidget(del_edge);
		
		Button color_graph = new Button(40, 515, "Run BCA", 75, 25);
		
		Callable<Void> color_graph_action = new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				if (graph.getVerticesCount() != 0) graph.color();
				return null;
			}
		};
		color_graph.setActionOnClick(color_graph_action);
		
		gui.addWidget(color_graph);
		
	}
	
	public void update() {
		gui.update();
		graph.update();
	}
	
	public void render(Graphics2D g) {
		graph.render(g);
		gui.render(g);
	}
	
	public void onMouseClick(MouseEvent e) {
		gui.onMouseClick(e);
		graph.onMouseClick(e);
	}
	
	public void onMouseRelease(MouseEvent e) {
		gui.onMouseRelease(e);
	}
	
	public void onKeyPressed(KeyEvent e) {
		gui.onKeyPress(e);
	}
	
	public void onKeyReleased(KeyEvent e) {
		gui.onKeyRelease(e);
	}
	
}
