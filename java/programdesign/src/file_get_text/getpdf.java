package file_get_text;
import com.spire.pdf.*;
public class getpdf {
	public static String getPDF(String path) {
		PdfDocument pdf = new PdfDocument(path);
		//实例化StringBuilder类
        StringBuilder sb = new StringBuilder();
        //定义一个int型变量
//        int index = 0;

        //遍历PDF文档中每页
        PdfPageBase page;
        
        String result = "";
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            //调用extractText()方法提取文本
            sb.append(page.extractText(true));

            try {
                result += sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pdf.close();
        return result;
	}
	
	public static void main(String[] args) {
		
	    String s=getPDF("09图形用户界面编程.pdf");
	    System.out.println(s);
	    
	}
}
