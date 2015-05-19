package shortestpath;

/**
 * @ClassName:   Vertex
 * @Description: 结点类
 * @author       zhoupengcheng
 * @version      2015年4月29日上午9:41:03
 */
public class Vertex {
	public String label;//节点的内容,一个元组的数据摘要
	public boolean isIntree;//标记该点是否已经包含在最短路径中
	public int id;//标识这个数据结点,到时要输出包含关键词节点的ID另作处理
	
	public Vertex(String label,int id){
		this.label=label;
		this.id=id;
		this.isIntree=false;
	}
}
