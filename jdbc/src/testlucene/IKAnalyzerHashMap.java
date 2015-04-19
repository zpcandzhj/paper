package testlucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

//把分词结果放到hashMap中
public class IKAnalyzerHashMap {

	public static HashMap<String, ArrayList<String>> myHashMap = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String textToBeProcess = "南京工业职业技术学院中国药科大学南京理工大学南京工业大学南京中医药大学南京财经大学南京农业大学一转眼是是IKAnalyzer的分词效果到底怎么样呢，12我们来看一下吧吧,南京财经大学信息工程学院，周鸿祎周鹏程奇虎，添加了userdic！";
		// 创建IKAnalyzer中文分词对象
		IKAnalyzer analyzer = new IKAnalyzer(true);
		// analyzer.setUseSmart(true);// 使用智能分词
		// 打印分词结果
		printAnalysisResult(analyzer, textToBeProcess);
		new IKAnalyzerHashMap().getResult();
		System.out.println("请输入一个关键词:");
		String keyWord = getString();
		new IKAnalyzerHashMap().getResultByKeyword("南京");
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public void getResultByKeyword(String keyword) {
		System.out.print(keyword + "\t出现在这些结点中:");
		ArrayList<String> arraylist = myHashMap.get(keyword);

		for (int i = 0; i < arraylist.size(); i++) {
			System.out.print(arraylist.get(i) + " ");
		}
		System.out.println();
	}

	public void getResult() {
		Iterator<String> iterator = myHashMap.keySet().iterator();
		while (iterator.hasNext()) {
			String word = iterator.next();
			System.out.print(word + "\t出现在这些结点中:");
			ArrayList<String> arraylist = myHashMap.get(word);

			for (int i = 0; i < arraylist.size(); i++) {
				System.out.print(arraylist.get(i) + " ");
			}
			System.out.println();
		}
	}

	private static void printAnalysisResult(IKAnalyzer analyzer,
			String textToBeProcess) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("[" + textToBeProcess + "]分词结果：");
		TokenStream tokenStream = analyzer.tokenStream("content",
				new StringReader(textToBeProcess));
		tokenStream.addAttribute(CharTermAttribute.class);
		while (tokenStream.incrementToken()) {
			CharTermAttribute charTermAttribute = tokenStream
					.getAttribute(CharTermAttribute.class);
			String item = charTermAttribute.toString();
			if (!myHashMap.containsKey(item)) {
				ArrayList<String> arraylist = new ArrayList<String>();
				arraylist.add("结点1");
				myHashMap.put(item, arraylist);
			} else {
				ArrayList<String> arraylist = myHashMap.get(item);
				arraylist.add("结点x");
				myHashMap.put(item, arraylist);
			}
		}
	}
}
