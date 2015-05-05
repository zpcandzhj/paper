package ccinfox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName: Graph
 * @Description: 基于Dijkstra算法的最短路径求解
 * @author zhoupengcheng
 * @version 2015年4月29日上午9:37:43
 */

public class Graph {
	private final int MAX_NUM = 10000;
	private final int INFINITY = 100000;
	private ArrayList<Vertex> vertexList;// ArrayList保存所有结点
	public HashMap<String, ArrayList<Integer>> invertedIndex;// 保存关键词和包含该关键词的结点ID之间的映射关系
	private ArrayList<Integer> rootList; // 保存一次关键词搜索找到的根节点
	private int adjMatrix[][];// 邻接矩阵
	private int nVerts;// 保存当前图中当前顶点数
	private int nTree; // 保存已经加入到最短路径上的结点
	// private DistPar sPath[];//保存源点到每个点的当前最短路径距离
	private ArrayList<DistPar> sPath;
	private int currentVert;// 搜索最短路径到达的当前结点
	private int startToCurrent;// 源点到当前结点的(最短)距离

	// --------------------构造函数--------------------
	public Graph() {
		vertexList = new ArrayList<Vertex>();
		adjMatrix = new int[MAX_NUM][MAX_NUM];
		nVerts = 0;
		nTree = 0;
		for (int i = 0; i < MAX_NUM; i++) {
			for (int j = 0; j < MAX_NUM; j++)
				adjMatrix[i][j] = INFINITY;
		}
		// sPath=new DistPar[MAX_NUM];
		// sPath = new ArrayList<DistPar>();
		invertedIndex = new HashMap<String, ArrayList<Integer>>();
	}

	// ---------------------------------------------
	public void addVertex(String lab, int id) {
		vertexList.add(new Vertex(lab, id));
		addToIndex(lab, id);
		nVerts++;
	}

	private void addToIndex(String lab, int id) {
		// TODO Auto-generated method stub
		String labs[] = lab.split(",");
		ArrayList<Integer> indexlist;
		for (int i = 0; i < labs.length; i++) {
			if (invertedIndex.get(labs[i]) == null) {
				indexlist = new ArrayList<Integer>();
				indexlist.add(id);
				invertedIndex.put(labs[i], indexlist);
			} else {
				indexlist = invertedIndex.get(labs[i]);
				indexlist.add(id);
			}
		}
	}

	// ---------------------------------------------
	public void addEdge(int start, int end, int weight) {
		adjMatrix[start][end] = weight;// 有向带权图
	}

	// ---------------------------------------------
	public void path(int startNode, String keyword) {// 以关键词结点为源点反向搜索最短路径
		sPath = new ArrayList<DistPar>();

		// int startTree=0;//假设都是从下标为0的点开始搜索
		vertexList.get(startNode).isIntree = true;
		nTree = 1;
		vertexList.get(startNode).visitations.add(keyword);// 源点(叶子结点都是关键词结点)
		// 根据邻接矩阵中两点的权值构造初始的sPath,sPath在搜索最短路径的过程中不断更新
		for (int k = 0; k < nVerts; k++) {
			int tmpDist = adjMatrix[startNode][k];
			sPath.add(new DistPar(startNode, tmpDist));
		}

		// 直到所有的结点都已经加入到最短路径中,否则继续迭代
		while (nTree < nVerts) {
			int indexMin = getMin();// 从sPath中获取最小的路径
			int minDist = sPath.get(indexMin).distance;
			if (minDist == INFINITY) {
				System.out.print("警告:从源点" + (startNode + 1) + "无法到达所有结点!  ");
				break;
			} else {
				currentVert = indexMin;
				// startToCurrent=sPath[indexMin].distance;
				startToCurrent = sPath.get(indexMin).distance;
			}
			vertexList.get(currentVert).isIntree = true;
			vertexList.get(currentVert).visitations.add(keyword);
			nTree++;
			adjust_sPath();
		}

		// displayPaths(startNode);// 打印最短路径搜索结果
		nTree = 0;
		// 清空标志位,还原一个没有遍历过的树
		for (int j = 0; j < nVerts; j++) {
			vertexList.get(j).isIntree = false;
		}
	}

	//----------------重载的path方法---------------------
	public void path(int startNode,int endNode){//寻找指定两点间的最短路径
		sPath = new ArrayList<DistPar>();
		vertexList.get(startNode).isIntree=true;
		nTree=1;
		
		//根据邻接矩阵中两点的权值构造初始的sPath,sPath在搜索最短路径的过程中不断更新
		for(int k=0;k<nVerts;k++){
			int tmpDist=adjMatrix[startNode][k];
			//sPath[k]=new DistPar(startNode,tmpDist);
			sPath.add(new DistPar(startNode,tmpDist));
		}
		
		//直到所有的结点都已经加入到最短路径中,否则继续迭代
		while(nTree<nVerts){
			int indexMin=getMin();//从sPath中获取最小的路径
			//int minDist=sPath[indexMin].distance;
			int minDist=sPath.get(indexMin).distance;
			if(minDist==INFINITY){
				System.out.println("存在源点无法到的结点!");
				break;
			}else{
				currentVert=indexMin;
				//startToCurrent=sPath[indexMin].distance;
				startToCurrent=sPath.get(indexMin).distance;
			}
			vertexList.get(currentVert).isIntree=true;
			nTree++;
			adjust_sPath();
		}
		
		displayPaths(startNode,endNode);//打印最短路径搜索结果
		nTree=0;
		//清空标志位,还原一个没有遍历过的树
		for(int j=0;j<nVerts;j++){
			vertexList.get(j).isIntree=false;
		}
	}
	
	//----------------重载的path方法---------------------
		public void path(int startNode){//搜索单源点最短路径
			sPath = new ArrayList<DistPar>();
			vertexList.get(startNode).isIntree=true;
			nTree=1;
			
			//根据邻接矩阵中两点的权值构造初始的sPath,sPath在搜索最短路径的过程中不断更新
			for(int k=0;k<nVerts;k++){
				int tmpDist=adjMatrix[startNode][k];
				//sPath[k]=new DistPar(startNode,tmpDist);
				sPath.add(new DistPar(startNode,tmpDist));
			}
			
			//直到所有的结点都已经加入到最短路径中,否则继续迭代
			while(nTree<nVerts){
				int indexMin=getMin();//从sPath中获取最小的路径
				//int minDist=sPath[indexMin].distance;
				int minDist=sPath.get(indexMin).distance;
				if(minDist==INFINITY){
					System.out.println("存在源点无法到的结点!");
					break;
				}else{
					currentVert=indexMin;
					//startToCurrent=sPath[indexMin].distance;
					startToCurrent=sPath.get(indexMin).distance;
				}
				vertexList.get(currentVert).isIntree=true;
				nTree++;
				adjust_sPath();
			}
			
			displayPaths(startNode);//打印最短路径搜索结果
			nTree=0;
			//清空标志位,还原一个没有遍历过的树
			for(int j=0;j<nVerts;j++){
				vertexList.get(j).isIntree=false;
			}
		}
		
	// ---------------清空每个结点的关键词来访记录-------------------
	public void clearVisitations() {
		for (int i = 0; i < nVerts; i++) {
			vertexList.get(i).clearVisitation();
		}
	}

	// -----------如果一个结点的来访记录数等于关键词个数则输出一个root结点-----------
	public void printRoots(int keyNum) {
		rootList = new ArrayList<Integer>();

		for (int i = 0; i < nVerts; i++) {
			int tmp = vertexList.get(i).visitations.size();
			if (tmp == keyNum) {
				// System.out.println((i + 1) +
				// "号结点的数据:"+vertexList.get(i).label);
				System.out.println((i + 1) + "号结点是此次关键词查询的一棵连接树的根,可达关键词如下:");
				rootList.add(i);
				for (String k : vertexList.get(i).visitations) {
					System.out.print(k + " ");
				}
				System.out.println("");
			}
			// else {
			// // System.out.println((i + 1) +
			// // "号结点的数据:"+vertexList.get(i).label);
			// System.out.println((i + 1) + "号结点没有能够被所有关键词到达,目前可达关键词如下:");
			// for (String k : vertexList.get(i).visitations) {
			// System.out.print(k + " ");
			// }
			// System.out.println();
			// }
			// System.out.println("");
		}
		System.out.println("根节点寻找完毕!");
	}

	//------------------输出最小连接树----------------------
	public void printJoinTrees(ArrayList<Integer> keywordsIds){
		System.out.println("所有的root结点如下:");
			for (Integer k : rootList)
				System.out.print((k+1)+" ");
		System.out.println();	
		//------------------重新建立邻接矩阵--------------------
		for (Integer t : rootList){
			for (Integer k : keywordsIds){
				path(t,k);
			}
		}
		System.out.println();
	}
	
	// ------------从sPath中获得一个离源点距离最短的结点----------
	private int getMin() {
		// TODO Auto-generated method stub
		int minDist = INFINITY;
		int indexMin = 0;
		for (int j = 0; j < nVerts; j++) {
			if (!vertexList.get(j).isIntree && sPath.get(j).distance < minDist) {
				indexMin = j;
				minDist = sPath.get(j).distance;
			}
		}
		return indexMin;
	}

	// ------------调整sPath中的值,更新最新的最短距离(距源点)------------
	private void adjust_sPath() {
		// TODO Auto-generated method stub
		int column = 0;
		while (column < nVerts) {
			if (vertexList.get(column).isIntree) {
				column++;
				continue;
			}

			int currentToFringe = adjMatrix[currentVert][column];
			int startToToFringe = currentToFringe + startToCurrent;
			int sPathDist = sPath.get(column).distance;
			if (startToToFringe < sPathDist) {
				sPath.get(column).distance = startToToFringe;
				sPath.get(column).parentVert = currentVert;
			}
			column++;
		}
	}

	private void displayPaths(int srcVertex ,int end) {//打印指定两点间最短路径
		// 打印源点到图中每个点走过的最短路径,根据DistPar对象的parent结点来找路径
		for (int j = 0; j < nVerts; j++) {
			if(j!=end)continue;//打印指定源点->终点的路径
			if (sPath.get(j).distance == INFINITY)
				continue;
			if (j == srcVertex)
				continue;
			int x = sPath.get(j).parentVert;
			if (x == srcVertex) {
				System.out.print((vertexList.get(j).id+1) + " <- "
						+ (vertexList.get(srcVertex).id+1) + " ");
			} else {
				System.out.print((vertexList.get(j).id+1) + " <- ");
				while (x != srcVertex) {
					System.out.print((vertexList.get(x).id+1) + " <- ");
					x = sPath.get(x).parentVert;
				}
				System.out.print((vertexList.get(srcVertex).id+1));
			}
			System.out.println("");
		}

	}
	
	//-------------重载的displayPaths方法,打印单源点最短路径------------------
	private void displayPaths(int srcVertex) {
		for (int i = 0; i < nVerts; i++) {
			System.out.print(vertexList.get(i).label + "=");
			if (sPath.get(i).distance == INFINITY) {
				System.out.print("inf");
			} else {
				System.out.print(sPath.get(i).distance);
			}
			String parent = vertexList.get(sPath.get(i).parentVert).label;
			System.out.print("(" + parent + ") ");
		}
		System.out.println();

		// 打印源点到图中每个点走过的最短路径,根据DistPar对象的parent结点来找路径
		for (int j = 0; j < nVerts; j++) {
			if (sPath.get(j).distance == INFINITY)
				continue;
			if (j == srcVertex)
				continue;
			int x = sPath.get(j).parentVert;
			if (x == srcVertex) {
				System.out.print((vertexList.get(j).label) + " <- "
						+ (vertexList.get(srcVertex).label) + " ");
			} else {
				System.out.print((vertexList.get(j).label) + " <- ");
				while (x != srcVertex) {
					System.out.print((vertexList.get(x).label) + " <- ");
					x = sPath.get(x).parentVert;
				}
				System.out.print((vertexList.get(srcVertex).label));
			}
			System.out.println("");
		}

	}
}
