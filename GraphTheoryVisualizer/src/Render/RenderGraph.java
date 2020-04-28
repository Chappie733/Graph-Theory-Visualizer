package Render;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import GraphTheory.Primitive.Edge;
import GraphTheory.Primitive.Graph;
import GraphTheory.Primitive.Node;
import Utils.Algorithms;

public class RenderGraph extends Graph {
	
	private byte state; // the current state of action
	private HashMap<Node, Integer> coloring;
	
	public static final byte DEFAULT = 0,
							 CREATING_EDGE = 1,
							 DELETING_NODE = 2,
							 DELETING_EDGE = 3;
	
	public RenderGraph(List<Node> Vertices, List<Edge> Edges) {
		super(Vertices, Edges);
		state = DEFAULT;
		isF_sel = false;
	}
	
	public RenderGraph() { 
		super(); 
		state = DEFAULT;
		isF_sel = false;
	}
	
	public void update() {
		for (Node n : V) {
			RenderNode rn = (RenderNode) n;
			rn.update();
		}
	}
	
	public void render(Graphics2D g) {
		for (Edge e : E) {
			RenderEdge re = (RenderEdge) e;
			re.render(g);
		}
		for (Node n : V) {
			RenderNode rn = (RenderNode) n;
			rn.render(g);
			if (coloring != null) {
				if (coloring.get(n) != null) g.drawString(String.valueOf(coloring.get(n)), rn.getScreen_pos().getX()-RenderNode.SIZE*1/3, rn.getScreen_pos().getY());
			}
		}
	}
	
	private RenderNode first_sel;
	private boolean isF_sel; // is the first node selected when creating an edge
	public void onMouseClick(MouseEvent e) {
		Node to_delete = null; // the node to delete (remains null if no node has to be deleted)
		for (Node n : V) { // for each vertex/node
			RenderNode rn = (RenderNode) n;
			// if the node has been clicked
			if (rn.onMouseClick(e)) {
				switch (state) {
				case DEFAULT: // if we aren't waiting to delete or create any edge or node
					rn.setSelected(!rn.isSelected());
					break;
				case CREATING_EDGE:
						if (!isF_sel) { // if this is the first node of the new edge
							first_sel = rn; // save it
							isF_sel = true; // save the fact that the first node has been selected
						} else { // if this is the second node
							if (rn != first_sel) { // if the second node selected is not the same as the first one
								E.add(new RenderEdge(first_sel, rn)); // add the edge
								isF_sel = false; state = DEFAULT; // set that the creation of the edge is complete and that the first edge hasn't been selected (for the next time an edge is created)
							}
						}
						break;
				case DELETING_NODE:
					if (rn.getDeg() != 0)  // if the node has adjacent nodes
						for (Node adj : rn.getAdjacentNodes()) // for each adjacent node
							adj.setDeg(adj.getDeg()-1); // decrease its degree by 1
					
					for (int i = 0; i < E.size(); i++) // for each edge
						if (E.get(i).isIncident(rn)) // if it's incident to the node getting deleted
							E.remove(i); // remove the edge
					
					to_delete = rn; // save the node to delete (to avoid concurrent modification exception)
					state = DEFAULT; // set the state back to default
					break;
				case DELETING_EDGE:
					if (!isF_sel) { // if this is the first node
						first_sel = rn; // save it
						isF_sel = true; // save the fact that the first node has been selected
					} else { // if this is the second node
						if (rn != first_sel) {
							first_sel.setDeg(first_sel.getDeg()-1); // decrease the degree of the first adjacent node by one
							rn.setDeg(rn.getDeg()-1); // "" second adjacent ""
							for (int i = 0; i < E.size(); i++) // for each edge
								if (E.get(i).isIncident(first_sel)&&E.get(i).isIncident(rn)) // if it's adjacent to the two nodes selected (it's the edge connecting them)
									E.remove(i); // delete it
							isF_sel = false; state = DEFAULT; // set that the creation of the edge is complete and that the first edge hasn't been selected (for the next time)
						}
					}
					break;
				}
			}
		}
		if (to_delete != null) V.remove(to_delete); // delete the node that has to be deleted
	}
	
	public void onKeyPress(KeyEvent e) {}
	public void onKeyRelease(KeyEvent e) {}
	
	public byte getState() { return this.state; }
	public void setState(byte state) { this.state = state; }
	public void setColoring(HashMap<Node, Integer> coloring) { this.coloring = coloring; }
	public void color() { coloring = Algorithms.getColoring(this); }
	public HashMap<Node, Integer> getColoring() { return this.coloring; }
	public int getColor(Node n) { return coloring.get(n); }
	
}
