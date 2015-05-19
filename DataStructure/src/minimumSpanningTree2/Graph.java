package minimumSpanningTree2;

/*
 * 有向带权图的最小生成树(基于Prim算法)
 * 基于邻接矩阵(二维数组)的图结构实现
 */
public class Graph {
	private final int MAX_NUM = 20;
	private final int INFINITY = 100000;// 巨大的权重表示不可达
	private Vertex[] vertexList;// 保存顶点
	private int adjMatrix[][];// 保存边的关系
	private int nVerts;// 保存当前图中顶点数
	private int currentVert;
	private PriorityQ thePQ;
	private int nTree;// 记录已经加入到Tree中的顶点数

	public Graph() {
		vertexList = new Vertex[MAX_NUM];
		adjMatrix = new int[MAX_NUM][MAX_NUM];
		nVerts = 0;
		for (int i = 0; i < MAX_NUM; i++) {// 邻接矩阵赋初值
			for (int j = 0; j < MAX_NUM; j++) {
				adjMatrix[i][j] = INFINITY;
			}
		}
		thePQ = new PriorityQ();
	}

	public void addVertex(char b) {
		vertexList[nVerts++] = new Vertex(b);
	}

	public void addEdge(int start, int end, int weight) {
		adjMatrix[start][end] = weight;
		adjMatrix[end][start] = weight;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	public void mstw() {// 输出最小生成树
		currentVert = 0;
		// 只要还有点没有被包含在最小生成树中就继续
		while (nTree < nVerts - 1) {
			vertexList[currentVert].isInTree = true;
			nTree++;
			// 把与currentVert邻接的边加入队列
			for (int j = 0; j < nVerts; j++) {
				if (j == currentVert) {
					continue;
				}
				if (vertexList[j].isInTree) {
					continue;
				}
				int distance = adjMatrix[currentVert][j];
				if (distance == INFINITY) {
					continue;
				}
				putInPQ(j, distance);
			}
			// 如果此时PQ队列里没有顶点,则说明此图不连通,返回
			if (thePQ.size() == 0) {
				System.out.println("图不连通！");
				return;
			}
			// -----想办法输出一条属于最小生成树的边-----
			Edge edge = thePQ.removeMin();
			int sourceVert = edge.srcVert;
			currentVert = edge.destVert;// 以新的终点为起点再发展新的边加入队列
			System.out.print(vertexList[sourceVert].label);
			System.out.print("——>");
			System.out.print(vertexList[currentVert].label);
			System.out.print(" ");
		}

	}

	public void putInPQ(int newVert, int newDistance) {
		// is there another edge with the same destination vertex?
		int queueIndex = thePQ.find(newVert);
		if (queueIndex != -1) // got edge's index
		{
			Edge tempEdge = thePQ.peekN(queueIndex); // get edge
			int oldDist = tempEdge.distance;
			if (oldDist > newDistance) // if new edge shorter,
			{
				thePQ.removeN(queueIndex); // remove old edge
				Edge theEdge = new Edge(currentVert, newVert, newDistance);
				thePQ.insert(theEdge); // insert new edge
			}
			// else no action; just leave the old vertex there
		} 
		else // no edge with same destination vertex
		{ // so insert new one
			Edge theEdge = new Edge(currentVert, newVert, newDistance);
			thePQ.insert(theEdge);
		}
	}
}
