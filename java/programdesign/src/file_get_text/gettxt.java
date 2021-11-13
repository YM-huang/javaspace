package file_get_text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class gettxt {

	public static String getTXT(String path){
		try {
			File f=new File(path);
			BufferedReader bufReader=new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
			String str = "";
			int size = (int)f.length();
			int charRead = 0;
			char[] content = new char[size];
		
			while(bufReader.ready()) {
				charRead += bufReader.read(content, charRead, size - charRead);
			}
		
			bufReader.close();
		
			str = new String(content, 0, charRead);	
			return str;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args) {
		
		String text=getTXT("หตร๗.txt");
	    System.out.println(text);

	}

}
