package sixth_experience;
//import com.google.gson.*;
import com.alibaba.fastjson.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.util.*;
public class jsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String js = readJsonFile("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\student.json");
        System.out.println(js);
        JSONArray jsonArray = JSON.parseArray(js);
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject key = (JSONObject)jsonArray.get(i);
            String stuid=key.get("stuid").toString();
            System.out.println(stuid);
        }
	}
	public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer strbuffer = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                strbuffer.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = strbuffer.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
