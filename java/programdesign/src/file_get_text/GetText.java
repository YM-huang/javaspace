package file_get_text;


public class GetText {
	public static String read(String path){
	    
		if(path.endsWith(".txt")||path.endsWith(".json")||path.endsWith(".md")) {
	      return gettxt.getTXT(path);
	    }
	    else if(path.endsWith(".doc")||path.endsWith(".docx")){
	      return getword.getWORD(path);
	    }
	    else if(path.endsWith(".pdf")){
	      return getpdf.getPDF(path);
	    }
	    else if(path.endsWith(".pptx")||path.endsWith(".ppt")){
	      return getppt.getPPT(path);
	    }
	    else if(path.endsWith(".xlsx")||path.endsWith(".xls")){
	      return getexcel.getEXCEL(path);
	    }
	    else {
	      return "";
	    }
		
	}
	public static void main(String[] args) {
		
		String text=read("example_50.json");
	    System.out.println(text);
	}
	
}
