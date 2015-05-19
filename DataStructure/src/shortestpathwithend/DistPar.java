package shortestpathwithend;

/**
 * @ClassName:   DistPar
 * @Description: 此类的实例是存放在sPath数组中的
 * @author       zhoupengcheng
 * @version      2015年4月29日上午9:40:38
 */
public class DistPar {
	public int distance;// 保存源点(起点)到此结点的当前最短距离
	public int parentVert;// 在最短路径中此结点的上一个(父)结点,设置此属性是为了方便输出路径序列

	public DistPar(int parent,int distance) {
		// TODO Auto-generated constructor stub
		this.parentVert=parent;
		this.distance=distance;
	}
}
