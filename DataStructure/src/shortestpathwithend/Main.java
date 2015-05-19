package shortestpathwithend;

/**
 * @ClassName:   Main
 * @Description: 工程的main函数
 * @author       zhoupengcheng
 * @version      2015年4月29日上午9:39:12
 */
public class Main {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		int num = 0;
		theGraph.addVertex("a", num++); // 0 (start)
		theGraph.addVertex("b", num++); // 1
		theGraph.addVertex("c", num++); // 2
		theGraph.addVertex("d", num++); // 3
		theGraph.addVertex("e", num++); // 4
		//theGraph.addVertex("f", num++); // 5
		
		theGraph.addEdge(0, 1, 50); // AB 50
		theGraph.addEdge(0, 3, 80); // AD 80
		theGraph.addEdge(1, 2, 60); // BC 60
		theGraph.addEdge(1, 3, 90); // BD 90
		theGraph.addEdge(2, 4, 40); // CE 40
		theGraph.addEdge(3, 2, 20); // DC 20
		theGraph.addEdge(3, 4, 70); // DE 70
		theGraph.addEdge(4, 1, 50); // EB 50

		//构造无向图
//		theGraph.addEdge(1, 0, 50); // AB 50
//		theGraph.addEdge(3, 0, 80); // AD 80
//		theGraph.addEdge(2, 1, 60); // BC 60
//		theGraph.addEdge(3, 1, 90); // BD 90
//		theGraph.addEdge(4, 2, 40); // CE 40
//		theGraph.addEdge(2, 3, 20); // DC 20
//		theGraph.addEdge(4, 3, 70); // DE 70
//		theGraph.addEdge(1, 4, 50); // EB 50
		
		System.out.println("Shortest paths");
		theGraph.path(0,1); // 设置源点和起点
		System.out.println();
	}
}
