package GraphTheory.Primitive;

public class Edge {
	
	protected Node first, second; // adjacent nodes
	
	public Edge(Node first, Node second) {
		this.first = first;
		this.second = second;
	}
	
	public boolean isIncident(Node n) {
		return n==first||n==second;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getSecond() {
		return second;
	}

	public void setSecond(Node second) {
		this.second = second;
	}
	
}
