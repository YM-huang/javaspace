package file_get_text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.presentation.*;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.*;

public class getppt {

	public static String getPPT(String path){
//		String result = "";
//		try {
//			//创建Presentation实例
//			Presentation ppt = new Presentation();
//			//加载PowerPoint文档
//			ppt.loadFromFile(path);
//			StringBuilder buffer = new StringBuilder();
//			//遍历文档中的幻灯片，提取文本
//			for (Object slide : ppt.getSlides()) {
//				for (Object shape : ((ISlide) slide).getShapes()) {
//					if (shape instanceof IAutoShape) {
//						for (Object tp : ((IAutoShape) shape).getTextFrame().getParagraphs())
//						{
//							buffer.append(((ParagraphEx) tp).getText());
//						}
//					}
//				}
//			}
//			result = buffer.toString();
//			return result;
//		}catch (Exception e) {
//            e.printStackTrace();
//        }
//		return result;
		String result = "";
		if(path==null){
			return "";
		}
		StringBuilder content=new StringBuilder();
		String ext = path.substring(path.lastIndexOf("."));//读取后缀,在poi里要不同处理
		SlideShow ss  = null;
		try {
			InputStream is = new FileInputStream(path);
			if(".ppt".equals(ext)){
				ss = new HSLFSlideShow(is);
			}else if(".pptx".equals(ext)){
				ss = new XMLSlideShow(is);
			}else{
				ss=null;
			}
			for(Slide slide: (List<Slide>) ss.getSlides()){
				List shapes=slide.getShapes();
		        if(shapes!=null){
		        	for(Object shape:shapes){

		        		if(shape==null) {
		        			continue;
		        		}
		        		if (shape instanceof HSLFTextShape) {// 文本框
		        			String text = ((HSLFTextShape) shape).getText();

		        			content.append(text);
		        		}
		        		if (shape instanceof XSLFTextShape) {// 文本框
		        			String text = ((XSLFTextShape) shape).getText();

		        			content.append(text);
		        		}
		        	}
		        }
			}
			is.close();
        	result = content.toString();
        	return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return result;
	}
	
	public static void main(String[] args) {
		
		String text=getPPT("09图形用户界面编程.pptx");
	    System.out.println(text);

	}

}
