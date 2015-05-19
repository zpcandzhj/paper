package testcode;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		// 设置了种子(48位)，则每次生成相同的伪随机序列
		Random r = new Random(49);
		int j = r.nextInt(100);// 范围在0-100，随机种子不变则每次重新运行程序生成的随机序列不变
		int k = r.nextInt(100);
		int h = r.nextInt(1000);
		int i = r.nextInt();
		boolean v = r.nextBoolean();
		print(j);
		print(k);
		print(h);
		print(i);
		print(v);
		//两种产生50个100以内的随机数的方法
		for (int y = 0; y < 50; y++) {
			int w = r.nextInt(100);
			System.out.println("w[" + y + "]=" + w);
		}
		System.out.println("################################");
		
		//第二种方法是“真”的随机,每次运行程序结果不同，第一种每次程序运行结果一样(因为设置了随机种子)
		for (int x = 0; x < 50; x++) {
			long m = (long) (Math.random() * 100);
			System.out.println("m[" + x + "]=" + m);
		}
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}
}
