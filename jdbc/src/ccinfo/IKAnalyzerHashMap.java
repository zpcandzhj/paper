package ccinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

//把分词结果放到hashMap中
public class IKAnalyzerHashMap {
	// 用HashMap保存单词与内存中包含该词的结点ID之间的映射关系(相当于倒排索引的功能)
	public static HashMap<String, ArrayList<Integer>> myHashMap = new HashMap<String, ArrayList<Integer>>();

	public void process(String label, int vertexId) throws IOException {
		// TODO Auto-generated method stub
		String textToBeProcess = label;
		// 创建IKAnalyzer中文分词对象
		IKAnalyzer analyzer = new IKAnalyzer(true);
		// analyzer.setUseSmart(true);// 使用智能分词
		// 打印分词结果
		printAnalysisResult(analyzer, textToBeProcess, vertexId);
		// new IKAnalyzerHashMap().getResultByKeyword(keyWord);
	}

	public void getResult() {
		Iterator<String> iterator = myHashMap.keySet().iterator();
		while (iterator.hasNext()) {
			String word = iterator.next();
			System.out.print(word + "\t出现在这些结点中:");
			ArrayList<Integer> arraylist = myHashMap.get(word);

			for (int i = 0; i < arraylist.size(); i++) {
				System.out.print(arraylist.get(i) + " ");
			}
			System.out.println();
		}
	}

	// 返回包含关键词keyword的结点ID,为OR语义查询做准备
	public ArrayList<Integer> getVertexsByKeyword(String keyword) {
		ArrayList<Integer> arraylist = myHashMap.get(keyword);
		return arraylist;
	}

	// 返回包含关键词keywords的结点ID,为AND语义查询做准备
	public ArrayList<Integer> getVertexsByKeyword(String[] keywords) {
		ArrayList<Integer> res = myHashMap.get(keywords[0]);
		if (res != null) {
			ArrayList<Integer> temp = (ArrayList<Integer>) res.clone();
			if (temp != null)
				for (int i = 1; i < keywords.length; i++) {
					ArrayList<Integer> arraylist = myHashMap.get(keywords[i]);
					if (arraylist != null)
						temp.retainAll(arraylist);

				}
			return temp;
		}
		return null;
	}

	private static void printAnalysisResult(IKAnalyzer analyzer,
			String textToBeProcess, int vertexId) throws IOException {
		// TODO Auto-generated method stub
		TokenStream tokenStream = analyzer.tokenStream("content",
				new StringReader(textToBeProcess));
		tokenStream.addAttribute(CharTermAttribute.class);
		while (tokenStream.incrementToken()) {
			CharTermAttribute charTermAttribute = tokenStream
					.getAttribute(CharTermAttribute.class);
			String item = charTermAttribute.toString();
			if (!myHashMap.containsKey(item)) {
				ArrayList<Integer> arraylist = new ArrayList<Integer>();
				arraylist.add(vertexId);
				myHashMap.put(item, arraylist);
			} else {
				ArrayList<Integer> arraylist = myHashMap.get(item);
				arraylist.add(vertexId);
				myHashMap.put(item, arraylist);
			}
		}
	}
}
