package heapSort;

/*
 * 堆排序:
 * heapSort最朴素的思想是使用insert()方法在堆中插入全部的无序的数据项，
 * 然后重复用remove()例程，就可以按序移除所有数据项。
 * 可以分别在时间和空间上做一些改进:
 * 时间：不采用insert每个元素的方式，而是通过对N/2-1节点开始一直到根节点为止调用trickleDown方法把一个无序数
 * 组变成堆。这样就避免了调用上移函数N次，而只是调用了下移函数N/2次
 * 空间：使得堆和初始数组使用同一个数组而不需要使用额外的空间。
 */
public class Heap {
	private Node[] heapArray;// 元素的存储机制还是基于数组的
	private int maxSize;
	private int currentSize;

	public Heap(int mx) {
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	// ---------------------出堆(删除元素)操作--------------------------
	public Node remove() {
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];// 把最后一项放到堆顶
		trickDown(0);
		return root;
	}

	// ----------出堆时要把数组最后一个元素放到堆顶,自然要进行下移操作------------
	/*
	 * 下移函数,参数是数组元素的下标,都是从0(即堆顶处)处开始调
	 * 只要index<currentSize/2(index标识的节点至少有一个孩子)就进行下去
	 */
	public void trickDown(int index) {
		int largerChild;
		Node top = heapArray[index];
		while (index < currentSize / 2) {
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;
			if (rightChild < currentSize
					&& heapArray[leftChild].getKey() < heapArray[rightChild]
							.getKey()) {// 最后一个节点的rightChild不一定存在的
				largerChild = rightChild;
			} else {
				largerChild = leftChild;
			}
			if (top.getKey() >= heapArray[largerChild].getKey()) {
				break;
			}
			heapArray[index] = heapArray[largerChild];// 元素上移
			index = largerChild;
		}
		heapArray[index] = top;
	}

	// ----------------------以树形的方式显示堆-------------------------
	public void displayHeap() {
		
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // 在循环内部起作用的 // current item
		String dots = "...............................";
		System.out.println(dots + dots); // dotted top line

		while (currentSize > 0) // for each heap item
		{
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++)
					// preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(heapArray[j].getKey());

			if (++j == currentSize) // done?
				break;

			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else
				// next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' '); // interim blanks
		} // end for
		System.out.println("\n" + dots + dots); // dotted bottom line
	} // end displayHeap()
	
	//--------------------------------------------------------
	public void displayArray(){
		System.out.print("heapArray: "); // array format
		for (int m = 0; m < maxSize; m++)
			if (heapArray[m] != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
	}
	//这里的insert函数只是完成一个录入数据的功能,没有调用下移方法维护堆的性质,程序中调用trickDown建堆
	public void insertAt(int index,Node newNode){
		heapArray[index]=newNode;
	}
	//-----------------------------------------------------
	public void incrementSize(){
		currentSize++;
	}
	
}
