package minimumSpanningTree2;
/*
 * 图中的顶点类
 */
public class Vertex {
	public char label;
	public boolean isInTree;
	
	public Vertex(char label){
		this.label=label;
		this.isInTree=false;
	}
}
