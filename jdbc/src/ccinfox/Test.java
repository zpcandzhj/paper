package ccinfox;

import java.util.ArrayList;
import java.util.HashSet;

public class Test {
	public static void main(String[] args) {
		String label="a,b,c,d,d";
		String[] labels=label.split(",");
		for(int i = 0; i < labels.length; i++){
			System.out.println(labels[i]);
		}
		
		ArrayList list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println(list.size());
		
		HashSet<String> set=new HashSet<String>();
		set.add("111");
		set.add("222");
		set.add("3333");
		set.add("222");
		set.add("3333");
		System.out.println("set.size():"+set.size());
		for(String s:set){
			System.out.println(s);
		}
	}
}
