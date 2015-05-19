package topsort;
/*
 * 对有向无环图(DAG)的拓扑排序(topSort)
 * 有向图的邻接矩阵不是对称矩阵(单方向赋值1)
 * 定义：将有向图中的顶点以线性方式进行排序.
 * 即对于任何连接自顶点u到顶点v的有向边uv，在最后的排序结果中，顶点u总是在顶点v的前面.
 */
public class Graph {
	private final int MAX_NUM=20;
	private Vertex[] vertexList;//保存顶点
	private int adjMatrix[][];//保存边的关系
	private int nVerts;//保存当前图中顶点数
	private char sortedArray[];//保存拓扑排序后的序列(这里只是保存顶点的标签)   
	
	public Graph(){
		vertexList=new Vertex[MAX_NUM];
		adjMatrix=new int[MAX_NUM][MAX_NUM];
		nVerts=0;
		for(int i=0;i<MAX_NUM;i++){//邻接矩阵赋初值
			for(int j=0;j<MAX_NUM;j++){
				adjMatrix[i][j]=0;
			}
		}
		sortedArray=new char[MAX_NUM];
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++]=new Vertex(lab);
	}
	
	public void addEdge(int start,int end){
		adjMatrix[start][end]=1;//有向图，只需单向记录边
		//adjMatrix[end][start]=1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].label);
	}
	//--------拓扑排序---------
	public void topsort(){
		int origin_nVerts=nVerts;
		while(nVerts>0){
			int currentVertex=noSuccessors();//返回一个没有后继结点的点
			if(currentVertex==-1)
			{
				System.out.println("ERROR:图中存在环！");
				return;
			}
			sortedArray[nVerts-1]=vertexList[currentVertex].label;
			deleteVertex(currentVertex);
		}
		System.out.println("Topologically sorted order:");
		for(int k=0;k<origin_nVerts;k++){
			System.out.print(sortedArray[k]+" ");
		}
		System.out.println("");
	}
	//-------通过查找链接矩阵,返回没有后继结点的点---------
	public int noSuccessors(){
		boolean isEdge;
		for(int row=0;row<nVerts;row++){
			isEdge=false;
			for(int col=0;col<nVerts;col++){
				if(adjMatrix[row][col]>0){
					isEdge=true;
					break;
				}
			}
			if(!isEdge)
				return row;//返回没有边(后继)的顶点
		}
		return -1;//没有满足条件的顶点了
	}
	
	public void deleteVertex(int delVert){
		if(delVert!=nVerts-1){//如果是删除最后一个点就不需要移动元素
			//删除顶点
			for(int j=delVert;j<nVerts-1;j++){
				vertexList[j]=vertexList[j+1];
			}
			//删除行(行要上移)
			for(int row=delVert;row<nVerts-1;row++){
				moveRowUp(row,nVerts);
			}
			//删除列(列要左移)
			for(int col=delVert;col<nVerts-1;col++){
				moveColLeft(col,nVerts-1);//nVerts-1是因为此时行已经少了一行(移动行的代码先执行了)
			}
		}
		nVerts--;
	}

	private void moveRowUp(int row, int length) {
		for(int col=0;col<length;col++){
			adjMatrix[row][col]=adjMatrix[row+1][col];
		}
	}
	
	private void moveColLeft(int col, int length) {
		for(int row=0;row<length;row++){
			adjMatrix[row][col]=adjMatrix[row][col+1];
		}
	}
	
}

