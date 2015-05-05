package ccinfox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
	public static Graph theGraph;

	public static void main(String[] args) throws IOException {
		theGraph = new Graph();
		final int INFINITY = 100000;
		String keywords[];
		ArrayList<Integer> keywordsIds;
		//buildGraphOneBackward();
		buildGraphTwoBackward();
		
		while (true) {
			keywords = new String[10];
			keywordsIds = new ArrayList<Integer>();
			System.out.println("请输入关键词集合,以空格分开:");
			String keyWord = getString();
			if (keyWord.equals("quit")) {
				System.out.println("程序已被终止！");
				break;
			}

			StringTokenizer tokenizer = new StringTokenizer(keyWord);
			int keyNum = 0;
			System.out
					.println("========================搜索最小连接树的根节点=========================");
			long begin = System.currentTimeMillis();
			while (tokenizer.hasMoreTokens()) {
				String temp = tokenizer.nextToken();
				keywords[keyNum++] = temp;
				ArrayList<Integer> vertexes = theGraph.invertedIndex.get(temp);
				if (vertexes != null) {
					System.out.print("包含关键词" + temp + "的结点有: ");
					for (int i = 0; i < vertexes.size(); i++) {
						int kid = vertexes.get(i);
						System.out.print((kid + 1) + " ");
						keywordsIds.add(kid);
						theGraph.path(vertexes.get(i), temp);
					}
				} else {
					System.out.println("没有相应的查询结果！");
				}
			}
			System.out.println("(输入了" + keyNum + "个关键词)");
			long end = System.currentTimeMillis();
			System.out.println("生成最小连接树耗时:" + (end - begin) + "ms");
			theGraph.printRoots(keyNum);
			theGraph.clearVisitations();
			System.out
					.println("===========================输出最小连接树==========================");
			//buildGraphOneForward();
			buildGraphTwoForward();
			theGraph.printJoinTrees(keywordsIds);// 调用此方法前必须已经先调用了printRoots()方法
			System.out.println("");
			//resetGraphOne(INFINITY);
			resetGraphTwo(INFINITY);
		}
	}

	// ----------------------构建数据图1-------------------------------
	private static void buildGraphOneBackward() {
		int num = 0;
		theGraph.addVertex("a", num++); // 1 (start)
		theGraph.addVertex("f", num++); // 2
		theGraph.addVertex("c,d", num++); // 3
		theGraph.addVertex("b", num++); // 4
		theGraph.addVertex("b", num++); // 5
		theGraph.addVertex("e", num++); // 6
		theGraph.addVertex("g", num++); // 7
		theGraph.addVertex("f", num++); // 8
		theGraph.addVertex("g", num++); // 9
		theGraph.addVertex("c", num++); // 10
		theGraph.addVertex("a", num++); // 11
		theGraph.addVertex("c", num++); // 12
		System.out.println("结点数" + num);

		theGraph.addEdge(1, 0, 1); // 2-1
		theGraph.addEdge(2, 0, 1); // 3-1
		theGraph.addEdge(3, 0, 1); // 4-1
		theGraph.addEdge(4, 1, 1); // 5-2
		theGraph.addEdge(5, 1, 1); // 6-2
		theGraph.addEdge(5, 2, 1); // 6-3
		theGraph.addEdge(6, 2, 1); // 7-3
		theGraph.addEdge(7, 2, 1); // 8-3
		theGraph.addEdge(8, 4, 1); // 9-5
		theGraph.addEdge(9, 4, 1); // 10-5
		theGraph.addEdge(10, 5, 1); // 11-6
		theGraph.addEdge(11, 5, 1); // 12-6

	}

	private static void buildGraphOneForward() {
		theGraph.addEdge(0, 1, 1); // 1-2
		theGraph.addEdge(0, 2, 1); // 1-3
		theGraph.addEdge(0, 3, 1); // 1-4
		theGraph.addEdge(1, 4, 1); // 2-5
		theGraph.addEdge(1, 5, 1); // 2-6
		theGraph.addEdge(2, 5, 1); // 3-6
		theGraph.addEdge(2, 6, 1); // 3-7
		theGraph.addEdge(2, 7, 1); // 3-8
		theGraph.addEdge(4, 8, 1); // 5-9
		theGraph.addEdge(4, 9, 1); // 5-10
		theGraph.addEdge(5, 10, 1); // 6-11
		theGraph.addEdge(5, 11, 1); // 6-12
	}

	// ----------------------构建数据图2-------------------------------
	private static void buildGraphTwoBackward() {
		int num = 0;
		theGraph.addVertex("a", num++); // 1 (start)
		theGraph.addVertex("b", num++); // 2
		theGraph.addVertex("c", num++); // 3
		theGraph.addVertex("d", num++); // 4
		theGraph.addVertex("e", num++); // 5
		theGraph.addVertex("d", num++); // 6
		theGraph.addVertex("g", num++); // 7
		theGraph.addVertex("f", num++); // 8
		theGraph.addVertex("b,g", num++); // 9
		theGraph.addVertex("f", num++); // 10
		theGraph.addVertex("g", num++); // 11
		theGraph.addVertex("c", num++); // 12
		System.out.println("结点数" + num);

		theGraph.addEdge(1, 0, 1); // 2-1
		theGraph.addEdge(2, 0, 1); // 3-1
		theGraph.addEdge(7, 1, 1); // 8-2
		theGraph.addEdge(3, 1, 1); // 4-2
		theGraph.addEdge(4, 1, 1); // 5-2
		theGraph.addEdge(4, 2, 1); // 5-3
		theGraph.addEdge(5, 2, 1); // 6-3
		theGraph.addEdge(6, 2, 1); // 7-3
		theGraph.addEdge(7, 3, 1); // 8-4
		theGraph.addEdge(8, 3, 1); // 9-4
		theGraph.addEdge(9, 4, 1); // 10-5
		theGraph.addEdge(10, 4, 1); // 11-5
		theGraph.addEdge(11, 4, 1); // 12-5
	}

	private static void buildGraphTwoForward() {
		theGraph.addEdge(0, 1, 1); // 1-2
		theGraph.addEdge(0, 2, 1); // 1-3
		theGraph.addEdge(1, 7, 1); // 2-8
		theGraph.addEdge(1, 3, 1); // 2-4
		theGraph.addEdge(1, 4, 1); // 2-5
		theGraph.addEdge(2, 4, 1); // 3-5
		theGraph.addEdge(2, 5, 1); // 3-6
		theGraph.addEdge(2, 6, 1); // 3-7
		theGraph.addEdge(3, 7, 1); // 4-8
		theGraph.addEdge(3, 8, 1); // 4-9
		theGraph.addEdge(4, 9, 1); // 5-10
		theGraph.addEdge(4, 10, 1); // 5-11
		theGraph.addEdge(4, 11, 1); // 5-12
	}

	private static void resetGraphOne(final int INFINITY) {
		theGraph.addEdge(0, 1, INFINITY); // 1-2
		theGraph.addEdge(0, 2, INFINITY); // 1-3
		theGraph.addEdge(0, 3, INFINITY); // 1-4
		theGraph.addEdge(1, 4, INFINITY); // 2-5
		theGraph.addEdge(1, 5, INFINITY); // 2-6
		theGraph.addEdge(2, 5, INFINITY); // 3-6
		theGraph.addEdge(2, 6, INFINITY); // 3-7
		theGraph.addEdge(2, 7, INFINITY); // 3-8
		theGraph.addEdge(4, 8, INFINITY); // 5-9
		theGraph.addEdge(4, 9, INFINITY); // 5-10
		theGraph.addEdge(5, 10, INFINITY); // 6-11
		theGraph.addEdge(5, 11, INFINITY); // 6-12
	}

	private static void resetGraphTwo(final int INFINITY) {
		theGraph.addEdge(0, 1, INFINITY); // 1-2
		theGraph.addEdge(0, 2, INFINITY); // 1-3
		theGraph.addEdge(1, 7, INFINITY); // 2-8
		theGraph.addEdge(1, 3, INFINITY); // 2-4
		theGraph.addEdge(1, 4, INFINITY); // 2-5
		theGraph.addEdge(2, 4, INFINITY); // 3-5
		theGraph.addEdge(2, 5, INFINITY); // 3-6
		theGraph.addEdge(2, 6, INFINITY); // 3-7
		theGraph.addEdge(3, 7, INFINITY); // 4-8
		theGraph.addEdge(3, 8, INFINITY); // 4-9
		theGraph.addEdge(4, 9, INFINITY); // 5-10
		theGraph.addEdge(4, 10, INFINITY); // 5-11
		theGraph.addEdge(4, 11, INFINITY); // 5-12
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
