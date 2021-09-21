package com.example.web_servlet_1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class jsonaa {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String js = readJsonFile("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\student.json");
        System.out.println(js);
//        JSONArray jsonArray = JSON.parseArray(js);
//        for(int i=0;i<jsonArray.size();i++) {
//            JSONObject key = (JSONObject)jsonArray.get(i);
//            String stuid=key.get("stuid").toString();
//            System.out.println(stuid);
//        }
        int loc = 10;
        while(js.indexOf("stuid",loc)!=-1) {

            if (loc == -1) {
                break;
            }
            String stuid = js.substring(js.indexOf("stuid", loc) + 8, js.indexOf('"', js.indexOf("stuid", loc) + 9));
            System.out.println(stuid);
            String name = js.substring(js.indexOf("name", loc) + 7, js.indexOf('"', js.indexOf("name", loc) + 8));
            System.out.println(name);
            String courseName = js.substring(js.indexOf("courseName", loc) + 13, js.indexOf('"', js.indexOf("courseName", loc) + 14));
            System.out.println(courseName);
            String score = js.substring(js.indexOf("score", loc) + 7, js.indexOf("score", loc) + 10);
            System.out.println(score);
            loc = (js.indexOf("stuid", loc + 1));
//            out.println("<tr><td>"+stuid+"</td><td>"+name+"</td><td>"+courseName+"</td><td>"+score+"</td></tr>");
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
