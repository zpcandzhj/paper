package linkQueue;
/*
 * linkStack.java和linkQueue.java强调栈和队列是概念上的实体,独立于它们的具体实现
 */
public class LinkQueueApp {
	public static void main(String[] args) {
		LinkQueue theQueue = new LinkQueue();
		System.out.println("theQueue.isEmpty():" + theQueue.isEmpty());
		theQueue.insert(11);
		theQueue.insert(12);
		theQueue.insert(13);
		theQueue.insert(14);
		theQueue.insert(15);
		theQueue.remove();
		theQueue.displayQueue();
	}
}
