package com.example.web_servlet_2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "fileUpload.do", value = "/fileUpload.do")
public class fileUploaddo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求Body
        byte[] body = readBody(request);
        //取得所有Body内容的字符串表示
        String textBody = new String(body, "ISO-8859-1");
        //取得上传的文件的文件名(取得路径并分离)
        String filename = getFilename(textBody);
        //取得文件内容在Body中的首尾索引
        Position p = getFilePosition(request, textBody);
        //将内容输出到文件
        writeTo(filename, body, p);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<title>所有学生的成绩</title>");
        out.println("</head><body>");
        out.println("<table width=400 border=1>");
        out.println("<h4>所有学生的成绩</h4>");
        out.println("<tr><td>学号</td><td>姓名</td><td>课程</td><td>成绩</td>");
        String str="";

        //文件读取位置
        String js = readJsonFile("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\" + filename);
        System.out.println(js);
        JSONArray jsonArray = JSON.parseArray(js);
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject key = (JSONObject)jsonArray.get(i);
            String stuid=key.get("stuid").toString();
            System.out.println(stuid);
            out.println("<tr align=center>");

            out.println("<td width=20%>"+stuid+"</td>");
            String name=key.get("name").toString();
            out.println("<tr align=center>");
            out.println("<td width=20%>"+name+"</td>");
            String courseName=key.get("courseName").toString();
            out.println("<tr align=center>");
            out.println("<td width=20%>"+courseName+"</td>");
            String score=key.get("score").toString();
            out.println("<tr align=center>");
            out.println("<td width=20%>"+score+"</td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<a href=\"exportScore.do\">导出所有学生成绩</a>");
        out.println("</body></html>");
    }


    //存放索引的类
    class Position{
        int begin;
        int end;
        Position(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    //读取请求body
    private byte[] readBody(HttpServletRequest request) throws IOException {
        int formDataLength = request.getContentLength();

        //获得ServletInputStream对象
        //getReader()和getInputStream()只能则一调用，否则会抛出IllegalStateException异常
        DataInputStream dataStream = new DataInputStream(request.getInputStream());
        byte[] body = new byte[formDataLength];
        int totalBytes = 0;
        while(totalBytes < formDataLength) {
            int bytes = dataStream.read(body, totalBytes, formDataLength);
            totalBytes += bytes;
        }
        return body;
    }

    //取得上传文件名称
    private String getFilename(String reqBody) {
        //获取filename的value,10是filename="的长度
        //通过后台调试我发现filename=后加的是带着双引号的路径名，在获取路径名时不需要分号所以在分离时就将分号也分离掉了
        String filename = reqBody.substring(reqBody.indexOf("filename=\"") + 10);

        //找到文件名这行的末尾，过滤掉对于获取文件名而言的无用信息
        filename = filename.substring(0, filename.indexOf("\n"));

        //获取不包含路径名的文件名
        filename =  filename.substring(filename.lastIndexOf("\\") + 1, filename.lastIndexOf("\""));

        //此时后台打印分离路径后的文件名并将其作为返回值返回
        System.out.println(filename);
        return filename;
    }

    //取得文件开始和结束位置
    private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {
        /*
         * 取得文件区段边界信息
         */
        String contentType = request.getContentType();
        String boundaryText = contentType.substring(
                contentType.lastIndexOf("=") + 1, contentType.length());

        /*
         * 取得实际上传文件的起始与结束位置
         */
        int pos = textBody.indexOf("filename=\"");
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;

        int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
        int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
        int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;

        return new Position(begin, end);
    }

    //输出至文件
    private void writeTo(String filename, byte[] body, Position p) throws IOException {
        //默认上传的文件是在F:\\javaeeAroundFiles目录下
        FileOutputStream fos = new FileOutputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\" + filename);
        fos.write(body, p.begin, (p.end - p.begin));
        fos.flush();
        fos.close();
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

