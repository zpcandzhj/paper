package linkListGraph;
import java.util.ArrayList;
/**
 * @ClassName:   Vertex
 * @Description: 顶点类,表示图中存储的顶点
 * @author       zhoupengcheng
 * @version      2015年5月17日下午4:31:54
 */
public class Vertex {
	public char label;
	public boolean wasVisted;
	public int indexId;//顶点的标号
	//由于采用"邻接表"方式表示图,所以每个顶点对象持有一个邻接表adjList
	private ArrayList<Vertex> adjacentList = null;

	public Vertex(char lab) // constructor
	{
		this.label = lab;
		this.wasVisted = false;
	}

	// 为节点添加邻接点
	public void addAdj(Vertex ver) {
		if (adjacentList == null)
			adjacentList = new ArrayList<Vertex>();
		adjacentList.add(ver);
	}

	//返回一个顶点的邻接表,在遍历图时需根据邻接表找下一个节点
	public ArrayList<Vertex> getAdj() {
		return this.adjacentList;
	}

	public void setIndex(int index) {
		this.indexId = index;
	}

	public String toString() {
		return "顶点 :" + label+" ";
	}
}
