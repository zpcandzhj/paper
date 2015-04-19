package testlucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String textToBeProcess = "中国药科大学南京理工大学南京工业大学南京中医药大学南京财经大学南京农业大学一转眼是是IKAnalyzer的分词效果到底怎么样呢，12我们来看一下吧吧,南京财经大学信息工程学院，周鸿祎周鹏程奇虎，添加了用户词典！";
		// 创建IKAnalyzer中文分词对象
		IKAnalyzer analyzer = new IKAnalyzer(true);
		//analyzer.setUseSmart(true);// 使用智能分词
		// 打印分词结果
		printAnalysisResult(analyzer, textToBeProcess);

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
			System.out.println(charTermAttribute.toString()+"\t表一");
		}
	}
}
