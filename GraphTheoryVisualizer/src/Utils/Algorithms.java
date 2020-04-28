package Utils;

import java.util.HashMap;
import java.util.List;
import GraphTheory.Primitive.Graph;
import GraphTheory.Primitive.Node;

public class Algorithms {
	
	public static HashMap<Node, Integer> getColoring(Graph g) {
		HashMap<Node, Integer> coloring = new HashMap<Node, Integer>(); // each node has its coloring
		List<Node> nodes = g.getVertices(); // instance of the set of nodes
		sortByDegree(nodes);
		
		for (Node n : nodes) // set the color of {each}n{in}V to deg(n)+1
			coloring.put(n, n.getDeg()+1);
		
		for (Node n : nodes) { // for each node n
			if (n.getDeg() == 0) {
				coloring.replace(n, 1); // if deg(n)==0 -> color(n)=1
				continue; // don't do the rest
			}
			int last = n.getDeg()+1; // last smallest available color
			while (coloring.get(n) > 0) { // while we haven't tried each color
				boolean same_color_neighbor = false; // does a neighbor have the same color?
				// find it out
				for (Node neighbor : n.getAdjacentNodes()) 
					if (coloring.get(neighbor) == coloring.get(n)) 
						same_color_neighbor = true;
				
				if (!same_color_neighbor) last = coloring.get(n); // if no neighbor has the same color, save this possible color
				coloring.put(n, coloring.get(n)-1); // decrease the color
			}
			coloring.replace(n, last); // assign to the node the best possible color
		}
		
		return coloring;
	}
	
	public static void sortByDegree(List<Node> list) {
		boolean out_of_order = true;
		while (out_of_order) {
			out_of_order = false;
			for (int i = 0; i < list.size()-1; i++) {
				if (list.get(i).getDeg() > list.get(i+1).getDeg()) {
					Node temp = list.get(i+1); // ]
					list.set(i+1, list.get(i)); //] swap them
					list.set(i, temp); //		  ] 
					out_of_order = true;
				}
			}
		}
	}
	
}
