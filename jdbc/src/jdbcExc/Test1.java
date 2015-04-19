package jdbcExc;

public class Test1 {
	public static void main(String[] args) {
		System.out.println("a".hashCode());
		System.out.println("b".hashCode());
		System.out.println("zpcandzhj".contains("hj"));
		boolean f1=false;
		boolean f2=true;
		boolean f3=true;
		System.out.println(f1&&f2);
		System.out.println(f2&&f3);
		System.out.println(f1&&f3);
		Boolean[] flag =new Boolean[10];
		boolean bb = true;
		flag[0]=true;
		flag[1]=true;
		flag[2]=true;
		for(int i=0;i<flag.length;i++){
			System.out.println(flag[i]);
			if(flag[i]!=null)
				bb=bb&&flag[i];
		}
		System.out.println("bb:"+bb);
		String[]  strings=new String[10];
		strings[1]="e";
		strings[2]="ee";
		for(int i=0;i<strings.length;i++){
			System.out.println(strings[i]);
		}
		
		int[] res={1,2,3,5};
		System.out.println("res:"+res.toString());
	}
}
