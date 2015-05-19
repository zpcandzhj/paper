package testlucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerDB {
	// 创建IKAnalyzer中文分词对象
	// IKAnalyzer analyzer = new IKAnalyzer(true);

	public static void main(String[] args) throws Exception {

		//String[] tables = {"rcd","rcd_item","page","inform"};
		//String[] tables = { "test" };
		String[] tables = {"rcd"};
		new IKAnalyzerDB().ProcessData(tables);

	}

	public void ProcessData(String tables[]) throws Exception {
		for (int i = 0; i < tables.length; i++) {
			ProcessTable(tables[i]);
		}
	}

	private void ProcessTable(String tableName) throws Exception {

		Connection conn = getDBConnection();
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("select *from " + tableName);
		ResultSetMetaData metaData = res.getMetaData();
		int columnCount = metaData.getColumnCount();
		int rowCount = 0;
		System.out.println("===========================开始处理表 \"" + tableName
				+ "\"(" + columnCount + "列)==============================");
		// res.next();
		while (res.next()) {
			// 对每条记录分词都得创建一个分词对象
			IKAnalyzer analyzer = new IKAnalyzer(true);
			rowCount++;
			String textToBeSplit = "";
			for (int i = 1; i <= columnCount; i++) {
				textToBeSplit += res.getString(i) + "\t";
			}
			printAnalysisResult(analyzer, textToBeSplit, tableName, rowCount);
		}
		stmt.close();
		System.out.println("----------------------------表 \"" + tableName
				+ "\"处理完成！------------------------------");
	}

	private Connection getDBConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coinfo", "root", "666666");
		return conn;
	}

	private static void printAnalysisResult(IKAnalyzer analyzer,
			String textToBeProcess, String tableName, int rowId)
			throws IOException {
		// System.out.println("[" + textToBeProcess + "]分词结果：");
		TokenStream tokenStream = analyzer.tokenStream("content",
				new StringReader(textToBeProcess));
		tokenStream.addAttribute(CharTermAttribute.class);
		while (tokenStream.incrementToken()) {
			CharTermAttribute charTermAttribute = tokenStream
					.getAttribute(CharTermAttribute.class);
//			System.out.println(charTermAttribute.toString() + "\t" + tableName
//					+ ":" + rowId);
			
			// 将数据写入文件
			FileOutputStream fout=new FileOutputStream("f:\\datatest.txt", true);
			fout.write((charTermAttribute.toString() + "\t" + tableName
					+ ":" + rowId+"\r\n").getBytes());
			
//			FileWriter fw = null;
//			try {
//				// 如果文件存在，则追加内容；如果文件不存在，则创建文件
//				File f = new File("f:\\data1.txt");
//				fw = new FileWriter(f, true);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			PrintWriter pw = new PrintWriter(fw);
//			pw.println(charTermAttribute.toString() + "\t" + tableName
//					+ ":" + rowId);
//			pw.flush();
//			try {
//				fw.flush();
//				pw.close();
//				fw.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}
}
