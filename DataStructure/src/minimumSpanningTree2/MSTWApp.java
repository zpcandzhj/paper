package minimumSpanningTree2;
//有向带权图的最小生成树
public class MSTWApp {
	public static void main(String[] args) {
		Graph theGraph=new Graph();
		theGraph.addVertex('A');//位置为0
		theGraph.addVertex('B');//位置为1
		theGraph.addVertex('C');//位置为2
		theGraph.addVertex('D');//位置为3
		theGraph.addVertex('E');//位置为4
		theGraph.addVertex('F');//位置为5

		theGraph.addEdge(0, 1,6);
		theGraph.addEdge(0, 3,4);
		theGraph.addEdge(1, 2,10);
		theGraph.addEdge(1, 3,7);
		theGraph.addEdge(1, 4,7);
		theGraph.addEdge(2, 3,8);
		theGraph.addEdge(2, 4,5);
		theGraph.addEdge(2, 5,6);
		theGraph.addEdge(3, 4,12);
		theGraph.addEdge(4, 5,7);
		System.out.println("最小生成树路径：");
		theGraph.mstw();
	}
}
