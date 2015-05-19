package linkListGraph;
/*
 * 深度优先遍历需要借助栈保存当前访问过的顶点
 */
public class StackX {
	//private int size=20;
	private Vertex[] stackArray;//用数组实现栈的存储(理论上链栈效率更高)
	private int top;
	public StackX(int size){
		stackArray=new Vertex[size];
		top=-1;
	}

	public Vertex pop(){
		return stackArray[top--];
	}
	
	public Vertex peek(){
		return stackArray[top];
	}
	
	public boolean isEmpty(){
		return top==-1;
	}

	public void push(Vertex vertex) {
		// TODO Auto-generated method stub
		stackArray[++top]=vertex;
	}
}


