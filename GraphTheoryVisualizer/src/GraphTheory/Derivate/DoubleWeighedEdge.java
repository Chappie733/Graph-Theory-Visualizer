package GraphTheory.Derivate;

import GraphTheory.Primitive.Node;

public class DoubleWeighedEdge extends WeighedEdge {
	
	private int second_weight;
	
	public DoubleWeighedEdge(Node first, int first_weight, 
			Node second, int second_weight) {
		super(first, second, first_weight);
		this.second_weight = second_weight;
	}
	
	public int getWeight(Node n) {
		return (n==first)?weight:second_weight;
	}
	
}
