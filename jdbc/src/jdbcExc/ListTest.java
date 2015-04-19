package jdbcExc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 ArrayList底层就是一个数组。
 LinkedList底层则是一个双向链表。
  所以他们在增删改查方面性能上有很大的不同：
 ArrayList遍历：建议用get（速度是超音速级别），也可用iterator借口实现；
 增：如果是顺序一次往Arraylist中增加对象，可以采用；
 增加删除修改：如果是位置不再数组末尾，而是在数组中间某个位置，则就不要用ArrayList，特别是频繁的增删改Arraylist中的数据。
 LinkedList遍历：坚决不能用get方法（速度是蜗牛级别的），最好用iterator借口实现；
 增删改:非常建议用linkedList（速度相对于Arraylist是超音速级别）。
 总结：只遍历用Arrarlist，只要有修改就用linkedList，如果修改中还有遍历则是LinkedList+iterator借口遍历。

 以下是比较程序（100w）：
 */
public class ListTest {
	public static void main(String[] args) {
		ArrayList<String> alist = new ArrayList<String>();
		LinkedList<String> llist = new LinkedList<String>();

		/*
		 * 普通的add方法比较
		 */
		long abegin = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			alist.add(String.valueOf(i));
		}
		long aend = System.currentTimeMillis();
		System.out.println("arraylist add time:" + (aend - abegin));

		long lbegin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			llist.add(String.valueOf(i));
		}
		long lend = System.currentTimeMillis();
		System.out.println("linkedlist add time:" + (lend - lbegin));

		/*
		 * 普通的get方法遍历比较
		 */
		long agbegin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			alist.get(i);
		}
		long agend = System.currentTimeMillis();
		System.out.println("arraylist time get:" + (agend - agbegin));

		long lgbegin = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			llist.get(i);
		}
		long lgend = System.currentTimeMillis();
		System.out.println("linkedlist time get:" + (lgend - lgbegin));

		/*
		 * iterator方法遍历比较
		 */
		long lgibegin = System.currentTimeMillis();
		for (Iterator iterator = alist.iterator(); iterator.hasNext();) {
			iterator.next();
		}
		long lgiend = System.currentTimeMillis();
		System.out
				.println("arraylist time get iterator:" + (lgiend - lgibegin));

		long agibegin = System.currentTimeMillis();
		for (Iterator iterator = llist.iterator(); iterator.hasNext();) {
			iterator.next();
		}
		long agiend = System.currentTimeMillis();
		System.out.println("linkedlist time get iterator:"
				+ (agiend - agibegin));
	}
}
