package dfcopy.copy;
/*
 * 用链表实现的栈结构
 */
public class LinkStack {
	private LinkList theList;
	public LinkStack(){
		theList=new LinkList();
	}
	
	public void push(int j){
		theList.insertFirst(j);
	}
	
	public long pop(){
		return theList.deleteFirst();
	}
	
	public boolean isEmpty(){
		return theList.isEmpty();
	}
	
	public int peek(){
		return theList.getFirst().getData();
	}
	
	public void displayStack(){
		System.out.println("Stack(top-->bottom):");
		theList.displayList();
	}
}
