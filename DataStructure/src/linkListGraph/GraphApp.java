package linkListGraph;

import java.util.Iterator;

public class GraphApp {

	public static void main(String[] args) {
		Graph myGraph = new Graph();
		Vertex vertex;
		myGraph.addVertex(new Vertex('A'));//位置为0
		myGraph.addVertex(new Vertex('B'));//位置为1
		myGraph.addVertex(new Vertex('C'));//位置为2
		myGraph.addVertex(new Vertex('D'));//位置为3
		myGraph.addVertex(new Vertex('E'));//位置为4
		myGraph.addVertex(new Vertex('F'));//位置为5
		myGraph.addVertex(new Vertex('G'));//位置为6
		myGraph.addVertex(new Vertex('H'));//位置为7
		myGraph.addVertex(new Vertex('I'));//位置为8
		myGraph.addVertex(new Vertex('J'));//位置为9
		myGraph.addVertex(new Vertex('K'));//位置为10
		myGraph.addEdge(0, 1);
		myGraph.addEdge(0, 2);
		myGraph.addEdge(0, 3);
		myGraph.addEdge(1, 4);
		myGraph.addEdge(1, 5);
		myGraph.addEdge(3, 7);
		myGraph.addEdge(3, 10);
		myGraph.addEdge(4, 6);
		myGraph.addEdge(5, 6);
		myGraph.addEdge(5, 9);
		myGraph.addEdge(7, 8);
		myGraph.addEdge(8, 10);
		myGraph.displayGraph();
		
		System.out.println("深度优先迭代遍历：");
		for (Iterator iterator = myGraph.dfsIterator(); iterator.hasNext();) {
			vertex = (Vertex) iterator.next();
			System.out.println(vertex.toString());
		}

		System.out.println("广度优先迭代遍历：");
		for (Iterator iterator = myGraph.bfsIterator(); iterator.hasNext();) {
			vertex = (Vertex) iterator.next();
			System.out.println(vertex.toString());
		}
	}
}
