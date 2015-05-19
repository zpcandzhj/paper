package minimumSpanningTree2;
/*
 * 基于Prim算法的有向带权图最小生成树
 * 图中边的表示,边的对像存储在优先级队列中
 */
public class Edge {
	public int srcVert;//边的起始点
	public int distance;
	public int destVert;//边指向的点
	public Edge(int srcVert,int destVert,int distance){
		this.destVert=destVert;
		this.distance=distance;
		this.srcVert=srcVert;
	}
}
