package file_get_text;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
public class getexcel {
	public static String getEXCEL(String path){
		String result = "";
		if(path==null){
			return "";
		}
		String ext = path.substring(path.lastIndexOf("."));//读取后缀,在poi里要不同处理
		Workbook wb;
		try {
			InputStream is = new FileInputStream(path);
			if(".xls".equals(ext)){
				wb = new HSSFWorkbook(is);
			}else if(".xlsx".equals(ext)){
				wb = new XSSFWorkbook(is);
			}else{
				wb=null;
			}
			for(Sheet sheet:wb){
		        for(Row row:sheet){
		        	for(Cell cell:row){
		        		if(cell!=null){
		        			DataFormatter formatter=new DataFormatter();
		        			result=result+formatter.formatCellValue(cell)+"\t";
		        		}
		        		else{
		        			result=result+"\t\t";
		        		}
		        	}
		        	result=result+"\n";
		        }
			}
		    is.close();
		    return result;
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return result;
	}
	public static void main(String[] args) {
		
		String text=getEXCEL("1.xlsx");
	    System.out.println(text);
	}
}
