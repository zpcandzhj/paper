package dfcopy;

public class Link {
	public int dData;//真实的应用一个节点可能有多种数据项构成
	public Link next;
	//本例中的栈主要是用来保存元素在邻接矩阵中的下标
	public Link(int index){
		dData=index;
		next=null;
	}
	
	public void display(){
		System.out.print(" "+dData);
	}
	
	public int getData(){
		return dData;
	}
}
