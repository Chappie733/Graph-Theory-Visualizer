package GraphTheory.Derivate;

import GraphTheory.Primitive.Edge;
import GraphTheory.Primitive.Node;

public class WeighedEdge extends Edge {
	
	protected int weight;
	
	public WeighedEdge(Node first, Node second, int weight) {
		super(first, second);
		this.weight = weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
}
