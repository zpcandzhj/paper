package ccinfo;

import java.util.ArrayList;

/*
 * 基于邻接矩阵(二维数组)的图结构实现
 * 采用深度优先(DFS)遍历
 */
public class Graph {
	private final int MAX_NUM = 20000;
	private ArrayList<Vertex> vertexList;// ArrayList保存所有结点
	private int adjMatrix[][];// 保存边的关系
	private int nVerts;// 保存当前图中顶点数
	private LinkStack theStack;// 用于在dfs搜索过程中保存顶点
	private LinkStack theStackk;

	public Graph() {
		vertexList = new ArrayList<Vertex>();
		vertexList.ensureCapacity(40000);
		adjMatrix = new int[MAX_NUM][MAX_NUM];
		nVerts = 0;
		for (int i = 0; i < MAX_NUM; i++) {// 邻接矩阵赋初值
			for (int j = 0; j < MAX_NUM; j++) {
				adjMatrix[i][j] = 0;
			}
		}
		theStack = new LinkStack();
		theStackk = new LinkStack();
	}

	public void addVertex(String label, int id) {
		Vertex v = new Vertex(label, id);
		vertexList.add(v);
		nVerts++;
	}

	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList.get(v).label + "\n");
	}

	// 对此图的遍历算法
	public void dfsOne(String key, int start) {
		vertexList.get(start).wasVisited = true;
		if (vertexList.get(start).label.contains(key))
			displayVertex(start);
		theStack.push(start);
		while (!theStack.isEmpty()) {
			int v = getAdjUnvisitedVertex(theStack.peek());
			if (v == -1) {
				theStack.pop();// 目标顶点没有未访问的邻接点了
			} else {
				vertexList.get(v).wasVisited = true;
				boolean f = vertexList.get(v).label.contains(key);
				if (f) {
					displayVertex(v);
				}
				theStack.push(v);
			}
		}
		// ------遍历完成,清除所有访问标志位--------
		for (int i = 0; i < nVerts; i++) {
			vertexList.get(i).wasVisited = false;
		}
	}

	// AND语义
	public void dfsTwo(String[] keys, int start) {
		Boolean[] flag = new Boolean[keys.length];
		boolean result = true;
		vertexList.get(start).wasVisited = true;
		// -----------------单独判断第一个节点---------------
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				boolean f = vertexList.get(start).label.contains(keys[i]);
				flag[i] = f;
			}
		}

		for (int k = 0; k < flag.length; k++) {
			if (flag[k] != null)
				result = result && flag[k];
		}

		if (result)
			displayVertex(start);
		// -------------------------------------------
		theStackk.push(start);
		while (!theStackk.isEmpty()) {
			flag = new Boolean[keys.length];
			result = true;
			int v = getAdjUnvisitedVertex(theStackk.peek());
			if (v == -1) {
				theStackk.pop();// 目标顶点没有未访问的邻接点了
			} else {
				vertexList.get(v).wasVisited = true;
				for (int i = 0; i < keys.length; i++) {
					if (keys[i] != null) {
						boolean f = vertexList.get(v).label.contains(keys[i]);
						flag[i] = f;
					}
				}

				for (int k = 0; k < flag.length; k++) {
					if (flag[k] != null)
						result = result && flag[k];
				}

				if (result)
					displayVertex(v);

				theStackk.push(v);
			}
		}
		// ------遍历完成,清除所有访问标志位--------
		for (int i = 0; i < nVerts; i++) {
			vertexList.get(i).wasVisited = false;
		}
	}

	// -------返回下标为v的顶点的一个未曾访问过的邻接点-------
	public int getAdjUnvisitedVertex(int v) {
		for (int i = 0; i < nVerts; i++) {
			if (adjMatrix[v][i] == 1 && vertexList.get(i).wasVisited == false) {
				return i;
			}
		}
		return -1;
	}

}
