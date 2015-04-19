package ccinfo;
/*
 * 图中的顶点类
 */
public class Vertex {
	public String label;//节点的内容,一个元组的数据记录
	public boolean wasVisited;//遍历时标记是否访问过
	public int id;//标识这个数据结点,到时要输出包含关键词节点的ID另作处理
	
	
	public Vertex(String label,int id){
		this.label=label;
		this.id=id;
		this.wasVisited=false;
	}
}
