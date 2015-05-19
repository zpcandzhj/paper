package bitset;

import java.util.BitSet;

/* 标明一个字符串中用了哪些字符 
 * 利用BitSet类
 */
public class WhichChars {
	private BitSet used =new BitSet();
	public WhichChars(String s){
		int len=s.length();
		//一个字符串中的重复字符被映射到相同的位上，注意字符数字互通
		for(int i=0;i<len;i++){
			used.set(s.charAt(i));
		}
	}
	
	public String toString(){
		String res="[";
		for(int j=0;j<used.length();j++){
			if(used.get(j))
				res+=(char)j;
		}
		return res+"]";
	}
	public static void main(String[] args) {
		 WhichChars w=new WhichChars("How do you do");
	        System.out.println(w);
	}
}
