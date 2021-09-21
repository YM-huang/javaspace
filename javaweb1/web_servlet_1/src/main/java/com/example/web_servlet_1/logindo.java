package com.example.web_servlet_1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@WebServlet(name = "login.do", value = "/login.do")
public class logindo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user_name = request.getParameter("userName");
        String password = request.getParameter("password");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\output\\info.txt")));
        boolean d=false;
        String strLine;
        while (br.ready()) {
            strLine = br.readLine();
            if(strLine.equals("")) {
                break;
            }
            String pass = strLine.substring(strLine.indexOf('|')+1,strLine.indexOf('|',strLine.indexOf('|')+1));
            String sha=getSHA256StrJava(password);
            if(strLine.substring(0,strLine.indexOf('|')).equals(user_name) &&sha.equals(pass)){
                d=true;
                break;
            }
        }
        if(d){
            out.print("<html><body>");
            out.println("<h1>" + "Welcome"+user_name+"</h1>");
            out.println("</body><ml>");
        }
        else{
            out.print("<html><body>");
            out.println("<h1>" + "Something wrong in userName or password!"+"</h1>");
            out.println("</body><ml>");
        }

    }
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


}
