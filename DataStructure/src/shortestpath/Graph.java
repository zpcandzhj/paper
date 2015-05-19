package shortestpath;

import java.util.ArrayList;

/**
 * @ClassName:   Graph
 * @Description: 基于Dijkstra算法的最短路径求解
 * @author       zhoupengcheng
 * @version      2015年4月29日上午9:37:43
 */

public class Graph {
	private final int MAX_NUM = 20000;
	private final int INFINITY = 100000;
	private ArrayList<Vertex> vertexList;// ArrayList保存所有结点
	private int adjMatrix[][];//邻接矩阵
	private int nVerts;// 保存当前图中当前顶点数
	private int nTree; // 保存已经加入到最短路径上的结点
	//private DistPar sPath[];//保存源点到每个点的当前最短路径距离
	private ArrayList<DistPar> sPath;
	private int currentVert;//搜索最短路径到达的当前结点
	private int startToCurrent;//源点到当前结点的(最短)距离
	//--------------------构造函数--------------------
	public Graph(){
		vertexList=new ArrayList<Vertex>();
		adjMatrix=new int[MAX_NUM][MAX_NUM];
		nVerts=0;
		nTree=0;
		for(int i=0;i<MAX_NUM;i++){
			for(int j=0;j<MAX_NUM;j++)
				adjMatrix[i][j]=INFINITY;
		}
		//sPath=new DistPar[MAX_NUM];
		sPath=new ArrayList<DistPar>();
	}
	
	//---------------------------------------------
	public void addVertex(String lab,int id){
		vertexList.add(new Vertex(lab,id));
		nVerts++;
	}
	//---------------------------------------------
	public void addEdge(int start, int end,int weight) {
		adjMatrix[start][end] = weight;//有向带权图
	}
	//---------------------------------------------
	public void path(int startNode){//寻找最短路径
		//int startTree=0;//假设都是从下标为0的点开始搜索
		vertexList.get(startNode).isIntree=true;
		nTree=1;
		
		//根据邻接矩阵中两点的权值构造初始的sPath,sPath在搜索最短路径的过程中不断更新
		for(int k=0;k<nVerts;k++){
			int tmpDist=adjMatrix[startNode][k];
			//sPath[k]=new DistPar(startTree,tmpDist);
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
	//------------从sPath中获得一个离源点距离最短的结点----------
	private int getMin() {
		// TODO Auto-generated method stub
		int minDist=INFINITY;
		int indexMin=0;
		for(int j=0;j<nVerts;j++){
			if(!vertexList.get(j).isIntree&&sPath.get(j).distance<minDist){
				indexMin=j;
				minDist=sPath.get(j).distance;
			}
		}
		return indexMin;
	}
	//------------调整sPath中的值,更新最新的最短距离(距源点)------------
	private void adjust_sPath() {
		// TODO Auto-generated method stub
		int column=0;//起始点就不要更新了
		while(column<nVerts){
			if(vertexList.get(column).isIntree)
			{
				column++;
				continue;
			}
			
			int currentToFringe=adjMatrix[currentVert][column];
			int startToToFringe=currentToFringe+startToCurrent;
			int sPathDist=sPath.get(column).distance;
			if(startToToFringe<sPathDist){
				sPath.get(column).distance=startToToFringe;
				sPath.get(column).parentVert=currentVert;
			}
			column++;
		}
	}

	private void displayPaths(int srcVertex) {
		// TODO Auto-generated method stub
		for(int i=0;i<nVerts;i++){
			System.out.print(vertexList.get(i).label+"=");
			if(sPath.get(i).distance==INFINITY){
				System.out.print("inf");
			}else{
				System.out.print(sPath.get(i).distance);
			}
			String parent=vertexList.get(sPath.get(i).parentVert).label;
			System.out.print("("+parent+") ");
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
				System.out.print(vertexList.get(j).label + "<-"
						+ vertexList.get(srcVertex).label + " ");
			} else {
				System.out.print(vertexList.get(j).label + "<-");
				while (x != srcVertex) {
					System.out.print(vertexList.get(x).label + "<-");
					x = sPath.get(x).parentVert;
				}
				System.out.print(vertexList.get(srcVertex).label);
			}
			System.out.println("");
		}
	}
	
}

 
