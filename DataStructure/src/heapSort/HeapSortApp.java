package heapSort;
import java.io.*;

public class HeapSortApp {
	public static void main(String[] args) throws IOException {
		int size,j;
		System.out.println("Enter the number of items:");
		size=getInt();
		Heap theHeap=new Heap(size);
		for(j=0;j<size;j++){
			int random=(int)(Math.random()*200);
			Node newNode=new Node(random);
			theHeap.insertAt(j, newNode);
			theHeap.incrementSize();
		}
		System.out.println("---------------原始数组----------------");
		theHeap.displayArray();//输出原始数组
		//执行trickDown方法生成堆
		for(j=size/2-1;j>=0;j--){
			theHeap.trickDown(j);
		}
		System.out.println("---------------堆数组----------------");
		theHeap.displayArray();//输出堆数组
		System.out.println("----------------堆-----------------");
		theHeap.displayHeap();//输出堆
		//--------元素出堆,构建有序数组------------
		for(j=size-1;j>=0;j--){
			Node biggstNode=theHeap.remove();
			theHeap.insertAt(j, biggstNode);
		}
		//输出从小到大的序列
		System.out.println("------------Sorted:------------");
		theHeap.displayArray();//输出排完序的数组
	}

	// -------------------------------------------------------------
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	// -------------------------------------------------------------
	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	// -------------------------------------------------------------
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}
