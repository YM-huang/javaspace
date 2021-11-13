import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFInvoiceExtractor {

	/**
     * @param path pdf�ļ���·��
     * @return  pdf�е��ı���Ϣ����������pdf�����ʽ�����⣬�����ı���˳����ܻ���ҡ�
     */
    public  String parsePDF(String path){
    	String result = "";
    	try{
        	PDDocument document = PDDocument.load(new File(path));
            PDFTextStripper stripper = new PDFTextStripper();

            stripper.setSortByPosition(true);

            for (int p = 1; p <= document.getNumberOfPages(); ++p)
            {
                // Set the page interval to extract. If you don't, then all pages would be extracted.
                stripper.setStartPage(p);
                stripper.setEndPage(p);

                // let the magic happen
                String text = stripper.getText(document);
                result += text;

            }   		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;    	
    }
    
  
	
	public static void main(String[] args) {

		PDFInvoiceExtractor pie = new PDFInvoiceExtractor();
		System.out.println(pie.parsePDF("031001800211-84462201 - ͼ��.pdf"));
		
		
		//TODO  ���￪ʼ���ǵı��ݣ�
		//ʹ��String�����������ı���Ϣ(������ı���s)��ֻ�����Ʊ���룬��Ʊ���룬��Ʊ���ڣ�YYYYMMDD��ʾ����У�������λ
		String s = pie.parsePDF("031001800211-84462201 - ͼ��.pdf");
		Pattern Pattern1 = Pattern.compile("��Ʊ����:\\d*");//��Ϊ���涼�����֣�������������ʽ�����ֶ���ȡ��������ͬ
	    Matcher matcher1 = Pattern1.matcher(s);
	    while (matcher1.find()) {
            String sub = s.substring(matcher1.start(), matcher1.end());
            System.out.println(sub);
	    }
	    
	    
	    
	    Pattern Pattern2 = Pattern.compile("��Ʊ����:\\d*");
	    Matcher matcher2 = Pattern2.matcher(s);
	    while (matcher2.find()) {
            String sub = s.substring(matcher2.start(), matcher2.end());
            System.out.println(sub);
	    }
	    
	    
	    Pattern Pattern3 = Pattern.compile("��Ʊ����:\\s*\\S*\\s*\\S*\\s*\\S*\\s*\\S*\\s*\\S*");
	    Matcher matcher3 = Pattern3.matcher(s);
	    while (matcher3.find()) {
            String sub = s.substring(matcher3.start(), matcher3.end());
            sub = sub.replaceAll("\\s*","");
            sub = sub.replaceAll("��","");
            sub = sub.replaceAll("��","");
            sub = sub.replaceAll("��","");
            System.out.println(sub);
	    }
	    
	    
	    Pattern Pattern4 = Pattern.compile("(У\\s*��\\\s*��:\\d{5})\\s*(\\d{5})\\s*(\\d{5})\\s*(\\d{5})");
	    Matcher matcher4 = Pattern4.matcher(s);
	    while (matcher4.find()) {
            String sub = s.substring(matcher4.start(), matcher4.end());
            sub = sub.replaceAll("\\s*","");//ȥ���ո񷽱���һ������
            int t = sub.length();
            System.out.println(sub.substring(0,4)+sub.substring(t-6,t));
//	    	String g1 = matcher4.group(1);
//            String g2 = matcher4.group(2);
//            String g3 = matcher4.group(3);
//            String g4 = matcher4.group(4);
//            System.out.println(g1);
//            System.out.println(g2);
//            System.out.println(g3);
//            System.out.println(g4);
	    }
		
		//		int t=0;
//		String s2 = "";
//		t = s.indexOf("��Ʊ����");
//		s2 = s2 + "��Ʊ���룺" + s2.substring(t+5,10);
//		
//		
//		t = s.indexOf("��Ʊ����");
//		t = s.indexOf("��Ʊ����");
//		t = s.indexOf("У����");

		
	}

}

