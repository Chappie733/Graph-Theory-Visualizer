package Render;

import java.awt.Graphics2D;

import GraphTheory.Primitive.Edge;

public class RenderEdge extends Edge {
	
	public RenderEdge(RenderNode first, RenderNode second) {
		super(first, second);
		first.registerAdjacentNode(second);
		second.registerAdjacentNode(first);
	}
	
	public void render(Graphics2D g) {
		RenderNode f = (RenderNode) first;
		RenderNode s = (RenderNode) second;
		g.drawLine(f.getScreen_pos().getX()+RenderNode.SIZE/2, f.getScreen_pos().getY()+RenderNode.SIZE/2, s.getScreen_pos().getX()+RenderNode.SIZE/2, s.getScreen_pos().getY()+RenderNode.SIZE/2);
	}
	
}
