package file_get_text;

import com.spire.doc.*;

public class getword {
	public static String getWORD(String path){
	    Document doc = new Document();
	    doc.loadFromFile(path);
	    //获取文本保存为String
	    String result = doc.getText().substring(71);
	    return result;
	  }
	
	public static void main(String[] args) {
	   
		String text=getWORD("自述.docx");
		System.out.println(text);
	}
}
