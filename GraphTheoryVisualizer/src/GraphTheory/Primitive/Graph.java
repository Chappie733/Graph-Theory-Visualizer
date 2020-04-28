package GraphTheory.Primitive;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	protected List<Node> V; // Vertices
	protected List<Edge> E; // Edges

	public Graph(List<Node> Vertices, List<Edge> Edges) {
		this.V = Vertices;
		this.E = Edges;
	}
	
	public Graph() {
		V = new ArrayList<Node>();
		E = new ArrayList<Edge>();
	}
	
	public void addNode(Node n) { V.add(n); }
	public void removeNode(Node n) { V.remove(n); }
	public void addEdge(Edge e) { E.add(e); }
	public void removeEdge(Edge e) { E.remove(e); }
	
	public int getVerticesCount() { return V.size(); }
	public int getEdgesCount() { return E.size(); }
	public List<Node> getVertices() { return V; }
	public List<Edge> getEdges() { return E; }
}
