package dfcopy;
/*
 * 为用链表实现栈这种抽象的数据结构而做准备
 */
public class LinkList {
	private Link first;
	public LinkList(){
		first=null;
	}
	
	public boolean isEmpty(){
		return first==null;
	}
	
	//为入栈操作铺垫
	public void insertFirst(int index){
		Link temp=new Link(index);
		temp.next=first;
		first=temp;
	}
	
	//为出栈操作铺垫
	public long deleteFirst(){
		Link temp=first;
		long value=temp.dData;
		first=first.next;
		return value;
	}
	
	//为获取栈顶元素做准备
	public Link getFirst(){
		return first;
	}
	
	//为遍历栈的内容铺垫
	public void displayList(){
		Link current=first;
		while(current!=null){
			current.display();
			current=current.next;
		}
		System.out.println(" ");
	}
}
