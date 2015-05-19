package shortestpath;

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
		theGraph.addVertex("A", num++); // 0 (start)
		theGraph.addVertex("B", num++); // 1
		theGraph.addVertex("C", num++); // 2
		theGraph.addVertex("D", num++); // 3
		theGraph.addVertex("E", num++); // 4
		//theGraph.addVertex("f", num++); // 5
		
		theGraph.addEdge(0, 1, 10); // AB 50
		theGraph.addEdge(0, 3, 5); // AD 80
		theGraph.addEdge(1, 2, 1); // BC 60
		theGraph.addEdge(1, 3, 2); // BD 90
		theGraph.addEdge(2, 4, 4); // CE 40
		theGraph.addEdge(3, 1, 3); // DB 20
		theGraph.addEdge(3, 2, 9); // DC 70
		theGraph.addEdge(3, 4, 2); // DE 70
		theGraph.addEdge(4, 0, 7); // EB 50
		theGraph.addEdge(4, 2, 6); // EC 70

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
		theGraph.path(0); // 设置源点
		System.out.println();
	}
}
