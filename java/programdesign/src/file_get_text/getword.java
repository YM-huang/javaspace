package file_get_text;

import com.spire.doc.*;

public class getword {
	public static String getWORD(String path){
	    Document doc = new Document();
	    doc.loadFromFile(path);
	    //��ȡ�ı�����ΪString
	    String result = doc.getText().substring(71);
	    return result;
	  }
	
	public static void main(String[] args) {
	   
		String text=getWORD("����.docx");
		System.out.println(text);
	}
}
