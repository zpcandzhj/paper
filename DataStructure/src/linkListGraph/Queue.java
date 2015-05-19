package linkListGraph;
/*
 * 广度优先需要借助队列保存当前已经访问过的顶点
 */
public class Queue {
	private int maxSize=20;
	private int front;//队头指针
	private int rear;//队尾指针
	private Vertex[] queArray;//用数组实现队列的存储(理论上链队列效率更高)

	public Queue(int size) {
		this.maxSize=size;
		queArray = new Vertex[maxSize];
		front = 0;
		rear = -1;
	}

	public void insert(Vertex value) {
		if (rear == maxSize - 1)
			rear = -1;
		queArray[++rear] = value;
	}

	// 返回被删除的元素
	public Vertex remove() {
		if (front == maxSize)
			front = 0;
		Vertex temp = queArray[front++];
		return temp;
	}

	public boolean isEmpty() {
		return (rear+1==front||(front+maxSize-1==rear));
	}
}
