package testNLPIR;

import java.io.IOException;

import kevin.zhang.NLPIR;

public class TestSplit {
	public static void main(String[] args) throws IOException {
		testSplit();
	}

	private static void testSplit() throws IOException {
		// 创建接口实例
		NLPIR nlpir = new NLPIR();
		// NLPIR_Init方法第二个参数设置0表示编码为GBK, 1表示UTF8编码(此处结论不够权威)
		if (!NLPIR.NLPIR_Init("./file/".getBytes("utf-8"), 1)) {
			System.out.println("NLPIR初始化失败...");
			return;
		}
		
		String temp = "NLPIR的分词效果到底怎么样呢，12我们来看一下吧,南京财经大学是信息工程学院周鸿祎周鹏程奇虎！";
		// 要统一编码, 否则分词结果会产生乱码
		nlpir.NLPIR_AddUserWord("周鹏程 nl".getBytes("utf-8"));
		byte [] resBytes = nlpir.NLPIR_ParagraphProcess(temp.getBytes("UTF-8"), 0);
		System.out.println("分词结果: " + new String(resBytes, "UTF-8"));
		
		//指定要处理的文件
		String utf8File = "./test/test-utf8.TXT";
		String utf8FileResult = "./test/test-utf8_result.TXT";
		nlpir.NLPIR_FileProcess(utf8File.getBytes("utf-8"), utf8FileResult.getBytes("utf-8"), 0);
		
		// 退出, 释放资源
		NLPIR.NLPIR_Exit();
	}
}
