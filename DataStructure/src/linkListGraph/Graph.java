package linkListGraph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName: Graph
 * @Description: 图结构实现,封装了图的dfs、bfs遍历算法
 * @author zhoupengcheng
 * @version 2015年5月17日下午5:07:34
 */
public class Graph {
	private ArrayList<Vertex> vertexList;// 用一个ArrayList保存图中的所有结点
	private boolean isDirected = false;// 标记是否为有向图
	private int nVerts = 0;// 保存总的顶点数

	private StackX theStack;// 深度优先搜寻时使用
	private Queue theQueue; // 广度优先搜索时使用
	private ArrayList<Vertex> bfs;// 在bfs函数中使用,保存bfs得到的结果序列
	private ArrayList<Vertex> dfs;// 在dfs函数中使用,保存dfs得到的结果序列

	public Graph() {
		vertexList = new ArrayList<Vertex>();
		dfs = new ArrayList<Vertex>();
		bfs = new ArrayList<Vertex>();
	}

	public Graph(boolean is) {
		this();
		this.isDirected = is;
	}

	public boolean isDirected() {
		return this.isDirected;
	}

	public ArrayList<Vertex> getVertexList() {
		return vertexList;
	}

	public ArrayList<Vertex> getDFS() {
		return dfs;
	}

	public ArrayList<Vertex> getBFS() {
		return bfs;
	}

	public void addVertex(Vertex vertex) {
		vertex.setIndex(nVerts);
		vertexList.add(vertex);
		nVerts++;
	}

	public void addEdge(int start, int end) {
		vertexList.get(start).addAdj(vertexList.get(end));
		if (!isDirected) {
			vertexList.get(end).addAdj(vertexList.get(start));
		}
	}

	public int getVertsCount() {
		return nVerts;
	}
	
	//深度优先迭代器
	public Iterator dfsIterator(){  
        dfs();  
        return new DfsIterator();  
    } 
	
	//广度优先迭代器
	public Iterator bfsIterator(){  
        bfs();  
        return new BfsIterator();  
    }  
	
	// 打印邻接表
	public void displayGraph() {
		for (int i = 0; i < vertexList.size(); i++) {
			printVertx(vertexList.get(i));
		}
	}

	public void printVertx(Vertex vertex) {
		ArrayList<Vertex> next = vertex.getAdj();
		if (next == null) {
			System.out.println(vertex.toString() + " 无邻接点！");
		} else {
			System.out.print(vertex.toString() + "邻接表:");
			for (int i = 0; i < next.size(); i++) {
				System.out.print(next.get(i).label + " ");
			}
			System.out.println();
		}
	}

	// 深度优先遍历
	public void dfs() {
		theStack = new StackX(nVerts);
		vertexList.get(0).wasVisted = true;
		dfs.add(vertexList.get(0));
		theStack.push(vertexList.get(0));
		Vertex vertex;
		while (!theStack.isEmpty()) {
			vertex = getAdjVertex((Vertex) theStack.peek());
			if (vertex == null) {
				theStack.pop();
			} else {
				vertex.wasVisted = true;
				dfs.add(vertex);
				theStack.push(vertex);
			}
		}
		// ------遍历完成,清除所有访问标志位--------
		for (int i = 0; i < getVertsCount(); i++) {
			vertexList.get(i).wasVisted = false;
		}

	}

	// 广度优先遍历
	public void bfs() {
		theQueue = new Queue(nVerts);
		vertexList.get(0).wasVisted = true;
		bfs.add(vertexList.get(0));
		theQueue.insert(vertexList.get(0));
		Vertex vertex1;
		while (!theQueue.isEmpty()) {
			Vertex vertex2 = (Vertex) theQueue.remove();
			while ((vertex1 = getAdjVertex(vertex2)) != null) {
				vertex1.wasVisted = true;
				bfs.add(vertex1);
				theQueue.insert(vertex1);
			}
		}
		// ------遍历完成,清除所有访问标志位--------
		for (int i = 0; i < getVertsCount(); i++) {
			vertexList.get(i).wasVisted = false;
		}
	}

	// 返回vertex顶点的一个未曾访问过的邻接点
	public Vertex getAdjVertex(Vertex vertex) {
		ArrayList<Vertex> adjVertexs = vertex.getAdj();
		for (int i = 0; i < adjVertexs.size(); i++) {
			if (!adjVertexs.get(i).wasVisted) {
				return adjVertexs.get(i);
			}
		}
		return null;
	}

	//专门写个迭代器来遍历结果
	private abstract class GraphIterator implements Iterator {
		int count = 0;
		public GraphIterator() {
		}

		public boolean hasNext() {
			return count != getVertsCount() ;
		}

		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

		public void remove() {
			// TODO Auto-generated method stub
		}
	}

	// 深度优先迭代
	private class DfsIterator extends GraphIterator {
		public DfsIterator() {
			super();
		}

		public Vertex next() {
			return dfs.get(count++);
		}
	}

	// 广度优先迭代
	private class BfsIterator extends GraphIterator {
		public BfsIterator() {
			super();
		}

		public Object next() {
			return bfs.get(count++);
		}
	}
}
