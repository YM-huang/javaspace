package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "exportScore.do", value = "/exportScore.do")
public class exportScoredo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Encoding", "gb2312");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + java.net.URLEncoder.encode("学生成绩.xls", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        String filename = (String) this.getServletContext().getAttribute("filename");
        PrintWriter out = response.getWriter();
        out.println("学号\t姓名\t课程\t成绩\t");
        String str = "";

            String js = readJsonFile("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\" + filename);
            System.out.println(js);
            int loc = 10;
            while(js.indexOf("stuid",loc)!=-1) {

                if (loc == -1) {
                    break;
                }
                String stuid = js.substring(js.indexOf("stuid", loc) + 8, js.indexOf('"', js.indexOf("stuid", loc) + 9));
                out.print(stuid + "\t");
                String name = js.substring(js.indexOf("name", loc) + 7, js.indexOf('"', js.indexOf("name", loc) + 8));
                out.print(name + "\t");
                String courseName = js.substring(js.indexOf("courseName", loc) + 13, js.indexOf('"', js.indexOf("courseName", loc) + 14));
                out.print(courseName + "\t");
                String score = js.substring(js.indexOf("score", loc) + 7, js.indexOf("score", loc) + 10);
                out.print(score);
                loc = (js.indexOf("stuid", loc + 1));
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
