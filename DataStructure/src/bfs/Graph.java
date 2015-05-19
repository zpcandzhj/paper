package bfs;

/*
 * 基于邻接矩阵的图结构
 * 采用广度优先(BFS)遍历
 */
public class Graph {
	private final int MAX_NUM=20;
	private Vertex[] vertexList;//保存顶点
	private int adjMatrix[][];//保存边的关系
	private int nVerts;//保存当前图中顶点数
	private Queue theQueue;//用于在bfs搜索过程中保存顶点的队列
	
	public Graph(){
		vertexList=new Vertex[MAX_NUM];
		adjMatrix=new int[MAX_NUM][MAX_NUM];
		nVerts=0;
		for(int i=0;i<MAX_NUM;i++){//邻接矩阵赋初值
			for(int j=0;j<MAX_NUM;j++){
				adjMatrix[i][j]=0;
			}
		}
		//theQueue=new Queue();//虽然每一次遍历完队列理应为空,为保险起见,还是在遍历函数里初始化队列
	}
	
	public void addVertex(char b){
		vertexList[nVerts++]=new Vertex(b);
	}
	
	public void addEdge(int start,int end){
		adjMatrix[start][end]=1;
		adjMatrix[end][start]=1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].label+" ");
	}
	
	public void bfs(){
		theQueue=new Queue();
		vertexList[0].wasVisited=true;
		displayVertex(0);
		theQueue.insert(0);
		int v2;
		while(!theQueue.isEmpty()){
			int v1=theQueue.remove();
			while((v2=getAdjUnvisitedVertex(v1))!=-1){
				vertexList[v2].wasVisited=true;
				displayVertex(v2);
				theQueue.insert(v2);
			}
		}
		//------遍历完成,清除所有访问标志位--------
		for(int i=0;i<nVerts;i++){
			vertexList[i].wasVisited=false;
		}
	}
	//-------返回下标为v的顶点的一个未曾访问过的邻接点-------
	public int getAdjUnvisitedVertex(int v){
		for(int i=0;i<nVerts;i++){
			if(adjMatrix[v][i]==1&&vertexList[i].wasVisited==false){
				return i;
			}
		}
		return -1;
	}
}
