package topsort;
/*
 * 对有向无环图(DAG)的拓扑排序
 * 定义：将有向图中的顶点以线性方式进行排序.
 * 即对于任何连接自顶点u到顶点v的有向边uv，在最后的排序结果中，顶点u总是在顶点v的前面.
 */
public class TopApp {
public static void main(String[] args) {
	Graph theGraph = new Graph();
	theGraph.addVertex('A');    // 0
    theGraph.addVertex('B');    // 1
    theGraph.addVertex('C');    // 2
    theGraph.addVertex('D');    // 3
    theGraph.addVertex('E');    // 4
    theGraph.addVertex('F');    // 5
    theGraph.addVertex('G');    // 6
    theGraph.addVertex('H');    // 7

    theGraph.addEdge(0, 3);     // AD
    theGraph.addEdge(0, 4);     // AE
    theGraph.addEdge(1, 4);     // BE
    theGraph.addEdge(2, 5);     // CF
    theGraph.addEdge(3, 6);     // DG
    theGraph.addEdge(4, 6);     // EG
    theGraph.addEdge(5, 7);     // FH
    theGraph.addEdge(6, 7);     // GH

    theGraph.topsort();  
    //注意排序结果的输出顺序！
    }  // end main()
}
