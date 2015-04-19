package jdbcExc;

import java.util.ArrayList;
import java.util.List;

public class ArraylistTest2 {
	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		List<String> strList2 = new ArrayList<String>();
		List<String> strList3 = new ArrayList<String>();
		for(int i = 0; i < 10; i ++) {
			strList.add("aaa>>" + i);
			strList2.add("aaa>>" + (15 - i));
			strList3.add("aaa>>" + (10 - i));
		}
		System.out.println("strList:");
		for(int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
		System.out.println("strList2:");
		for(int i = 0; i < strList2.size(); i++) {
			System.out.println(strList2.get(i));
		}
		System.out.println("strList3:");
		for(int i = 0; i < strList3.size(); i++) {
			System.out.println(strList3.get(i));
		}
		
		
		//求出交集
		strList.retainAll(strList2);
		strList.retainAll(strList3);
		System.out.println("交集大小：" + strList.size());
		
		for(int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}	
	}
}
