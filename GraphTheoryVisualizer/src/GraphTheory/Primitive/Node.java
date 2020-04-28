package GraphTheory.Primitive;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
	protected int deg;
	protected List<Node> adjacent_nodes;
	
	public Node(int deg) {
		this.deg = deg;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg<0?0:deg;
	}
	
	public List<Node> getAdjacentNodes() { return this.adjacent_nodes; }
	
	public void registerAdjacentNode(Node adj) {
		if (adjacent_nodes == null) adjacent_nodes = new LinkedList<Node>(); // if it's null, initialize the list with the nodes connected to this node
		adjacent_nodes.add(adj); // add the node to the list
		++deg; // increase the degree
	}
	
}
