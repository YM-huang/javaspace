import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFInvoiceExtractor {

	/**
     * @param path pdf文件的路径
     * @return  pdf中的文本信息，但是由于pdf本身格式的问题，导致文本的顺序可能会错乱。
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
		System.out.println(pie.parsePDF("031001800211-84462201 - 图书.pdf"));
		
		
		//TODO  这里开始你们的表演：
		//使用String解析上述的文本信息(即下面的变量s)，只输出发票代码，发票号码，开票日期（YYYYMMDD公示），校验码后六位
		String s = pie.parsePDF("031001800211-84462201 - 图书.pdf");
		Pattern Pattern1 = Pattern.compile("发票代码:\\d*");//因为后面都是数字，所以用正则表达式把数字都提取出来。下同
	    Matcher matcher1 = Pattern1.matcher(s);
	    while (matcher1.find()) {
            String sub = s.substring(matcher1.start(), matcher1.end());
            System.out.println(sub);
	    }
	    
	    
	    
	    Pattern Pattern2 = Pattern.compile("发票号码:\\d*");
	    Matcher matcher2 = Pattern2.matcher(s);
	    while (matcher2.find()) {
            String sub = s.substring(matcher2.start(), matcher2.end());
            System.out.println(sub);
	    }
	    
	    
	    Pattern Pattern3 = Pattern.compile("开票日期:\\s*\\S*\\s*\\S*\\s*\\S*\\s*\\S*\\s*\\S*");
	    Matcher matcher3 = Pattern3.matcher(s);
	    while (matcher3.find()) {
            String sub = s.substring(matcher3.start(), matcher3.end());
            sub = sub.replaceAll("\\s*","");
            sub = sub.replaceAll("年","");
            sub = sub.replaceAll("月","");
            sub = sub.replaceAll("日","");
            System.out.println(sub);
	    }
	    
	    
	    Pattern Pattern4 = Pattern.compile("(校\\s*验\\\s*码:\\d{5})\\s*(\\d{5})\\s*(\\d{5})\\s*(\\d{5})");
	    Matcher matcher4 = Pattern4.matcher(s);
	    while (matcher4.find()) {
            String sub = s.substring(matcher4.start(), matcher4.end());
            sub = sub.replaceAll("\\s*","");//去掉空格方便下一步操作
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
//		t = s.indexOf("发票代码");
//		s2 = s2 + "发票代码：" + s2.substring(t+5,10);
//		
//		
//		t = s.indexOf("发票号码");
//		t = s.indexOf("发票日期");
//		t = s.indexOf("校验码");

		
	}

}

