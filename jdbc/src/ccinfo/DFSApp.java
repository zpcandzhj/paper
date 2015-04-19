package ccinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

public class DFSApp {
	public static int vertexId;
	// 用HashMap保存单词与内存中包含该词的结点ID之间的映射关系(相当于倒排索引的功能)
	public static IKAnalyzerHashMap invertedIndex = new IKAnalyzerHashMap();

	public static void main(String[] args) throws IOException {
		Graph theGraph = new Graph();
		String tableName;
		String[] keywords = new String[10];
		try {
			long begin = System.currentTimeMillis();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/coinfo", "root", "666666");
			Statement stmt = conn.createStatement();
			tableName = "rcd";
			ResultSet res = stmt.executeQuery("select *from " + tableName);
			while (res.next()) {
				String label = res.getInt(1) + "\t" + res.getInt(2) + "\t"
						+ res.getInt(3) + "\t" + res.getInt(4) + "\t"
						+ res.getInt(5) + "\t" + res.getString(6) + "\t"
						+ res.getString(7) + "\t";
				invertedIndex.process(label, vertexId);
				theGraph.addVertex(label, vertexId++);
				// System.out.println(label);
			}
			System.out.println("==============表 " + tableName
					+ " 数据已处理完成,当前数据图结点数:" + vertexId + "===============");

			int temp = vertexId;
			tableName = "rcd_item";
			res = stmt.executeQuery("select *from " + tableName);
			while (res.next()) {
				String label = res.getInt(1) + "\t" + res.getInt(3) + "\t"
						+ res.getInt(5) + "\t" + res.getInt(6);
				invertedIndex.process(label, vertexId);
				theGraph.addVertex(label, vertexId++);
				// System.out.println(label);
			}
			for (int i = temp; i < vertexId; i++) {
				for (int j = 0; j < temp; j++) {
					theGraph.addEdge(i, j);
				}
			}
			System.out.println("==============表 " + tableName
					+ " 数据已处理完成,当前数据图结点数:" + vertexId + "===============");

			int temp2 = vertexId;
			tableName = "page";
			res = stmt.executeQuery("select *from " + tableName);
			while (res.next()) {
				String label = res.getString(7) + "\t" + res.getString(8);
				invertedIndex.process(label, vertexId);
				theGraph.addVertex(label, vertexId++);
				// System.out.println(label);
			}
			for (int i = 0; i < temp; i++) {
				for (int j = temp2; j < vertexId; j++) {
					theGraph.addEdge(i, j);
				}
			}
			System.out.println("==============表 " + tableName
					+ " 数据已处理完成,当前数据图结点数:" + vertexId + "===============");

			int temp3 = vertexId;
			tableName = "inform";
			res = stmt.executeQuery("select *from " + tableName);
			while (res.next()) {
				String label = res.getString(2) + "\t";
				invertedIndex.process(label, vertexId);
				theGraph.addVertex(label, vertexId++);
				// System.out.println(label);
			}
			for (int i = temp3; i < vertexId; i++) {
				for (int j = 0; j < temp; j++) {
					theGraph.addEdge(i, j);
				}
			}
			System.out.println("==============表 " + tableName
					+ "\t数据已处理完成,当前数据图结点数:" + vertexId + "===============");
			long end = System.currentTimeMillis();
			System.out.println("数据预处理耗时:" + (end - begin) + "ms");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			System.out.println("请输入关键词集合,以空格分开:");
			String keyWord = getString();
			if (keyWord.equals("quit")) {
				System.out.println("程序已被终止！");
				break;
			}

			StringTokenizer tokenizer = new StringTokenizer(keyWord);
			int k = 0;
			System.out
					.println("===========================OR语义查询==========================");
			long begin = System.currentTimeMillis();
			while (tokenizer.hasMoreTokens()) {
				String temp = tokenizer.nextToken();
				keywords[k++] = temp;
				ArrayList<Integer> vertexes = invertedIndex
						.getVertexsByKeyword(temp);
				if (vertexes != null)
					for (int i = 0; i < vertexes.size(); i++) {
						theGraph.displayVertex(vertexes.get(i));
					}
				else {
					System.out.println("没有相应的查询结果！");
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("OR查询耗时:" + (end - begin) + "ms");
			System.out.println("");

			System.out
					.println("==========================AND语义查询==========================");
			begin = System.currentTimeMillis();
			ArrayList<Integer> vertexess = invertedIndex
					.getVertexsByKeyword(keywords);
			if (vertexess != null)
				for (int temp : vertexess)
					theGraph.displayVertex(temp);
			else {
				System.out.println("没有相应的查询结果！");
			}
			end = System.currentTimeMillis();
			System.out.println("AND查询耗时:" + (end - begin) + "ms");
			System.out.println("");
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

}
