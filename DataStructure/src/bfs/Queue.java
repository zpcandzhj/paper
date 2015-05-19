package bfs;
/*
 * 广度优先需要借助队列保存顶点
 */
public class Queue {
	private final int maxSize=20;
	private int front;//队头指针
	private int rear;//队尾指针
	private int[] queArray;// 用数组实现队列的存储

	public Queue() {
		queArray = new int[maxSize];
		front = 0;
		rear = -1;
	}

	public void insert(int value) {
		if (rear == maxSize - 1)
			rear = -1;
		queArray[++rear] = value;
	}

	// 返回被删除的元素
	public int remove() {
		if (front == maxSize)
			front = 0;
		int temp = queArray[front++];
		return temp;
	}

	public boolean isEmpty() {
		return (rear+1==front||(front+maxSize-1==rear));
	}
}
