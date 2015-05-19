package minimumSpanningTree2;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;

/*
 * 存放边(长度)的优先级队列(不是非常严格的对列操作)
 * 本例的优先级队列基于数组(大量数据的话建议用堆实现优先级队列)
 */
public class PriorityQ {
	private final int SIZE=20;
	private Edge[] queArray;
	private int currentSize;
	
	public PriorityQ(){
		queArray=new Edge[SIZE];
		currentSize=0;
	}
	//---------insert函数---------
	public void insert(Edge item){
		//队尾放置较小的元素,队列中元素的删除(出队)都是在队尾进行
		int j;
		for(j=0;j<currentSize;j++){
			if(item.distance>=queArray[j].distance){
				break;
			}
		}
		//腾出位置
		for(int k=currentSize-1;k>=j;k--){
			queArray[k+1]=queArray[k];
		}
		queArray[j]=item;
		currentSize++;
	}
	//-----------出队,返回最小值-----------
	public Edge removeMin(){
		return queArray[--currentSize];
	}
	//-------返回最小值,但是不删除---------
	public Edge peekMin(){
			return queArray[currentSize-1];
	}
	//-----删除指定下标的的元素,只是这里的特殊用法,标准的队列里没有这个操作---------
	public void removeN(int n){
		for(int j=n;j<currentSize-1;j++){
			queArray[j]=queArray[j+1];
		}
		currentSize--;
	}
	
	//-------返回指定下标的的元素,但是不删除---------
	public Edge peekN(int n){
		return queArray[n];
	}
	//--------查找队列是否已经存在指定目标顶点的Edge-------
	public int find(int findDex){
		for(int j=0;j<currentSize;j++){
			if(queArray[j].destVert==findDex){
				return j;
			}
		}
		return -1;
	}
	public int size(){
		return currentSize;
	}
	
	public boolean isEmpty(){
		return currentSize==0;
	}
}
