package file_get_text;
import com.spire.pdf.*;
public class getpdf {
	public static String getPDF(String path) {
		PdfDocument pdf = new PdfDocument(path);
		//ʵ����StringBuilder��
        StringBuilder sb = new StringBuilder();
        //����һ��int�ͱ���
//        int index = 0;

        //����PDF�ĵ���ÿҳ
        PdfPageBase page;
        
        String result = "";
        for (int i= 0; i<pdf.getPages().getCount();i++) {
            page = pdf.getPages().get(i);
            //����extractText()������ȡ�ı�
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
		
	    String s=getPDF("09ͼ���û�������.pdf");
	    System.out.println(s);
	    
	}
}
