package bitset;
/*java BitSet类
 * 此类实现了一个按需增长的位向量。位 set 的每个组件都有一个 boolean 值。
 * 用非负的整数将 BitSet 的位编入索引。可以对每个编入索引的位进行测试、设置或者清除。
 * 通过逻辑与、逻辑或和逻辑异或操作，可以使用一个 BitSet 修改另一个 BitSet 的内容。
 */
import java.util.BitSet;

public class TestBitSet {
	public static void main(String[] args) {
		BitSet bt1 = new BitSet(4);// 创建一个长度为4的位"数组",0至长度减1
		BitSet bt2 = new BitSet(6);
		//bt1.set(1);// 第1位设为true
		bt1.set(2);
		bt2.set(0);
		bt2.set(3);
		bt2.set(4);
		System.out.println("b1.length="+bt1.length());
		// length()为BitSet的逻辑大小（实际使用大小）,值为“true”最大的索引位加1
		System.out.println("b2.length="+bt2.length());
		System.out.println("b1.size="+bt1.size());
		System.out.println("b2.size="+bt2.size());


		// 输出原来的bitSet
		for (int i = 0; i < bt1.length(); i++) {
			System.out.print(bt1.get(i) + " ");
			if (i == bt1.length() - 1) {
				System.out.println();
			}
		}
		for (int i = 0; i < bt2.length(); i++) {
			System.out.print(bt2.get(i) + " ");
			if (i == bt2.length() - 1) {
				System.out.println();
			}
		}
		System.out.println("bt1=>" + bt1);
		System.out.println("bt2=>" + bt2);
		(bt1).or(bt2);
		// bt1执行了或之后结果保存在bt1中
		System.out.println("(bt1).or(bt2)=>" + bt1);
		// 输出执行了or之后的bitSet
		for (int i = 0; i < bt1.length(); i++) {
			System.out.print(bt1.get(i) + " ");
			if (i == bt1.length() - 1) {
				System.out.println();
			}
		}

		for (int i = 0; i < bt2.length(); i++) {
			System.out.print(bt2.get(i) + " ");
			if (i == bt2.length() - 1) {
				System.out.println();
			}
		}
	}
}
