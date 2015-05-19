package linkQueue;
/*
 * 用链表实现队列这种抽象的数据结构
 * 此队列不会满
 */
public class LinkQueue {
	private FirstLastList theList;
	public LinkQueue(){
		theList=new FirstLastList();
	}
	
	public boolean isEmpty(){
		return theList.isEmpty();
	}
	public void insert (long val){
		theList.insertLast(val);
	}
	public Link remove(){
		return	theList.deleteFirst();
	}
	
	public void displayQueue() {
		System.out.println("Queue(front-->rear):");
		theList.displayList();
	}
}
