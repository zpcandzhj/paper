package testcode;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		File f = new File("F:/zoc.txt");
		if (f.exists()) {
			System.out.println("f.exists!");
		}else{
			System.out.println("f.does not exist!");
		}
	}
}
